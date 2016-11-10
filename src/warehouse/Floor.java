/**
 * @author Chris Douglas
 * Floor.java
 */

/* Example: a number of 10 shelves passed into the Floor
 * Key:
 * P = Picking Station
 * S = Shelf
 * C = Charging Station
 * Open Space = Open road Space

	|P|P|P|P|P|P|
	| | | | | | |
	| | | | | | |
	| | |S|S| | |
	| | |S|S| | |
	| | |S|S| | |
	| | |S|S| | |
	| | |S|S| | |
	| | | | | | |
	| | | | | | |
	|C|C|C|C|C|C|
	
 */


package warehouse;

import java.util.ArrayList;

public class Floor {
    
	private int numShelves;
	private int width;
	private int height;
	
	private int[] chargeLocation;
	private int[] pickLocation;
	
	private ArrayList< int[] > shelfLocations;
	private ArrayList< int[] > chargeLocations;
	private ArrayList< int[] > pickLocations;
	
	/**
	 * Initializer for the Floor class
	 * @param numShelves The number of shelves being passed into the Floor
	 */
	public Floor(int numShelves) {
		this.numShelves = numShelves;
		width = 6;
		height = (numShelves / 2) + 6;
		
		chargeLocation = new int[] {0, 0};
		pickLocation = new int[] {width - 1, 0};
		
		shelfLocations = new ArrayList<>();
		chargeLocations = new ArrayList<>();
		pickLocations = new ArrayList<>();
		
		initFloor();
	}	
	
	/**
	 * Initializes the shelf locations for the floor
	 * based of the number passed to the constructor
	 * 
	 * Places shelves on the outer borders of the floor.
	 * Splits them up, half on the left border, half on the right
	 */
	private void initFloor() {
		for (int row = 2; row <= 3; row++) {
			for (int col = 3; col <= numShelves / 2; col++) {
				shelfLocations.add(new int[] {row, col});
			}
		}
		
		for (int i = 0; i < width; i++) {
			pickLocations.add(new int[] {i, 0});
			chargeLocations.add(new int[] {height - 1, i});
		}
	}
	
	/**
	 * Returns the list of shelf locations relative to this floor
	 * @return The list of shelf locations
	 */
	public ArrayList< int[] > getShelfLocations() {
		return shelfLocations;
	}
	
	/**
	 * Returns the location of a given shelf based off its id
	 * @param shelf The shelf to find the location for
	 * @return The location of a given shelf object
	 */
	public int[] getShelfLocation(Shelf shelf) {
		return shelfLocations.get(shelf.shelfNumber); // Assuming shelves are numbered 0 -> n - 1
	}
	
	public int[] getChargeLocation() {
		return chargeLocations.get(0);
	}
	
	public int[] getPickLocation() {
		return pickLocations.get(0);
	}
	
	public Shelf getShelfAt(int[] loc) {
		return null; // Not sure if I'll have instances of Shelves at runtime
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	// getEmptyShelfLocation() ??
}