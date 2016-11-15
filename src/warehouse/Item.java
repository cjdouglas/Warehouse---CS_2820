package warehouse;

import java.util.ArrayList;

/**
 * 
 * @author sethpratt
 *
 */

public class Item {
	int itemID;
	public String name;
	public double price;
	int weight;
	int numStocked;
	public boolean reStock;
	int amazonID;
	InventoryCatalog inventory = new InventoryCatalog();
	CatItem catitem;
	static ArrayList<Item> itemList;

	/*
	 * Constructor for creating a new Item
	 */
	public Item(int itemID, String name, double price, int weight) {
		this.itemID = itemID;
		this.name = name;
		this.price = price;
		this.weight = weight;
	}

	/*
	 * @author Seth Pratt
	 */

	public Item(int amazonID, int id, String description) {
		for (int i = 0; i < catitem.getcalalog().length; i++) {
			amazonID = i;
			id = catitem.getID();
			description = catitem.getDescription();
		}
	}

	/*
	 * Create a list of items from the item catalog
	 */
	void buildItemList(CatItem[] catalog) {
		for (int i = 0; i < catalog.length; i++) {
			itemList.add(new Item(i, catalog[i].getID(), catalog[i].getDescription()));
		}
	}

	/*
	 * Display list of items
	 */
	protected static void displayItemList() {
		System.out.println("Item List: ");
		for (Item item : itemList) {
			System.out.print(item + ", ");
		}
	}

	// Objectives
	// Items need to know what shelf they are one
	//

	/*
	 * @return Returns shelf number item being searched is located on
	 */

	// GETTERS
	int getAmazonItem() {
		return this.amazonID;
	}

	int getItemID() {
		return this.itemID;
	}

	String getName() {
		return this.name;
	}

	double getPrice() {
		return this.price;
	}

	int getWeight() {
		return this.weight;
	}

	protected int getNumStocked(Item item) {
		System.out.println("Item: " + item.getName() + ". Available: " + item.numStocked);
		return item.numStocked;
	}

	// SETTERS
	void setPrice(double newPrice) {
		this.price = newPrice;
	}

	public static void main(String[] args) {
		// String fileName = "ItemsList.csv";
		// File file = new File(fileName);
		// try {
		// Scanner inputStream = new Scanner(file);
		// inputStream.next(); // Disregard first line
		// while (inputStream.hasNext()) {
		// String data = inputStream.next();
		// // data = data.replaceAll("\\s+", "");
		// String[] ItemParams = data.split(",");
		// int itemID = Integer.parseInt(ItemParams[0]);
		// String name = ItemParams[1];
		// double price = Double.parseDouble(ItemParams[2]);
		// double weight = Double.parseDouble(ItemParams[3]);
		// // System.out.println(ItemParams[0]);
		// }
		// inputStream.close();
		//
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }

		displayItemList();

	}
}
