package Artaud.Cauchy.Fenoll.TwitMiner.Phase0;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
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
    	Query query = new Query("jeux vidéos");
    	query.setLang("fr");
    	query.setCount(200);
        QueryResult result = twitter.search(query);
		System.out.println("Showing results");
        for (Status status : result.getTweets()) {
            String row = status.getUser().getName() + ":";
        	String[] splited = status.getText().split("\\s+");
            for (String s : splited)
            	row += "\"" + s + "\";";
            System.out.println(row);
        }
    }
}
