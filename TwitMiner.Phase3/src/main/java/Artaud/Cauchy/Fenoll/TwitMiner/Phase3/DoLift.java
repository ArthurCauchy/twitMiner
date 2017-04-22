package Artaud.Cauchy.Fenoll.TwitMiner.Phase3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class DoLift {
	/* Returns true if the string can be interpreted as an Integer number */
	private static boolean isInteger(String str) {
		try {
			Integer integer = Integer.valueOf(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;  
	}
	
	public static void main (String[] args) throws IOException, InterruptedException {
		if (args.length < 4) {
			System.out.println("Usage : ./dolift <file_in.apriori> <file_in.assoc> <how_much> <file_out.lift>");
			return;
		}
		BufferedReader aprioriReader = new BufferedReader(new FileReader(args[0]));
		BufferedReader assocReader = new BufferedReader(new FileReader(args[1]));
		PrintWriter liftWriter = new PrintWriter(args[3], "UTF-8");
		SortedMap<Double, String> scoreMap = new TreeMap<Double, String>(Collections.reverseOrder());
		Map<String, Integer> freqMap = new HashMap<String, Integer>();
		String lineApriori;
	    while ((lineApriori = aprioriReader.readLine()) != null) {
	    	String wordIdText = lineApriori.split(" \\(")[0];
	    	Integer freq = Integer.parseInt(lineApriori.split("\\(")[1].split("\\)")[0]);
	    	freqMap.put(wordIdText, freq);
	    }
		String lineAssoc;
	    while ((lineAssoc = assocReader.readLine()) != null) {
	    	String assoc = lineAssoc.split(" \\(")[0];
	    	Double conf = Double.parseDouble(lineAssoc.split("\\(")[1].split("\\)")[0]);
	    	String Y = assoc.split("-> ")[1];
	    	Integer Yfreq = freqMap.get(Y);
	    	if (Yfreq == null || Yfreq == 0) // bug ???
	    		continue;
	    	Double score = conf / Yfreq;
	    	/*if (score <= 1)
	    		continue;*/
	    	scoreMap.put(score, assoc);
	    }
	    
	    int i = 0;
	    for(Entry<Double, String> entry : scoreMap.entrySet()) {
	    	if (i >= Integer.parseInt(args[2]))
	    		break;
	    	Double score = entry.getKey();
	    	String assoc = entry.getValue();
	    	liftWriter.print(assoc + " (" + score + ")\n");
	    	++i;
	    }
	    
	    liftWriter.close();
	}
}
