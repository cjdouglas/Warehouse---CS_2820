package warehouse;

import java.util.ArrayList;

public class Floor {
    
	private int numShelves;
	private int width;
	private int height;
	
	private int[] chargeLocation;
	private int[] pickLocation;
	
	private ArrayList< int[] > shelfLocations;
	
	/**
	 * Initializer for the Floor class
	 * @param numShelves The number of shelves being passed into the Floor
	 */
	public Floor(int numShelves) {
		this.numShelves = numShelves;
		width = numShelves / 2;
		height = numShelves / 2 + 1;
		
		chargeLocation = new int[] {0, 0};
		pickLocation = new int[] {width - 1, 0};
		
		shelfLocations = new ArrayList<>();
		
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
		for (int i = 1; i < height; i++) {
			shelfLocations.add( new int[] {0, i} );
			shelfLocations.add( new int[] {width - 1, i} );
		}
	}
	
	public ArrayList< int[] > getShelfLocations() {
		return shelfLocations;
	}
	
	public int[] getShelfLocation(Shelf shelf) {
		return shelfLocations.get(shelf.getId()); // Assuming shelves are numbered 0 -> n - 1
	}
	
	public int[] getChargeLocation() {
		return chargeLocation;
	}
	
	public int[] getPickLocation() {
		return pickLocation;
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