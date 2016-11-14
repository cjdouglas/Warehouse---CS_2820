/**
 * @author Chris Douglas
 * Floor.java
 */


/*
 * 
 */

/*
 * Key:
 * P = Picking Station
 * K = Packer
 * B = Belt 
 * H = Shipping dock
 * R = Receiving dock 
 * S = Shelf
 * C = Charging Station
 * Open Space = Open road Space

	 0 1 2 3 4 5 6 7
  0 | | | | |P|K|B|H|
  1 | | | | | | | | |
  2 | | |S| | |S| | |
  3 | | |S| | |S| | |
  4 | | |S| | |S| | |
  5 | | |S| | |S| | |
  6 | | | | | | | | |
  7 |C| | | | | | |R|
	
 */


package warehouse;

import java.awt.Point;
import java.util.HashMap;
import java.util.Set;

public class Floor {
    
	private final int width = 8;
	private final int height = 8;
	
	private HashMap<Integer, Point> locations;
	
	private Point chargeLocation;
	private Point pickLocation;
	private Point packLocation;
	private Point beltLocation;
	private Point shippingDock;
	private Point receivingDock;
	
	/**
	 * Initializer for the Floor class
	 * @param numShelves The number of shelves being passed into the Floor
	 */
	public Floor() {
		locations = new HashMap<>();
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
		
		int counter = 0;
		
		for (int i = 2; i < 6; i++) {
			locations.put(counter++, new Point(2, i));
			locations.put(counter++, new Point(5, i));
		}
		
		chargeLocation = new Point(0, height - 1);
		pickLocation = new Point(0, 4);
		packLocation = new Point(0, 5);
		beltLocation = new Point(0, 6);
		shippingDock = new Point(0, 7);
		receivingDock = new Point(width - 1, height - 1);
	}
	
	/**
	 * Returns the location of a given shelf based off its id
	 * @param shelf The shelf to find the location for
	 * @return The location of a given shelf object
	 */
	public Point getShelfLocation(Shelf shelf) {
		return locations.get(shelf.getShelfNumber());
	}
	
	public Point getChargeLocation() {
		return chargeLocation;
	}
	
	public Point getPickLocation() {
		return pickLocation;
	}
	
	public Point getPackLocation() {
		return packLocation;
	}
	
	public Point getBeltLocation() {
		return beltLocation;
	}
	
	public Point getShippingDockLocation() {
		return shippingDock;
	}
	
	public Point getReceivingDockLocation() {
		return receivingDock;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Set<Integer> keySet() {
		return locations.keySet();
	}
	
	public Point locAt(int x) {
		return locations.get(x);
	}
}