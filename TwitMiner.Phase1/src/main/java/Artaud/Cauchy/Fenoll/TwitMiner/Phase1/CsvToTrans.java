package Artaud.Cauchy.Fenoll.TwitMiner.Phase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CsvToTrans 
{
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException
    {
    	if (args.length != 2) {
    		System.out.println("Usage : ./CsvToTrans <file.csv> <file.trans>");
    		System.exit(1);
    	}
    	Map<String, Integer> mapTrans = new HashMap<String, Integer>();
    	List<String> twits = new ArrayList<String>();
    	try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
    	    String line;
    	    Integer count = 0;
    	    while ((line = br.readLine()) != null) {
    	    	twits.add(line);
    	    	String[] words = line.split("\";\"");
    	    	for (int i = 2; i < words.length; ++i) {
    	    		if (words[i].length() == 0) continue;
    	    		//System.out.println(words[i]);
    	    		String word = words[i].substring(0, words[i].length());
    	    		if (!mapTrans.containsKey(word)) {
    	    			mapTrans.put(word, ++count);
    	    		}
    	    	}
    	    }
    	}
    	/*
    	for (Map.Entry<String, Integer> entry : mapTrans.entrySet()) {
    	    String key = entry.getKey();
    	    Object value = entry.getValue();
    	    System.out.println(key + " ||| " + value);
    	}
    	*/
    	
    	File outFile = new File(args[1]);
    	outFile.createNewFile();
    	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
    	for (String twit : twits) {
    		String sentence = new String();
    		String words [] = twit.split("\";\"");
    		for (int i = 2; i < words.length; ++i) {
    			String word = words[i];
    			sentence += mapTrans.get(word.substring(0, word.length()))+" ";	
    		}
    		output.write(sentence);
    		output.newLine();
    	}
    	output.close();
    	
    	Map<Integer, String> mapAssoc = mapTrans.entrySet().stream()
    			.collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    	File mapFile = new File(args[0].split("\\.")[0] + ".map");
    	mapFile.createNewFile();
    	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(mapFile));
    	out.writeObject(mapAssoc);
    	out.close();
    }
}
