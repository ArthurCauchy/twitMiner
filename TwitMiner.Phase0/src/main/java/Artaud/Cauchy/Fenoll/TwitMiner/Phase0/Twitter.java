package Artaud.Cauchy.Fenoll.TwitMiner.Phase0;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Twitter {
	
	
	public static void main(String[] args) {
		twitter4j.Twitter twitter = TwitterFactory.getSingleton();
	    Query query = new Query("source:twitter4j yusukey");
	    QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for (Status status : result.getTweets())
	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	}
	
}
