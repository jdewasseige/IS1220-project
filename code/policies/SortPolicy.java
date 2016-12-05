package policies;

import users.Restaurant;

/**
 * The abstract class <code>SortPolicy</code> allow restaurants and managers
 * to sort the shipped orders according to different criteria.
 * 
 * The classes implementing this interface are
 * <ul>
 * 	<li><code>MealSort</code></li>
 *  <li><code>DishSort</code></li>
 * </ul>
 * 
 * @author John de Wasseige
 * @author Patrick von Platen
 */
public abstract class SortPolicy implements Comparable<SortPolicy> {
	
	private int count;
	private Restaurant rest;
	
	/**
	 *@param	count	is the number of times the respective choice was taken
	 *@param	rest	is the restaurant where the choice was ordered from
	 *
	 *is protected so that only classes that inherit <code>SortPolicy</code> can access the
	 *constructor
	 */
	protected SortPolicy(int count, Restaurant rest) {
		super();
		this.count = count;
		this.rest = rest;
	}

	/**
	 * 
	 * is there because core needs an attributeless SortPolicy to save as policy
	 * is protected so that only classes that inherit <code>SortPolicy</code> can access the
	 *constructor
	 */
	protected SortPolicy() {
		super();
	}

	/*********************************************************************/
	/* Getters and Setter */ 
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}
	/*********************************************************************/
	
	/**
	 *the abstract function to implement
	 *@param	order	is a boolean that states whether the list will be displayed 
	 *in ascending or descending order 
	 *@return	return	order	boolean
	 */
	public abstract boolean howToSortOrder(boolean order);
	
	/**
	 * override compareTo method to sort list according to Count
	 */
	@Override
	public int compareTo(SortPolicy o) {
		return (this.count <= o.getCount()) ? 1 : -1;
	}
	
}
