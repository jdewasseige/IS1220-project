package users;

import java.util.LinkedList;
import java.util.List;

import core.Order;
import restaurantSetUp.Address;

/**
 * The class <code>Courier</code> allows to create a Courier which will be able to
 * <ul>
 * 	<li>register/unregister their Account to the MyFoodora system</li>
 *  <li>set their state as on-duty or off-duty</li>
 *  <li>change their position</li>
 *  <li>accept/refuse a delivery call (received from the MyFoodora system)</li>
 * </ul>
 * 
 * @author John de Wasseige
 * @author Patrick von Platen
 */

public class Courier extends User {
	
	//TODO discuss with John whether a static attribute of the core is a good idea or not
	
	private String surname;
	private Address position;
	private String phoneNumber;
	private int nbOfDeliveredOrders;
	private boolean state; //true = on duty; false = off duty
	
	private LinkedList<Order> listOfReceivedOrders; 
	
	
	/**
	 * Constructor 
	 * 
	 * @param	name 	of courier
	 * @param	surname	of courier
	 * @param	position	of courier
	 * @param	phonenumber	of courier 
	 * @param	username	of courier to log into the system
	 * @param	id	unique id of courier
	 * @param	nbOfDeliveredOrders	amount of orders delivered by courier
	 * @param	state	states whether courier is on duty (true = yes)
	 */	
	public Courier(String name, String surname, Address position, String phoneNumber, String username){
		super(name, username);
		this.surname = surname;
		this.position = position;
		this.phoneNumber = phoneNumber;
		this.nbOfDeliveredOrders = 0;
		this.state = true;
		listOfReceivedOrders = new LinkedList<Order>();
	}
	

	/*********************************************************************/
	
	/**
	 * the function <code>acceptOrder</code> accepts the order by 
	 * setting the courier of that order to this courier
	 * @return	order	that was given to the courier
	 */
	public void acceptOrder(){
		Order order = this.listOfReceivedOrders.remove(); // rm first element of linkedlist
		order.setCourier(this);
		nbOfDeliveredOrders++;
	}
	
	/**
	 * the function <code>declineOrder</code> accepts the order by 
	 * setting the courier of that order null
	 * @return	order	that was given to the courier
	 */
	public void declineOrder(){
		this.listOfReceivedOrders.remove();  // rm first element of linkedlist
	}
	
	/**
	 * the function <code>replyRand</code> randomly either accepts or declines an order
	 * @return	order	that was given to the courier
	 */
	public void replyRandom(){
		if(Math.random() <= 0.7)
			acceptOrder();
		else
			declineOrder();
	}
	
	/**
	 * @param	order	that is to be added to the list of received orders
	 */
	public void addNewOrder(Order order) {
		this.listOfReceivedOrders.add(order);
	}
	
	/**
	 * @param	message	of which this user is going to be notified
	 */
	public void update(String message) {
		System.out.println("[Courier UPDATE] " + message);
	}

	@Override
	public String toString() {
		return "Courier [getUsername()=" + getUsername() + ", getName()=" + getName() + "]";
	}


	/*********************************************************************/
	/* Getters and Setter */ // no setter for the ID, nor for the COUNTER !

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Address getPosition() {
		return position;
	}
	public void setPosition(Address position) {
		this.position = position;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getNbOfDeliveredOrders() {
		return nbOfDeliveredOrders;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void setNbOfDeliveredOrders(int nbOfDeliveredOrders) {
		this.nbOfDeliveredOrders = nbOfDeliveredOrders;
	}
	
	
	public List<Order> getListOfReceivedOrders() {
		return listOfReceivedOrders;
	}

	public void setListOfReceivedOrders(LinkedList<Order> listOfReceivedOrders) {
		this.listOfReceivedOrders = listOfReceivedOrders;
	}
	
	
	
}
