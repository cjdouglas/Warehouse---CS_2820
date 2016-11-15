package warehouse;

import java.util.HashSet;
import java.util.ArrayList;

/** @author Liam Crawford
 * 
 * Group 2A - Master Class
 *
 *The MasterController class is a simulation of a warehouse. It creates instances of
 *floor, robotScheduler, and visualizer after being handed an inventory and an OrderSystem
 *(which determines "events").
 *
 */

public class MasterController implements Tickable {
	
	Inventory I;
	Floor F;
	RobotScheduler RS;
	Visualizer V;
	OrderSystem OS;
	Belt B;
	
	// static int numShelves = 15;
	static int numRobots = 13;
	
	/*
	 * @author Liam Crawford
	 * @param I
	 * Given an inventory that has been pre-stocked and knows shelf locations of items
	 * @param OS
	 * Given an OrderSystem that will determine when orders have been "submitted"
	 */
	
	public MasterController(Inventory I, OrderSystem OS){
		this.I = I;
		this.OS = OS;
		this.F = new Floor();
		this.RS = new RobotScheduler(F, numRobots);
		this.V = new Visualizer(F, RS.getRobotList());			
	}
	
	
	/*
	 * @author Liam Crawford 
	 * @param numTime
	 * 				number of time units
	 * 
	 * ticks the entire system forward the number of specified units of time
	 * 
	 */
	
	public void advance(int numTime){
		for(int i=0; i<numTime; i++){
			tick();
		}
	}
	
	/*
	 * @author Liam Crawford
	 * @param none
	 * Ticks forward the system in the following order:
	 * 
	 * Check belt to see if there is a complete order ready to be shipped
	 * tick -> belt
	 * tick -> order system
	 * If the OS has an order event, takes the needed shelves and hands them to RS
	 * If the inventory needs a restock, takes the needed shelves and hands them to RS
	 * tick -> RobotScheduler
	 * Hand updated robot positions to the visualizer
	 * tick/update -> Visualizer
	 * 
	 */
	
	public void tick(){
		HashSet<Shelf> orderShelves;
		ArrayList<Item> orderItems;
		ArrayList<Shelf> restockShelves;
		ArrayList<Item> pickedItems;
		
		
		// check to see if items can be added to the bin
		if(RS.robotsAtPicker()){
			pickedItems = I.pickItems(RS.shelvesAtPicker());
			B.addItemsToBin(pickedItems);
		}
		
		// check to see if the next order can start to be fulfilled
		// when we implement more than one picking station, this will be updated
		if(B.isOrderComplete()){
			OS.OrderComplete();
		}
		
		// tick Belt and OrdeingSystem
		B.tick();
		OS.tick();
		
		// new Order to hand to the scheduler?
		if(OS.isOrder()){
			orderShelves = OS.getOrderShelves();
			B.newOrder(orderItems);
			RS.assignOrder(orderShelves);
		}
		
		// restock shelves if they're at the right spot
		if(RS.robotsAtRestock()){
			I.restockShelves(RS.shelvesAtRestock());}
		
		// anything need restocked? tell RS
		if(I.needRestock()){
			restockShelves = I.getRestockShelves();
			for(Shelf rs: restockShelves){
				if(!RS.restockingNow(rs)){
					RS.restock(rs);}
			}
		}
		
		// tick and update RS and V
		RS.tick();
		V.setRobotList(RS.getRobotList());
		V.tick();
	}
	
	/*
	 * @author Liam Crawford
	 * @param none
	 * outputs diagnostics of the system
	 */
	
	//public String toString(){	
	//}
	
	
	
}
