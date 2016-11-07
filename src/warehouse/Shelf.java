package warehouse;

import java.util.ArrayList;

public class Shelf {
	final int shelfNumber; // Each shelf is numbered 0 - (n-1)
	final int maxWeight = 100; // Maximum amount of Item weight shelf can stow
	int shelfWeight; // Tracks current weight on shelf
	boolean isFull; // Test whether a shelf is full

	/*
	 * Items on shelf will be stored as objects in an arrayList
	 * 
	 */
	ArrayList<Item> ItemsOnShelf = new ArrayList<Item>();

	/*
	 * Constructor for shelf object
	 * 
	 * @param shelfNumber
	 * 
	 */
	public Shelf(int shelfNumber) {
		this.shelfNumber = shelfNumber;
		isFull = false;
	}

	// Objectives
	// Restock an item
	// Decrement an item being sold
	// Add items if shelf not full
	// Shelf Contains 1 type of item for now

	/*
	 * Add item objects onto the shelf
	 */
	protected void addItem(Item item) {
		if (item.getWeight() + shelfWeight <= maxWeight - shelfWeight) {
			ItemsOnShelf.add(item);
			item.numStocked++;
			shelfWeight = shelfWeight + item.getWeight();
		} else {
			System.out.println("Shelf if full!");
		}
	}

	/*
	 * Remove an item from the shelf
	 */
	protected void removeItem(Item item) {
		if (ItemsOnShelf.contains(item)) {
			ItemsOnShelf.remove(item);
			item.numStocked--;
			shelfWeight = shelfWeight - item.getWeight();
		} else
			System.out.println("Out of Stock");
	}

	/*
	 * Check if a shelf contains an item
	 */
	protected boolean contains(Item item) {
		for (Item items : ItemsOnShelf) {
			if (item.equals(items))
				return true;
		}
		return false;
	}

	/*
	 * @return Returns current weight on shelf
	 */
	protected int getShelfWeight() {
		System.out.println("Current shelf Weight: " + shelfWeight);
		return shelfWeight;
	}

	/*
	 * Display items currently on shelf
	 */
	protected void displayShelf() {
		for (Item items : ItemsOnShelf) {
			System.out.print(items.getName() + " " + items.numStocked + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Item item1 = new Item(0, "hat", 5.00, 55);
		Item item2 = new Item(1, "shirt", 7.25, 3);
		Item item3 = new Item(2, "pants", 9.00, 6);

		Shelf shelf0 = new Shelf(0);
		Shelf shelf1 = new Shelf(0);

		shelf0.addItem(item1);
		shelf0.addItem(item1);
		shelf0.displayShelf();
		shelf0.getShelfWeight();

	}

}
