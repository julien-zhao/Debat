import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Utile {
	
	public static int nbArg() {
		System.out.println("Entrer le nombre d'argument n : ");
		Scanner sc = new Scanner(System.in);
		int nb = sc.nextInt();
		return nb;
	}
	
	
	public static void menu1(int nbArg, ListeAdjacence maps) {
		int choix;
		
		if(nbArg >0) {
			do {
	
				System.out.println("1) ajouter une contradiction;");
				System.out.println("2) fin. ");
				Scanner scChoix = new Scanner(System.in);
				choix = scChoix.nextInt();
				
				if(choix == 1) {
					for(int i=0; i<nbArg; i++) {
						System.out.println("A quel argument vous voulez ajouter une contradiction");
						Scanner sc1 = new Scanner(System.in);
						
					
						Noeud unArg = new Noeud(sc1.nextLine());
						
						if(maps.argument(unArg) ==false) {
							System.out.println("Argument identique, veuillez ajouter un autre argument");
							i--;
						}
						
						
					}
					
					//Toute clé dans une liste
					ArrayList<Noeud> uneListe = new ArrayList<Noeud>();
					
					System.out.println(maps.affichage());
					
					for(Noeud unNoeud : maps.getMaps().keySet()) {
						uneListe.add(unNoeud);
						
						//A : [B]
					}

					
					for(int i =1; i<uneListe.size();i++) {
						for(Noeud unNoeud : maps.getMaps().keySet()) {
							maps.contradiction(unNoeud, uneListe.get(i));
						}
					}
					
					
					/*
					 * A, B, C
					 * 
					 * A : []
					 * B : []
					 * C : []
					 * 
					 * attendu : 
					 * A : [B]
					 * B : [C]
					 * C : []
					 * 
					 * 
					 */

					
					//TODO
				}
			
				if(choix !=1 && choix != 2) {
					System.out.println("erreur de choix");
					//Gestion d'erreur
				}
				
			
			}while(choix == 1);
			
			if(choix ==2) {
				menu2(maps);
			}
		
		}
		
		
	}
	
	public static void menu2(ListeAdjacence maps) {
		int choix;
		System.out.println("1) ajouter un argument;");
		System.out.println("2) retirer;");
		System.out.println("3) vérifier la solution;");
		System.out.println("4) fin;");
		
		Scanner sc = new Scanner(System.in);
		choix = sc.nextInt();
		
		switch(choix) {
			case 1:
				System.out.println("Le nom de l'argument : ");
				Scanner sc1 = new Scanner(System.in);		
				Noeud Arg1 = new Noeud(sc1.nextLine());
				maps.argument(Arg1);
			case 2:
				System.out.println("Le nom de l'argument a retirer");
				Scanner sc2 = new Scanner(System.in);
				Noeud Arg2 = new Noeud(sc2.nextLine());
				maps.getMaps().remove(Arg2);
			case 3:
				//verifier la solution
			case 4:
				//affichage ensemble
		
		}
		
	}
	
}
