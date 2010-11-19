
public class Customer {
	String customerName;
	int customerKey;
	
	public Customer(String name, int key){
		customerName = name;
		customerKey = key;
	}
	public String getName(){
		return customerName;
	}
	public int getKey(){
		return customerKey;
	}
	public void setKet(int newKey){
		customerKey = newKey;
	}
}
