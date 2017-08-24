import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

public class TwitterAPI {

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

    public void destroyTweet(twitter4j.Twitter twitter, long id) throws TwitterException {
        twitter.destroyStatus(id);
    }

    public void deleteAllStatuses(twitter4j.Twitter twitter) throws TwitterException {
        int length = twitter.getUserTimeline().size();
        List<Status> statuses = twitter.getUserTimeline();
        for (Status st : statuses) {
            destroyTweet(twitter, st.getId());
        }
    }
}
