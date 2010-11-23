/**
 * Customers with the properties: name, price.
 * 
 * @author Robin Persson Sšderholm, Andreas KŠllberg
 *
 */
public class Customer {
	String customerName;
	int customerValue;

	/**
	 * Constructs a new Customer with "name" and "value".
	 * 
	 * @param name
	 * @param value
	 */
	public Customer(String name, int value){
		customerName = name;
		customerValue = value;
	}
	/**
	 * Returns the name.
	 * @return
	 */
	public String getName(){
		return customerName;
	}
	/**
	 * Returns the value.
	 * @return
	 */
	public int getPrice(){
		return customerValue;
	}
	/**
	 * Set the value to newValue.
	 * @param newValue
	 */
	public void setPrice(int newValue){
		customerValue = newValue;
	}
	/**
	 * Returns a String object representing this Customer's name and value.
	 */
	public String toString() {
		return customerName + " " + customerValue;
	}
}
