package warehouse;

import org.junit.*;

public class FloorTest {

	@Test
	public void RunFloorTests() {
		int numShelves = 8;
		Floor floor = new Floor(numShelves);
		
		Assert.assertArrayEquals(new int[] {0, 0}, floor.getChargeLocation());
		Assert.assertArrayEquals(new int[] {floor.getWidth() - 1, 0}, floor.getPickLocation());
		
		Assert.assertEquals(numShelves / 2, floor.getWidth());
		Assert.assertEquals(numShelves / 2 + 1, floor.getHeight());
	}
	
}
