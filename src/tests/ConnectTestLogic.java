package tests;

import main.JDBCconnect;


public class ConnectTestLogic {
	private JDBCconnect connect = new JDBCconnect();
	
	
	// test to check connection
	public boolean connection(double lat, double lon, int distance) {
		connect.getFinalBreweries(lat, lon, distance);
		return true;
	}
		
	
	// test to check if there is any data
	public boolean breweriesList(double lat, double lon, int distance) {
		if (!connect.getFinalBreweries(lat, lon, distance).isEmpty())
			return true;
		return false;
	}
}
