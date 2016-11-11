package warehouse;

import java.awt.Point;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Ben East
 * 
 * Runs tests on the robot class
 *
 */
@SuppressWarnings("deprecation")
public class RobotTester {

	/**
	 * @author Ben East
	 * Runs tests on the Robot class
	 */
	@Test
	public void runRobotTests() {
		Point startPos = new Point(0, 0);
		Robot r = new Robot(startPos);
		Point testPos = new Point(1, 1);

		// Test getCurrentPosition()
		Assert.assertEquals("X coordinates are not equal: ", testPos.getX(), r.getCurrentPosition().getX());
		Assert.assertEquals("Y Coordinates are not equal: ", testPos.getY(), r.getCurrentPosition().getY());

		// Test moveRight()
		testPos.setLocation(testPos.getX() + 1, testPos.getY());
		r.moveRight();
		Assert.assertEquals("X coordinates are not equal: ", testPos.getX(), r.getCurrentPosition().getX());

		// Test moveLeft()
		testPos.setLocation(testPos.getX() - 1, testPos.getY());
		r.moveLeft();
		Assert.assertEquals("X coordinates are not equal: ", testPos.getX(), r.getCurrentPosition().getX());

		// Test moveDown()
		testPos.setLocation(testPos.getX(), testPos.getY() - 1);
		r.moveDown();
		Assert.assertEquals("Y Coordinates are not equal: ", testPos.getY(), r.getCurrentPosition().getY());

		// Test moveUp()
		testPos.setLocation(testPos.getX(), testPos.getY() + 1);
		r.moveUp();
		Assert.assertEquals("Y Coordinates are not equal: ", testPos.getY(), r.getCurrentPosition().getY());

		Shelf testShelf = new Shelf(5);

		// Test grabShelf(Shelf s)
		r.grabShelf(testShelf);
		Assert.assertEquals("grabShelf incorrect", testShelf, r.getCurrentShelf());

		// Test hasShelf()
		Assert.assertEquals("hasShelf incorrect", true, r.hasShelf());

		// Test dropShelf()
		Assert.assertEquals("dropShelf incorrect", testShelf, r.dropShelf());
		Assert.assertEquals("dropShelf incorrect", null, r.dropShelf());

		// Test hasShelf()
		Assert.assertEquals("hasShelf incorrect", false, r.hasShelf());

		// Test getCurrentShelf()
		Assert.assertEquals("getCurrentShelf incorrect", null, r.getCurrentShelf());
		Assert.assertEquals("getCurrentShelf incorrect", testShelf, r.getCurrentShelf());

		// Test toggleBusy, isBusy
		Assert.assertEquals("isBusy incorrect", false, r.isBusy());
		r.setBusy(true);
		Assert.assertEquals("toggleBusy incorrect", true, r.isBusy());

		// Test recharge, isCharged
		Assert.assertEquals("isCharged incorrect", false, r.isCharged());
		r.recharge();
		Assert.assertEquals("recharge incorrect", true, r.isCharged());

		// Test getTarget, setTarget
		Assert.assertEquals("getTarget incorrect", null, r.getTarget());
		r.setTarget(testPos);
		Assert.assertEquals("setTarget incorrect", testPos, r.getTarget());
	}
}
