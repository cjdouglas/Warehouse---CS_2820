package warehouse;

import java.util.ArrayList;

/**
 * 
 * @author sethpratt
 *
 */

// 52 Shelves
// Restock entire shelf each time it ventures to dock
// Stock shelves

public class Shelf {
	final int shelfNumber; // Each shelf is numbered 0 - (n-1)
	final int maxWeight = 100; // Maximum amount of Item weight shelf can stow
	int shelfWeight; // Tracks current weight on shelf
	boolean isFull; // Test whether a shelf is full
	final int shelfCap = 10;

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
		if (willFit(item)) {
			ItemsOnShelf.add(item);
			item.numStocked++;
			shelfWeight = shelfWeight + item.getWeight();
			System.out.println("Adding " + item.getName() + " to shelf ");

			// if (item.getWeight() + shelfWeight <= maxWeight) {
			// ItemsOnShelf.add(item);
			// item.numStocked++;
			// shelfWeight = shelfWeight + item.getWeight();
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
	 * @return Returns shelf number
	 */
	protected int getShelfNumber() {
		System.out.println("Shelf number " + shelfNumber);
		return shelfNumber;
	}

	/*
	 * @return boolean Returns whether a shelf is full or not
	 */
	protected boolean isFull() {
		if (shelfWeight < 100 && shelfWeight > 0) {
			System.out.println("Not full");
			return false;
		}
		System.out.println("Full");
		return true;
	}

	/*
	 * @return boolean Returns whether an item will fit onto the shelf
	 */
	protected boolean willFit(Item item) {
		if (shelfWeight + item.getWeight() <= maxWeight) {
			System.out.println("Item fits");
			return true;
		}
		System.out.println("Item does not fit");
		return false;
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

	/*
	 * @return Return re-stocked shelf
	 */
	protected Shelf restock(Item item, Shelf shelf) {

		while (shelf.willFit(item)) {
			shelf.addItem(item);
		}

		return shelf;
	}
	
	/**
	 * Returns a String representation of this Shelf object for testing purposes
	 * @return A String representation of this Shelf object
	 */
	public String toString() {
		return "" + shelfNumber;
	}

	public static void main(String[] args) {
		Item item1 = new Item(0, "hat", 5.00, 55);
		Item item2 = new Item(1, "shirt", 7.25, 45);
		Item item3 = new Item(2, "pants", 9.00, 6);

		Shelf shelf0 = new Shelf(0);
		Shelf shelf1 = new Shelf(1);
		Shelf shelf2 = new Shelf(2);

		shelf0.addItem(item1);
		shelf0.addItem(item1);
		shelf0.displayShelf();
		shelf0.getShelfWeight();
		shelf0.isFull();
		shelf0.willFit(item2);
		shelf0.addItem(item2);
		shelf0.isFull();
		shelf0.willFit(item3);
		shelf0.getShelfNumber();
		shelf1.getShelfNumber();
		shelf2.getShelfNumber();
		shelf0.displayShelf();
		shelf0.removeItem(item1);
		shelf0.displayShelf();

	}

}
