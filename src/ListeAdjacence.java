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
	
	private ArrayList<Noeud> listAttack;
	private ArrayList<Noeud> listSeFaireAttack;
	
	private ArrayList<ArrayList<String>> listAdmissible;
	private ArrayList<ArrayList<String>> listPrefere;

	/**
	 * initialisation de maps, solutions
	 */
	public ListeAdjacence() {
		maps = new LinkedHashMap<Noeud, Arc>();
		solutions = new ArrayList<Noeud>();
		listAttack = new ArrayList<Noeud>();
		listSeFaireAttack = new ArrayList<Noeud>();
		listAdmissible = new ArrayList<ArrayList<String>>();
		listPrefere = new ArrayList<ArrayList<String>>();
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
		//System.out.println(nom + " n'existe pas");
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
			if (afficheInListContredit(solutions.get(i)) && ((afficheInListContradiction(solutions.get(i)))))
				tmp++; 
		}
		if (tmp == solutions.size()) {
			System.out.println("Solution admissible : " + afficheSolutions());
		}
	}

	public void verifSolution2() {
		if(testSolution2()) {
			System.out.println("Solution admissible : " + afficheSolutions());
		}
	}
	/**
	 * vérifie si tous les noeuds de solutions a au moins un argument contradictoire
	 * de solutions ou non,
	 * 
	 * @return true s'il n'existe pas de contradiction.s entre les différents noeuds
	 *         de solutions et il s'il n'existe pas un noeud qui lui contredit
	 * @return false s'il existe au moins une contradiction entre les différents
	 *         noeuds de solutions
	 */
	public boolean testSolution() {
		int tmp = 0;
		ArrayList<Noeud> tmp1 = null;
		ArrayList<Noeud> tmp2 =null;
		Noeud tmp3 = null;
		ArrayList<Noeud> tmpList = new ArrayList<Noeud>();
		//si la taille de la solution est 1, et qu'un arg lui contredit et lui il contredit personne, donc
		//c'est pas une solution
		if(solutions.size()==1) {
			
			Noeud tmpIn3 = solutions.get(0);
			ArrayList<Noeud> tmpIn1 = getListContradiction(solutions.get(0));
			ArrayList<Noeud> tmpIn2 = getListContredit(solutions.get(0));
			if(tmpIn1.size()== 0 && tmpIn2.size() >0 ) {
				tmp3 = tmpIn3;

				tmp1 = tmpIn1;

				tmp2 = tmpIn2;

				tmpList.add(tmp3);
				return false;
			
			}
			
		}

		for (int i = 0; i < solutions.size(); i++) {
			if (!inListContredit(solutions.get(i)) && ((inListContradiction(solutions.get(i))))) {
				tmp++;
			}
		}
		if (tmp == solutions.size()) {
			return true;
		}
		
		
		return false;

	}
	
	public void remplieListAttackEtSeFaireAttack() {
		for(int i=0; i< solutions.size();i++) {
			ArrayList<Noeud> listContredit = getListContredit(solutions.get(i));
			ArrayList<Noeud> listContradiction = getListContradiction(solutions.get(i));
			
			for(int j =0; j< listContradiction.size(); j++) {
			
				if(!listAttack.contains(listContradiction.get(j))) {
					listAttack.add(listContradiction.get(j));
				}
			}
			for(int j =0; j< listContredit.size(); j++) {
				if(!listSeFaireAttack.contains(listContredit.get(j))) {
					listSeFaireAttack.add(listContredit.get(j));
				}
			}
		}
	}
	
	public boolean testSolution2() {
		if(solutions.size() ==0) {
			return true;
		}
		remplieListAttackEtSeFaireAttack();
		
		for(int i =0; i< listAttack.size();i++) {
			if(solutions.contains(listAttack.get(i))) {
				return false;
			}
		}

		if(listAttack.containsAll(listSeFaireAttack)) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * vérifie si le noeud noeud existe comme clé dans maps
	 * 
	 * @param noeud
	 * @return true ou false correspondant à l'appartenance du noeud noeud comme clé
	 *         dans maps
	 */
	public boolean inSolution(Noeud noeud) {
		return maps.keySet().contains(noeud);
	}

	/*
	 * retourne une liste d'argument.s qui contredit noeud
	 * 
	 * @return la liste list de tous les arguments qui viennent contredire noeud
	 */
	public ArrayList<Noeud> getListContredit(Noeud noeud) {
		ArrayList<Noeud> list = new ArrayList<>();
		for (Noeud unNoeud : maps.keySet()) {
			if (maps.get(unNoeud).getArc().contains(noeud)) {
				list.add(unNoeud);
			}
		}
		return list;
	}

	public ArrayList<Noeud> getListContradiction(Noeud noeud) {
		return maps.get(noeud).getArc();
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
			list = getListContredit(unNoeud);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(noeud)) {
					System.out.println("L'argument " + noeud.getNoeud() + " contredit " + unNoeud.getNoeud());
					tmp = false;
				}
			}
		}
		return tmp;
	}
	//la raison pk non admissible
	public boolean afficheInListContradiction(Noeud noeud) {
		for (Noeud unNoeud : maps.keySet()) { // parcour tous les arguments du graphe
			ArrayList<Noeud> listContradiction = getListContradiction(unNoeud);
			// Un ensemble qui est égal a une contradiction d'un noeud n'est pas une
			// solution
			if (getSolutions().equals(listContradiction)) {
				System.out.println("L'argument " + unNoeud.getNoeud() +" contredit " + afficheSolutions());
				return false;
			}

			for (int j = 0; j < listContradiction.size();) {
				if (listContradiction.get(j).equals(solutions.get(0)))
					System.out.println("L'argument " + unNoeud.getNoeud() +" contredit " + solutions.get(0));
					return false;
			}

		}
		return true;
	}

	/**
	 * parcourt la liste solutions, trouve si noeud contredit un argument dans
	 * solutions rentré par utilisateur
	 * 
	 * @param noeud, le noeud qui contredit les noeuds dans l'ensemble E
	 */

	public boolean inListContredit(Noeud noeud) {
		ArrayList<Noeud> list = new ArrayList<>();

		
		for (Noeud unNoeud : solutions) {
			list = getListContredit(unNoeud);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(noeud) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	

	// verifier si le noeud à été contradit par un autre noeud dans le graphe
	public boolean inListContradiction(Noeud noeud) {
		
		for (Noeud unNoeud : maps.keySet()) { // parcour tous les arguments du graphe
			ArrayList<Noeud> listContradiction = getListContradiction(unNoeud);
			// Un ensemble qui est égal a une contradiction d'un noeud n'est pas une
			// solution
	
			//si a contradit b et b contradit a, alors a et b sont solution
			for(int i =0; i< solutions.size();i++) {
				if(doubleContradiction(solutions.get(i))) {
					return true;
				
				}
			}
			//si la solution est egal a une contradiction d'un argument alors la solution est fausse
			if (getSolutions().equals(listContradiction)) {
				return false;
			}

		}
		return true;
	}
	
	//retourne vrai si le noeud contredit un arg et arg lui contredit aussi
	public boolean doubleContradiction(Noeud noeud) {
		ArrayList<Noeud> listContradiction= getListContradiction(noeud);
		for(Noeud unNoeud : listContradiction) {
			
			ArrayList<Noeud> tmp = getListContradiction(unNoeud);
			for(int i =0;i< tmp.size();i++) {
				if(tmp.get(i).equals(noeud)) {
					return true;
				}	
			}
		}
		return false;
		
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

	public ArrayList<Noeud> getSolutions() {
		return solutions;
	}

	public void afficheSolution() {
		for (int i = 0; i < solutions.size(); i++) {
			System.out.println(solutions.get(i));
		}
	}

	// Phase 2: les methodes suivantes sont utilisés dans la phase 2

	// methode: recupere A et il fait map.argument(A)

	/**
	 * méthode qui prend une chaine et génère un graphe
	 * 
	 * @param chaine un argument en string
	 */

	public void scanArgument(String chaine) {

		if (chaine.startsWith("argument(")) {
			StringBuilder sb = new StringBuilder();
			String[] tab = chaine.split("[\\(\\)]");
			sb.append(tab[1]);
			argument(new Noeud(sb.toString()));
		} 
	}

	/**
	 * méthode qui va séparé la chaine en trouvant 2 arguments qui sont séparés par
	 * une virgule et crée la contradiction
	 * 
	 * @param chaine un string
	 */
	public void scanContradiction(String chaine) {
		String[] tmp = chaine.split("[\\(\\)]");
		String[] tab = tmp[1].split(",");
		contradiction(stringToNoeud(tab[0]), stringToNoeud(tab[1]));
	}

	public int getSizeMatrice() {
		return maps.size();
	}

	public String[] getAllArgument() {
		String[] tab = new String[maps.keySet().size()];
		int index = 0;
		for (Noeud unNoeud : maps.keySet()) {
			tab[index++] = unNoeud.getNoeud();
		}
		return tab;
	}

	public void BuildCombinationPossible(ArrayList<ArrayList<String>> map, ArrayList<String> sousMap,
			String[] Input_Array, String[] tabVide, int debut, int fin, int indice, int tailleDeLaList) {

		if (indice == tailleDeLaList) {
			ArrayList<String> newMapVide = new ArrayList<>();
			for (int x = 0; x < tailleDeLaList; x++) {
				newMapVide.add(tabVide[x]);
			}
			sousMap = newMapVide;
			map.add(sousMap);
			return;
		}

		for (int y = debut; y <= fin && fin - y + 1 >= tailleDeLaList - indice; y++) {
			tabVide[indice] = Input_Array[y];
			BuildCombinationPossible(map, sousMap, Input_Array, tabVide, y + 1, fin, indice + 1, tailleDeLaList);
		}
	}

	public void Print_Combination(ArrayList<ArrayList<String>> mapo, ArrayList<String> map, String[] Input_Array, int n,
			int r) {
		String[] Empty_Array = new String[r];
		BuildCombinationPossible(mapo, map, Input_Array, Empty_Array, 0, n - 1, 0, r);
	}

	public ArrayList<ArrayList<String>> getAllCombinaison() {
		String[] tab = getAllArgument();
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		ArrayList<String> mapo = new ArrayList<String>();
		// On ajoute la solution vide
		mapo.add("");
		map.add(mapo);
		mapo.clear();

		int n = tab.length;
		for (int i = 1; i <= tab.length; i++) {
			Print_Combination(map, mapo, tab, n, i);
		}
		return map;
	}

	
	public void addSolutionAdmissible(String noeud) {
		Noeud tmp = stringToNoeud(noeud);
		solutions.add(tmp);

	}

	public void getAllAdmissible() {
		ArrayList<ArrayList<String>> listAllCombinaison = getAllCombinaison();
		for (int i = 0; i < listAllCombinaison.size(); i++) {
			solutions.clear();
			listAttack.clear();
			listSeFaireAttack.clear();
			for (int j = 0; j < listAllCombinaison.get(i).size(); j++) {
				addSolutionAdmissible(listAllCombinaison.get(i).get(j));
			}
			if (testSolution2()) {
				//System.out.println(listAllCombinaison.get(i));
				listAdmissible.add(listAllCombinaison.get(i));			
			}
		}
	}

	public ArrayList<ArrayList<String>> getListAdmissible() {
		return listAdmissible;
	}
	
	private static int compteur =0;
	public void afficheListAdmissible() {
		getAllAdmissible();
		String str;
		if(listAdmissible.size() >0) {
			if(compteur < listAdmissible.size()) {
				str = String.join(",", listAdmissible.get(compteur++));
				System.out.println(str);
			}else {
				compteur = 0;
				str = String.join(",", listAdmissible.get(compteur++));
				System.out.println(str);
			}
		}
	}
	
	public boolean inclu(ArrayList<String> unAdmissible) {
		for(int i =0; i< listAdmissible.size(); i++) {
			if(listAdmissible.get(i).containsAll(unAdmissible) && listAdmissible.get(i) != unAdmissible) {
				return true;
			}
		}
		return false;
	}
	
	
	public void getAllPrefere() {
		getAllAdmissible();

		//si list admissble possede que la solution null
		if(listAdmissible.size() == 1) {
			listPrefere.add(listAdmissible.get(0));
		}else {
			listAdmissible.remove(0);
			for(ArrayList<String> unAdmissible : listAdmissible) {
				if(!inclu(unAdmissible)) {
					//System.out.println("\n"+unAdmissible);
					listPrefere.add(unAdmissible); 
				}
			}
		}
	}
	
	public ArrayList<ArrayList<String>> getListPrefere() {
		return listPrefere;
	}
	
	
	public void afficheListPrefere() {
		
		getAllPrefere();
		String str= null;
		if(listPrefere.size() >0) {
			if(compteur < listPrefere.size()) {
				str = String.join(",", listPrefere.get(compteur++));
				System.out.println(str);
			}else {
				compteur = 0;
				str = String.join(",", listPrefere.get(compteur++));
				System.out.println(listPrefere.get(compteur++));
			}
		}	
	}

}
