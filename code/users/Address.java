package users;

/**
 * Class that represents a 2-dim address.
 * 
 * @author John de Wasseige
 * @author Patrick von Platen
 */
public class Address {
	
	private int xCoordinate;
	private int yCoordinate;
	
	
	/************************************************************/
	/**
	 * @param xCoordinate the x coordinate of the address
	 * @param yCoordinate the y coordinate of the address
	 */
	public Address(int xCoordinate, int yCoordinate) {
		super();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	

	public double calculateDistance(Address address){
		return Math.sqrt(Math.pow((this.xCoordinate-address.xCoordinate),2) + Math.pow((this.yCoordinate-address.yCoordinate),2));
	}
	
	/************************************************************/
	/* Getters and Setters */

	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate() {
		return xCoordinate;
	}


	/**
	 * @param xCoordinate the xCoordinate to set
	 */
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}


	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate() {
		return yCoordinate;
	}


	/**
	 * @param yCoordinate the yCoordinate to set
	 */
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}


	/************************************************************
	 * Overridden methods
	 * toString, hashCode and equals  
	 */
	@Override
	public String toString() {
		return "Address [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
		return result;
	}


	/**
	 * @param	obj of Address
	 * @return TRUE if two addresses are equal, meaning they have the same X and Y coordinates.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}
	
	
};