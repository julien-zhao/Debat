import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListeAdjacence {
	private HashMap<Noeud, Arc> maps;
	
	public ListeAdjacence() {
		maps = new HashMap<Noeud,Arc>();
	}
	
	public boolean argument(Noeud noeud) {
		/*
		if(!maps.containsKey(noeud)) {
			maps.put(noeud, new Arc());
		}
		
		*/
		
		boolean identique = true;
		for(Noeud unNoeud : maps.keySet()) {
			if(noeud.getNoeud().equals(unNoeud.getNoeud())) {
				identique = false;
			}
		}
		if(identique) {
			maps.put(noeud, new Arc());
		}
		
		return identique;
	}
	
	public void contradiction(Noeud arg1, Noeud arg2) {
		if(!maps.containsKey(arg1)) {
			maps.put(arg1, new Arc());
		}
		
		if(!maps.containsKey(arg2)) {
			maps.put(arg2, new Arc());
		}
		
		
		maps.get(arg1).add(arg2);
	}
	
	
	public HashMap<Noeud, Arc> getMaps(){
		return maps;
	}
	
	
	
	public String affichage() {
		StringBuilder sb  = new StringBuilder();
		
		for(Noeud unNoeud : maps.keySet()) {
			sb.append(unNoeud.getNoeud() + " : ");
			sb.append(maps.get(unNoeud).getArc());
			sb.append("\n");
		}
		return sb.toString();
	}
}




























