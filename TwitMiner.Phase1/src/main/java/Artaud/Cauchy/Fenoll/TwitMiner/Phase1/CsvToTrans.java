package Artaud.Cauchy.Fenoll.TwitMiner.Phase1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvToTrans 
{
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException
    {
    	if (args.length != 2) {
    		System.out.println("Usage : ./CsvToTrans <file.csv> <file.trans>");
    		System.exit(1);
    	}
    	Map<String, Integer> mapTrans = new HashMap<String, Integer>();
    	try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
    	    String line;
    	    Integer count = 0;
    	    while ((line = br.readLine()) != null) {
    	    	String[] words = line.split(";");
    	    	for (int i = 2; i < words.length; ++i) {
    	    		String word = words[i].substring(1, words[i].length()-1);
    	    		if (!mapTrans.containsKey(word)) {
    	    			mapTrans.put(word, ++count);
    	    		}
    	    	}
    	    }
    	}
    	for (Map.Entry<String, Integer> entry : mapTrans.entrySet()) {
    	    String key = entry.getKey();
    	    Object value = entry.getValue();
    	    System.out.println(key + " ||| " + value);
    	}
    }
}
