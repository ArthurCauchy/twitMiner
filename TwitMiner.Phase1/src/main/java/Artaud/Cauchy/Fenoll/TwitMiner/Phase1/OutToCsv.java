package Artaud.Cauchy.Fenoll.TwitMiner.Phase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class OutToCsv {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		if (args.length != 1) {
			System.out.println("Usage : ./OutToCsv <file.apriori>");
			return;
		}
		File mapFile = new File(args[0].split("\\.")[0] + ".map");
		
		Map<Integer, String> mapAssoc = null;
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(mapFile))){
			mapAssoc = (Map<Integer, String>) in.readObject();
		}
		System.out.println(mapAssoc);
		
		File aprioriFile = new File(args[0]);
		try(BufferedReader in = new BufferedReader(new FileReader(aprioriFile))) {
			File aprioriCsv = new File(args[0] + ".csv");
			aprioriCsv.createNewFile();
			try (BufferedWriter out = new BufferedWriter(new FileWriter(aprioriCsv))){
				String line;
				while ((line = in.readLine()) != null) {
					String words [] = line.split(" ");
					for (int i = 0; i < words.length - 1; ++i) {
						if(words[i].equals("->")) out.write("\"->\";");
						//System.out.println(mapAssoc.get(Integer.parseInt(words[i])));
						out.write("\"" + (mapAssoc.get(Integer.parseInt(words[i])) + "\";"));
					}
					out.write("\"" + words[words.length - 1] + "\";");
					out.newLine();
				}
				
			}
		}
	}

}
