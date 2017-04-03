package Artaud.Cauchy.Fenoll.TwitMiner.Phase2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractAssoc {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length != 2) {
			System.out.println("Usage : <File.apriori> <minconf>");
		}
		
		List<String> motifs = new ArrayList<String>();
		File apriori = new File(args[0]);
		try (BufferedReader in = new BufferedReader(new FileReader(apriori))) {
			String line = null;
			while ((line = in.readLine()) != null){
				motifs.add(line);
			}
		}
		
	}
}
