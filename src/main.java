import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class main {
	public static void main(String [] agrs) {
		int nbArg = Utile.nbArg();
		
		ListeAdjacence matrice = new ListeAdjacence();
		
		Utile.menu1(nbArg, matrice);
		
		/*
		Noeud n = new Noeud("A");
		Noeud n2 = new Noeud("A");
		matrice.argument(n);
		matrice.argument(n2);
		*/
		
		System.out.println(matrice.affichage());
		
	}
}
