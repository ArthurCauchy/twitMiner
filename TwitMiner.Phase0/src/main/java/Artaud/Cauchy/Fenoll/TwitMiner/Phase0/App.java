package Artaud.Cauchy.Fenoll.TwitMiner.Phase0;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import twitter4j.TwitterException;

public class App 
{
	private static long WAITING = 900000; 
	
    public static void main( String[] args ) throws TwitterException, IOException
    {
    	if (args.length != 4) {
    		System.err.println("query lang number file");
    		System.exit(1);
    	}
    	int quantity = Integer.parseInt(args[2]);
    	File file = new File(args[3]);
    	if (! file.exists()) 
    		file.createNewFile();
    	PrintWriter out = new PrintWriter(new FileOutputStream(file)); 
    	TwitterSearch ts = new TwitterSearch();
    	for (int i = 0; i <= quantity/100; ++i) {
    		ArrayList<String> rows = ts.search(args[0], args[1]);
    		for (String row : rows) {
    			System.out.println(row);
    			out.println(row);
    			out.flush();
    		}
    		
    		try {
				Thread.sleep(WAITING);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	out.close();
    }
}
