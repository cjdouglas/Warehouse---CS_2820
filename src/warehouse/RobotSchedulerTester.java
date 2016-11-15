package warehouse;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Ben East
 *
 *         Runs tests on the RobotScheduler class
 * 
 */
public class RobotSchedulerTester {
	/**
	 * @author Ben East Runs tests on the RobotScheduler class.
	 */
	@Test
	public void runRobotSchedulerTests() {
		RobotScheduler rs = new RobotScheduler(new Floor(), 5);

		// test getRobotList
		ArrayList<Robot> list = new ArrayList<Robot>();
		for (int i = 0; i < 5; ++i) {
			list.add(new Robot(new Point(i, 0)));
		}
		Assert.assertEquals("getRobotList incorrect", list, rs.getRobotList());

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
		loc.setLocation(4, 0);
		Assert.assertEquals("notOccupied incorrect", false, rs.notOccupied(loc));

		// Test moveTowardTarget
		Robot testBot = new Robot(loc);
		Point target = new Point(3, 2);
		testBot.setTarget(target);
		while (testBot.getCurrentPosition() != testBot.getTarget()) {
			rs.moveTowardTarget(testBot);
		}
		Assert.assertEquals("moveTowardTarget incorrect", target, testBot.getCurrentPosition());

		// test restock, restockingNow
		Shelf testShelf = new Shelf(5);
		Assert.assertEquals("restockingNow incorrect", false, rs.restockingNow(testShelf));
		rs.restock(testShelf);
		Assert.assertEquals("restock incorrect", true, rs.restockingNow(testShelf));

		// test assignOrder, assignShelf
		
	}
}
