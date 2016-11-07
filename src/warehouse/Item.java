package warehouse;

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

}
