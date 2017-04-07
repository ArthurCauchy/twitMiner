package Artaud.Cauchy.Fenoll.TwitMiner.Phase3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Filter {
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage : ./filter <file_in.csv> <filtered_out.csv> [uselesswords.txt]");
			return;
		}
		BufferedReader fileIn = new BufferedReader(new FileReader(args[0]));
		PrintWriter writer = new PrintWriter(args[1], "UTF-8");
		BufferedReader uselessDico = null;
		if (args[2] != null) {
			uselessDico = new BufferedReader(new FileReader(args[2]));
		} else {
			uselessDico = new BufferedReader(new FileReader("motinutiles.txt"));
		}
		String line;
	    while ((line = fileIn.readLine()) != null) {
	    	String[] words = line.split("\";\"");
	    	for (int i = 2; i < words.length; ++i) {
	    		System.out.println(words);
	    	}
	    	System.out.println("");
	    }
	}
}
