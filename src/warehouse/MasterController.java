package warehouse;

/** @author Liam Crawford
 * 
 * Group 2A - Master Class
 *
 */

/**
 * Tasks of Master Controller:
 * Creates an instance of inventory by handing it an file of items
 * Creates an instance of Floor with the number of shelves by querying the created inventory
 * Creates a robot scheduler by handing it said floor and an arbitrary number of robots
 * Creates a visulizer by handing it the robot scheduler
 *
 */

public class MasterController {
	// initialize the components
	MockInventory MI;
	Floor F;
	RobotScheduler RS;
	MockVisualizer MV;
	
	MasterController(String inventoryFile, int numRobots){
		MI = new MockInventory(inventoryFile);
		F = new Floor(MockInventory.getNumShelves());
		RS = new RobotScheduler(F ,numRobots);
	}
	
	public static class MockInventory {
		MockInventory(String filename){}
		public static int getNumShelves(){return 30;}
	}
	
	public class MockVisualizer{
		MockVisualizer(RobotScheduler RS){}
	}
}
