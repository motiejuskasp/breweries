package tests;

import main.Calculations;


public class CalculationsTestLogic {
	private Calculations calc = new Calculations();

	// test to see if distances are same when input reversed
	public boolean distance(double lat1, double lon1, double lat2, double lon2) {
		// if error less than 1cm assume results are equal
		if (calc.haversineDistance(lat1, lat2, lon1, lon2) - calc.haversineDistance(lat2, lat1, lon2, lon1) < 0.00001)
			return true;
		return false;
	}
}
