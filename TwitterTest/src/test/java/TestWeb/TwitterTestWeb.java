package TestWeb;

import Web.PageObjects.GlobalElements;
import Web.PageObjects.HomeTimeLinePage;

import Web.PageObjects.TweetPostInTimeLine;
import Web.TwitterWeb;
import org.testng.Assert;
import org.testng.annotations.*;
import twitter4j.TwitterException;

import java.util.concurrent.TimeUnit;

public class TwitterTestWeb {
    private TwitterWeb twitterWeb;
    private HomeTimeLinePage homeTimeLinePage;
    private TweetPostInTimeLine tweetPostInTimeLine;
    private GlobalElements globalElements;

    private static String siteURL = "https://twitter.com/";
    private static String loginValue = "xxxxxxxxxx";
    private static String passwordValue = "xxxxxxxxxxxxxxxxx";
    private static String createdTweetText = "Created tweet";
    private static String createdTweetWithParametersText = "Created tweet with parameters";
    private static String createdAt = "now";
    private static String retweetCount = "";
    private static String tweetTextForDestroying = "This tweet will be destroyed";
    private static String tweetWithDuplicate = "Duplicated tweet";
    private static String alertDuplicateMessage = "Вы уже отправили этот твит.";
    private static String tweetIsDeletedAlertMessage = "Ваш твит удален.";

    @BeforeMethod
    public void navigateToSiteAndLogin() throws InterruptedException {
        twitterWeb = new TwitterWeb();
        twitterWeb.setUpDriver();
        twitterWeb.navigateToSite(siteURL);
        twitterWeb.logIn(loginValue, passwordValue);
    }

    @Test(description = "Create tweet and check parameters: created at; retweet count; text")
    public void checkTweetParameters() throws InterruptedException {
        tweetPostInTimeLine = new TweetPostInTimeLine(twitterWeb.getDriver(), 1);
        twitterWeb.createTweet(createdTweetWithParametersText);
        String actualText = tweetPostInTimeLine.getStatus().getText();
        String actualCreatedAt = tweetPostInTimeLine.getCreatedAt().getText();
        String actualRetweetCount = tweetPostInTimeLine.getRetweetCount().getText();

        Assert.assertEquals(actualText, createdTweetWithParametersText);
        Assert.assertEquals(actualCreatedAt, createdAt);
        Assert.assertEquals(actualRetweetCount, retweetCount);
    }

    @Test(description = "Create tweet and check it on HomeTimeLine")
    public void checkCreatedTweet() throws InterruptedException {
        homeTimeLinePage = new HomeTimeLinePage(twitterWeb.getDriver());
        twitterWeb.createTweet(createdTweetText);
        TimeUnit.SECONDS.sleep(2);
        String actualResult = homeTimeLinePage.getStatusByPosition(1).getText();

        Assert.assertEquals(actualResult, createdTweetText);
    }

    @Test(description = "Destroy tweet and check that tweet is absent on UserTimeLine")
    public void checkThatTweetIsDestroyed() throws InterruptedException {
        twitterWeb.createTweet(tweetTextForDestroying);
        twitterWeb.deleteTweet(1);
        homeTimeLinePage = new HomeTimeLinePage(twitterWeb.getDriver());
        globalElements = new GlobalElements(twitterWeb.getDriver());
        String actualTweetMessage = homeTimeLinePage.getStatusByPosition(1).getText();
        String actualAlertMessage = globalElements.getAlertMessage().getText();

        Assert.assertEquals(actualAlertMessage, tweetIsDeletedAlertMessage);
        Assert.assertNotEquals(actualTweetMessage, tweetTextForDestroying);
    }

    @Test(description = "Check that user can not tweet equal statuses")
    public void checkDuplicated() throws InterruptedException {
        globalElements = new GlobalElements(twitterWeb.getDriver());
        twitterWeb.createTweet(tweetWithDuplicate);
        twitterWeb.createTweet(tweetWithDuplicate);
        String actualAlertMessage = globalElements.getAlertMessage().getText();
        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(actualAlertMessage, alertDuplicateMessage);
    }

    @AfterMethod(alwaysRun = true)
    public void setDown() throws TwitterException {
        twitterWeb.setDownDriver();
    }

    @AfterSuite
    public void destroyAllStatuses() throws TwitterException {
        twitterWeb.deleteAllStatusesByAPI();
    }
}
