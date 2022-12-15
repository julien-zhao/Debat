import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	/**
	 * la class main qui teste si args[0] est null, on exécute la phase 1,
	 * si args[0] existe mais le contenue est vide, le graphe est vide
	 * sinon on éxécute phase 2
	 * @param args
	 */
	public static void main(String [] args){

		Scanner sc = new Scanner(System.in);
		ListeAdjacence matrice = new ListeAdjacence();
		
		if(args.length ==0) {
			int nbArg = Utile.nbArg(sc);		
			Menus.menu1(sc,nbArg, matrice);
		}else {
			try {
				Utile.lireFichier(args[0],matrice);
			}catch(FileNotFoundException f) {
				System.out.println("Erreur : "+ f.getMessage());
			}
			if(matrice.getSizeMatrice()>0) {
				matrice.afficheGraphe();
				matrice.afficheAdmissible();
				matrice.affichePrefere();		
				Menus.menu3(sc,matrice);
			}else {
				System.out.println("Le graphe est vide");
			}	
		}
		sc.close();	
	}
}

