package Artaud.Cauchy.Fenoll.TwitMiner.Phase2;

import java.util.ArrayList;
import java.util.List;

public class Motif {
	private int suport;
	private List<Integer> items;
	
	public Motif(String values) {
		String itemsS [] = values.split(" "); // On sépare en plusieurs chaînes au niveau des espaces
		suport = Integer.parseInt(itemsS[itemsS.length-1].substring(1, itemsS[itemsS.length-1].length()-1)); // Le nombre d'occurences
		items = new ArrayList<Integer>();
		for (int i  = 0; i < itemsS.length-1; ++i) {
			//System.out.println("---" + Integer.parseInt(itemsS[i]));
			items.add(Integer.parseInt(itemsS[i])); // On ajoute les items
		}
	}
	
	public int getSuport() {
		return suport;
	}
	
	public List<Integer> getItems() {
		return items;
	}
	
	/* true si c'est un sous-ensemble */
	public boolean isSubEns (Motif motif) {
		return items.containsAll(motif.getItems());
	}
}
