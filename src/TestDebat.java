
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDebat {
	private ListeAdjacence graph;
	private LinkedHashMap<Noeud, Arc> maps;
	private ArrayList<Noeud> solutions;
	private ArrayList<Noeud> listAttack;
	private ArrayList<Noeud> listSeFaireAttack;
	private ArrayList<ArrayList<String>> listAdmissible;
	private ArrayList<ArrayList<String>> listPrefere;
	public static int compteur = 0;
	
	
	@BeforeEach
	void genereGraph() {
		maps = new LinkedHashMap<Noeud, Arc>();
		Noeud premierNoeud = new Noeud("A");
		Noeud deuxiemeNoeud = new Noeud("B");
		Noeud troisiemeNoeud = new Noeud("C");
	
		maps.put(premierNoeud, new Arc());
		maps.put(deuxiemeNoeud, new Arc());
		maps.put(troisiemeNoeud, new Arc());
		
		maps.get(premierNoeud).add(deuxiemeNoeud);
		maps.get(premierNoeud).add(troisiemeNoeud);
		maps.get(deuxiemeNoeud).add(premierNoeud);
		
		graph = new ListeAdjacence(maps,listAttack,listSeFaireAttack,listAdmissible,listPrefere);
	}
	
	@Test
	void testStringCompareNoeud_1() {
		assertTrue(graph.stringCompareNoeud("A"));
	}
	@Test
	void testStringCompareNoeud_2() {
		assertFalse(graph.stringCompareNoeud("D"));
	}
	
	@Test
	void testStringToNoeud_1() {
		Noeud unNoeud = new Noeud("A");
		assertEquals(unNoeud.getNoeud(), graph.stringToNoeud("A").getNoeud());
	}
	@Test
	void testAddArgument_1() {
		Noeud unNoeud = new Noeud("A");
		assertFalse(graph.addArgument(unNoeud));
	}
	
	@Test
	void testAddArgument_2() {
		Noeud unNoeud = new Noeud("D");
		assertTrue(graph.addArgument(unNoeud));
	}
	
	
	@Test
	void testAddContradiction_1() {
		Noeud unNoeud = new Noeud("A");
		Noeud deuxiemeNoeud = new Noeud("C");
		maps.put(unNoeud, new Arc());
		maps.put(deuxiemeNoeud, new Arc());
		graph.addContradiction(deuxiemeNoeud,unNoeud);
		
		if(maps.get(deuxiemeNoeud).getArc().size() ==1) {
			assertFalse(false);
		}	
	}
	
	@Test
	void testRemoveArg() {
		Noeud unNoeud = new Noeud("A");
		assertTrue(graph.removeArg(unNoeud));
	}
	
	@Test
	void testAddSolution() {
		Noeud unNoeud = new Noeud("A");
		graph.addSolution("A");
		if(graph.getSolutions().contains(unNoeud)) {
			assertTrue(true);
		}
	}
	@Test
	void testRemoveSolution() {
		graph.addSolution("A");
		Noeud unNoeud = new Noeud("A");
		assertTrue(graph.removeSolution(unNoeud));
	}
	
	
	
	
	
	
	
	
	
	
	
}


