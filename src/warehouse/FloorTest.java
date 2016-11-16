package warehouse;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.*;

public class FloorTest {
	
	@Test
	public void RunFloorTests() {
		int numShelves = 8;
		ArrayList<Shelf> shelves = new ArrayList<Shelf>();
		
		for (int i = 0; i < numShelves; i++) {
			shelves.add(new Shelf(i));
		}
		
		Floor floor = new Floor(shelves);
		Shelf[] shelfArr = floor.getShelves();
		Point[] locArr = floor.getLocations();
		
		printArray(shelfArr);
		System.out.println();
		System.out.println("Removing shelf at (2, 4)");
		System.out.println("Removing shelf at (2, 2)");
		System.out.println();
		
		Shelf temp = floor.getShelfAt(new Point(2, 4));
		Shelf temp2 = floor.getShelfAt(new Point(2, 2));
		
		printArray(shelfArr);
		System.out.println();
		System.out.println("Returning first shelf");
		floor.placeShelf(temp);
		System.out.println("Returning second shelf");
		floor.placeShelf(temp2);
		
		printArray(shelfArr);
	}
	
	private void printArray(Object[] arr) {
		for (Object obj : arr) {
			System.out.println(obj);
		}
	}
}
