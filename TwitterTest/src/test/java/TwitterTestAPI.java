import Core.Oauth;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import twitter4j.TwitterException;

import javax.annotation.PreDestroy;

public class TwitterTestAPI {
    Oauth oauth;
    twitter4j.Twitter twitter;
    TwitterAPI twitterAPI;

    @BeforeClass
    public void setUp(){
        oauth = new Oauth();
        twitter = oauth.getTwitterInstance();
        twitterAPI = new TwitterAPI();
    }

    @Test
    public void checkTopCreatedAtParameterInHomeTimeLine() throws TwitterException {
        String actualResult = twitterAPI.get_home_timeline_getCreated_at(twitter).get(0);
        String expectedResult = "Wed Aug 23 21:27:29 EEST 2017";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkTopRetweetCountParameterInHomeTimeLine() throws TwitterException {
        int actualResult = twitterAPI.get_home_timeline_getRetweet_count(twitter).get(0);
        int expectedResult = 0;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkTopTextParameterInHomeTimeLine() throws TwitterException {
        String actualResult = twitterAPI.get_home_timeline_getText(twitter).get(0);
        String expectedResult = "Sorry, I ate your mail https://t.co/1qHFsX7ud7";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkCreatedTweet() throws TwitterException {
        String tweet = "I am creating tweet1";
        twitterAPI.createTweet(twitter, tweet);
        String topStatus = twitter.getUserTimeline().get(0).getText();
        Assert.assertEquals(topStatus, tweet);
    }

    @Test
    public void checkThatTweetIsDestroyed() throws TwitterException {
        String tweetText = "New tweet for deleting";
        long id = twitterAPI.createTweet(twitter, tweetText).getId();
        twitterAPI.destroyTweet(twitter, id);
        String topStatus = twitter.getUserTimeline().get(0).getText();
        Assert.assertNotEquals(topStatus, tweetText);
    }

    @AfterSuite
    public void destroyAllStatuses() throws TwitterException {
        twitterAPI.deleteAllStatuses(twitter);
    }

}
