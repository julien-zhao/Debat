import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * La class listeAdajacence correspond aux associations entre les noeuds et
 * leurs voisins
 * 
 * @author julien et Victor
 *
 */
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
	 * une liste dont le noeud contredit
	 */
	private ArrayList<Noeud> listAttack;

	/**
	 * une liste qui contredit le noeud
	 */
	private ArrayList<Noeud> listSeFaireAttack;

	/**
	 * une liste de liste qui contient les noeuds admissible
	 */
	private ArrayList<ArrayList<String>> listAdmissible;
	/**
	 * une liste de liste qui contient les noeuds preferées
	 */
	private ArrayList<ArrayList<String>> listPrefere;

	/**
	 * un compteur qui sert a changer la solution admissible ou preferee
	 */
	public static int compteur = 0;

	/**
	 * initialisation de maps, solutions, listAttack, listSeFaireAttack,
	 * listAdmissible, listPrefere
	 */
	public ListeAdjacence() {
		maps = new LinkedHashMap<Noeud, Arc>();
		solutions = new ArrayList<Noeud>();
		listAttack = new ArrayList<Noeud>();
		listSeFaireAttack = new ArrayList<Noeud>();
		listAdmissible = new ArrayList<ArrayList<String>>();
		listPrefere = new ArrayList<ArrayList<String>>();
	}

	public ListeAdjacence(LinkedHashMap<Noeud,Arc> maps) {
		this.maps = maps;
		solutions = new ArrayList<Noeud>();
		listAttack = new ArrayList<Noeud>();
		listSeFaireAttack = new ArrayList<Noeud>();
		listAdmissible = new ArrayList<ArrayList<String>>();
		listPrefere = new ArrayList<ArrayList<String>>();
		
	}
	
	/**
	 * méthode qui compare un String au nom du Noeud qui sont dans la maps
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
	 * @return vrai si on a bien ajouté le noeud dans maps faux si le noeud existe
	 *         déjà dans maps
	 */
	public boolean addArgument(Noeud noeud) {
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
	public void addContradiction(Noeud arg1, Noeud arg2) {
		if (!maps.get(arg1).getArc().contains(arg2) && (!arg1.equals(arg2))) {
			maps.get(arg1).add(arg2);
		}
	}

	/**
	 * méthode qui supprime un Noeud dans maps
	 * 
	 * @param arg
	 * @return vrai s'il a bien supprimé dans les clés et values de maps faux si
	 *         l'argument qu'on veut supprimer n'existe pas
	 */
	public boolean removeArg(Noeud arg) {
		Noeud tmp = null;
		for (Noeud unNoeud : maps.keySet()) {
			if (unNoeud.getNoeud().equals(arg.getNoeud())) {
				tmp = unNoeud;
			}
		}
		maps.remove(tmp);

		for (Noeud unNoeud1 : maps.keySet()) {
			maps.get(unNoeud1).removeNoeudDeArc(tmp);
		}
		if (tmp == null) {
			System.out.println(("L'argument que vous voulez retirer n'existe pas "));
			return false;
		}
		return true;
	}

	/**
	 * renvoie la maps
	 * 
	 * @return une HashMap
	 */
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

	/**
	 * affiche le graphe dans la console
	 */
	public void afficheGraphe() {
		System.out.print("Affichage du graphe : \n" + affichage());
	}

	/**************************
	 * Methode pour solutions
	 ************************************/

	/**
	 * methode qui ajoute un String dans solutions 
	 * si la solution contient deja le noeud, il retourne 3
	 * si la le noeud n'existe pas de la graphe, il retourne 2
	 * si il a reussi a l'ajouté, il retourne 1
	 * 
	 * @param noeud
	 * @return int
	 */
	public int addSolution(String noeud) {
		Noeud tmp = stringToNoeud(noeud);

		if (solutions.contains(tmp)) {
			return 3;
		} else if (tmp == null) {
			return 2;
		} else {
			solutions.add(tmp);
			return 1;
		}
	}

	/**
	 * méthode qui supprime un noeud dans solutions
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
	 * en fonction de la solution, la methode remplit la listeAttack et
	 * listeSeFaireAttack
	 */
	private void remplieListAttackEtSeFaireAttack() {
		for (int i = 0; i < solutions.size(); i++) {
			ArrayList<Noeud> listContredit = getListContredit(solutions.get(i));
			ArrayList<Noeud> listContradiction = getListContradiction(solutions.get(i));

			for (int j = 0; j < listContradiction.size(); j++) {

				if (!listAttack.contains(listContradiction.get(j))) {
					listAttack.add(listContradiction.get(j));
				}
			}
			for (int j = 0; j < listContredit.size(); j++) {
				if (!listSeFaireAttack.contains(listContredit.get(j))) {
					listSeFaireAttack.add(listContredit.get(j));
				}
			}
		}
	}


	/**
	 * Teste la solution
	 * 
	 * @return si la solutions est admissible, elle retourne true sinon false
	 */
	protected boolean testSiSolutionAdmissible() {
		if (solutions.size() == 0) {
			return true;
		}
		remplieListAttackEtSeFaireAttack();

		for (int i = 0; i < listAttack.size(); i++) {
			if (solutions.contains(listAttack.get(i))) {
				return false;
			}
		}
		if (listAttack.containsAll(listSeFaireAttack)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Teste la solution pour la phase 1 en affichant la raison si la solution n'est
	 * pas admissible
	 * 
	 * @return vrai si solution admissible sinon false
	 */
	public boolean testSolutionForPhase1() {
		if (solutions.size() == 0) {
			return true;
		}
		remplieListAttackEtSeFaireAttack();
		for (int i = 0; i < listAttack.size(); i++) {
			if (solutions.contains(listAttack.get(i))) {
				for (Noeud unNoeud : maps.keySet()) {
					if (getListContradiction(unNoeud).contains(listAttack.get(i))) {
						System.out.println(listAttack.get(i).getNoeud() + " se fait contredire par " + unNoeud.getNoeud());
					}
				}
				return false;
			}
		}
		if (listAttack.containsAll(listSeFaireAttack)) {
			return true;
		} else {
			System.out.println(afficheSolutions() + " ne se defend pas");
			return false;
		}
	}

	/*
	 * retourne une liste d'argument.s qui contredit noeud
	 * 
	 * @return la liste list de tous les arguments qui viennent contredire noeud
	 */
	private ArrayList<Noeud> getListContredit(Noeud noeud) {
		ArrayList<Noeud> list = new ArrayList<>();
		for (Noeud unNoeud : maps.keySet()) {
			if (maps.get(unNoeud).getArc().contains(noeud)) {
				list.add(unNoeud);
			}
		}
		return list;
	}

	/**
	 * retourne une liste de contradiction d'un noeud
	 * 
	 * @param noeud
	 * @return une liste
	 */
	private ArrayList<Noeud> getListContradiction(Noeud noeud) {
		return maps.get(noeud).getArc();
	}

	/**
	 * affiche l'ensemble des solutions solutions rentré par l'utilisateur
	 */
	public String afficheSolutions() {
		StringBuilder sb = new StringBuilder("[");
		int i = 0;
		if (solutions.size() == 0) {
			return "[]";
		}
		for (i = 0; i < solutions.size() - 1; i++) {
			sb.append(solutions.get(i).getNoeud() + ",");
		}
		sb.append(solutions.get(i).getNoeud());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * retourne la solution
	 * 
	 * @return une liste de noeud
	 */
	public ArrayList<Noeud> getSolutions() {
		return solutions;
	}

	// Phase 2: les methodes suivantes sont utilisés dans la phase 2

	/**
	 * méthode qui prend une chaine et génère un graphe
	 * 
	 * @param chaine un argument en string
	 */
	public void scanArgument(String chaine) {

		StringBuilder sb = new StringBuilder();
		String[] tab = chaine.split("[\\(\\)]");

		sb.append(tab[1]);
		addArgument(new Noeud(sb.toString()));
	}

	/**
	 * méthode qui va séparer la chaine en trouvant 2 arguments qui sont séparés par
	 * une virgule et créé la contradiction
	 * 
	 * @param chaine un string
	 * @return un booleen true si argument 1 et argument 2 sont définis sinon faux
	 */
	public boolean scanContradiction(String chaine) {
		String[] tmp = chaine.split("[\\(\\)]");
		String[] tab = tmp[1].split(",");
		if (stringCompareNoeud(tab[0]) && stringCompareNoeud(tab[1])) {
			addContradiction(stringToNoeud(tab[0]), stringToNoeud(tab[1]));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * retourne la taille du graphe
	 * 
	 * @return int
	 */
	public int getSizeMatrice() {
		return maps.size();
	}

	/**
	 * retourne tous les arguments du graphe
	 * 
	 * @return tableau de string
	 */
	public String[] getAllArgument() {
		String[] tab = new String[maps.keySet().size()];
		int index = 0;
		for (Noeud unNoeud : maps.keySet()) {
			tab[index++] = unNoeud.getNoeud();
		}
		return tab;
	}

	/*Source : https://www.delftstack.com/fr/howto/java/java-combinations/
	 * Méthode qui remplit la liste map des solutions possibles sans répétition
	 *
	 * @param allSolution : prend une liste de liste de String qui va le remplir
	 * 
	 * @param sousMap : liste de String qu'on va stocker dans map
	 * 
	 * @param Input_Array : tableau initial avec les solutions à l'intérieur
	 * 
	 * @param tabVide: le nouveau sous tableau construit à partir du tableau initial
	 * 
	 * @param debut : indice de début de parcours
	 * 
	 * @param fin : indice de fin de parcours
	 * 
	 * @param indice: la taille du tableau initial Input_Array
	 * 
	 * @param taideDeLaList: taille de la liste
	 *
	 */
	private void BuildCombinationPossible(ArrayList<ArrayList<String>> allSolution, ArrayList<String> sousMap,
			String[] Input_Array, String[] tabVide, int debut, int fin, int indice, int tailleDeLaList) {

		if (indice == tailleDeLaList) {
			ArrayList<String> newMapVide = new ArrayList<>();
			for (int x = 0; x < tailleDeLaList; x++) {
				newMapVide.add(tabVide[x]);
			}
			sousMap = newMapVide;
			allSolution.add(sousMap);
			return;
		}

		for (int y = debut; y <= fin && fin - y + 1 >= tailleDeLaList - indice; y++) {
			tabVide[indice] = Input_Array[y];
			BuildCombinationPossible(allSolution, sousMap, Input_Array, tabVide, y + 1, fin, indice + 1,
					tailleDeLaList);
		}
	}

	/**
	 * méthode qui construit toute les combinaisons possible
	 * 
	 * @param allSolution la liste des solutions admissibles
	 * @param sousMap
	 * @param Input_Array tableau initial avec les solutions à l'intérieur
	 * @param n taille du nouveau tableau
	 * @param r taille de la liste
	 */
	private void BuildOneCombinaison(ArrayList<ArrayList<String>> allSolution, ArrayList<String> sousMap,
			String[] Input_Array, int n, int r) {
		String[] Empty_Array = new String[r];
		BuildCombinationPossible(allSolution, sousMap, Input_Array, Empty_Array, 0, n - 1, 0, r);
	}

	/**
	 * méthode qui renvoie une liste de liste qui contient toute les combinaisons
	 * possible en ajoutant la solution vide
	 * 
	 * @return une liste de liste
	 */
	protected ArrayList<ArrayList<String>> createAllCombinaison() {
		String[] tab = getAllArgument();
		ArrayList<ArrayList<String>> allSolution = new ArrayList<ArrayList<String>>();
		ArrayList<String> uneSolution = new ArrayList<String>();
		uneSolution.add("");
		allSolution.add(uneSolution);
		uneSolution.clear();

		int n = tab.length;
		for (int i = 1; i <= tab.length; i++) {
			BuildOneCombinaison(allSolution, uneSolution, tab, n, i);
		}

		return allSolution;
	}

	/**
	 * méthode qui prend un string, le convertie en Noeud et le rajoute dans solution
	 * 
	 * @param noeud un string
	 */
	private void addSolutionAdmissible(String noeud) {
		Noeud tmp = stringToNoeud(noeud);
		solutions.add(tmp);
	}

	/**
	 * creation de liste admissible
	 */
	protected void createListAdmissible() {
		ArrayList<ArrayList<String>> listAllCombinaison = createAllCombinaison();

		for (int i = 0; i < listAllCombinaison.size(); i++) {
			solutions.clear();
			listAttack.clear();
			listSeFaireAttack.clear();
			for (int j = 0; j < listAllCombinaison.get(i).size(); j++) {
				addSolutionAdmissible(listAllCombinaison.get(i).get(j));
			}
			if (testSiSolutionAdmissible()) {
				listAdmissible.add(listAllCombinaison.get(i));
			}
		}
	}

	/**
	 * retourne une liste admissible
	 * 
	 * @return une liste
	 */
	public ArrayList<ArrayList<String>> getListAdmissible() {
		return listAdmissible;
	}

	/**
	 * affiche une solution admissible, si pas de contradiction, on retourne la
	 * solution vide
	 * 
	 * @return un string
	 */
	public String afficheUneAdmissible() {
		if (listAdmissible.size() == 0) {
			createListAdmissible();
		}
		String str;
		if (listAdmissible.size() > 0) {

			if (compteur < listAdmissible.size()) {
				str = String.join(",", listAdmissible.get(compteur++));
				return str;

			} else {
				compteur = 0;
				str = String.join(",", listAdmissible.get(compteur++));
				return str;
			}
		}
		return "";
	}

	/*
	 * verifie si une liste admissible est inclue dans une autre liste admissible
	 * 
	 * @param unAdmissible
	 * @return true si il est inclu, false sinon
	 */
	protected boolean inclu(ArrayList<String> unAdmissible) {
		for (int i = 0; i < listAdmissible.size(); i++) {
			if (listAdmissible.get(i).containsAll(unAdmissible) && listAdmissible.get(i) != unAdmissible) {
				return true;
			}
		}
		return false;
	}

	/**
	 * creation d'une liste preferée, si dans la solution admissible il y a que la solution vide,
	 * la solution préférée contiendra uniquement la solution vide.
	 */
	public void createListPrefere() {
		if (listAdmissible.size() == 0) {
			createListAdmissible();
		}
		if (listAdmissible.size() == 1) {
			listPrefere.add(listAdmissible.get(0));
		} else {
			for (ArrayList<String> unAdmissible : listAdmissible) {
				if (!inclu(unAdmissible)) {
					listPrefere.add(unAdmissible);
				}
			}
		}
	}

	/**
	 * retourne la liste prefeée
	 * 
	 * @return une liste
	 */
	public ArrayList<ArrayList<String>> getListPrefere() {
		return listPrefere;
	}

	/**
	 * affiche une des solutions préférées
	 * 
	 * @return un string
	 */
	public String afficheUnePrefere() {
		if (listPrefere.size() == 0) {
			createListPrefere();
		}
		String str = null;
		if (listPrefere.size() > 0) {
			if (compteur < listPrefere.size()) {
				str = String.join(",", listPrefere.get(compteur++));
				return str;
			} else {
				compteur = 0;
				str = String.join(",", listPrefere.get(compteur++));
				return str;
			}
		}
		return "";
	}

	/**
	 * affiche toutes les solutions preferées
	 */
	public void affichePrefere() {
		if (listPrefere.size() == 0) {
			createListPrefere();
		}
		System.out.println("\nSolutions preferées : ");
		for (int i = 0; i < listPrefere.size(); i++) {
			System.out.println(listPrefere.get(i));
		}
	}

	/**
	 * affiche toutes les solutions admissibles
	 */
	public void afficheAdmissible() {
		if (listAdmissible.size() == 0) {
			createListAdmissible();
		}
		System.out.println("\nSolutions admissibles : ");
		for (int i = 0; i < listAdmissible.size(); i++) {
			System.out.println(listAdmissible.get(i));
		}
	}
}
