package warehouse;

import org.junit.*;

public class FloorTest {

	@Test
	public void RunFloorTests() {
		int numShelves = 10;
		Floor floor = new Floor(numShelves);
		
		Assert.assertArrayEquals(new int[] {10, 0}, floor.getChargeLocation());
		Assert.assertArrayEquals(new int[] {0, 0}, floor.getPickLocation());
		
		Assert.assertEquals(6, floor.getWidth());
		Assert.assertEquals(numShelves / 2 + 6, floor.getHeight());
	}
	
}
