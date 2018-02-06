package tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class ConnectTest {
	private final int FUEL_LIMIT = 2000;
	private double lat[] =
		{ 51.355468, 30.22340012, 37.78250122 };
	private double lon[] =
		{ 11.100790, -97.7696991, -122.3929977 };
	
	private ConnectTestLogic test = new ConnectTestLogic();
	
	
	// test connection
	@Test
	public void testConnection() {
		assertTrue(test.connection(51.355468, 11.100790, FUEL_LIMIT));
	}
	
	
	// test data
	@Test
	public void testBreweriesList() {
		for (int i = 0; i < lat.length; i++)
			assertTrue(test.breweriesList(lat[i], lon[i], FUEL_LIMIT));
	}
}
