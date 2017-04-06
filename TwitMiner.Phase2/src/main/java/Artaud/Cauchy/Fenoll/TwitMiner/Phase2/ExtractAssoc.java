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
			return;
		}
		
		List<String> motifs = new ArrayList<String>(); // Contiendra le fichier ligne par ligne
		File apriori = new File(args[0]); // Le fichier d'entrée
		try (BufferedReader in = new BufferedReader(new FileReader(apriori))) {
			String line = null;
			while ((line = in.readLine()) != null){
				motifs.add(line); // On place chaque ligne du fichier dnans l'arraylist
			}
		}
		for (String line : motifs) {
			Motif courant = new Motif(line); // On crée un nouveau motif Y à partir de la ligne
			//System.out.println(courant.getItems().toString() + " | " + courant.getSuport());
			if (courant.getItems().size() < 2) continue; // Si moins de 2 occurences on passe au suivant
			for (String line2 : motifs) {
				Motif sub = new Motif(line2); // On crée un second motif X
				if ((sub.getItems().isEmpty()) // Si X ne contient pas d'items
					||(! courant.isSubEns(sub)) // OU X n'est pas un sous-ensemble de Y
					||(sub.getItems().containsAll(courant.getItems()))) // OU si X = Y
					continue; // On passe au suivant
				//System.out.println("------" + sub.getItems().toString() + " | " + sub.getSuport());
				System.out.println(sub.getItems().toString() + " | " + sub.getSuport() + " sous-ensemble de " + courant.getItems().toString() + " | " + courant.getSuport());
			}
		}
	}
}
