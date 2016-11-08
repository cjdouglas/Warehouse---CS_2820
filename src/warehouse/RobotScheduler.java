package warehouse;

import java.util.LinkedList;

/**
 * @author Ben East
 *
 *         Group A2 - RobotScheduler class
 */
public class RobotScheduler {
	// Instance Variables
	private LinkedList<Robot> robotList;
	private Floor floor;

	/**
	 * Constructs a new robotScheduler object.
	 * 
	 * @param f
	 *            The floor of the warehouse to be navigated.
	 * @param numBots
	 *            The number of robots to be created.
	 */
	public RobotScheduler(Floor f, int numBots) {
		this.floor = f;
		this.robotList = new LinkedList<Robot>();
		createRobots(numBots);
	}

	/**
	 * Returns the list of robots in the warehouse. Made for testing purposes.
	 * 
	 * @return The list of robots in the warehouse.
	 */
	public LinkedList<Robot> getRobotList() {
		return this.robotList;
	}

	// TODO handle the case that all robots are occupied at the moment
	/**
	 * Assigns the shelves to robots
	 * 
	 * @param shelvesNeeded
	 */
	public void assignOrder(LinkedList<Shelf> shelvesNeeded) {
		for (Shelf s : shelvesNeeded) {
			if (robotHasShelf(s)) {
				for (Robot r : robotList) {
					// Find the robot that has the shelf and make it go to the
					// pick station
					if (r.getCurrentShelf().equals(s)) {
						r.setTarget(floor.getPickLocation());
						r.setBusy(true);
						shelvesNeeded.remove(s);
					}
				}
			} else {
				for (Robot r : robotList) {
					if (r.getCurrentShelf().equals(null)) {
						if (r.isCharged() && !r.isBusy()) {
							// if the robot is charged and not busy, make it get
							// the shelf.
							r.setTarget(this.floor.getShelfLocation(s));
							r.setBusy(true);
							shelvesNeeded.remove(s);
						}
					}
				}
			}
		}
	}

	/**
	 * Advances each robot 1 step toward their target, and makes them perform an
	 * action if they are at their target.
	 */
	public void advanceRobots() {
		for (Robot r : robotList) {
			int[] currentPos = r.getCurrentPosition(), targetPos = r.getTarget();

			if (!currentPos.equals(targetPos)) {
				// If the robot isn't at it's target, make it move.
				moveTowardTarget(r);
			} else { // If the robot is at the target
				if (targetPos.equals(this.floor.getChargeLocation())) {
					// If at a charge location, recharge the robot and mark it
					// not busy, then move it away from the charge location.
					r.recharge();
					r.setBusy(false);
					int[] curPos = r.getCurrentPosition().clone();
					curPos[1] += 2;
					r.setTarget(curPos);
				} else if (targetPos.equals(this.floor.getPickLocation())) {
					// Once we reach the pick station, don't move, and set your
					// new target to an empty space.
					r.setTarget(this.floor.getEmptyShelfLocation());
				} else if (targetPos.equals(this.floor.getEmptyShelfLocation())) {
					// When the robot drops the shelf, make it go recharge.
					r.dropShelf();
					r.setTarget(this.floor.getChargeLocation());
					r.setBusy(true);
				} else if (!this.floor.getShelfAt(targetPos).equals(null) && r.isBusy()) {
					// If the robot reaches it's target, there's a shelf there,
					// and it's busy, make it grab the shelf.
					r.grabShelf(this.floor.getShelfAt(targetPos));
				}
			}
		}
	}

	// TODO Update movement to make use of highways and shelving areas.
	/**
	 * Determines which direction the robot should move, and moves there if not
	 * occupied.
	 * 
	 * @param r
	 *            The robot to be moved.
	 */
	protected void moveTowardTarget(Robot r) {
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
	 * Checks if a robot is holding the given shelf.
	 * 
	 * @param s
	 *            The shelf that needs to be found.
	 * @return Returns true if a robot is holding the shelf, and false
	 *         otherwise.
	 */
	protected boolean robotHasShelf(Shelf s) {
		for (Robot r : robotList) {
			if (r.getCurrentShelf().equals(s)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @param location
	 *            The location to be checked.
	 * @return Returns true if no robot is at the location, and false otherwise.
	 */
	protected boolean notOccupied(int[] location) {
		for (Robot r : robotList) {
			if (r.getCurrentPosition().equals(location)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates all of the robots for the warehouse. For use in the constructor
	 * only.
	 * 
	 * @param numBots
	 *            The number of robots to be created.
	 */
	protected void createRobots(int numBots) {
		for (int i = 0; i < numBots; ++i) {
			int[] robotPos = { i, 0 };
			robotList.add(new Robot(robotPos));
		}
	}
}
