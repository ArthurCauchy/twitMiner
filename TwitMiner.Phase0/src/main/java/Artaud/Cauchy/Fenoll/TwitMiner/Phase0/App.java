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
    	  .setOAuthConsumerKey("veVx5ut2r7osOo3yOJgDAs18E")
    	  .setOAuthConsumerSecret("LMKS8EEBsd9yvtq2j6amaPKvqFRoBui9HrfP0ROg7sXU1iQwSV")
    	  .setOAuthAccessToken("847701684079869953-XRMgBKS6eloIkMvsORunF6ryj3qVj1y")
    	  .setOAuthAccessTokenSecret("dH8PTjk4gUqLhGYjOzWt4RmtB5rQfzetWHrl3gjscYdfL");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();
        List<twitter4j.Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (twitter4j.Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                               status.getText());
        }
    }
}
