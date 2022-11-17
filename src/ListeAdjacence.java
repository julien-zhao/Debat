import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ListeAdjacence {

	/**
	 * un HashMap de Noeud et ses arc
	 */
	private LinkedHashMap<Noeud, Arc> maps;
	/**
	 * une list de noeud
	 */
	private ArrayList<Noeud> solutions;

	/**
	 * un HashMap de integer et des solutions
	 */
	private LinkedHashMap<Integer, ArrayList<Noeud>> mapForPhase2;
	
	/**
	 * initialisation de maps, solutions et mapForPhase2 à vide
	 */
	public ListeAdjacence() {
		maps = new LinkedHashMap<Noeud, Arc>();	
		solutions = new ArrayList<Noeud>();
		mapForPhase2 = new LinkedHashMap<Integer,ArrayList<Noeud>>();
	}

	/**
	 * méthode qui compare un String au nom du Noeud
	 * 
	 * @param nom
	 * @return vrai si c'est identique, faux sinon
	 */
	public boolean stringCompareNoeud(String nom) {

		for (Noeud unNoeud : maps.keySet()) {
			if (unNoeud.getNoeud().equals(nom)) {
				return true;
			}
		}
		System.out.println("Nom incorrect : " + nom);
		return false;
	}

	/**
	 * méthode qui verifie si un String correspond à un Noeud
	 * 
	 * @param nom
	 * @return null si le String correspond à aucun Noeud, sinon il retourne le
	 *         Noeud qui correspond au String
	 */
	public Noeud stringToNoeud(String nom) {
		for (Noeud unNoeud : maps.keySet()) {
			if (unNoeud.getNoeud().equals(nom)) {
				return unNoeud;
			}
		}
		return null;
	}

	/**
	 * méthode qui ajoute un Noeud dans la maps
	 * 
	 * @param noeud
	 * @return vrai si on à bien ajouté le noeud dans maps faux si le noeud existe
	 *         déjà dans maps
	 */
	public boolean argument(Noeud noeud) {
		boolean identique = true;
		for (Noeud unNoeud : maps.keySet()) {
			if (noeud.getNoeud().equals(unNoeud.getNoeud())) {
				identique = false;
			}
		}
		if (identique) {
			maps.put(noeud, new Arc());
		}

		return identique;
	}

	/**
	 * méthode qui ajoute les contradictions en verifiant si la contradiction
	 * n'existe pas déjà
	 * 
	 * @param arg1
	 * @param arg2
	 * 
	 */
	public void contradiction(Noeud arg1, Noeud arg2) {

		// si arg2 n'est pas déjà dans arg1 (redondance) et que arg1 et diffrent que
		// arg2 (contradiction lui même)

		
		
		if (!maps.get(arg1).getArc().contains(arg2) && (!arg1.equals(arg2))) {
			maps.get(arg1).add(arg2);
		}
	}
 
	/**
	 * méthode qui supprime un Noeud dans maps
	 * 
	 * @param arg
	 * @return vrai s'il à bien supprimé dans les clés et values de maps faux si
	 *         l'argument qu'on veux supprimer n'existe pas
	 */
	public boolean removeArg(Noeud arg) {
		Noeud tmp = null;
		for (Noeud unNoeud : maps.keySet()) {
			if (unNoeud.getNoeud().equals(arg.getNoeud())) {
				tmp = unNoeud;
			}
		}
		maps.remove(tmp); // on a enlevé la cle arg de la maps

		// on a enlevé la cle arg qui est dans les arc
		for (Noeud unNoeud1 : maps.keySet()) {
			maps.get(unNoeud1).removeNoeudDeArc(tmp);
		}

		if (tmp == null) {
			System.out.println(("L'argument que vous voulez retirer n'existe pas "));
			return false;
		}
		return true;
	}

	public LinkedHashMap<Noeud, Arc> getMaps() {
		return maps;
	}

	/**
	 * methode qui affiche l'argument et ses contradictions
	 * 
	 * @return affiche la maps
	 */
	public String affichage() {
		StringBuilder sb = new StringBuilder();
		for (Noeud unNoeud : maps.keySet()) {
			sb.append(unNoeud.getNoeud() + " : ");
			sb.append(maps.get(unNoeud).afficheArc());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**************************
	 * Methode pour solutions
	 ************************************/

	/**
	 * methode qui ajoute un String dans solutions en tenant compte s'il existe déjà
	 * et si il existe dans le graphe
	 * 
	 * @param noeud
	 */
	public void addSolution(String noeud) {
		Noeud tmp = stringToNoeud(noeud);

		// soit solutions contient tmp
		if (solutions.contains(tmp)) {
			System.out.println("L'argument existe déjà");

		}
		// soit tmp est null car il exiiste pas dans le graphe, donc pas besoin de
		// l'ajouter
		else if (tmp == null) {
			System.out.println("L'argument " + noeud + " n'existe pas dans le graphe");

		}
		// soit n est pas encore present dans solution et il existe dans graphe donc on
		// ajoute
		else {
			System.out.println("L'élément " + noeud + " a été ajouté.");
			solutions.add(tmp);
		}
	}

	/**
	 * méthode qui supprimer un noeud dans solutions
	 * 
	 * @param noeud
	 * @return vrai s'il est bien supprimer faux sinon
	 */
	public boolean removeSolution(Noeud noeud) {

		Noeud tmp = null;
		for (Noeud unNoeud : solutions) {
			if (unNoeud.getNoeud().equals(noeud.getNoeud())) {
				tmp = unNoeud;
			}
		}
		return solutions.remove(tmp);
	}

	/**
	 * vérifie si tous les noeuds de solutions a au moins un argument contradictoire
	 * de solutions ou non, s'il n'existe pas de contradiction.s entre les
	 * différents noeuds de solutions affiche les solutions admissibles
	 */
	public void verifSolution() {
		int tmp = 0;
		for (int i = 0; i < solutions.size(); i++) {
			if (afficheInListContredit(solutions.get(i)))
				tmp++;
		}
		if (tmp == solutions.size()) {
			System.out.println("Solution admissible : " + afficheSolutions());
		}
	}

	/**
	 * vérifie si tous les noeuds de solutions a au moins un argument contradictoire
	 * de solutions ou non,
	 * 
	 * @return true s'il n'existe pas de contradiction.s entre les différents noeuds
	 *         de solutions
	 * @return false s'il existe au moins une contradiction entre les différents
	 *         noeuds de solutions
	 */
	public boolean testSolution() {
		int tmp = 0;
		for (int i = 0; i < solutions.size(); i++) {
			if (inListContredit(solutions.get(i))) {
				tmp++;
			}
		}
		if (tmp == solutions.size()) {
			return true;
		}
		return false;

	}


	/**
	 * vérifie si le noeud noeud existe comme clé dans maps
	 * @param noeud
	 * @return true ou false correspondant à l'appartenance du noeud noeud comme clé
	 * dans maps
	 */
	public boolean inSolution(Noeud noeud) {
		return maps.keySet().contains(noeud);
	}

	/*
	 * retourne une liste d'argument.s qui contredit noeud
	 * 
	 * @return la liste list de tous les arguments qui viennent contredire noeud
	 */
	public ArrayList<Noeud> listContredit(Noeud noeud) {
		ArrayList<Noeud> list = new ArrayList<>();
		for (Noeud unNoeud : maps.keySet()) {
			if (maps.get(unNoeud).getArc().contains(noeud)) {
				list.add(unNoeud);
			}
		}
		return list;
	}

	/**
	 * parcourt la liste solutions, trouve si noeud contredit un argument dans
	 * solutions rentré par utilisateur et affiche par quel argument il contredit
	 * 
	 * @param noeud, le noeud qui contredit les noeuds dans l'ensemble E
	 */
	public boolean afficheInListContredit(Noeud noeud) {
		ArrayList<Noeud> list = new ArrayList<>();
		boolean tmp = true;
		for (Noeud unNoeud : solutions) {
			list = listContredit(unNoeud);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(noeud)) {
					System.out.println("L'argument " + noeud.getNoeud() + " contredit " + unNoeud.getNoeud());
					tmp = false;
				}
			}
		}
		return tmp;
	}

	/**
	 * parcourt la liste solutions, trouve si noeud contredit un argument dans
	 * solutions rentré par utilisateur
	 * 
	 * @param noeud, le noeud qui contredit les noeuds dans l'ensemble E
	 */

	public boolean inListContredit(Noeud noeud) {
		ArrayList<Noeud> list = new ArrayList<>();
		boolean tmp = true;
		for (Noeud unNoeud : solutions) {
			list = listContredit(unNoeud);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(noeud)) {
					tmp = false;
				}
			}
		}
		return tmp;
	}

	/**
	 * affiche l'ensemble des solutions solutions rentré par l'utilisateur
	 */
	public String afficheSolutions() {
		StringBuilder sb = new StringBuilder("[");
		int i = 0;
		if(solutions.size() == 0) {
			return "[]";
		}
		for (i = 0; i < solutions.size() - 1; i++) {
			sb.append(solutions.get(i).getNoeud() + ",");
		}
		sb.append(solutions.get(i).getNoeud());
		sb.append("]");
		return sb.toString();

	}

	public ArrayList<Noeud> getSolutions() {
		return solutions;
	}
	
	
	
	
	
	//Phase 2: les methodes suivantes sont utilisés dans la phase 2

	
	//methode: recupere A et il fait map.argument(A)
	
	/**
	 * méthode qui prend une chaine et génère un graphe
	 * @param chaine un argument en string
	 */
	public void scanArgument(String chaine) {
		if(chaine.startsWith("argument(") ){
			StringBuilder sb = new StringBuilder();
			String [] tab = chaine.split("[\\(\\)]");
			sb.append(tab[1]);	
			argument(new Noeud(sb.toString()));
		}else {
			System.out.println("La chaine "+ chaine +" est mal formaté" );
		}		
	}
	
	/**
	 * méthode qui va séparé la chaine en trouvant 2 arguments qui sont séparés
	 * par une virgule et crée la contradiction
	 * 
	 * @param chaine un string
	 */
	public void scanContradiction(String chaine) {
		String [] tmp = chaine.split("[\\(\\)]");	 
		String []tab = tmp[1].split(",");
		contradiction(stringToNoeud(tab[0]), stringToNoeud(tab[1]));
	}
	
	
	static ArrayList<String> list = new ArrayList<String>();

	

	
	
	public ArrayList<Noeud> convertKeyToList() {
		ArrayList<Noeud> list = new ArrayList<>();
		for(Noeud unNoeud : maps.keySet()) {
			list.add(unNoeud);
		}

		return list;
	}
	
	
	
	
	public void chercheAdmissible() {
		ArrayList<Noeud> list = convertKeyToList();
		StringBuilder sb = new StringBuilder();
		
		
		for(int i =0; i< list.size() ;i++) {
			
		}
		
		
		/** pour ABC il y a 7 solutions(combinaisons) possible
		 * [A,B,C]
		 *[A,B] || [A,C] || [B,C]
		 * [A] || [B] || [C]
		 * 
		 * ABC
		 * AB || 
		 */


		
	}
	
	
	
	
	
	
	
	
	
	
}
