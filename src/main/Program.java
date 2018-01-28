package main;

import java.util.ArrayList;

public class Program {

	private final int FUEL_LIMIT = 2000;
	
	private final double latitude = 51.355468;
	private final double longitude = 11.100790;

	Calculations calc = new Calculations();
	private ArrayList<BreweriesFull> breweries = new ArrayList<>();
	double distanceMatrix[][];
	
	
	// Sets starting coordinates
	public void getCoordinates() {
		// to do: read from file
		breweries.add(new BreweriesFull(0, "starting point", latitude, longitude));
	}
	
	
	// Adds breweries from DB to list
	public void fillBreweries() {
		JDBCconnect data = new JDBCconnect();
		ArrayList<BreweriesFull> breweriesTemp = data.getFinalBreweries(latitude, longitude, FUEL_LIMIT);
		
		for (int i = 0; i < breweriesTemp.size(); i++) {
			breweries.add(breweriesTemp.get(i));
		}
	}
	
	
	// Calculates distances between breweries (including starting point)
	private void findDistances() {
		distanceMatrix = new double[breweries.size()][breweries.size()];
		double distance = 0;
		int n = 1;

		for (int i = 0; i < breweries.size(); i++) {
			for (int j = n; j < breweries.size(); j++) {
				distance = calc.HaversineDistance(breweries.get(i).getLatitude(), breweries.get(j).getLatitude(),
						breweries.get(i).getLongitude(), breweries.get(j).getLongitude());
				distanceMatrix[i][j] = distance;
				distanceMatrix[j][i] = distance;
			}
			n++;
		}
	}
	
	
	// Find best route
	public void findBreweries() {
		
		
	}
	
	
	// Program execution
	public void execute() {
		getCoordinates();
		fillBreweries();
		findDistances();		
	}
	
	
	public static void main(String[] args) {
		Program p = new  Program();
		p.execute();
		System.out.println("Program finished...");
    }
	
}
