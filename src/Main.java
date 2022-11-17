import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		ListeAdjacence matrice = new ListeAdjacence();
		
		//Phase 1
		/*
		//int choix = 0;
		int nbArg = Utile.nbArg(sc);		
		Utile.menu1(sc,nbArg, matrice);
		
		*/
		//commentaire
		/**
		 * 
		 */
		
		
		
		

		
		//Phase 2
		
		//Lire un fichier 
		
		try {
			Utile.lireFichier("/Users/julien/Documents/L3/JavaA/Debat_ZHAO_YE/src/debat.txt",matrice);
		}catch(FileNotFoundException f) {
			System.out.println("Erreur : "+ f.getMessage());
		}
		System.out.println("Affiche après contradiction: \n"+matrice.affichage());
		
		Utile.menu3(sc, matrice);
		
		System.out.println(matrice);
		
		
		
		
		
		
		
		
		
		
		/*
		ArrayList<String> list = new ArrayList<String>();
		
		String chaine = "ABC";
		for(int i =0 ; i< chaine.length();i++) {
	
				StringBuilder sb;
				int tmp = 0;
				if(i== 0) {
					for(int j = 0; j<chaine.length() ; i++){
						sb = new StringBuilder();
						sb.append(chaine.charAt(i));
						list.add(sb.toString());
					}
						
				}else {
					for(int z = 0; z<i ; z++) {
						sb = new StringBuilder();
						sb.append(chaine.charAt(z) + chaine.charAt(i));
						list.add(sb.toString());
				}
				
			}
		}
		for(String c : list) {
			System.out.println(c);
		}
		*/
		/** pour ABC il y a 7 solutions(combinaisons) possible
		 * [A,B,C]
		 *[A,B] || [A,C] || [B,C]
		 * [A] || [B] || [C]
		 * 
		 */
		
		
		
		sc.close();
	}
}
