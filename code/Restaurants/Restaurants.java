// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package projet_oop;

import java.util.ArrayList;
import java.util.List;


/************************************************************/
/**
 * The class is there to create objects representing restaurants 
 * Restaurants include a menu with different dishes and a list of all meals that are 
 * offered by the restaurant
 * 
 * 
 * @author Patrick
 * 
 */

class Restaurant {
	
	private String name;
	private static int counter;
	private Address Adress;
	private String username;
	private double discountFactor;
	private double specDiscFact;
	private Menu menu;
	private int id;
	private List<Meal> listOfMeal;

	
	
	
	/**
	 * 
	 * @param name = name of restaurant
	 * @param address = address of restaurant
	 * @param username = log-in name of restaurant
	 * @param discountFactor = in %/ Factor
	 * @param by which the price of a meal is cheaper than the sum of its single dishes
	 * @param specDiscFact = > discountFactor/ applied only
	 * @param for one meal, menu = menu of restaurant that includes all dishes
	 * @param listOfMeal = list of all the meals offered by the restaurant
	 * 
	 * Constructor where all attributes are taken as input
	 *  
	 */
	public Restaurant(String name, Address adress, String username, double discountFactor, double specDiscFact,
			Menu menu, List<Meal> listOfMeal) {
		super();
		this.id = (counter++);
		this.name = name;
		Adress = adress;
		this.username = username;
		this.discountFactor = discountFactor;
		this.specDiscFact = specDiscFact;
		this.menu = menu;
		this.listOfMeal = listOfMeal;
	}
	
	/**
	 * @param name = name of restaurant
	 * @param address = address of restaurant
	 * @param username = log-in name of restaurant
	 * @param discountFactor = in %/ Factor
	 * @param by which the price of a meal is cheaper than the sum of its single dishes
	 * @param specDiscFact = > discountFactor/ applied only
	 * @param for one meal, menu = menu of restaurant that includes all dishes
	 * @param listOfMeal = list of all the meals offered by the restaurant
	 *   
	 * Constructor where a new menu is created
	 * 
	 */
	public Restaurant(String name, Address adress, String username, double discountFactor, double specDiscFact,
			List<Meal> listOfMeal) {
		super();
		this.id = (counter++);
		this.name = name;
		Adress = adress;
		this.username = username;
		this.discountFactor = discountFactor;
		this.specDiscFact = specDiscFact;
		this.menu = new Menu();
		this.listOfMeal = listOfMeal;
	}
	
	/**
	 * @param name = name of restaurant
	 * @param address = address of restaurant
	 * @param username = log-in name of restaurant
	 * @param discountFactor = in %/ Factor
	 * @param by which the price of a meal is cheaper than the sum of its single dishes
	 * @param specDiscFact = > discountFactor/ applied only
	 * @param for one meal, menu = menu of restaurant that includes all dishes
	 * @param listOfMeal = list of all the meals offered by the restaurant
	 * Constructor where a new listOfMeal is created
	 * 
	 */
	public Restaurant(String name, Address adress, String username, double discountFactor, double specDiscFact,
			Menu menu) {
		super();
		this.id = (counter++);
		this.name = name;
		Adress = adress;
		this.username = username;
		this.discountFactor = discountFactor;
		this.specDiscFact = specDiscFact;
		this.menu = menu;
		this.listOfMeal = new ArrayList<Meal>();
	}
	
	
	/**
	 * @param name = name of restaurant
	 * @param address = address of restaurant
	 * @param username = log-in name of restaurant
	 * @param discountFactor = in %/ Factor
	 * @param by which the price of a meal is cheaper than the sum of its single dishes
	 * @param specDiscFact = > discountFactor/ applied only
	 * @param for one meal, menu = menu of restaurant that includes all dishes
	 * @param listOfMeal = list of all the meals offered by the restaurant
	 * 
	 *  Constructor where a new listOfMeal and a new Menu is created
	 *  
	 */
	public Restaurant(String name, Address adress, String username, double discountFactor, double specDiscFact) {
		super();
		this.id = (counter++);
		this.name = name;
		Adress = adress;
		this.username = username;
		this.discountFactor = discountFactor;
		this.specDiscFact = specDiscFact;
		this.menu = new Menu();
		this.listOfMeal = new ArrayList<Meal>();
	}
	
	/************************************************************
	 * Getters and Setters 
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAdress() {
		return Adress;
	}

	public void setAdress(Address adress) {
		Adress = adress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getDiscountFactor() {
		return discountFactor;
	}

	public void setDiscountFactor(int discountFactor) {
		this.discountFactor = discountFactor;
	}

	public double getSpecDiscFact() {
		return specDiscFact;
	}

	public void setSpecDiscFact(int specDiscFact) {
		this.specDiscFact = specDiscFact;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Meal> getListOfMeal() {
		return listOfMeal;
	}

	public void setListOfMeal(List<Meal> listOfMeal) {
		this.listOfMeal = listOfMeal;
	}

	public int getId() {
		return id;
	}
	
	/************************************************************/

	/**
	 * @param dish is either starter, mainDish or dessert and will be added to the Menu 
	 */
	public void addDish(Dish dish) {
		//TODO complete function
	}

	/**
	 * @param dish is either starter, mainDish or dessert and will be removed from the Menu
	 */
	public void removeDish(Dish dish) {
		//TODO complete function
	}

	/**
	 * @param meal is either HalfMeal or FullMeal will be added to the listOfMeal 
	 * 
	 */
	public void addMeal(Meal meal) {
		//TODO complete function
	}

	/**
	 * @param meal is either HalfMeal or FullMeal will be removed from the listOfMeal 
	 * 
	 */
	public void removeMeal(Meal meal) {
		//TODO complete function
	}

	//TODO decide whether the following two functions are necessary or whether the setters and getters are enough
	
//	/**
//	 * @param discFac 
//	 */
//	public void setDiscFac(int discFac) {
//	}
//
//	/**
//	 * @param specDiscFac 
//	 */
//	public void setSpecDiscFac(int specDiscFac) {
//	}

	/**
	 * @param meal is either HalfMeal or FullMeal
	 * @return the price of a meal is returned as an int
	 */
	public double getPrice(Meal meal) {
		//TODO complete function
		return discountFactor;
	}

	
	//TODO need function to decide on special offer meal 
	
	@Override
	public String toString() {
		return "Restaurants [name=" + name + ", Adress=" + Adress + ", username=" + username + ", discountFactor="
				+ discountFactor + ", specDiscFact=" + specDiscFact + ", menu=" + menu + ", id=" + id + ", listOfMeal="
				+ listOfMeal + "]";
	}
	
	
};
