package tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class CalculationsTest {
	private CalculationsTestLogic test = new CalculationsTestLogic();
	private double lat[] =
		{ 51.355468, 30.22340012, 37.78250122, 50.76679993, 59.74509811 };
	private double lon[] =
		{ 11.100790, -97.7696991, -122.3929977, 4.308100224, 10.21350002 };


	// test distance calculations
	@Test
	public void testDistance() {
		for (int i = 1; i < lat.length; i++)
			assertTrue(test.distance(lat[i-1], lon[i-1], lat[i], lon[i]));
	}
}
