package Artaud.Cauchy.Fenoll.TwitMiner.Phase3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Filter {
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage : ./filter <file_in.csv> <filtered_out.csv> [uselesswords.txt]");
			return;
		}
		BufferedReader fileIn = new BufferedReader(new FileReader(args[0]));
		PrintWriter fileOut = new PrintWriter(args[1], "UTF-8");
		BufferedReader uselessDicoReader = null;
		if (args.length > 2) {
			uselessDicoReader = new BufferedReader(new FileReader(args[2]));
		} else {
			uselessDicoReader = new BufferedReader(new FileReader("motinutiles.txt"));
		}
		List<String> uselessDico = new ArrayList<String>();
		String lineDico;
	    while ((lineDico = uselessDicoReader.readLine()) != null) {
	    	uselessDico.add(lineDico);
	    }
		String lineIn;
	    while ((lineIn = fileIn.readLine()) != null) {
	    	System.out.println(lineIn);
	    	String[] words = lineIn.split("\";\"");
	    	String lineOut = "";
	    	for (int i = 0; i < words.length; ++i) {
	    		if (uselessDico.contains(words[i]))
	    			continue;
	    		lineOut += "\"" + words[i] + "\";";
	    	}
	    	//fileOut.println(lineOut);
	    	System.out.println(lineOut);
	    	System.out.println("");
	    }
	}
}
