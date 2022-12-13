import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * la classe Utile correspond a toutes les methodess qui ont besoin de faire une
 * entrée-sortie, un scanner et les menus
 * 
 * @author julien et Victor
 *
 */

public class Utile {
	/**
	 * méthode qui va lire un entier
	 * 
	 * @param sc
	 * @param message
	 * @return un entier
	 */
	public static int lireEntier(Scanner sc, String message) {
		boolean lecture = false;
		int res = 0;

		while (!lecture) {
			try {
				System.out.println(message);
				res = sc.nextInt();
				lecture = true;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper un nombre");
				sc.nextLine();
			}
		}
		return res;
	}

	/**
	 * méthode qui va lire un String
	 * 
	 * @param sc
	 * @param message
	 * @return un string
	 */
	public static String lireString(Scanner sc, String message) {
		boolean lecture = false;
		String res = null;

		while (!lecture) {
			try {
				System.out.println(message);
				res = sc.nextLine();
				lecture = true;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper un string");
				sc.nextLine();
			}
		}
		return res;
	}

	/**
	 * méthode qui demande à l'utilisateur le nombre d'argument
	 * 
	 * @return nb le nombre d'argument
	 */
	public static int nbArg(Scanner sc) {
		int nb;
		do {
			nb = lireEntier(sc, "Entrez le nombre d'argument n : ");
		} while (nb < 0);

		return nb;
	}
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
			maps.argument(unArg);
		}

		do {
			System.out.println("*******************Menu1*********************");
			System.out.println("1) ajouter une contradiction;");
			System.out.println("2) fin. ");
			System.out.println("*********************************************\n");
			choix = lireEntier(sc, "Votre choix ?");
			if (choix == 1) {
				String arg1 = null;
				String arg2 = null;

				arg1 = lireString(scString, "A quel argument vous voulez ajouter une contradiction?");
				arg2 = lireString(scString, "Nom de l'argument contradictoire ");
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
					maps.contradiction(maps.stringToNoeud(arg1), maps.stringToNoeud(arg2));
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

			choix = lireEntier(sc, "Votre choix ? ");

			switch (choix) {
			case 1:
				chaine = lireString(scString, "Le nom de l'argument");
				maps.addSolution(chaine);
				System.out.println("Solution actuelle : " + maps.afficheSolutions());
				break;
			case 2:

				chaine = lireString(scString, "Le nom de l'argument à retirer");
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

	// Phase 2
	/**
	 * methode qui permet de lire un fichier
	 * 
	 * @param chemin
	 * @param maps
	 * @throws FileNotFoundException
	 */
	public static void lireFichier(String chemin, ListeAdjacence maps) throws FileNotFoundException {

		File file = new File(chemin);

		if (!file.exists()) {
			System.out.println("Le fichier n'existe pas");
			System.exit(-1);
		} else {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {

				String line = scanner.nextLine();

				if (line.startsWith("argument(") && line.endsWith(").")) {
					maps.scanArgument(line);
				} else if (line.startsWith("contradiction(") && line.endsWith(").")) {
					if (!maps.scanContradiction(line)) {
						System.out.println(line + " possède un argument non defini pour faire une contradiction");
						System.exit(-1);
					}

				} else {
					System.out.println("La ligne " + line + " est mal formatée");
					System.exit(-1);
				}
			}
			scanner.close();
		}
	}

	/**
	 * methode qui permet de sauvegarder une solution 
	 * si le fichier existe pas, il sera crée 
	 * si le fichier existe deja, il sera écraser
	 * si le chemin n'existe pas, le menu réapparait.
	 * 
	 * @param chemin
	 * @param solution
	 */
	public static void saveSolution(String chemin, String solution) {

		File file = new File(chemin);

		try {
			
			if (!file.exists() ) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(solution);
			bw.close();

			System.out.println("Solution enregistrée");

		} catch (IOException e) {
			System.out.println("Erreur du chemin");
		}
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

			choix = lireEntier(sc, "Votre choix ?");
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

						String chemin = lireString(sc2, "Veuiller entrer le chemin pour sauvegarder la solution");
						if (tmp > 0) {
							tmp--;
							str = String.join(",", maps.getListAdmissible().get(tmp));
						} else {
							str = String.join(",", maps.getListAdmissible().get(tmp));
						}
						saveSolution(chemin, str);
					}
				} else if (choixSolution == 2) {
					if (maps.getListAdmissible().size() != 0 || maps.getListPrefere().size() != 0) {

						String chemin = lireString(sc2, "Veuiller entrer le chemin pour sauvegarder la solution");
						if (tmp > 0) {
							tmp--;
							str = String.join(",", maps.getListPrefere().get(tmp));
						} else {
							str = String.join(",", maps.getListPrefere().get(tmp));
						}
						saveSolution(chemin, str);
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
	}
}
