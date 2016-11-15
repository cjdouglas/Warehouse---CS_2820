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

import java.util.List;
import java.awt.Point;
import java.util.HashMap;
import java.util.Set;

public class Floor {
    
	private final int width = 8;
	private final int height = 8;
	
	private HashMap<Integer, Point> locations;
	private List<Point> beltArea;
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
	 * Initializes the locations for the floor
	 */
	private void initFloor() {
		
		int counter = 0;
		
		for (int i = 2; i < 6; i++) {
			locations.put(counter++, new Point(2, i));
			locations.put(counter++, new Point(5, i));
		}
		
		chargeLocation = new Point(0, height - 1);
		//--------BELT AREA-----------
		pickLocation = new Point(0, 4);
		packLocation = new Point(0, 5);
		beltLocation = new Point(0, 6);
		beltArea.add(pickLocation);
		beltArea.add(new Point(0,3));
		beltArea.add(new Point(1,3));
		beltArea.add(new Point(1,4));
		beltArea.add(new Point(1,5));
		beltArea.add(packLocation);
		//----------------------------
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
	
	/**
	 * Returns the Point representing the charge location
	 * @return The Point representing the charge location
	 */
	public Point getChargeLocation() {
		return chargeLocation;
	}
	
	/**
	 * Returns the Point representing the pick location
	 * @return The Point representing the pick location
	 */
	public Point getPickLocation() {
		return pickLocation;
	}
	
	/**
	 * Returns the Point representing the pack location
	 * @return The Point representing the pack location
	 */
	public Point getPackLocation() {
		return packLocation;
	}
	
	/**
	 * Returns the Point representing the belt location
	 * @return The Point representing the belt location
	 */
	public Point getBeltLocation() {
		return beltLocation;
	}
	
	/**
	 * Returns the Point representing the shipping dock location
	 * @return The Point representing the shipping dock location
	 */
	public Point getShippingDockLocation() {
		return shippingDock;
	}
	
	/**
	 * Returns the Point representing the receiving dock location
	 * @return The Point representing the receiving dock location
	 */
	public Point getReceivingDockLocation() {
		return receivingDock;
	}
	
	/**
	 * Returns the width of the floor
	 * @return The width of the floor
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the height of the floor
	 * @return The height of the floor
	 */
	public int getHeight() {
		return height;
	}
	
	public Set<Integer> keySet() {
		return locations.keySet();
	}
	
	public Point locAt(int x) {
		return locations.get(x);
	}

	public List<Point> getBeltArea() {
		return beltArea;
	}
}