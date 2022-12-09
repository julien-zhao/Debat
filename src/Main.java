import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	/**
	 * la class main qui teste si args[0] est null, on exécute la phase 1, sinon la phase 2
	 * @param args
	 */
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		ListeAdjacence matrice = new ListeAdjacence();
		
		if(args.length ==0) {
			int nbArg = Utile.nbArg(sc);		
			Utile.menu1(sc,nbArg, matrice);
		}else {
			try {
				Utile.lireFichier(args[0],matrice);
			}catch(FileNotFoundException f) {
				System.out.println("Erreur : "+ f.getMessage());
			}
			matrice.afficheGraphe();
			matrice.afficheAdmissible();
			matrice.affichePrefere();		
			Utile.menu3(sc,matrice);
		}
		sc.close();	
	}
}

