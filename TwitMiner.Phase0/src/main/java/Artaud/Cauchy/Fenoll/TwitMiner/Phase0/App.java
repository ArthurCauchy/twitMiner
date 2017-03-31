package Artaud.Cauchy.Fenoll.TwitMiner.Phase0;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class App 
{
    public static void main( String[] args ) throws TwitterException
    {
    	// The factory instance is re-useable and thread safe.
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("*********************")
    	  .setOAuthConsumerSecret("******************************************")
    	  .setOAuthAccessToken("**************************************************")
    	  .setOAuthAccessTokenSecret("******************************************");
        Twitter twitter = TwitterFactory.getSingleton();
        List<twitter4j.Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (twitter4j.Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                               status.getText());
        }
    }
}
