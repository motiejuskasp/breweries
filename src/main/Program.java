package main;

import java.util.ArrayList;
import java.util.Stack;

/*
* Main class to run program
*/
public class Program {

	private final int FUEL_LIMIT = 2000;
	
	private final double latitude = 51.355468;
	private final double longitude = 11.100790;

	Calculations calc = new Calculations();
	private ArrayList<BreweriesFull> breweries = new ArrayList<>();
	double distanceMatrix[][];
	
	
	// Sets starting coordinates
	public void getCoordinates() {
		// to do: read from file or something
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
				distance = calc.haversineDistance(breweries.get(i).getLatitude(), breweries.get(j).getLatitude(),
						breweries.get(i).getLongitude(), breweries.get(j).getLongitude());
				distanceMatrix[i][j] = distance;
				distanceMatrix[j][i] = distance;
			}
			n++;
		}
	}
	
	
	// Find best route
	public void findBreweries() {
		boolean	visited[] = new boolean[breweries.size()];
		Stack<Integer> route = new Stack<>();
		int types = 0;
		double km = FUEL_LIMIT;
		
		//find(0, 0, types, km, route, visited);
		
		// for result print test only!
		route.push(0);
		route.push(1);
		route.push(2);
		route.push(0);
		// ---
		printResult(route);
	}
	
	
	// Route search method
	// not working! currently throws StackOverflowError
	private int find(int curr, int prev, int types, double km, Stack<Integer> route, boolean visited[]) {
		if (km <= 0 && curr != 0) return types;
		
		if (curr != 0) visited[curr] = true;
		types += breweries.get(curr).getBeerTypes();
		km -= distanceMatrix[curr][prev];
		
		for (int i = 0; i < breweries.size(); i++)
			if (visited[i] == false) {
				int t = find(i, curr, types, km, route, visited);
				if (t > types) {
					types = t;
					route.push(i);
				}
			}
		
		return types;		
	}
	
	
	/* Sorts all breweries to make list of possible to reach breweries
	 * @param route - stack of indexes of breweries
	 */
	public void printResult(Stack<Integer> route) {
		int distance = 0, types = 0;
		
		if (!route.isEmpty() && route.size() > 3) {
			System.out.println("Found " + (route.size()-2) + " beer breweries");
			for (int i = 0; i < route.size(); i++)
				System.out.println(breweries.get(route.get(i)).getName());
			
			for (int i = 1; i < route.size(); i++)
				distance += distanceMatrix[i-1][i];
			System.out.println("\nTotal distance travelled " + distance);
			
			System.out.print("\nBeer types collected ");
			for (int i = 1; i < route.size()-1; i++) {
				types += breweries.get(i).getBeerTypes();
			}
			System.out.println(types);
		}
		else System.out.println("No breweries in range found");
	}
	
	
	// Program execution
	public void execute() {
		getCoordinates();
		fillBreweries();
		findDistances();
		findBreweries();
	}
	
	
	public static void main(String[] args) {
		Program p = new  Program();
		p.execute();
		System.out.println("\nProgram finished...");
    }
	
}
