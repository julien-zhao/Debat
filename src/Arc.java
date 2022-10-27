import java.util.ArrayList;
import java.util.List;

public class Arc {
	private List<Noeud> listNoeud;
	
	
	public Arc() {
		listNoeud = new ArrayList<Noeud>();
	}
	public void add(Noeud unNoeud) {
		listNoeud.add(unNoeud);
	}
	
	public String getArc() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<listNoeud.size();i++){		
			sb.append(listNoeud.get(i).getNoeud() +" ");
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * A : [{A,C}, {A,B}]
	 * B : []
	 *
	 */
}
