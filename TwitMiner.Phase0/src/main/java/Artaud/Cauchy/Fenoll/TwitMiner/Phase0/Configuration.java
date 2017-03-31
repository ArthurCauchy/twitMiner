package Artaud.Cauchy.Fenoll.TwitMiner.Phase0;

import twitter4j.conf.ConfigurationBuilder;

public class Configuration {
	private ConfigurationBuilder cb = new ConfigurationBuilder();
	
	private static Configuration Instance = new Configuration();  
	
	private Configuration() {
		cb.setDebugEnabled(true)
	  	  .setOAuthConsumerKey("veVx5ut2r7osOo3yOJgDAs18E")
	  	  .setOAuthConsumerSecret("LMKS8EEBsd9yvtq2j6amaPKvqFRoBui9HrfP0ROg7sXU1iQwSV")
	  	  .setOAuthAccessToken("847701684079869953-XRMgBKS6eloIkMvsORunF6ryj3qVj1y")
	  	  .setOAuthAccessTokenSecret("dH8PTjk4gUqLhGYjOzWt4RmtB5rQfzetWHrl3gjscYdfL");
	}
	
	public static Configuration getInstance() {
		return Instance;
	}
	
	public twitter4j.conf.Configuration build() {
		return cb.build();
	}
}
