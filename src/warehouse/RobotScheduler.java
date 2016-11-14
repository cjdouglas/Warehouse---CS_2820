package warehouse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.awt.Point;

/**
 * @author Ben East
 *
 *         Group A2 - RobotScheduler class
 */
public class RobotScheduler implements Tickable {
	// Instance Variables
	private ArrayList<Robot> robotList;
	private HashSet<Shelf> shelvesForOrder;
	private PriorityQueue<Shelf> shelvesForRestock;
	private PriorityQueue<HashSet<Shelf>> pendingOrders;
	private Floor floor;

	/**
	 * @author Ben East Constructs a new robotScheduler object.
	 * 
	 * @param f
	 *            The floor of the warehouse to be navigated.
	 * @param numBots
	 *            The number of robots to be created.
	 */
	public RobotScheduler(Floor f, int numBots) {
		floor = f;
		this.robotList = new ArrayList<Robot>();
		this.shelvesForOrder = new HashSet<Shelf>();
		this.shelvesForRestock = new PriorityQueue<Shelf>();
		this.pendingOrders = new PriorityQueue<HashSet<Shelf>>();
		createRobots(numBots);
	}

	/**
	 * @author Ben East
	 * @return Returns the list of robots in the warehouse.
	 */
	public ArrayList<Robot> getRobotList() {
		return this.robotList;
	}

	/**
	 * @author Ben East
	 * 
	 *         Takes a new assignment of shelves, saves the list, and sends
	 *         robots to the pick location if they're already holding it.
	 * 
	 * @param shelvesNeeded
	 */
	public void assignOrder(HashSet<Shelf> shelvesNeeded) {
		// if a robot already has the shelf, reroute to the pick station.
		if (this.shelvesForOrder.isEmpty()) {
			for (Shelf s : shelvesNeeded) {
				for (Robot r : robotList) {
					if (r.getCurrentShelf().equals(s)) {
						r.setTarget(floor.getPickLocation());
						r.setBusy(true);
						shelvesNeeded.remove(s);
					}
				}
			}
			// Save the remaining shelves for future robots
			this.shelvesForOrder = shelvesNeeded;
		} else {
			this.pendingOrders.add(shelvesNeeded);
		}
	}

	/**
	 * @author Ben East
	 * @param s
	 *            The shelf to be restocked.
	 * 
	 *            This method allows the master to give the scheduler a shelf
	 *            that needs to be restocked. The scheduler will assign the
	 *            shelf on the next tick.
	 */
	public void restock(Shelf s) {
		// If a robot already has the shelf, make it go to the restock area.
		for (Robot r : this.robotList) {
			if (r.getCurrentShelf().equals(s)) {
				// make the robot go to the restocking area
				r.setTarget(floor.getShippingDockLocation());
				r.setBusy(true);
			}
		}
		// Otherwise, add to the list of shelves that need to be restocked.
		this.shelvesForRestock.add(s);
	}

	/**
	 * @author Ben East
	 * 
	 *         Assigns a shelf to an idle robot if there are shelves to be
	 *         assigned.
	 */
	protected void assignShelf(Robot r) {
		// Prioritize restock over the current order.
		if (!this.shelvesForRestock.isEmpty()) {
			r.setTarget(floor.getShelfLocation(this.shelvesForRestock.remove()));
			r.setBusy(true);
		} else if (!this.shelvesForOrder.isEmpty()) {
			Shelf nextShelf = this.shelvesForOrder.iterator().next();
			r.setTarget(floor.getShelfLocation(nextShelf));
			r.setBusy(true);
			this.shelvesForOrder.remove(nextShelf);
		} else if (this.shelvesForOrder.isEmpty() && !this.pendingOrders.isEmpty()) {
			this.shelvesForOrder = this.pendingOrders.remove();
			// since hasn't gotten a shelf, run through the above conditionals
			// again.
			assignShelf(r);
		}
	}

	/**
	 * @author Ben East
	 * 
	 *         Advances each robot 1 step toward their target, and makes them
	 *         perform an action if they are at their target.
	 */
	public void advanceRobots() {
		for (Robot r : robotList) {
			Point currentPos = r.getCurrentPosition(), targetPos = r.getTarget();

			if (!currentPos.equals(targetPos)) {
				// If the robot isn't at it's target, make it move.
				moveTowardTarget(r);
			} else { // If the robot is at the target
				if (targetPos.equals(floor.getChargeLocation())) {
					// If at a charge location, recharge and move away from
					// charge station
					r.recharge();
					r.setBusy(false);
					r.setTarget(floor.getShelfLocation(r.getCurrentShelf()));
				} else if (targetPos.equals(floor.getPickLocation())) {
					// Once we reach the pick station, set target to free space
					// in the shelving area.
					r.setTarget(floor.getShelfLocation(r.getCurrentShelf()));
				} else if (targetPos.equals(floor.getShelfLocation(r.getCurrentShelf()))) {
					// When the robot drops the shelf, make it go recharge.
					r.dropShelf();
					r.setTarget(floor.getChargeLocation());
					r.setBusy(true);
				} else if (r.isBusy()) { // because of shelf location tracking,
											// we can assume the shelf is here
											// if the robot is busy
					r.grabShelf(floor.getShelfAt(targetPos));
				}
			}
		}
	}

	// TODO Update movement to make use of highways and shelving areas.
	/**
	 * @author Ben East Determines which direction the robot should move, and
	 *         moves there if not occupied.
	 * 
	 * @param r
	 *            The robot to be moved.
	 */
	protected void moveTowardTarget(Robot r) {
		Point currentPos = r.getCurrentPosition(), target = r.getTarget();

		// Move right
		if (currentPos.getX() < target.getX()) {
			Point potentialLocation = new Point((int) currentPos.getX() + 1, (int) currentPos.getY());
			if (notOccupied(potentialLocation) && currentPos.getX() != floor.getWidth()) {
				r.moveRight();
				return; // Return to limit to one movement per method call.
			}
		}

		// Move left
		if (currentPos.getX() > target.getX()) {
			Point potentialLocation = new Point((int) currentPos.getX() - 1, (int) currentPos.getY());
			if (notOccupied(potentialLocation) && currentPos.getX() != 0) {
				r.moveLeft();
				return; // Return to limit to one movement per method call.
			}
		}

		// Move up
		if (currentPos.getY() < target.getY()) {
			Point potentialLocation = new Point((int) currentPos.getX(), (int) currentPos.getY() + 1);
			if (notOccupied(potentialLocation) && currentPos.getY() != floor.getHeight()) {
				r.moveUp();
				return; // Return to limit to one movement per method call.
			}
		}

		// Move down
		if (currentPos.getY() > target.getY()) {
			Point potentialLocation = new Point((int) currentPos.getX(), (int) currentPos.getY() - 1);
			if (notOccupied(potentialLocation) && currentPos.getY() != 0) {
				r.moveDown();
				return; // Return to limit to one movement per method call.
			}
		}
	}

	/**
	 * @author Ben East
	 * @param location
	 *            The location to be checked.
	 * @return Returns true if no robot is at the location, and false otherwise.
	 */
	protected boolean notOccupied(Point location) {
		for (Robot r : robotList) {
			if (r.getCurrentPosition().equals(location)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @author Ben East
	 * 
	 *         Creates all of the robots for the warehouse. For use in the
	 *         constructor only.
	 * 
	 * @param numBots
	 *            The number of robots to be created.
	 */
	protected void createRobots(int numBots) {
		for (int i = 0; i < numBots; ++i) {
			Point robotPos = new Point(i, 0);
			robotList.add(new Robot(robotPos));
		}
	}

	/**
	 * @author Ben East
	 * @param time
	 *            The position in time the simulation is at. Allows the
	 *            RobotScheduler to take a step forward in time.
	 */
	public void tick() {
		// Assign shelves to free robots
		for (Robot r : this.robotList) {
			if (!r.isBusy() && r.isCharged()) {
				// If the robot is ready to work, attempt to assign it a shelf.
				assignShelf(r);
			}
		}
		// Move each robot 1 step
		advanceRobots();
	}
}
