package warehouse;

import java.util.HashSet;

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
	
	static int numShelves = 15;
	static int numRobots = 5;
	
	/*
	 * @author Liam Crawford
	 * 
	 * @param I
	 * Given an inventory that has been pre-stocked and knows shelf locations of items
	 * 
	 * @param OS
	 * Given an OrderSystem that will determine when orders have been "submitted"
	 * 
	 */
	
	public MasterController(Inventory I, OrderSystem OS){
		this.I = I;
		this.OS = OS;
		this.F = new Floor(numShelves);
		this.RS = new RobotScheduler(F, numRobots);
		this.V = new Visualizer(F, RS.getRobotList());			
	}
	
	
	/*
	 * @author Liam Crawford
	 * 
	 * @param numTime
	 * number of time units
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
	 * 
	 * @param none
	 * 
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
		Shelf[] restockShelves;
		
		if(B.isOrderComplete()){
			OS.OrderComplete();
		}
		
		B.tick();
		OS.tick();
		
		if(OS.isOrder()){
			orderShelves = OS.getOrderShelves();
			RS.assignOrder(orderShelves);
		}
		
		if(I.needRestock()){
			restockShelves = I.getRestockShelves();
			for(shelf rs: restockShelves){
				RS.restock(rs);
			}
		}
		
		RS.tick();
		V.giveRobotList(RS.getRobotList());
		V.tick();
	}
	
	/*
	 * @author Liam Crawford
	 * 
	 * @param none
	 * 
	 * outputs diagnostics of the system
	 */
	
	//public String toString(){	
	//}
	
	
	
}
