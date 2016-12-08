package restaurantSetUp;

/**
 * Starter extends the <code>Dish</code> class and
 * represent a starter with name, price and price.
 * 
 * @author John de Wasseige
 * @author Patrick von Platen
 */
public class Starter extends Dish {

	/**
	 * Constructor for a Starter with given type.
	 * 
	 * @param name	 name of the Starter
	 * @param price  price of the Starter
	 * @param type   type of the Starter (ie. standard/vegetarian/glutenfree)
	 * 
	 */
	public Starter(String name, double price, String type) {
		super(name, price, type);
	}
	
	/**
	 * Constructor for a Starter with no given type
	 * and will thus be standard.
	 * 
	 * @param name 	 name of the Starter
	 * @param price  price of the Starter
	 * 
	 */
	public Starter(String name, double price) {
		super(name, price, "standard");
	}

	@Override
	public String toString() {
		return "Starter [getName()=" + getName() + ", getType()=" + getType() + ", getPrice()=" + getPrice() + "]";
	}
}
