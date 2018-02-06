package main;

/*
* Class for calculation methods used in multiple classes
*/
public class Calculations {
	
	/* Finds distance between two points in kilometers
	 * @param lat1 - latitude of first point
	 * @param lat2 - latitude of second point
	 * @param lon1 - longitude of first point
	 * @param lon2 - longitude of second point
	 * @return - distance in kilometers
	 */
	public double haversineDistance(double lat1, double lat2, double lon1, double lon2) {
		final int R = 6371; // Radius of the earth
	 
		double latDistance = toRad(lat2-lat1);
		double lonDistance = toRad(lon2-lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
				Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
				Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return R * c;
	}
	 
	private Double toRad(Double value) {
		return value * Math.PI / 180;
	}
	
}
