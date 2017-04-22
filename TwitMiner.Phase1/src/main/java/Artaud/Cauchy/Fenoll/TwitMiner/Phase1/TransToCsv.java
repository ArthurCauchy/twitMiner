package Artaud.Cauchy.Fenoll.TwitMiner.Phase1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TransToCsv {
	/* Retrouve le mot associé à un nombre */
	private static String convertToWord(Map<Integer, String> mapTrans, Integer id) {
		return mapTrans.get(id);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		if (args.length != 3) {
			System.out.println("Usage : ./TransToCsv <file.trans> <file.map> <file.csv>");
			return;
		}
		
		ObjectInputStream dicoIn = new ObjectInputStream(new FileInputStream(args[1]));
		
		HashMap<Integer, String> mapTrans = (HashMap<Integer, String>) dicoIn.readObject();
		
		dicoIn.close();
	    
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		PrintWriter out = new PrintWriter(args[2], "UTF-8");
		
		String line;
	    while ((line = in.readLine()) != null) {
	    	String[] ids = line.split(" ");
	    	for (int i = 0; i < ids.length; ++i) {
	    		String word = convertToWord(mapTrans, Integer.valueOf(ids[i]));
	    		if (word == null)
	    			continue;
	    		out.print("\"" + word + "\";");
	    	}
	    	out.print('\n');
	    }
		
		in.close();
		out.close();
	}

}
