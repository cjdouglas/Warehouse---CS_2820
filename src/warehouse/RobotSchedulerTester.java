package warehouse;

import java.awt.Point;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Ben East
 *
 * Runs tests on the RobotScheduler class
 * 
 */
public class RobotSchedulerTester {
	/**
	 * @author Ben East
	 * Runs tests on the RobotScheduler class.
	 */
	@Test
	public void runRobotSchedulerTests() {
		RobotScheduler rs = new RobotScheduler(new Floor(1), 1);

		// Test createRobots
		int i = 0;
		for (Robot r : rs.getRobotList()) {
			Point coord = new Point(i, 0);
			Assert.assertEquals("createRobots incorrect", coord, r.getCurrentPosition());
			i++;
		}

		// Test notOccupied
		Point loc = new Point(5, 5);
		Assert.assertEquals("notOccupied incorrect", true, rs.notOccupied(loc));
		loc.setLocation(1, 0);
		Assert.assertEquals("notOccupied incorrect", false, rs.notOccupied(loc));

		// Test moveTowardTarget
		Robot testBot = new Robot(loc);
		Point target = new Point(3, 2);
		testBot.setTarget(target);
		while (testBot.getCurrentPosition() != testBot.getTarget()) {
			rs.moveTowardTarget(testBot);
		}
		Assert.assertEquals("moveTowardTarget incorrect", target, testBot.getCurrentPosition());
	}
}
