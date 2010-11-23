
public class Customer {
	String customerName;
	int customerValue;
	
	public Customer(String name, int value){
		customerName = name;
		customerValue = value;
	}
	public String getName(){
		return customerName;
	}
	public int getPrice(){
		return customerValue;
	}
	public void setPrice(int newValue){
		customerValue = newValue;
	}
	public String toString() {
		return customerName + " " + customerValue;
	}
}
