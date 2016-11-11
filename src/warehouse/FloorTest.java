package warehouse;

import java.awt.Point;

import org.junit.*;

public class FloorTest {

	@Test
	public void RunFloorTests() {
		Floor floor = new Floor();
		
		for (int x : floor.keySet()) {
			System.out.println(floor.locAt(x));
		}
		
		/*
		 * chargeLocation = new Point(0, height - 1);
		pickLocation = new Point(0, 4);
		packLocation = new Point(0, 5);
		beltLocation = new Point(0, 6);
		shippingDock = new Point(0, 7);
		receivingDock = new Point(width - 1, height - 1);
		 */
		
		Assert.assertEquals(new Point(0, floor.getHeight() - 1), floor.getChargeLocation());
		Assert.assertEquals(new Point(0, 4), floor.getPickLocation());
		Assert.assertEquals(new Point(0, 5), floor.getPackLocation());
		Assert.assertEquals(new Point(0, 6), floor.getBeltLocation());
		Assert.assertEquals(new Point(0, 7), floor.getShippingDockLocation());
		Assert.assertEquals(new Point(floor.getWidth() - 1, floor.getHeight() - 1), floor.getReceivingDockLocation());
	}
	
}
