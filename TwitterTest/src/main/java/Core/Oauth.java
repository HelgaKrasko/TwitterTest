package Core;

import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Oauth {
    private ConfigurationBuilder configurationBuilder;

    private static String consumerKey = "vSE3Efwk8zIhT6E16dagX6IHW";
    private static String consumerSecret = "VWbFTJXFayBp0fZrpjqb5iZzWAa8MpYZpMafwXbb3q65OBuwTN";
    private static String accessToken = "1140922939-SqqztQpWzaUoGz4DcoOto8xVUivoeXiHz1ekZQJ";
    private static String accessTokenSecret = "bMdS5MdNsEVUOTlhc2MlVEXCIGVsA1SOaC6gvp8fqlFdw";

    public twitter4j.Twitter getTwitterInstance() {
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        return twitterFactory.getInstance();
    }
}
