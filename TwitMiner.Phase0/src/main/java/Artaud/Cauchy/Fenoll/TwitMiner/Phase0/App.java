package Artaud.Cauchy.Fenoll.TwitMiner.Phase0;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class App 
{
    public static void main( String[] args ) throws TwitterException
    {
    	// The factory instance is re-useable and thread safe.
    	
    	TwitterFactory tf = new TwitterFactory(Configuration.getInstance().build());
    	Twitter twitter = tf.getInstance();
        List<twitter4j.Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (twitter4j.Status status : statuses) {
            String row = status.getUser().getName() + ":";
        	String[] splited = status.getText().split("\\s+");
            for (String s : splited)
            	row += "\"" + s + "\";";
            System.out.println(row);
        }
    }
}
