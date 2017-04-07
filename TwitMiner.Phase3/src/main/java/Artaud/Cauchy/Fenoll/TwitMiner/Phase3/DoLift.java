package Artaud.Cauchy.Fenoll.TwitMiner.Phase3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class DoLift {
	public static void main (String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Usage : ./dolift <file_in.apriori> <file_in.assoc> <file_out.lift>");
			return;
		}
		BufferedReader aprioriReader = new BufferedReader(new FileReader(args[0]));
		BufferedReader assocReader = new BufferedReader(new FileReader(args[1]));
		PrintWriter liftWriter = new PrintWriter(args[2], "UTF-8");
		SortedMap<Integer, Double> scoreMap = new TreeMap<Integer, Double>();
		List<Double> freqList = new ArrayList<Double>();
		String lineApriori;
	    while ((lineApriori = aprioriReader.readLine()) != null) {
	    	freqList.add(Double.parseDouble(lineApriori.split("(")[1].split(")")[0]));
	    }
		String lineAssoc;
	    while ((lineAssoc = assocReader.readLine()) != null) {
	    	Double conf = Double.parseDouble(lineAssoc.split("(")[1].split(")")[0]);
	    	/*Double score = conf / freq; -------------- ça sera un truc de ce style
	    	scoreMap.put(key, value)*/
	    }
	    
	    // FREQ : fréquence entre parenthèse dans apriori
	    // CONF : confiance entre parenthèse dans assoc
	    
	    // TODO : Pouvoir faire le lien entre apriori et assoc pour récupérer la fréquence associée
	    // Sélectionner les 10 (ou autre) meilleurs associations et les écrire dans le fichier de sortie
	}
}
