package warehouse;

/**
 * @author Ben East
 *
 *         Group A2 - Robot class
 */
public class Robot {
	// Instance variables
	private int[] currentPosition;
	private int[] targetPosition;
	private Shelf currentShelf;
	private boolean charged;
	private boolean busy;

	/**
	 * Constructs a new robot object.
	 * 
	 * @param position
	 *            The current position of the new robot.
	 */
	public Robot(int[] position) {
		this.currentPosition = position;
		this.targetPosition = null;
		this.charged = true;
		this.busy = false;
	}

	/**
	 * @return Returns the currentPosition (x, y) coordinate pair for the robot
	 */
	protected int[] getCurrentPosition() {
		return this.currentPosition;
	}

	/**
	 * @return Returns the shelf that the robot is currently holding, or null if
	 *         the robot isn't holding a shelf.
	 */
	protected Shelf getCurrentShelf() {
		if (this.hasShelf()) {
			return this.currentShelf;
		} else {
			return null;
		}
	}

	/**
	 * Makes the robot "grab" a shelf from the warehouse.
	 * 
	 * @param s
	 *            The shelf the Robot needs to grab.
	 */
	protected void grabShelf(Shelf s) {
		this.currentShelf = s;
	}

	/**
	 * @return Returns true if the robot is holding a shelf, and false
	 *         otherwise.
	 */
	protected boolean hasShelf() {
		if (this.currentShelf.equals(null)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Sets the robot's busy value to the given parameter val.
	 * 
	 * @param val
	 *            The busy status of the robot.
	 */
	protected void setBusy(boolean val) {
		this.busy = val;
	}

	/**
	 * @return Returns true if the robot is busy, and false otherwise.
	 */
	protected boolean isBusy() {
		return this.busy;
	}

	/**
	 * Sets the robot's charged value to true.
	 */
	protected void recharge() {
		this.charged = true;
	}

	/**
	 * @return Returns true if the robot is charged, and false otherwise.
	 */
	protected boolean isCharged() {
		return this.charged;
	}

	/**
	 * Makes the robot "drop" it's shelf at it's current position.
	 * 
	 * @return Returns the shelf that the robot was previously carrying.
	 */
	protected Shelf dropShelf() {
		// If we're holding a shelf, put it down and give the scheduler the
		// shelf info the scheduler will inform the floor of the new shelf at
		// this.currentPosition.
		if (hasShelf()) {
			Shelf tempShelf = this.currentShelf;
			this.currentShelf = null;
			this.charged = false;

			return tempShelf;
			// If not, don't give it a shelf.
		} else {
			return null;
		}
	}

	/**
	 * Sets the robot's target position to the given target coordinates.
	 * 
	 * @param target
	 *            The coordinates the robot should move toward.
	 */
	protected void setTarget(int[] target) {
		this.targetPosition = target;
	}

	/**
	 * @return Returns the current target coordinates of the robot.
	 */
	protected int[] getTarget() {
		return this.targetPosition;
	}

	/**
	 * Decrements the robot's x coordinate value to "move" it one step to the
	 * left.
	 */
	protected void moveLeft() {
		this.currentPosition[0]--;
	}

	/**
	 * Increments the robot's x coordinate value to "move" it one step to the
	 * right.
	 */
	protected void moveRight() {
		this.currentPosition[0]++;
	}

	/**
	 * Increments the robot's y coordinate value to "move" it one step up.
	 */
	protected void moveUp() {
		this.currentPosition[1]++;
	}

	/**
	 * Decrements the robot's y coordinate value to "move" it one step down.
	 */
	protected void moveDown() {
		this.currentPosition[1]--;
	}
}
