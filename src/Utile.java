import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Utile {
	static Scanner sc = new Scanner(System.in);

	/**
	 * méthode qui va lire un entier
	 * @param sc
	 * @param message
	 * @return
	 */
	public static int lireEntier(Scanner sc, String message) {
		boolean lecture = false;
		int res =0;
		
		while(!lecture) {
			try {
				System.out.println(message);
				res = sc.nextInt();
				lecture = true;
			}catch(InputMismatchException e) {
				System.out.println("Il faut taper un nombre");
				sc.nextLine();
			}
		}
		return res;
	}
	/**
	 * méthode qui va lire un string
	 * @param sc
	 * @param message
	 * @return
	 */
	public static String lireString(Scanner sc, String message) {
		boolean lecture = false;
		String res =null;
		
		while(!lecture) {
			try {
				System.out.println(message);
				res = sc.nextLine();
				lecture = true;
			}catch(InputMismatchException e) {
				System.out.println("Il faut taper un string");
				sc.nextLine();
			}
		}
		return res;
	}
	/**
	 * méthode qui demande à l'utilisateur le nombre d'argument
	 * @return nb le nombre d'argument
	 */
	public static int nbArg(Scanner sc) {
		int nb;
		do {
			
			nb = lireEntier(sc,"Entrez le nombre d'argument n : ");

		}while(nb <0);

		return nb;
	}

	public static void menu1(Scanner sc,int nbArg, ListeAdjacence maps) {
		Scanner scString = new Scanner(System.in);
		int choix = -1;
		for(int i=1; i<=nbArg ;i++) {
			Noeud unArg = new Noeud("A"+i);
			maps.argument(unArg);
		}

		do {
			System.out.println("*******************Menu1*********************");
			System.out.println("1) ajouter une contradiction;");
			System.out.println("2) fin. ");
			System.out.println("*********************************************\n");
			choix = lireEntier(sc, "Votre choix ?");
			if(choix ==1) {
				String arg1 =null;
				String arg2 =null;

				arg1 = lireString(scString,"A quel argument vous voulez ajouter une contradiction");
				arg2 = lireString(scString, "Nom de l'argument contradictoire ");
				if(!maps.stringCompareNoeud(arg1) && !maps.stringCompareNoeud(arg2)) {
					System.out.println(arg1 +" "+ arg2 + " n'existent pas" );
				}else {
					if(!maps.stringCompareNoeud(arg1)) {
						System.out.println(arg1 + " n'existe pas" );
					}else if(!maps.stringCompareNoeud(arg2)){
						System.out.println(arg2 + " n'existe pas" );
					}
				}			
				if(maps.stringCompareNoeud(arg1) && maps.stringCompareNoeud(arg2)) {
					maps.contradiction(maps.stringToNoeud(arg1), maps.stringToNoeud(arg2));
				}
			}
			if(choix != 1 && choix !=2){
				System.out.println("Le choix est incorrect, veuillez taper 1 ou 2");
			}
		}while(choix != 2);
		
		System.out.println("Argument après les contradiction : \n" + maps.affichage());
		
		if(choix == 2) {
			menu2(sc,maps);
		}
		scString.close();
	}

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
			
			choix = lireEntier(sc,"Votre choix ? ");

			switch (choix) {
			case 1:
				chaine = lireString(scString, "Le nom de l'argument");
				maps.addSolution(chaine);
				System.out.println("Solution actuel : " +maps.afficheSolutions());
				break;
			case 2:
		
				chaine = lireString(scString, "Le nom de l'argument a retirer");
				Noeud Arg2 = new Noeud(chaine);	
				if(maps.removeSolution(Arg2)) {
					System.out.println("L'élément "+ Arg2.getNoeud() + " a été retiré.");
					System.out.println("Solution actuel : " +maps.afficheSolutions());
				}else {
					System.out.println("L'élément "+Arg2.getNoeud() + " n'existe pas");
					System.out.println("Solution actuel : " +maps.afficheSolutions());
				}
				break;
			case 3:
				if(maps.getSolutions().isEmpty()) {
					System.out.println("Solution admissible : " + maps.afficheSolutions());
					//System.out.println("Solution actuel : " +maps.afficheSolutions());
					break;
				}
				if(maps.testSolution2() ==false) {
					System.out.println("Solution non admissible");
					maps.verifSolution2() ;
					System.out.println("Solution actuel : " +maps.afficheSolutions());
				}else {
					maps.verifSolution2();
					System.out.println("Solution actuel : " +maps.afficheSolutions());
				}
				break;
			case 4:
				if(maps.testSolution2()== true) {
					System.out.println("Solution admissible : "+ maps.afficheSolutions());
				}else {
					System.out.println("Solution non admissible : "+ maps.afficheSolutions());
				}
				break;
			}
			if(choix != 1 && choix !=2 && choix !=3 && choix !=4){
				System.out.println("Le choix est incorrect, veuillez taper 1 ou 2 ou 3 ou 4");
			}
		} while (choix != 4);
		scString.close();
	}

 
	
	//Phase 2
	public static void lireFichier(String chemin, ListeAdjacence maps) throws FileNotFoundException {
		// Le fichier d'entrée
		System.out.println("");
		File file = new File(chemin);
			
		if (!file.exists()) {
			throw new FileNotFoundException("Le fichier existe pas");
		} else {
			Scanner scanner = new Scanner(file);
			// renvoie true s'il y a une autre ligne à lire
			while (scanner.hasNext()) {
				
				String line = scanner.nextLine();
			
                if(line.startsWith("argument(") && line.endsWith(").")) {
                	maps.scanArgument(line);
                }else if(line.startsWith("contradiction(") && line.endsWith(").")){

                	maps.scanContradiction(line);
                }else {
                	throw new FileNotFoundException("La ligne " + line+" est  mal formaté");
                	//System.out.println("Mal formaté");
                	//break;
                }
			}
			scanner.close();
		}	 
	}
	
	
	

	public static void menu3(Scanner sc, ListeAdjacence maps) {
		int choix;
		do {
			System.out.println("*******************Menu3*********************");
			System.out.println("1) chercher une solution admissible;");
			System.out.println("2) chercher une solution préférée;");
			System.out.println("3) sauvegarder la solution;");
			System.out.println("4) fin;");
			System.out.println("*********************************************");
			
			choix = lireEntier(sc,"Votre choix ?");
			switch (choix) {
			case 1:
				maps.afficheListAdmissible();
				break;
			case 2:
				maps.afficheListPrefere();
				break;
			case 3:
				if(maps.getListAdmissible().size() ==0 || maps.getListPrefere().size() == 0) {
					//demander a l'utilisateur un chemin pour stcoker la soltion dans un ficheir
					String chemin = lireString(sc,"Veuiller entrer le chemin pour sauvegarder la solution");
				}else {
					System.out.println("Veuillez choisir au moins une fois l'option 1 ou 2");
				}

				break;
			case 4:
				System.out.println("Fin du programme");
				break;
			}
			
			if(choix != 1 && choix !=2 && choix !=3 && choix !=4){
				System.out.println("Le choix est incorrect, veuillez taper 1 ou 2 ou 3 ou 4");
			}

		} while (choix !=4 );
	}

}
































