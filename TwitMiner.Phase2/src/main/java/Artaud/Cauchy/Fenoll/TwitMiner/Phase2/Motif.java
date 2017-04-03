package Artaud.Cauchy.Fenoll.TwitMiner.Phase2;

import java.util.ArrayList;
import java.util.List;

public class Motif {
	private int suport;
	private List<Integer> items;
	
	public Motif(String values) {
		String itemsS [] = values.split(" "); 
		suport = Integer.parseInt(itemsS[itemsS.length-1].substring(1, itemsS[itemsS.length-1].length()-1));
		items = new ArrayList<Integer>();
		for (int i  = 0; i < itemsS.length-1; ++i) {
			items.add(Integer.parseInt(itemsS[i]));
		}
	}
	
	public int getSuport() {
		return suport;
	}
	
	public List<Integer> getItems() {
		return items;
	}
	
	public boolean isSubEns (Motif motif) {
		return items.containsAll(motif.getItems());
	}
}
