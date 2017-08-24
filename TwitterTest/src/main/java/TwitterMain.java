import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterMain {

    long id = 0;
    public static void main(String[] args) throws TwitterException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("vSE3Efwk8zIhT6E16dagX6IHW")
                .setOAuthConsumerSecret("VWbFTJXFayBp0fZrpjqb5iZzWAa8MpYZpMafwXbb3q65OBuwTN")
                .setOAuthAccessToken("1140922939-SqqztQpWzaUoGz4DcoOto8xVUivoeXiHz1ekZQJ")
                .setOAuthAccessTokenSecret("bMdS5MdNsEVUOTlhc2MlVEXCIGVsA1SOaC6gvp8fqlFdw");

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter4j.Twitter twitter = twitterFactory.getInstance();
        TwitterMain testTw = new TwitterMain();

//        get_home_timeline_getTest(twitter);
//        get_home_timeline_getCreated_at(twitter);
//        get_home_timeline_getRetweet_count(twitter);
//        get_home_timeline_getText(twitter);
//        testTw.post_destroy_post_Update(twitter);
//        testTw.post_destroy_post_Destroy(twitter);
    }

    public static List<Status> get_home_timeline_getTest(twitter4j.Twitter twitter) throws TwitterException {
        List<Status> status = twitter.getHomeTimeline();
        for (Status s : status) {
            System.out.println(s.getUser().getName() + " " + s.getText());
        }
        return status;
    }

    public List<String> get_home_timeline_getText(twitter4j.Twitter twitter) throws TwitterException {
        List<Status> status = twitter.getHomeTimeline();
        List<String> resultList = new ArrayList<String>();
        for (Status s : status) {
            resultList.add(s.getText());
        }
        return resultList;
    }

    public List<Integer> get_home_timeline_getRetweet_count(twitter4j.Twitter twitter) throws TwitterException {
        List<Status> status = twitter.getHomeTimeline();
        List<Integer> resultList = new ArrayList<Integer>();
        for (Status s : status) {
            resultList.add(s.getRetweetCount());
        }
        return resultList;
    }

    public List<String> get_home_timeline_getCreated_at(twitter4j.Twitter twitter) throws TwitterException {
        List<Status> status = twitter.getHomeTimeline();
        List<String> resultList = new ArrayList<String>();
        for (Status s : status) {
            resultList.add(s.getCreatedAt().toString());
        }
        return resultList;
    }

    public Status createTweet(twitter4j.Twitter twitter, String tweet) throws TwitterException {
       twitter.updateStatus(tweet);
       return twitter.getUserTimeline().get(0);
    }
    // if call method twice - Additional

    public void updateTweet(twitter4j.Twitter twitter, String updatedTweet) throws TwitterException {
        twitter.updateUserList(0,"I am creating tweet1", false, updatedTweet);
    }

    public  void destroyTweet(twitter4j.Twitter twitter, long id) throws TwitterException {
        twitter.destroyStatus(id);
    }

    public  void post_destroy_post_Destroy_2(twitter4j.Twitter twitter, long id) throws TwitterException {
        twitter.destroyStatus(twitter.getUserTimeline().get(0).getId());

        System.out.println("Status is destroyed");
    }

}
