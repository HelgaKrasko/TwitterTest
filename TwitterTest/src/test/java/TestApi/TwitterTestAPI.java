package TestApi;

import Api.Oauth;
import Api.TwitterAPI;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import twitter4j.TwitterException;

import java.util.Date;

public class TwitterTestAPI {
    private Oauth oauth;
    private twitter4j.Twitter twitter;
    private TwitterAPI twitterAPI;

    private static String tweetCreatedAtParameter = "Tweet for checking created at";
    private static String tweetRetweetParameter = "Tweet for checking retweet count";
    private static String tweetTextParameter = "Tweet for checking text";
    private static String retweetCount = "0";
    private static String createdTweet = "Created tweet";
    private static String deletedTweet = "Deleted tweet";
    private static String duplicatedTweet = "Duplicated tweet";

    @BeforeSuite
    public void setUp() {
        oauth = new Oauth();
        twitter = oauth.getTwitterInstance();
        twitterAPI = new TwitterAPI();
    }

    @Test(description = "Create tweet and check parameter created_at by Api")
    public void checkTopCreatedAtParameterInHomeTimeLine() throws TwitterException {
        twitterAPI.createTweet(twitter, tweetCreatedAtParameter);
        String tweetDateWithParameters = new Date(System.currentTimeMillis()).toString();
        String actualCreatedAt = twitterAPI.getHomeTimelineCreatedAt(twitter).get(0);
        Assert.assertEquals(actualCreatedAt, tweetDateWithParameters);
    }

    @Test(description = "Create tweet and check parameter retweet_count by Api")
    public void checkTopRetweetCountParameterInHomeTimeLine() throws TwitterException {
        twitterAPI.createTweet(twitter, tweetRetweetParameter);
        int actualResult = twitterAPI.getHomeTimelineRetweetCount(twitter).get(0);
        Assert.assertEquals(actualResult, retweetCount);
    }

    @Test(description = "Create tweet and check parameter text by Api")
    public void checkTopTextParameterInHomeTimeLine() throws TwitterException {
        twitterAPI.createTweet(twitter, tweetTextParameter);
        String actualResult = twitterAPI.getHomeTimelineGetText(twitter).get(0);
        Assert.assertEquals(actualResult, tweetTextParameter);
    }

    @Test(description = "Create tweet and check that it is present")
    public void checkCreatedTweet() throws TwitterException {
        twitterAPI.createTweet(twitter, createdTweet);
        String topStatus = twitter.getUserTimeline().get(0).getText();
        Assert.assertEquals(topStatus, createdTweet);
    }

    @Test(description = "Delete created tweet and check that it is absent")
    public void checkThatTweetIsDestroyed() throws TwitterException {
        long id = twitterAPI.createTweet(twitter, deletedTweet).getId();
        twitterAPI.destroyTweet(twitter, id);
        String topStatus = twitter.getUserTimeline().get(0).getText();
        Assert.assertNotEquals(topStatus, deletedTweet);
    }

    @Test(description = "Check that user can not tweet equal statuses")
    public void duplicate() throws TwitterException {
        boolean thrown = false;
        twitterAPI.createTweet(twitter, duplicatedTweet);
        try {
            twitterAPI.createTweet(twitter, duplicatedTweet);
        } catch (TwitterException ex) {
            thrown = true;
            System.out.println("Duplicate! User can not tweet equal statuses");
        }
        Assert.assertTrue(thrown);
    }

    @AfterSuite
    public void destroyAllStatuses() throws TwitterException {
        twitterAPI.deleteAllStatuses(twitter);
    }

}
