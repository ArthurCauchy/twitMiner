package Artaud.Cauchy.Fenoll.TwitMiner.Phase2;

import java.util.HashSet;
import java.util.Set;

public class Motif {
	private int suport;
	private Set<Integer> items;
	
	public Motif(String values) {
		String itemsS [] = values.split(" "); // On sépare en plusieurs chaînes au niveau des espaces
		suport = Integer.parseInt(itemsS[itemsS.length-1].substring(1, itemsS[itemsS.length-1].length()-1)); // Le nombre d'occurences
		items = new HashSet<Integer>();
		for (int i  = 0; i < itemsS.length-1; ++i) {
			//System.out.println("---" + Integer.parseInt(itemsS[i]));
			items.add(Integer.parseInt(itemsS[i])); // On ajoute les items
		}
	}
	
	public int getSuport() {
		return suport;
	}
	
	public Set<Integer> getItems() {
		return items;
	}
	
	/* true si c'est un sous-ensemble */
	public boolean estSousEnsembleDe (Motif motif) {
		return motif.items.containsAll(this.items);
	}
	
	@Override
	public String toString() {
		String res = new String();
		for (Integer elem : items) {
			res += elem+" ";
		}
		return res;
	}
}

