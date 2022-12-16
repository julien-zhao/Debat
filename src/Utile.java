import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * la classe Utile correspond a toutes les methodess qui ont besoin de faire une
 * entrée-sortie ou un scanner
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


	// Phase 2
	/**
	 * methode qui permet de lire un fichier, la methode verifier 
	 * si le fichier est vide
	 * si la ligne est mal formaté 
	 * si la ligne est vide 
	 * dans une contradiction, les arguments doivent être défini
	 * 
	 * 
	 * @param chemin
	 * @param maps
	 * @throws FileNotFoundException
	 */
	public static void lireFichier(String chemin, ListeAdjacence maps) throws FileNotFoundException {

		File file = new File(chemin);
		int compteurLine = 0;
		if (!file.exists()) {
			System.out.println("Le fichier n'existe pas");
			System.exit(-1);
		} else {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {

				String line = scanner.nextLine();
				compteurLine++;
				
				if (line.startsWith("argument(") && line.endsWith(").")) {
					maps.scanArgument(line);
				} 
				else if (line.startsWith("contradiction(") && line.endsWith(").")) {
					if (!maps.scanContradiction(line)) {
						System.out.println('"'+line+'"'+" [ligne: "+compteurLine+"]" + " possède un argument non defini pour faire une contradiction");
						System.exit(-1);
					}
				} 
				else if(line.equals("")) {
				}
				else {
					System.out.println('"'+line+'"'+" [ligne: "+compteurLine+"]" + " est mal formatée");
					System.exit(-1);
				}
			}
			scanner.close();
		}
	}

	/**
	 * methode qui permet de sauvegarder une solution 
	 * si le fichier existe pas, il sera crée 
	 * si le fichier existe deja, il sera écrasé
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
}
