package Artaud.Cauchy.Fenoll.TwitMiner.Phase3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		if (args.length < 5) {
			System.out.println("Usage : ./dolift <file_in.trans> <file_in.apriori> <file_in.assoc> <how_much> <file_out.lift>");
			return;
		}
		BufferedReader transReader = new BufferedReader(new FileReader(args[0]));
		BufferedReader aprioriReader = new BufferedReader(new FileReader(args[1]));
		BufferedReader assocReader = new BufferedReader(new FileReader(args[2]));
		PrintWriter liftWriter = new PrintWriter(args[4], "UTF-8");
		SortedMap<Double, Object[]> scoreMap = new TreeMap<Double, Object[]>(Collections.reverseOrder());
		Map<String, Integer> freqMap = new HashMap<String, Integer>();
		ArrayList<ArrayList<Integer>> transFileContent = new ArrayList<ArrayList<Integer>>();
		
		String lineTrans;
	    while ((lineTrans = transReader.readLine()) != null) {
	    	String[] ids = lineTrans.split(" ");
	    	ArrayList<Integer> line = new ArrayList<Integer>();
	    	for (short i = 0; i < ids.length; ++i) {
	    		line.add(new Integer(ids[i]));
	    	}
	    	transFileContent.add(line);
	    }
	    transReader.close();
		
		String lineApriori;
	    while ((lineApriori = aprioriReader.readLine()) != null) {
	    	String wordIdText = lineApriori.split(" \\(")[0];
	    	Integer freq = Integer.parseInt(lineApriori.split("\\(")[1].split("\\)")[0]);
	    	freqMap.put(wordIdText, freq);
	    }
	    aprioriReader.close();
	    
		String lineAssoc;
	    while ((lineAssoc = assocReader.readLine()) != null) {
	    	String assoc = lineAssoc.split(" \\(")[0];
	    	Double conf = Double.parseDouble(lineAssoc.split("\\(")[1].split("\\)")[0]);
	    	String X = assoc.split(" -> ")[0];
	    	String Y = assoc.split(" -> ")[1];
	    	Integer Yfreq = freqMap.get(Y);
	    	if (Yfreq == null || Yfreq == 0) // bug ???
	    		continue;
	    	/*if (score <= 1)
    			continue;*/
	    	
	    	Double score = conf / Yfreq;
	    	
	    	Object[] datas = {X, Y, conf};
	    	scoreMap.put(score, datas);
	    }
	    assocReader.close();
	    
	    short i = 0;
	    for(Entry<Double, Object[]> entry : scoreMap.entrySet()) {
	    	if (i >= Integer.parseInt(args[3]))
	    		break;
	    	
	    	Object[] objects = entry.getValue();
	    	String X = (String) objects[0];
	    	String Y = (String) objects[1];
	    	Double conf = (Double) objects[2];
	    	
	    	int occur = 0;
	    	for (ArrayList<Integer> transLine : transFileContent) {
	    		boolean containsAll = true;
	    		String[] idsX = X.split(" ");
	    		String[] idsY = Y.split(" ");
	    		for (short j = 0; j < idsX.length; ++j) {
	    			if (!transLine.contains(Integer.valueOf(idsX[j]))) {
	    				containsAll = false;
	    				break;
	    			}
	    		}
	    		if (containsAll) {
	    			for (short j = 0; j < idsY.length; ++j) {
		    			if (!transLine.contains(Integer.valueOf(idsY[j]))) {
		    				containsAll = false;
		    				break;
		    			}
		    		}
	    		}
	    		if (containsAll)
	    			++occur;
	    	}
	    	Double freq = ((double) occur) / transFileContent.size();
	    	
	    	Double score = entry.getKey();
	    	
	    	liftWriter.print(X + " -> " + Y + " (lift:" + score + " conf:" + conf + " freq:" + freq + ")\n");
	    	++i;
	    }
	    
	    liftWriter.close();
	}
}
