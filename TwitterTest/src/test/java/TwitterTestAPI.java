import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.Console;

public class TwitterTestAPI {
    ConfigurationBuilder configurationBuilder;
    twitter4j.Twitter twitter;
    TwitterMain twitterMain;

    @BeforeClass
    public void setUp(){
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("vSE3Efwk8zIhT6E16dagX6IHW")
                .setOAuthConsumerSecret("VWbFTJXFayBp0fZrpjqb5iZzWAa8MpYZpMafwXbb3q65OBuwTN")
                .setOAuthAccessToken("1140922939-SqqztQpWzaUoGz4DcoOto8xVUivoeXiHz1ekZQJ")
                .setOAuthAccessTokenSecret("bMdS5MdNsEVUOTlhc2MlVEXCIGVsA1SOaC6gvp8fqlFdw");
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }

    @Test
    public void checkTopCreatedAtParameterInHomeTimeLine() throws TwitterException {
        twitterMain = new TwitterMain();
        String actualResult = twitterMain.get_home_timeline_getCreated_at(twitter).get(0);
        String expectedResult = "Wed Aug 23 21:27:29 EEST 2017";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkTopRetweetCountParameterInHomeTimeLine() throws TwitterException {
        twitterMain = new TwitterMain();
        int actualResult = twitterMain.get_home_timeline_getRetweet_count(twitter).get(0);
        int expectedResult = 0;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkTopTextParameterInHomeTimeLine() throws TwitterException {
        twitterMain = new TwitterMain();
        String actualResult = twitterMain.get_home_timeline_getText(twitter).get(0);
        String expectedResult = "Sorry, I ate your mail https://t.co/1qHFsX7ud7";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkCreatedTweet() throws TwitterException {
        twitterMain = new TwitterMain();
        String tweet = "I am creating tweet";
        twitterMain.createTweet(twitter, tweet);
        String topStatus = twitter.getHomeTimeline().get(0).getText();
        Assert.assertEquals(topStatus, tweet);
    }


    @Test
    public void checkThatTweetIsDestroyed() throws TwitterException {
        twitterMain = new TwitterMain();
        String tweetText = "New tweet for deleting";
        long id = twitterMain.createTweet(twitter, tweetText).getId();
        twitterMain.destroyTweet(twitter, id);
        String topStatus = twitter.getHomeTimeline().get(0).getText();
        Assert.assertNotEquals(topStatus, tweetText);
    }

    @Test
    public void checkTweetDuplicationError() throws TwitterException {
        twitterMain = new TwitterMain();
        String tweetText = "New tweet for duplicating";
        String errorMessage = "403 duplication";
        twitterMain.createTweet(twitter, tweetText).getId();
        twitterMain.createTweet(twitter, tweetText).getId();
        Assert.assertEquals(Console.class.toString(), errorMessage);
    }

}
