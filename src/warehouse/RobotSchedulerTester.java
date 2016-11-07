package warehouse;

import org.junit.Assert;
import org.junit.Test;

public class RobotSchedulerTester {
	/**
	 * Runs tests on the RobotScheduler class.
	 */
	@Test
	public void runRobotSchedulerTests() {
		RobotScheduler rs = new RobotScheduler(new Floor(), 1);

		// Test createRobots
		int i = 0;
		for (Robot r : rs.getRobotList()) {
			int[] coord = { i, 0 };
			Assert.assertEquals("createRobots incorrect", coord, r.getCurrentPosition());
			i++;
		}

		// Test notOccupied
		int[] loc = { 5, 5 };
		Assert.assertEquals("notOccupied incorrect", true, rs.notOccupied(loc));
		loc[0] = 1;
		loc[1] = 0;
		Assert.assertEquals("notOccupied incorrect", false, rs.notOccupied(loc));

		// Test moveTowardTarget
		Robot testBot = new Robot(loc);
		int[] target = { 3, 2 };
		testBot.setTarget(target);
		while (testBot.getCurrentPosition() != testBot.getTarget()) {
			rs.moveTowardTarget(testBot);
		}
		Assert.assertEquals("moveTowardTarget incorrect", target, testBot.getCurrentPosition());
	}
}
