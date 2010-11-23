public class Heap {
	int size;
	Customer[] arrayOfCustomers;
	Hashmap hashMap;
	int order;

	public Heap(boolean largestOnTop) {
		size = 0;
		arrayOfCustomers = new Customer[4];
		hashMap = new Hashmap();
		order = largestOnTop ? 1 : -1;
	}

	public int getPrice(int position) {
		return arrayOfCustomers[position].getPrice();
	}

	public String getName(int position) {
		return arrayOfCustomers[position].getName();
	}

	public int getPosition(String name) {
		return hashMap.read(name);
	}

	public void add(String name, int price) {
		if (hashMap.hasName(name)) {
			
			// cast exception
		}
		if (arrayOfCustomers.length >=size)
			growArray();
		arrayOfCustomers[size] = new Customer(name, price);
		hashMap.add(name, size);
		bubbleSortUp(size, price);
		size++;
	}
	
	public void growArray() {
			Customer[] temp = arrayOfCustomers;
			arrayOfCustomers = new Customer[2 * arrayOfCustomers.length];
			for (int i = 0; i < temp.length; i++) {
			    arrayOfCustomers[i] = temp[i];
			}
	}

	public void change(String name, int oldPrice, int newPrice) {
		if (!hashMap.hasName(name)) {
			// cast exception
		}
		// position = e.position
		int position = hashMap.read(name);
		if (oldPrice != arrayOfCustomers[position].getPrice()) {
			// fel fel fel
		}
		arrayOfCustomers[position].setPrice(newPrice);
		// BubbleSort (som uppdatera hashmap)
		bubbleSort(position, oldPrice);

	}

	public void remove(String name) {
		if (!hashMap.hasName(name)) {
			// fel fel fel
		}
		if (size == 1){
			size--;
			arrayOfCustomers[0] = null;
			hashMap.remove(name);
			return;
		}
		size--;
		Customer lastElement = arrayOfCustomers[size];
		arrayOfCustomers[size] = null;
		int position = hashMap.read(name);
		int oldPrice = arrayOfCustomers[position].getPrice();
		arrayOfCustomers[position] = lastElement;
		hashMap.remove(name);
		hashMap.changeHeapPos(lastElement.getName(), position);
		bubbleSort(position, oldPrice);
	}

	private void bubbleSort(int position, int oldPrice) {
		Customer element = arrayOfCustomers[position];
		int price = element.getPrice();
		if (order * price > order * oldPrice) {
			bubbleSortUp(position, price);
		} else if (order * price < order * oldPrice) {
			bubbleSortDown(position, price);
		}
	}

	private void bubbleSortUp(int position, int price) {
		if (position == 0) {
			return;
		}
		int parentPosition = getParentPosition(position);
		if (order * arrayOfCustomers[parentPosition].getPrice() >= order
				* price) {
			return;
		}
		changePosition(position, parentPosition);
		bubbleSortUp(parentPosition, price);
	}

	private void bubbleSortDown(int position, int price) {
		if (!hasLeftChild(position)) {
			return;
		}
		if (!hasRightChild(position)) {
			int leftChildPosition = getLeftChildPosition(position);
			if (order * arrayOfCustomers[leftChildPosition].getPrice() <= order * price) {
				return;
			}
			changePosition(position, leftChildPosition);
			bubbleSortDown(leftChildPosition, price);
			return;
		}
		int rightChildPosition = getRightChildPosition(position);
		int rightChildPrice = arrayOfCustomers[rightChildPosition].getPrice();
		int leftChildPosition = getLeftChildPosition(position);
		int leftChildPrice = arrayOfCustomers[leftChildPosition].getPrice();
		if (order * leftChildPrice >= order * rightChildPrice) {
			if (order * leftChildPrice <= order * price) {
				return;
			}
			changePosition(position, leftChildPosition);
			bubbleSortDown(leftChildPosition, price);
			return;
		}
		if (order * rightChildPrice <= order * price) {
			return;
		}
		changePosition(position, rightChildPosition);
		bubbleSortDown(rightChildPosition, price);
	}

	private void changePosition(int position, int newPosition) {
		Customer temp = arrayOfCustomers[newPosition];
		String tempName = temp.getName();
		arrayOfCustomers[newPosition] = arrayOfCustomers[position];
		hashMap.changeHeapPos(arrayOfCustomers[newPosition].getName(),
				newPosition);
		arrayOfCustomers[position] = temp;
		hashMap.changeHeapPos(tempName, position);
	}

	private int getParentPosition(int position) {
		if (position % 2 == 0) {
			return (position - 2) / 2;
		} else {
			return (position - 1) / 2;
		}
	}

	private int getLeftChildPosition(int position) {
		return position * 2 + 1;
	}

	private int getRightChildPosition(int position) {
		return position * 2 + 2;
	}

	private boolean hasLeftChild(int position) {
		return arrayOfCustomers[getLeftChildPosition(position)] != null;
	}

	private boolean hasRightChild(int position) {
		return arrayOfCustomers[getRightChildPosition(position)] != null;
	}
	
	public void printMe(){
		String namePrice = "";
		for (int i = 0; i<size; i++) {
			if (i > 0){
			namePrice = namePrice + ", " +arrayOfCustomers[i];
			} else {
				namePrice = namePrice +arrayOfCustomers[i];
			}
		}
		System.out.println(namePrice);
	}
}
