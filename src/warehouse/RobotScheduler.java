package warehouse;

import java.util.LinkedList;

/**
 * @author Ben East
 *
 *         Group A2 - RobotScheduler class
 */
public class RobotScheduler {
	private LinkedList<Robot> robotList;
	
	private Floor floor;

	/**
	 * Constructs a new robotScheduler object.
	 * 
	 * @param f
	 *            The floor of the warehouse to be navigated.
	 */
	public RobotScheduler(Floor f) {
		this.floor = f;
		this.robotList = new LinkedList<Robot>();
		createRobots();
	}

	// NOTE: THIS DOES NOT HANDLE THE EVENT THAT A ROBOT ALREADY HAS THE SHELF
	// AT THIS TIME
	/**
	 * Assigns an order to the robots by finding shelves and making robots grab
	 * them.
	 * 
	 * @param o
	 *            The order to be assigned.
	 */
	public void assignOrder(Order o) {
		LinkedList<int[]> shelvesNeeded = findShelvesNeeded(o);

		// Find the first available robot
		// while (!shelvesNeeded.isEmpty()) {
		for (int[] shelf : shelvesNeeded) {
			for (Robot r : robotList) {
				// If we find a robot that is charged and not busy, make it
				// get the shelf
				if (!r.isBusy() && r.isCharged()) {
					r.setTarget(shelf);
					r.toggleBusy();
					shelvesNeeded.remove(shelf);
				} else if (!r.isCharged()) {
					// If r isn't busy or charged, make it recharge
					if (!r.isBusy() && !r.isCharged()) {
						r.setTarget(this.floor.getChargeLocation());
						r.toggleBusy();
					}
				}
			}
		}
		// }
	}

	/**
	 * Advances each robot 1 step toward their target, and makes them perform an
	 * action if they are at their target.
	 */
	public void advanceRobots() {
		for (Robot r : robotList) {
			int[] currentPos = r.getCurrentPosition(), target = r.getTarget();

			if (!currentPos.equals(target)) {
				moveTowardTarget(r);
			} else if (currentPos.equals(target)) {
				if (target.equals(this.floor.getChargeLocation())) {
					r.recharge();
					r.toggleBusy();
				} else if (target.equals(this.floor.getPickLocation())) {
					r.setTarget(this.floor.getEmptyShelfLocation());
				}
			}
		}

	}

	// NOTE: MUST BE UPDATED TO MAKE USE OF HIGHWAYS/AVOID SHELVES WHEN
	// NECESSARY
	/**
	 * Determines which direction the robot should move, and moves there if not
	 * occupied.
	 * 
	 * @param r
	 *            The robot to be moved.
	 */
	public void moveTowardTarget(Robot r) {
		int[] currentPos = r.getCurrentPosition(), target = r.getTarget();

		if (currentPos[0] < target[0]) { // RIGHT
			int[] potentialLocation = { currentPos[0] + 1, currentPos[1] };
			if (notOccupied(potentialLocation) && currentPos[0] != this.floor.getWidth()) {
				r.moveRight();
				return;
			}
		}

		if (currentPos[0] > target[0]) { // LEFT
			int[] potentialLocation = { currentPos[0] - 1, currentPos[1] };
			if (notOccupied(potentialLocation) && currentPos[0] != 0) {
				r.moveLeft();
				return;
			}
		}

		if (currentPos[1] < target[1]) { // UP
			int[] potentialLocation = { currentPos[0], currentPos[1] + 1 };
			if (notOccupied(potentialLocation) && currentPos[1] != this.floor.getHeight()) {
				r.moveUp();
				return;
			}
		}

		if (currentPos[1] > target[1]) { // DOWN
			int[] potentialLocation = { currentPos[0], currentPos[1] - 1 };
			if (notOccupied(potentialLocation) && currentPos[1] != 0) {
				r.moveDown();
				return;
			}
		}
	}

	/**
	 * @param location
	 *            The location to be checked.
	 * @return Returns true if no robot is at the location, and false otherwise.
	 */
	public boolean notOccupied(int[] location) {
		for (Robot r : robotList) {
			if (r.getCurrentPosition().equals(location)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Finds the shelves necessary to fulfill the given order.
	 * 
	 * @param o
	 *            The order to be fulfilled.
	 * @return A LinkedList of shelf coordinate values.
	 */
	public LinkedList<int[]> findShelvesNeeded(Order o) {
		LinkedList<int[]> shelvesNeeded = new LinkedList<int[]>();
		for (Item i : o.items) {
			int[] shelfPos = findShelfByItem(i);
			if (!shelvesNeeded.contains(shelfPos)) {
				shelvesNeeded.add(shelfPos);
			} else {
				// prioritize the shelf
			}
		}
		return shelvesNeeded;
	}

	/**
	 * Finds the coordinates of the shelf that contains the given item.
	 * 
	 * @param i
	 *            The item to be found on a shelf.
	 * @return The coordinates of the shelf that contains the given item.
	 */
	public int[] findShelfByItem(Item i) {
		for (Robot r : robotList) {
			if (r.getCurrentShelf().contains(i)) {
				return r.getCurrentPosition();
			}
		}
		return this.floor.find(i);
	}

	/**
	 * Creates all of the robots for the warehouse. For use in the constructor
	 * only.
	 */
	public void createRobots() {
		for (int i = 0; i < 5; ++i) {
			int[] robotPos = { i, 0 };
			robotList.add(new Robot(robotPos));
		}
	}

	/**
	 * Returns the list of robots in the warehouse. Made for testing purposes.
	 * 
	 * @return The list of robots in the warehouse.
	 */
	public LinkedList<Robot> getRobotList() {
		return this.robotList;
	}
}
