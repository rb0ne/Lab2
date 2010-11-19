public class Heap {
	int size;
	Customer[] arrayOfCustomers;
	
	public Heap() {
		size = 0;
		arrayOfCustomers = new Customer[10];
	}

	public void add(String name, int price) {
		arrayOfCustomers[size] = new Customer(name, price);
		size++;
	}

	public void change(String name, int oldPrice, int newPrice) {

	}
	private void getPosition(String name){
		
	}
}
