import Core.Oauth;
import Pages.HomeTimeLinePage;

import Pages.TweetPostInTimeLine;
import Pages.UserTimeLinePage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import twitter4j.TwitterException;

import java.util.concurrent.TimeUnit;

public class TwitterTestWeb {
    private TwitterWeb twitterWeb;
    private HomeTimeLinePage homeTimeLinePage;
    private UserTimeLinePage userTimeLinePage;
    TweetPostInTimeLine tweetPostInTimeLine;

    private static String siteURL = "https://twitter.com/";
    private static String loginValue = "orkgirl";
    private static String passwordValue = "twittertest123";
    private static String createdTweetText = "Created tweet";
    private static String createdTweetWithParametersText = "Created tweet with parameters";
    private static String createdAt = "now";
    private static String retweetCount = "";
    private static String tweetMessageForDestroying = "This tweet will be destroyed";

    @BeforeSuite
    public void setUp() {
        twitterWeb = new TwitterWeb();
        twitterWeb.setUpDriver();
    }

    @BeforeTest(description = "Navigate to target site and log in")
    public void navigateToSiteAndLogin() {
        twitterWeb.navigateToSite(siteURL);
        twitterWeb.logIn(loginValue, passwordValue);
    }

    @Test(description = "Create tweet and check parameters: created at; retweet count; text")
    public void checkCTweetParameters() throws InterruptedException {
        tweetPostInTimeLine = new TweetPostInTimeLine(twitterWeb.getDriver(), 1);
        twitterWeb.createTweet(createdTweetWithParametersText);
        TimeUnit.SECONDS.sleep(2);
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
        twitterWeb.createTweet(tweetMessageForDestroying);
        twitterWeb.goToUserTimeLineByAccountNameLink();
        twitterWeb.deleteTweet(1);
        TimeUnit.SECONDS.sleep(2);
        userTimeLinePage = new UserTimeLinePage(twitterWeb.getDriver());
        String actualResult = userTimeLinePage.getStatusByPosition(1).getText();
        Assert.assertNotEquals(actualResult, tweetMessageForDestroying);
    }

    @AfterSuite
    public void setDown() throws TwitterException {
        twitterWeb.deleteAllStatusesByAPI();
        twitterWeb.setDownDriver();
    }

}
