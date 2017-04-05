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
			Motif courant = new Motif(line); // On crée un nouveau motif à partir de la ligne
			if (courant.getItems().size() < 2) continue; // Si moins de 2 occurences on passe au suivant
			for (String line2 : motifs) { // Sinon on compare aux autres mots
				Motif sub = new Motif(line2); // On crée un second motif
				if (! courant.isSubEns(sub)) continue; // Si c'est un sous-ensemble on passe au suivant
				
			}	
		}
	}
}
