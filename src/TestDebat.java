
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDebat {
	private ListeAdjacence graph;
	private LinkedHashMap<Noeud, Arc> maps;
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
		
		graph = new ListeAdjacence(maps);
		graph.getSolutions().add(premierNoeud);
		graph.getSolutions().add(deuxiemeNoeud);
		
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
		
		boolean res= false;
		if(maps.get(deuxiemeNoeud).getArc().contains(unNoeud)) {
			res = true;
		}
		assertTrue(res);
	}
	
	@Test
	void testRemoveArg() {
		Noeud unNoeud = new Noeud("A");
		assertTrue(graph.removeArg(unNoeud));
	}
	
	@Test
	void testAddSolution() {
		Noeud unNoeud = new Noeud("D");
		maps.put(unNoeud, new Arc());
		graph.addSolution("D");
		boolean res = false;
		if(graph.getSolutions().contains(unNoeud)) {
			res = true;
		}
		assertTrue(res);
	}
	@Test
	void testRemoveSolution() {
		graph.addSolution("A");
		Noeud unNoeud = new Noeud("A");
		assertTrue(graph.removeSolution(unNoeud));
	}
	
	
	@Test
	void testSolution1() {
		assertFalse(graph.testSiSolutionAdmissible());
	}
	@Test
	void testSolution2() {
		graph.getSolutions().remove(0);
		assertTrue(graph.testSiSolutionAdmissible());
	}
	
	@Test
	void testScanArgument() {
		graph.scanArgument("argument(D)");
		Noeud unNoeud = graph.stringToNoeud("D");
		Boolean res = false;
		if(graph.getMaps().keySet().contains(unNoeud)) {
			res = true;
		}	
		assertTrue(res);	
	}
	
	@Test
	void testScanContradiction() {
		graph.scanContradiction("contradiction(B,C)");
		Noeud unNoeud = graph.stringToNoeud("B");
		Noeud deuxiemeNoeud = graph.stringToNoeud("C");
		boolean res =false;
		if(maps.get(unNoeud).getArc().contains(deuxiemeNoeud)) {
			res = true;
		}
		assertTrue(res);
	}
	@Test
	void testGetSizeMatrice() {
		assertEquals(3,graph.getSizeMatrice());		
	}
	
	@Test
	void testGetAllArgument() {
		String[]tab = new String[graph.getSizeMatrice()];
		tab[0] = "A";
		tab[1] = "B";
		tab[2] = "C";
		boolean res= false;
		if(Arrays.equals(tab, graph.getAllArgument())) {
			res = true;
		}		
		assertTrue(res);		
	}
	
	@Test
	void testCreateAllCombinaison() {
		ArrayList<ArrayList<String>> bigList = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("");
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("A");
		ArrayList<String> list3 = new ArrayList<>();
		list3.add("B");
		ArrayList<String> list4 = new ArrayList<>();
		list4.add("C");
		ArrayList<String> list5 = new ArrayList<>();
		list5.add("A, B");
		ArrayList<String> list6 = new ArrayList<>();
		list6.add("A, C");
		ArrayList<String> list7 = new ArrayList<>();
		list7.add("B, C");
		ArrayList<String> list8 = new ArrayList<>();
		list8.add("A, B, C");
		
		bigList.add(list1);	
		bigList.add(list2);
		bigList.add(list3);
		bigList.add(list4);
		bigList.add(list5);
		bigList.add(list6);
		bigList.add(list7);
		bigList.add(list8);

		boolean res = false;
		if(graph.createAllCombinaison().equals(bigList)) {
			res= true;
		}
		assertFalse(res);
	}
	
	@Test
	void testCreateListAdmissible() {
		ArrayList<ArrayList<String>> bigList = new ArrayList<ArrayList<String>>();
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("");
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("A");
		ArrayList<String> list3 = new ArrayList<>();
		list3.add("B");
		ArrayList<String> list4 = new ArrayList<>();
		list4.add("B, C");
		
		bigList.add(list1);
		bigList.add(list2);
		bigList.add(list3);
		bigList.add(list4);
		graph.createListAdmissible();
		boolean res = false;
		if(bigList.equals(graph.getListAdmissible())) {
			res = true;
		}
		assertFalse(res);
	}
	
	@Test
	void testInclu() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("A");
		graph.createListAdmissible();
		assertTrue(graph.inclu(list));
	}
	
	
	@Test
	void testCreateListPrefere() {
		ArrayList<ArrayList<String>> bigList = new ArrayList<ArrayList<String>>();

		ArrayList<String> list1 = new ArrayList<>();
		list1.add("A");
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("B, C");
		
		bigList.add(list1);
		bigList.add(list2);
		graph.createListPrefere();
		boolean res = false;
		if(bigList.equals(graph.getListPrefere())) {
			res = true;
		}
		assertFalse(res);

	}
	
}


