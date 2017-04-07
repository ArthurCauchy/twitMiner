package Artaud.Cauchy.Fenoll.TwitMiner.Phase2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExtractAssoc {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length != 2) {
			System.out.println("Usage : <File.apriori> <minconf>");
			return;
		}
		double minConf = Double.parseDouble(args[1]);
		List<String> motifs = new ArrayList<String>(); // Contiendra le fichier ligne par ligne
		File apriori = new File(args[0]); // Le fichier d'entrée
		File outFile = new File(args[0].split("\\.")[0]+".assoc");
		try (BufferedReader in = new BufferedReader(new FileReader(apriori))) {
			String line = null;
			while ((line = in.readLine()) != null){
				motifs.add(line); // On place chaque ligne du fichier dnans l'arraylist
			}
		}
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(outFile))) {
			for (String line : motifs) {
				Motif courant = new Motif(line); // On crée un nouveau motif Y à partir de la ligne
				if (courant.getItems().size() < 2) continue; // Si moins de 2 occurences on passe au suivant
				for (String line2 : motifs) {
					Motif sub = new Motif(line2); // On crée un second motif X
					if ((sub.getItems().isEmpty()) // Si X ne contient pas d'items
						||(! sub.estSousEnsembleDe(courant)) // OU X n'est pas un sous-ensemble de Y
						||(sub.getItems().equals(courant.getItems()))) // OU si X = Y
						continue; // On passe au suivant
					//System.out.println(""+courant.getSuport()+" / "+sub.getSuport());
					double conf = ((double)courant.getSuport())/((double)sub.getSuport());
					if (conf > 1) continue;	// suprimer les confiances superieur à 1 dù à un probleme d'apriori
					if (conf > minConf) {
						String strCour = new String();
						Set<Integer> setCour =  new HashSet<Integer>(courant.getItems());
						setCour.removeAll(sub.getItems());
						for (Integer elem : setCour) {
							strCour += ""+elem+" ";
						}
						out.write(sub.toString().substring(0, sub.toString().length()-1)+
								" -> "+strCour+"("+conf+")");
						out.newLine();
						out.flush();
					}
				}
			}
		}
	}
}
