package Artaud.Cauchy.Fenoll.TwitMiner.Phase1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CsvToTrans 
{
    public static void main(String[] args) throws IOException
    {
    	if (args.length != 3) {
    		System.out.println("Usage : ./CsvToTrans <file.csv> <file.map> <file.trans>");
    		return;
    	}
    	
    	Map<String, Integer> mapTrans = new HashMap<String, Integer>();
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		PrintWriter out = new PrintWriter(args[2], "UTF-8");
	    
		String line;
	    Integer count = 0;
	    while ((line = in.readLine()) != null) {
	    	String[] words = line.split("\";\"");
	    	for (int i = 2; i < words.length; ++i) {
	    		if (words[i].length() == 0) continue;
	    		String word = words[i].substring(0, words[i].length());
	    		Integer wordID = mapTrans.get(word);
	    		if (wordID == null) {
	    			out.print(count+1);
	    			mapTrans.put(word, ++count);
	    		}
	    		else
	    			out.print(wordID);
	    		if (i < words.length -1)
	    			out.print(' ');
	    	}
	    	out.print('\n');
	    }
	    
	    in.close();
	    out.close();
    	
	    ObjectOutputStream dicoOut = new ObjectOutputStream(new FileOutputStream(args[1]));
	    
    	dicoOut.writeObject(mapTrans); // On serialize la relation entre les mots et leurs nombre pour plus tard
    	
    	dicoOut.close();
    }
}
