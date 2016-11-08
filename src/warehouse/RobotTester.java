package warehouse;

import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class RobotTester {

	/**
	 * Runs tests on the Robot class
	 */
	@Test
	public void runRobotTests() {
		int[] startPos = { 0, 0 };
		Robot r = new Robot(startPos);
		int[] testPos = { 1, 1 };

		// Test getCurrentPosition()
		Assert.assertEquals("X coordinates are not equal: ", testPos[0], r.getCurrentPosition()[0]);
		Assert.assertEquals("Y Coordinates are not equal: ", testPos[1], r.getCurrentPosition()[1]);

		// Test moveRight()
		testPos[0]++;
		r.moveRight();
		Assert.assertEquals("X coordinates are not equal: ", testPos[0], r.getCurrentPosition()[0]);

		// Test moveLeft()
		testPos[0]--;
		r.moveLeft();
		Assert.assertEquals("X coordinates are not equal: ", testPos[0], r.getCurrentPosition()[0]);

		// Test moveDown()
		testPos[1]--;
		r.moveDown();
		Assert.assertEquals("Y Coordinates are not equal: ", testPos[1], r.getCurrentPosition()[1]);

		// Test moveUp()
		testPos[1]++;
		r.moveUp();
		Assert.assertEquals("Y Coordinates are not equal: ", testPos[1], r.getCurrentPosition()[1]);

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
