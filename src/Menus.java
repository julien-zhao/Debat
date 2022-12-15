import java.util.Scanner;

public class Menus {
	/**
	 * Le menu1 nous demande le nombre d'argument n et il crée les arguments nommés A0 jusqu'a n-1 argument.
	 * Ensuite, le menu nous demande de faire tous les contradictions en vérifiant si l'argument existe ou non
	 * @param sc un scanner
	 * @param nbArg le nombre d'arguement
	 * @param maps 
	 */
	public static void menu1(Scanner sc, int nbArg, ListeAdjacence maps) {
		Scanner scString = new Scanner(System.in);
		int choix = -1;
		for (int i = 1; i <= nbArg; i++) {
			Noeud unArg = new Noeud("A" + i);
			maps.addArgument(unArg);
		}

		do {
			System.out.println("*******************Menu1*********************");
			System.out.println("1) ajouter une contradiction;");
			System.out.println("2) fin. ");
			System.out.println("*********************************************\n");
			choix = Utile.lireEntier(sc, "Votre choix ?");
			if (choix == 1) {
				String arg1 = null;
				String arg2 = null;

				arg1 = Utile.lireString(scString, "A quel argument vous voulez ajouter une contradiction?");
				arg2 = Utile.lireString(scString, "Nom de l'argument contradictoire ");
				if (!maps.stringCompareNoeud(arg1) && !maps.stringCompareNoeud(arg2)) {
					System.out.println(arg1 + " " + arg2 + " n'existent pas");
				} else {
					if (!maps.stringCompareNoeud(arg1)) {
						System.out.println(arg1 + " n'existe pas");
					} else if (!maps.stringCompareNoeud(arg2)) {
						System.out.println(arg2 + " n'existe pas");
					}
				}
				if (maps.stringCompareNoeud(arg1) && maps.stringCompareNoeud(arg2)) {
					maps.addContradiction(maps.stringToNoeud(arg1), maps.stringToNoeud(arg2));
				}

			}
			if (choix != 1 && choix != 2) {
				System.out.println("Le choix est incorrect, veuillez taper 1 ou 2");
			}
		} while (choix != 2);

		System.out.println("Argument après les contradictions : \n" + maps.affichage());

		if (choix == 2) {
			menu2(sc, maps);
		}
		scString.close();
	}
	
	/**
	 * Le menu2 compléte le menu1 avec ajouter et retirer des arguments afin de verifier si la solution est bonne
	 * @param sc un scanner
	 * @param maps
	 */
	public static void menu2(Scanner sc, ListeAdjacence maps) {
		int choix;
		String chaine;
		Scanner scString = new Scanner(System.in);
		do {
			System.out.println("*******************Menu2*********************");
			System.out.println("1) ajouter un argument;");
			System.out.println("2) retirer un argument;");
			System.out.println("3) vérifier la solution;");
			System.out.println("4) fin;");
			System.out.println("*********************************************");

			choix = Utile.lireEntier(sc, "Votre choix ? ");

			switch (choix) {
			case 1:
				chaine = Utile.lireString(scString, "Le nom de l'argument");
				
				if(maps.addSolution(chaine) == 1) {
					System.out.println("L'élément " + chaine + " a été ajouté");
				}else if (maps.addSolution(chaine) == 2) {
					System.out.println("L'argument " + chaine + " n'existe pas dans le graphe");
				}else {
					System.out.println("L'argument existe déjà");
				};
				
				System.out.println("Solution actuelle : " + maps.afficheSolutions());
				break;
			case 2:

				chaine = Utile.lireString(scString, "Le nom de l'argument à retirer");
				Noeud Arg2 = new Noeud(chaine);
				if (maps.removeSolution(Arg2)) {
					System.out.println("L'élément " + Arg2.getNoeud() + " a été retiré.");
					System.out.println("Solution actuelle : " + maps.afficheSolutions());
				} else {
					System.out.println("L'élément " + Arg2.getNoeud() + " n'existe pas");
					System.out.println("Solution actuelle : " + maps.afficheSolutions());
				}
				break;
			case 3:
				if (maps.getSolutions().isEmpty()) {
					System.out.println("Solution admissible : " + maps.afficheSolutions());
					break;
				}
				if (maps.testSolutionForPhase1() == false) {
					System.out.println("Solution non admissible");
					maps.verifSolution();
					System.out.println("Solution actuelle : " + maps.afficheSolutions());
				} else {
					maps.verifSolution();
					System.out.println("Solution actuelle : " + maps.afficheSolutions());
				}
				break;
			case 4:
				if (maps.testSolutionForPhase1() == true) {
					System.out.println("Solution admissible : " + maps.afficheSolutions());
					System.out.println("Fin du programme");
				} else {
					System.out.println("Solution non admissible : " + maps.afficheSolutions());
					System.out.println("Fin du programme");
				}
				break;
			}
			if (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
				System.out.println("Le choix est incorrect, veuillez taper 1 ou 2 ou 3 ou 4");
			}
		} while (choix != 4);
		scString.close();
	}

	/**
	 * menu qui permet à l'utilisateur de choisir de virifier une solution avec un fichier agrs[0].
	 * Ce menu nous donne les admissibles, préférées et sauvegarder dans un fichier en demandant le chemin
	 * 
	 * @param sc un scanner
	 * @param maps
	 */
	public static void menu3(Scanner sc, ListeAdjacence maps) {
		int choixSolution = 0;
		Scanner sc2 = new Scanner(System.in);
		int choix;
		do {
			System.out.println("*******************Menu3*********************");
			System.out.println("1) chercher une solution admissible;");
			System.out.println("2) chercher une solution préférée;");
			System.out.println("3) sauvegarder la solution;");
			System.out.println("4) fin;");
			System.out.println("*********************************************");

			choix = Utile.lireEntier(sc, "Votre choix ?");
			switch (choix) {
			case 1:
				choixSolution = 1;
				System.out.println(maps.afficheUneAdmissible());

				break;
			case 2:
				choixSolution = 2;
				System.out.println(maps.afficheUnePrefere());

				break;
			case 3:
				int tmp = ListeAdjacence.compteur;
				String str;
				if (choixSolution == 1) {
					if (maps.getListAdmissible().size() != 0 || maps.getListPrefere().size() != 0) {

						String chemin = Utile.lireString(sc2, "Veuiller entrer le chemin pour sauvegarder la solution");
						if (tmp > 0) {
							tmp--;
							str = String.join(",", maps.getListAdmissible().get(tmp));
						} else {
							str = String.join(",", maps.getListAdmissible().get(tmp));
						}
						Utile.saveSolution(chemin, str);
					}
				} else if (choixSolution == 2) {
					if (maps.getListAdmissible().size() != 0 || maps.getListPrefere().size() != 0) {

						String chemin = Utile.lireString(sc2, "Veuiller entrer le chemin pour sauvegarder la solution");
						if (tmp > 0) {
							tmp--;
							str = String.join(",", maps.getListPrefere().get(tmp));
						} else {
							str = String.join(",", maps.getListPrefere().get(tmp));
						}
						Utile.saveSolution(chemin, str);
					}
				} else {
					System.out.println("Veuillez choisir au moins une fois l'option 1 ou 2");
				}
				break;
			case 4:
				System.out.println("Fin du programme");
				break;
			}
			if (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
				System.out.println("Le choix est incorrect, veuillez taper 1 ou 2 ou 3 ou 4");
			}
		} while (choix != 4);
		sc2.close();
	}

}
