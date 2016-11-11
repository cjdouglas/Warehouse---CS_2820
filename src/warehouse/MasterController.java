package warehouse;

/** @author Liam Crawford
 * 
 * Group 2A - Master Class
 *
 */

/**
 * Initialization of Master Controller:
 * Creates an instance of inventory by handing it a file of items
 * Creates an instance of Floor with the number of shelves by querying the created inventory
 * Creates a robot scheduler by handing it said floor and an arbitrary number of robots
 * Creates a visualizer by handing it the robot scheduler
 *
 */


/**
 * method tick()
 * 
 * If the OrderSystem has an order, takes item IDs and asks the inventory which shelves
 * Hands a HashSet of needed shelves to the robot scheduler as an order
 * Queries the robot scheduler for list of robots after a tick
 * Clone list of robots and hand to the visualizer
 * 
 *
 *
 */



public class MasterController implements Tickable {
	
	MasterController(
			
			
	}
	
	public static class MockInventory {
		MockInventory(String filename){}
		public static int getNumShelves(){return 30;}
	}
	
	public class MockVisualizer{
		MockVisualizer(RobotScheduler RS){}
	}
}
