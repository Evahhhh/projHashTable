package datastruct;
/**
*This class create and manage a HashTable
*@author Eve-Anne OFFREDI
*/
public class HashTable implements Table {
	
	//Attributes
	
	/**Error message : invariant*/
	private static final String MESSAGE = "Invariant violé !";
	/** the array that will contain all the tuples */
	private Tuple[] table;
	/** the number of tuples in the array */
	private int nbTuples;
	/** constant that defines the size of the array */
	private final static int TAILLE = 10;
	
	/**
	 * Constructor that initializes HashMap attributes
	 */
	public HashTable( ) {
		table = new Tuple[TAILLE];
		nbTuples = 0;
		
		//invariant
		assert (invariant()) : MESSAGE;
	}
	
	/**
	 * nbTuples getter
	 * @return the number of tuples in the array
	 */
	public int getNbTuples( ) {
		return nbTuples;
	}
	
	/**
	 * Calculates, on the basis of the ASCII codes of the characters which form
	 * the key, the index of the array where the Tuple will be stored
	 * @param key - a given key
	 * @return the index of the given key in the array
	 */
	private int computeIndex ( String key ) {
		int ret = -1;
		
		if(key == null)throw new IllegalArgumentException("Entered key is null");
		else {
			for(int i = 0; i < key.length(); i++) {
				//For each character of the key, take its ASCII code and add it to the final int which will be returned
				ret = ret + Character.hashCode(key.charAt(i));
			}
			ret = ret % TAILLE;		//modulo of the ASCII code of the key
			
			//invariant
			assert (invariant()) : MESSAGE;
		}
		
		return ret;
	}
	
	/**
	 * Returns the array index where the tuple is located
	 * searched for (or -1 if not found). The array search is circular and starts at (index
	 * + 1)
	 * @param key - searched key
	 * @param indice - searched indice
	 * @return the array index where the tuple is located or -1 if not found
	 */
	private int circularSearch ( String key, int indice ) {
		int i = -1;
		if (key == null) throw new IllegalArgumentException("Entered key is null");
		else if (indice < 0 || indice > TAILLE - 1) throw new IllegalArgumentException("Entered index is negative or is larger than the size of the array");
		else {
			i = (indice + 1) % TAILLE;
			if (table[i] == null || !table[i].sameKey(key)) {
				while (i != indice) {	
					if (table[i] != null && table[i].sameKey(key))
						break;
					i = (i + 1) % TAILLE;
				}
				if (table[i] == null || !table[i].sameKey(key))
					i = -1;
			}	
			assert i == -1 || table[i].sameKey(key);
			
			//invariant
			assert (invariant()) : MESSAGE;
		}	
		return i;
	}
	
	/**
	 * Returns the entire table as a string
	 * @return the string
	 */
	public String toString( ) {
		StringBuilder s = new StringBuilder();
		s.append("Current table : " + "\n");
		int i = 0;
		while (i < TAILLE) {
			if (table[i] != null) {
				s.append(table[i].toString());				
				if (i < TAILLE - 1)
					s.append(", ");
			}
			i++;
		}
		
		//invariant
		assert (invariant()) : MESSAGE;

		return s.toString();
	}
	

	/**
	 * This method returns the data of the Tuple corresponding to the search key.
	 * Returns null if no tuple matches the key.
	 * @param key - search key
	 * @return data or null
	 */
	public Object select ( String key ) {
		Object ret = null;
		if (key == null) throw new IllegalArgumentException("Enterend key is null");
		else {
			int ind = computeIndex(key);
			
			if(table[ind] != null && table[ind].sameKey(key)) {
				ret = table[ind].data;
			}else {
				int tmp = circularSearch(key,ind);
				if(tmp != -1 && table[ind] != null && table[ind].sameKey(key)) {
					ret = table[tmp].data;
				}
			}
			
			//invariant
			assert (invariant()) : MESSAGE;
		}
		
		return ret;
	}
	
	/**
	 * If no tuple in the table matches the key passed as a parameter, this method inserts at the correct
	 * place in the table a new Tuple whose key (key) and data (data) are passed as parameters.
	 * Returns false if the insertion is not possible (either the key already exists or the array is full).
	 * @param key - the search key
	 * @param data - data which can be insert in a certain case
	 * @return true or false
	 */
	public boolean insert ( String key, Object data ) {
		boolean ret = false;
		if (key == null || data == null) throw new IllegalArgumentException("Entered key or data is null");
		else {
			int tmp = computeIndex(key);
			int i = tmp;
			if(table[i] != null) {
				i++;
				while(table[i] != null && i != tmp-1) i=(i+1)%TAILLE;
				if(table[i] == null) {
					Tuple t = new Tuple(key,data);
					table[i] = t;
					ret = true;
					nbTuples ++;
				}else System.out.println("The array is already full");;
			}else {
				Tuple t = new Tuple(key,data);
				table[i] = t;
				ret = true;
				nbTuples ++;
			}
						
			//invariant
			assert (invariant()) : MESSAGE;
		}
		
		return ret;
	}
	
	/**
	 * Deletes from the table the Tuple corresponding to the key passed in parameter. Returns false if the
	 * deletion is not possible 
	 * @param key - the search key
	 * @return true or false
	 */
	public boolean delete ( String key ) {
		boolean ret = false;
		if (key == null) throw new IllegalArgumentException("Entered key is null");
		else {	
			int index = computeIndex(key);
			if (table[index] == null || !table[index].sameKey(key))
				index = circularSearch(key, index);
			
			ret = (index != -1);
			if (index != -1) {
				table[index] = null;
				nbTuples--;
			}
			
			assert (index == -1 && !ret) || (select(key) == null && ret);
			
			//invariant
			assert (invariant()) : MESSAGE;
		}
		
		return ret;
	}
	
	/**
	 * Tests the validity of each of the attributes of the HashTable object
	 * @return true if all of the attributes are valid or false if 1 or more is not valid
	 */
	private boolean invariant() {
		boolean ans = true;
		
		if(this.table == null) {
			ans = false;
			System.out.println("table null");
		}
		
		if(this.nbTuples < 0 || this.nbTuples > 10) {
			ans = false;
			System.out.println("nbTuples < 0 or nbTuples > 10");
		}
	
		return ans;
	}
	
	/**
	 * Inner class contained in the HashTable class. Its role concerns the tuples themselves.
	 * @author Eve-Anne OFFREDI
	 */
	private class Tuple {
		
		// Attributes
		/** the identification key of the tuple */
		String key; 
		/** the tuple data */
		Object data;
		
		
		/**
		 * Constructor of a tuple, it initializes the attributes.
		 * @param key - key of the tuple
		 * @param data - data contains in the tuple
		 */
		Tuple ( String key, Object data ) {
			this.key = key;
			this.data = data;
		}
		
		/**
		 * Method that compares the Tuple's key with another key.
	 	 * It is used to look up the tuple in the table. Returns true if the two keys are identical (false otherwise).
		 * @param otherKey - the compared key
		 * @return true or false
		 */
		boolean sameKey ( String otherKey ) {
			return otherKey.equals(key);
		}
		
		/**
		 * Returns the Tuple in the form of a character string which will contain its key and its data
		 */
		public String toString() {
			return "Key : " + key + "Data : " + data.toString();
		}
	}
}
