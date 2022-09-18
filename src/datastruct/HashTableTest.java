package datastruct;
import static org.junit.Assert.*;


import org.junit.Test;

/**
*This class tests all methods in HashTable class
*@author Eve-Anne OFFREDI
*/
public class HashTableTest {
	
	/**HashTable*/
	HashTable hashTVide = new HashTable();
	HashTable hashT = new HashTable();
	
	/**
	 * HashTable builder test
	 */
	@Test
	public void testHashTable() {
		System.out.println("HashTable builder test : ");
		
		//cas normaux : hashTVide
		System.out.println("Cas normaux : ");
		if(hashTVide.toString().equals("Current table : " + "\n")) System.out.println("Passed test");
		else fail("Test failed");
		
		System.out.println("\n" + "------------------------------------------" + "\n");
	}
	
	/**
	 * Test nbTuples method
	 */
	@Test
	public void testGetNbTuples() {
		System.out.println("testGetNbTuples : ");
		
		//Création tableau
		HashTable t = new HashTable();
		Object e = new Object();
		Object d = new Object();
				
		t.insert("Martin", e);
		t.insert("Julie", d);
		
		//cas normaux :
		System.out.println("Cas normaux : ");
		if(t.getNbTuples()==2) System.out.println("Passed test");
		else fail("Test failed");
				
		//cas limite : hashTVide
		System.out.println("Cas limite : ");
		if(hashTVide.getNbTuples()==0) System.out.println("Passed test");
		else fail("Test failed");
		
		System.out.println("\n" + "------------------------------------------" + "\n");
	}
	
	/**
	 * /**
	 * Test toString method
	 */
	@Test
	public void testToString() {
		System.out.println("testToString : ");
		
		//Création tableau
		HashTable t = new HashTable();
		Object e = new Object();
		Object d = new Object();
		
		t.insert("Martin", e);
		t.insert("Julie", d);

		//cas normaux
		System.out.println("Cas normaux : ");
		if(t.toString().equals("Current table : " + "\n" + "Key : JulieData : java.lang.Object@57175e74, Key : MartinData : java.lang.Object@7bb58ca3, "))System.out.println("Passed test");
		else fail("Test failed");	
				
		//cas limite : hashTVide
		System.out.println("Cas limite : ");
		if(hashTVide.toString().equals("Current table : " + "\n")) System.out.println("Passed test");
		else fail("Test failed");	
		
		System.out.println("\n" + "------------------------------------------" + "\n");
	}
	
	/**
	 * Test select method
	 */
	@Test
	public void testSelect() {
		System.out.println("testSelect : ");
		
		//Création tableau
		Object a = new Object();
		Object b = new Object();
		
		HashTable t = new HashTable();
		t.insert("Maeva", b);
		t.insert("Julie", a);
		t.insert("Roxanne", b);
		
		
		//Cas normaux
		System.out.println("Cas normaux : ");
		if(t.select("Inexistant") == null)System.out.println("Passed test");
		else fail("Test failed");
		if(t.select("Maeva") == b)System.out.println("Passed test");
		else fail("Test failed");
		if(t.select("Roxanne") == b)System.out.println("Passed test");
		else fail("Test failed");
				
		System.out.println("\n" + "------------------------------------------" + "\n");
	}
	
	/**
	 * Test insert method
	 */
	@Test
	public void testInsert() {
		System.out.println("testInsert : ");
		
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		Object d = new Object();
		
		
		//cas limite : 1er insert hashT
		System.out.println("Cas limite : 1er insert : ");
		if(hashT.insert("Daniel",a)) System.out.println("Passed test");
		else fail("Test failed");
		
		//cas normaux : hashT
		System.out.println("Cas normaux : ");

		if(hashT.insert("Daniel",a)) System.out.println("Passed test");
		else fail("Test failed");
		
		if(hashT.insert("Daniel",b)) System.out.println("Passed test");
		else fail("Test failed");
		
		if(hashT.insert("Michel",b)) System.out.println("Passed test");
		else fail("Test failed");
		
		if(hashT.insert("Patrick",c)) System.out.println("Passed test");
		else fail("Test failed");
		
		if(hashT.insert("Romain",d)) System.out.println("Passed test");
		else fail("Test failed");
		
		if(hashT.insert("Romain",d)) System.out.println("Passed test");
		else fail("Test failed");
		
		if(hashT.insert("Evan",d)) System.out.println("Passed test");
		else fail("Test failed");
		
		if(hashT.insert("Gringe",a)) System.out.println("Passed test");
		else fail("Test failed");
		
		//cas limite : dernier insert possible hashT
		System.out.println("Cas limite : dernier insert : ");
		if(hashT.insert("Jason",b)) System.out.println("Passed test");
		else fail("Test failed");
		
		//cas d'erreur : insert dans un tableau plein
		System.out.println("Cas d'erreur : tableau plein : ");
		if(!hashT.insert("Romain",c)) System.out.println("Passed test");
		else fail("Test failed");
		
		System.out.println("\n" + "------------------------------------------" + "\n");
	}
	
	/**
	 * Test delete method
	 */
	@Test
	public void testDelete() {
		System.out.println("testDelete : ");
		
		//création de la hashtable
		HashTable t = new HashTable();
		Object e =new Object();
		t.insert("hey", e);
		t.insert("bye", e);
		
		//cas normaux :
		System.out.println("Cas normaux : ");
		if(t.delete("hey")) System.out.println("Passed test");
		else fail("Test failed");
		if(t.delete("bye")) System.out.println("Passed test");
		else fail("Test failed");
		
		//cas limite : hashTables vides
		System.out.println("Cas limite : tableaux vides : ");
		if(!hashT.delete("Test")) System.out.println("Passed test");
		else fail("Test failed");
		if(!t.delete("bye")) System.out.println("Passed test");
		else fail("Test failed");
		
		System.out.println("\n" + "------------------------------------------" + "\n");
	}

}
