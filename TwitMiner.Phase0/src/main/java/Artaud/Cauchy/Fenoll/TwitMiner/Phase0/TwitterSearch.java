package Artaud.Cauchy.Fenoll.TwitMiner.Phase0;

import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterSearch {
	private Twitter twitter;
	public TwitterSearch() {
		TwitterFactory tf = new TwitterFactory(Configuration.getInstance().build());
    	twitter = tf.getInstance();
	}
	
	public ArrayList<String> search(String query, String lang) throws TwitterException {
    	Query request = new Query(query);
    	request.setLang(lang);
    	request.setCount(100);
        QueryResult result = twitter.search(request);
        ArrayList<String> res = new ArrayList<String>();
        for (Status status : result.getTweets()) {
            String row = "\"" + status.getCreatedAt() + "\";\"@" + status.getUser().getScreenName() + "\";";
        	String[] splited = status.getText().split("\\s+");
            for (String s : splited)
            	row += "\"" + s + "\";";
            res.add(row);
        }
        return res;
	}
	
}
