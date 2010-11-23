/**
 * Heap implemented by the use of arrays.
 * 
 * @author Robin Persson Sšderholm, Andreas KŠllberg
 * 
 */

public class Heap {
	int size;
	Customer[] arrayOfCustomers;
	Hashmap hashMap;
	int order;

	/**
	 * Constructs a new Heap that takes it's priority order described by
	 * largestOnTop.
	 * 
	 * @param largestOnTop
	 *            decides if larger numbers should have higher priority.
	 */
	public Heap(boolean largestOnTop) {
		size = 0;
		arrayOfCustomers = new Customer[4];
		hashMap = new Hashmap();
		order = largestOnTop ? 1 : -1;
	}

	/**
	 * Returns the price from the Customer at position.
	 * 
	 * @param position
	 * @return
	 */
	public int getPrice(int position) {
		return arrayOfCustomers[position].getPrice();
	}

	/**
	 * Returns the name from the Customer at position.
	 * 
	 * @param position
	 * @return
	 */
	public String getName(int position) {
		return arrayOfCustomers[position].getName();
	}

	/**
	 * Returns the position for the Customer that has name = "name".
	 * 
	 * @param name
	 * @return
	 */
	public int getPosition(String name) {
		return hashMap.read(name);
	}

	/**
	 * Adds one Customer with name="name" and price="price".
	 * 
	 * @param name
	 * @param price
	 */
	public void add(String name, int price) {
		if (arrayOfCustomers.length >= size)
			growArray();
		arrayOfCustomers[size] = new Customer(name, price);
		hashMap.add(name, size);
		bubbleSortUp(size, price);
		size++;
	}

	/**
	 * Allows the arrayOfCustomers to double in length when needed to.
	 */
	public void growArray() {
		Customer[] temp = arrayOfCustomers;
		arrayOfCustomers = new Customer[2 * arrayOfCustomers.length];
		for (int i = 0; i < temp.length; i++) {
			arrayOfCustomers[i] = temp[i];
		}
	}

	/**
	 * Change the price of the Customer that has name = "name" and the putting
	 * it in the right place by calling the bubbleSort-method
	 * 
	 * @param name
	 * @param oldPrice
	 * @param newPrice
	 */
	public void change(String name, int oldPrice, int newPrice) {
		int position = hashMap.read(name);
		arrayOfCustomers[position].setPrice(newPrice);
		bubbleSort(position, oldPrice);

	}

	/**
	 * Removes the Customer that has name = "name" and resort the heap by
	 * calling the bubbleSort-method
	 * 
	 * @param name
	 */
	public void remove(String name) {
		if (!hashMap.hasName(name)) {
			// fel fel fel
		}
		if (size == 1) {
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

	/**
	 * Checks if the Customer should be bubbled down or up by comparing it's
	 * price to the positions old price.
	 * 
	 * @param position
	 * @param oldPrice
	 */
	private void bubbleSort(int position, int oldPrice) {
		Customer element = arrayOfCustomers[position];
		int price = element.getPrice();
		if (order * price > order * oldPrice) {
			bubbleSortUp(position, price);
		} else if (order * price < order * oldPrice) {
			bubbleSortDown(position, price);
		}
	}

	/**
	 * Bubble sort Customer up until it's parent has higher priority than it.
	 * 
	 * @param position
	 * @param price
	 */
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

	/**
	 * Bubble sort Customer up until it's children has lower priority than it.
	 * 
	 * @param position
	 * @param price
	 */
	private void bubbleSortDown(int position, int price) {
		if (!hasLeftChild(position)) {
			return;
		}
		if (!hasRightChild(position)) {
			int leftChildPosition = getLeftChildPosition(position);
			if (order * arrayOfCustomers[leftChildPosition].getPrice() <= order
					* price) {
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

	/**
	 * Swap position for two Customers and updates the hashmap.
	 * 
	 * @param position
	 * @param newPosition
	 */
	private void changePosition(int position, int newPosition) {
		Customer temp = arrayOfCustomers[newPosition];
		String tempName = temp.getName();
		arrayOfCustomers[newPosition] = arrayOfCustomers[position];
		hashMap.changeHeapPos(arrayOfCustomers[newPosition].getName(),
				newPosition);
		arrayOfCustomers[position] = temp;
		hashMap.changeHeapPos(tempName, position);
	}

	/**
	 * Returns the position of the Customer at positions' parent.
	 * 
	 * @param position
	 * @return
	 */
	private int getParentPosition(int position) {
		if (position % 2 == 0) {
			return (position - 2) / 2;
		} else {
			return (position - 1) / 2;
		}
	}

	/**
	 * Returns the position of the Customer at positions' left child.
	 * 
	 * @param position
	 * @return
	 */
	private int getLeftChildPosition(int position) {
		return position * 2 + 1;
	}

	/**
	 * Returns the position of the Customer at positions' right child.
	 * 
	 * @param position
	 * @return
	 */
	private int getRightChildPosition(int position) {
		return position * 2 + 2;
	}

	/**
	 * Returns the true if the Customer at positions has a left child.
	 * 
	 * @param position
	 * @return
	 */
	private boolean hasLeftChild(int position) {
		return arrayOfCustomers[getLeftChildPosition(position)] != null;
	}

	/**
	 * Returns the true if the Customer at positions has a right child.
	 * 
	 * @param position
	 * @return
	 */
	private boolean hasRightChild(int position) {
		return arrayOfCustomers[getRightChildPosition(position)] != null;
	}

	/**
	 * Prints all the Customers in the heap
	 * 
	 */
	public void printMe() {
		if (size > 0) {
			System.out.print(arrayOfCustomers[0]);
		}
		for (int i = 0; i < size; i++) {
			System.out.print(", " + arrayOfCustomers[i]);
		}
		System.out.println();
	}
}
