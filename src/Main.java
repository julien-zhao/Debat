import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		ListeAdjacence matrice = new ListeAdjacence();
		
		//Phase 1
		
		//int choix = 0;
		
		//int nbArg = Utile.nbArg(sc);		
		//Utile.menu1(sc,nbArg, matrice);
		
		

		//Phase 2
		//Lire un fichier 
		
		
		try {
			Utile.lireFichier(args[0],matrice);
		}catch(FileNotFoundException f) {
			System.out.println("Erreur : "+ f.getMessage());
		}
		System.out.println("Affiche après contradiction: \n"+matrice.affichage());
		 
		
		//un tab qui contient tout argument
		/*
		String [] s;
		s = matrice.getAllArgument();
		for(int i =0;i<matrice.getSizeMatrice(); i++) {
			System.out.println(s[i] +" ");
		}
		System.out.println("\n");
*/
		//matrice.getAllAdmissible(); 
		//matrice.getAllPrefere();
		
		Utile.menu3(sc, matrice);
		
		//System.out.println(matrice);
		
		
		
		sc.close();
	}
}

