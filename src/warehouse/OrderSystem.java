package warehouse;

import java.util.HashSet;
import java.util.Queue;
import java.util.Random;

/*
 * @author Liam Crawford
 * 
 * Class OrderSystem takes a list of orders and creates events out of them,
 * complete with time delay. Also, the system is able to delay events if inventory is out 
 */

public class OrderSystem implements Tickable {
	
	Inventory I;
	Queue<Order> orders;
	HashSet<Shelf> orderShelves;
	Order nextOrder;
	Queue<Order> ordersBeingFulfilled;

	Random tilNext = new Random();
	int ticksTilNext;
	
	/*
	 * @author Liam Crawford
	 * @param I
	 * 		    An inventory that will be used to determine if the items in a particular
	 * 			order are in stock
	 * @param orders
	 * 			All of the orders that are be to fulfilled
	 */
	
	public OrderSystem(Inventory I, Queue<Order> orders){
		this.I = I;
		this.orders = orders;
		ticksTilNext = tilNext.nextInt(25);
	}
	
	/*
	 * @author Liam Crawford
	 * @param none
	 * Ticks forward the system and determines if there is an order to occur. If there is,
	 * it checks it against the inventory
	 */
	
	public void tick(){
		Order o;
		Boolean itemsInStock = true;
		
		if(ticksTilNext > 0){ticksTilNext -= 1;}		
		else{
			/*empty the orderShelves 
			
			//for item in order, check to see if it's in stock. if it is
			// add the item's shelf to orderShelves
			
			// if all are in stock, send the order to the robot
			// scheduler and add this to the orders to be fulfilled
			// queue
			
			// if all items are not in stock, return order to back of "orders"
			*/
			ticksTilNext = tilNext.nextInt(25);
		}
	}
	
	/*
	 * @author Liam Crawfod
	 * @param none
	 * returns true if the system has a new order for the master to relay
	 * elsewhere
	 */
	
	public Boolean isOrder(){
		if(nextOrder == null){return false;}
		return true;
	}
	
	/*
	 * @author Liam Crawford
	 * @param none
	 * returns a HashSet of the shelves needed to be handed to the RobotScheduler
	 */
	
	public HashSet<Shelf> getOrderShelves(){
		ordersBeingFulfilled.add(nextOrder);
		nextOrder = null;
		return orderShelves;
	}
	
	/*
	 * @author Liam Crawford
	 * @param none
	 * recognizes that the order has been fulfilled and pushes it out of the queue
	 * future iterations may output diagnostics as to how long it took to fulfill
	 * the order
	 */
	
	public void OrderComplete(){
		Order fo = ordersBeingFulfilled.remove();
	}
	
}
