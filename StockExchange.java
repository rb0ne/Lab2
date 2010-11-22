import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StockExchange {
	public static void main(String[] args) {
		String name;
		String action;
		int price1;
		int price2;
		int numberOfBuyers = 0;
		int numberOfSellers = 0;

		Heap buyersHeap = new Heap(true);
		Heap sellersHeap = new Heap(false);
		// Ta emot indata
		Scanner sc;
		if (args.length > 0) {
			String fileName = args[0];
			try {
				sc = new Scanner(new File(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
				return;
			}
		} else {
			sc = new Scanner(System.in);
		}
		while (true) {
			System.out.println("What do you want to do: ");
			// HŠr ska nŒgon form av felhantering lŠggas in. DONE!
			if (sc.hasNext()) {
				name = sc.next();
			} else {
				return;
			}
			if (sc.hasNext()) {
				action = sc.next();
			} else {
				return;
			}
			if (sc.hasNextInt()) {
				price1 = sc.nextInt();
			} else {
				if (sc.hasNext()) {
					System.out.println("Wrong input format!");
					sc.next();
					continue;
				}
				return;
			}
			// Ska Šven kontrollera sŒ att alla namn Šr unika! DONE!
			if (action.equalsIgnoreCase("K")) {
				if (!buyersHeap.hashMap.hasName(name)) {
					buyersHeap.add(name, price1);
					numberOfBuyers++;
				} else {
					System.out.println("All names must be unique.");
				}
			} else if (action.equalsIgnoreCase("S")) {
				if (!sellersHeap.hashMap.hasName(name)) {
					sellersHeap.add(name, price1);
					numberOfSellers++;
				} else {
					System.out.println("All names must be unique.");
				}
			} else if (action.equalsIgnoreCase("NK") && sc.hasNextInt()) {
				price2 = sc.nextInt();
				if (buyersHeap.hashMap.hasName(name)) {
					if (buyersHeap.getPrice(buyersHeap.getPosition(name)) == price1) {
						buyersHeap.change(name, price1, price2);
					} else {
						System.out.println("The old price must be accurate");
					}
				} else {
					System.out
							.println("There exist no bids from the person you have given.");
				}

			} else if (action.equalsIgnoreCase("NS")) {
				price2 = sc.nextInt();
				if (sellersHeap.hashMap.hasName(name)) {
					if (sellersHeap.getPrice(sellersHeap.getPosition(name)) == price1) {
						sellersHeap.change(name, price1, price2);
					} else {
						System.out.println("The old price must be accurate");
					}
				} else {
					System.out
							.println("There exist no bids from the person you have given.");
				}
			} else {
				System.out.println("The input should be in the format:");
				System.out.println("Name Action(S, K, NS, NK) Price "
				   + "Price2(if Ns or NK has been given as action).");
				continue;
			}
			// Skriv ut bŒda kšerna:
			System.out.println("Kšpare: ");
			buyersHeap.printMe();
			System.out.println("SŠljare: ");
			sellersHeap.printMe();
			// kontrollera om det finns tvŒ matchande sŠlj och
			// kšpbud DONE!
			if (numberOfBuyers > 0 && numberOfSellers > 0) {
				if (buyersHeap.getPrice(0) >= sellersHeap.getPrice(0)) {
					String buyersName = buyersHeap.getName(0);
					int buyersPrice = buyersHeap.getPrice(0);
					String sellersName = sellersHeap.getName(0);
					buyersHeap.remove(buyersName);
					sellersHeap.remove(sellersName);
					System.out.println("" + buyersName
							+ " has bought stock from " + sellersName
							+ " for $" + buyersPrice);
					numberOfBuyers--;
					numberOfSellers--;
				}

			}
			System.out.println("Kšpare: ");
			buyersHeap.printMe();
			System.out.println("SŠljare: ");
			sellersHeap.printMe();

		}
	}
}
