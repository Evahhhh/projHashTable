package datastruct;
/**
*This class contains all methods about the interface Table
*@author Eve-Anne OFFREDI
*/
public interface Table {
	
	/**
	 * This method returns the data of the Tuple corresponding to the search key.
	 * Returns null if no tuple matches the key.
	 * @param key - search key
	 * @return data or null
	 */
	public Object select ( String key );
	
	/**
	 * If no tuple in the table matches the key passed as a parameter, this method inserts at the correct
	 * place in the table a new Tuple whose key (key) and data (data) are passed as parameters.
	 * Returns false if the insertion is not possible (either the key already exists or the array is full).
	 * @param key - the search key
	 * @param data - data which can be insert in a certain case
	 * @return true or false
	 */
	public boolean insert ( String key, Object data ) ;
	
	/**
	 * Deletes from the table the Tuple corresponding to the key passed in parameter. Returns false if the
	 * deletion is not possible 
	 * @param key - the search key
	 * @return true or false
	 */
	public boolean delete ( String key ) ;
}
