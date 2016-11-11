package warehouse;

import java.util.LinkedList;
import java.awt.Point;

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
	 * @author Ben East
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
	 * @author Ben East
	 * Returns the list of robots in the warehouse. Made for testing purposes.
	 * 
	 * @return The list of robots in the warehouse.
	 */
	public LinkedList<Robot> getRobotList() {
		return this.robotList;
	}

	// TODO handle the case that all robots are occupied at the moment
	/**
	 * @author Ben East
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
	 * @author Ben East
	 * Advances each robot 1 step toward their target, and makes them perform an
	 * action if they are at their target.
	 */
	public void advanceRobots() {
		for (Robot r : robotList) {
			Point currentPos = r.getCurrentPosition(), targetPos = r.getTarget();

			if (!currentPos.equals(targetPos)) {
				// If the robot isn't at it's target, make it move.
				moveTowardTarget(r);
			} else { // If the robot is at the target
				if (targetPos.equals(this.floor.getChargeLocation())) {
					// If at a charge location, recharge the robot and mark it
					// not busy, then move it away from the charge location.
					r.recharge();
					r.setBusy(false);
					Point curPos = r.getCurrentPosition();
					r.moveUp();
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
	 * @author Ben East
	 * Determines which direction the robot should move, and moves there if not
	 * occupied.
	 * 
	 * @param r
	 *            The robot to be moved.
	 */
	protected void moveTowardTarget(Robot r) {
		Point currentPos = r.getCurrentPosition(), target = r.getTarget();

		if (currentPos.getX() < target.getX()) { // RIGHT
			Point potentialLocation = new Point((int) currentPos.getX() + 1, (int) currentPos.getY());
			if (notOccupied(potentialLocation) && currentPos.getX() != this.floor.getWidth()) {
				r.moveRight();
				return;
			}
		}

		if (currentPos.getX() > target.getX()) { // LEFT
			Point potentialLocation = new Point((int) currentPos.getX() - 1, (int) currentPos.getY());
			if (notOccupied(potentialLocation) && currentPos.getX() != 0) {
				r.moveLeft();
				return;
			}
		}

		if (currentPos.getY() < target.getY()) { // UP
			Point potentialLocation = new Point((int) currentPos.getX(), (int) currentPos.getY() + 1);
			if (notOccupied(potentialLocation) && currentPos.getY() != this.floor.getHeight()) {
				r.moveUp();
				return;
			}
		}

		if (currentPos.getY() > target.getY()) { // DOWN
			Point potentialLocation = new Point((int) currentPos.getX(), (int) currentPos.getY() - 1);
			if (notOccupied(potentialLocation) && currentPos.getY() != 0) {
				r.moveDown();
				return;
			}
		}
	}

	/**
	 * @author Ben East
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
	 * Creates all of the robots for the warehouse. For use in the constructor
	 * only.
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
}
