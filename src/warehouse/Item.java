package warehouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Item {
	final int itemID;
	public String name;
	public double price;
	final int weight;
	int numStocked;
	public boolean reStock;

	public Item(int itemID, String name, double price, int weight) {
		this.itemID = itemID;
		this.name = name;
		this.price = price;
		this.weight = weight;
	}

	// Objectives
	// Items need to know what shelf they are one
	//

	/*
	 * @return Returns shelf number item being searched is located on
	 */

	// GETTERS
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
		String fileName = "ItemsList.csv";
		File file = new File(fileName);
		try {
			Scanner inputStream = new Scanner(file);
			inputStream.next(); // Disregard first line
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				// data = data.replaceAll("\\s+", "");
				String[] ItemParams = data.split(",");
				int itemID = Integer.parseInt(ItemParams[0]);
				String name = ItemParams[1];
				double price = Double.parseDouble(ItemParams[2]);
				double weight = Double.parseDouble(ItemParams[3]);
				// System.out.println(ItemParams[0]);
			}
			inputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
