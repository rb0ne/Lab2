import java.util.Scanner;

public class StockExchange {
	public static void main(String[] args) {
		String name;
		String action;
		int price1;
		int price2 = 0; // kan man komma runt detta på något vettigare sätt?

		Heap buyersHeap = new Heap();
		Heap sellersHeap = new Heap();
		// Ta emot indata
		while (true) {
			System.out.println("Skriv in önskad handling: ");
			Scanner sc = new Scanner(System.in);
			// Här ska någon form av felhantering läggas in.
			name = sc.next();
			action = sc.next();
			price1 = sc.nextInt();
			if (sc.hasNext()) {
				price2 = sc.nextInt();
			}
			// Ska även kontrollera så att alla namn är unika!
			if (action.equalsIgnoreCase("K")) {
				buyersHeap.add(name, price1);
			} else if (action.equalsIgnoreCase("S")) {
				sellersHeap.add(name, price1);
			} 
			// följande två if satser behöver komplimenteras med en kontroll
			// av att det verkligen finns ett bud av den storleken från den personen.
			else if (action.equalsIgnoreCase("NK")) {
				buyersHeap.change(name, price1, price2);
			} else if (action.equalsIgnoreCase("NS")) {
				sellersHeap.change(name, price1, price2);
			}
			else if (action.equalsIgnoreCase("Exit") || name.equalsIgnoreCase("Exit") ){
				break;
			}
		}
	}
}
