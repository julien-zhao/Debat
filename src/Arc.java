import java.util.ArrayList;

public class Arc {
	/**
	 * La liste des noeuds de l'arc
	 */
	private ArrayList<Noeud> listNoeud;

	/**
	 * Creer une liste vide de noeud
	 */
	public Arc() {
		listNoeud = new ArrayList<Noeud>();
	}
 
	/**
	 * ajoute un noeud
	 * 
	 * @param unNoeud le noeud à ajouter
	 */
	public void add(Noeud unNoeud) {
		listNoeud.add(unNoeud);
	}

	/**
	 * affiche tous les noeuds de la liste listNoeud
	 */
	public String afficheArc() {

		StringBuilder sb = new StringBuilder("[");
		int i = 0;
		for (i = 0; i < listNoeud.size(); i++) {

			sb.append(listNoeud.get(i).getNoeud());
			if (i < listNoeud.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public ArrayList<Noeud> getArc() {
		return listNoeud;
	}

	/**
	 * Enleve un noeud de listNoeud
	 */
	public void removeNoeudDeArc(Noeud unNoeud) {
		for (int i = 0; i < listNoeud.size(); i++)
			if (listNoeud.get(i).equals(unNoeud)) {
				listNoeud.remove(i);
			}
	}
}