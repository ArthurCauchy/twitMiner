package Artaud.Cauchy.Fenoll.TwitMiner.Phase3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results {
	/* Returns true if the string can be interpreted as an Integer number */
	private static boolean isInteger(String str) {
		try {
			Integer integer = Integer.valueOf(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;  
	}
	
	/* Retrouve le mot associé à un nombre */
	private static String convertToWord(Map<Integer, String> mapTrans, Integer id) {
		return mapTrans.get(id);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if (args.length < 3) {
			System.out.println("Usage : ./results <file.lift> <file.map> <file.result>");
			return;
		}
		
		ObjectInputStream dicoIn = new ObjectInputStream(new FileInputStream(args[1]));
		
		HashMap<Integer, String> mapTrans = (HashMap<Integer, String>) dicoIn.readObject();
		
		dicoIn.close();
		
		BufferedReader fileIn = new BufferedReader(new FileReader(args[0]));
		PrintWriter fileOut = new PrintWriter(args[2], "UTF-8");
		
		String line;
	    while ((line = fileIn.readLine()) != null) {
	    	String assoc = line.split(" \\(")[0];
	    	String score = line.split(" \\(")[1];
	    	String left = assoc.split(" -> ")[0];
	    	String right = assoc.split(" -> ")[1];
	    	String[] ids = left.split(" ");
	    	for (int i = 0; i < ids.length; ++i) {
	    		if (!isInteger(ids[i])) { // si le mot n'est pas un nombre
	    			continue;
	    		}
	    		
	    		String word = convertToWord(mapTrans, Integer.valueOf(ids[i]));
	    		if (word == null) // bug ???
	    			continue;
	    		if (i != ids.length - 1)
	    			fileOut.print(word + " + ");
	    		else
	    			fileOut.print(word + " ");
	    	}
	    	
	    	fileOut.print("-> ");
	    	
	    	String[] ids2 = right.split(" ");
	    	for (int i = 0; i < ids2.length; ++i) {
	    		if (!isInteger(ids2[i])) { // si le mot n'est pas un nombre
	    			continue;
	    		}
	    		
	    		String word = convertToWord(mapTrans, Integer.valueOf(ids2[i]));
	    		if (word == null) // bug ???
	    			continue;
	    		if (i != ids2.length - 1)
	    			fileOut.print(word + " + ");
	    		else
	    			fileOut.print(word + " ");
	    	}
	    	fileOut.print("(" + score + '\n');
	    }
	    
	    fileIn.close();
	    fileOut.close();
	}
}
