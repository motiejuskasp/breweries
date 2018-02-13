package main;

import java.util.ArrayList;
import java.util.Collections;
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
		
		Collections.sort(breweriesTemp);
		
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
	
	
	public void findBreweries() {
		Stack<Integer> best = new Stack<>();
		Stack<Integer> current = new Stack<>();
		int types = 0, t = 0, km;
		
		for (int i = 1; i < breweries.size()-1; i++) {
			t = 0;
			km = FUEL_LIMIT;
			current.clear();
			current.push(0);
			
			km -= distanceMatrix[0][i];
			if (km - distanceMatrix[0][i] > 0) {
				t = find(breweries, i, km, current);
			}
			//System.out.println(t);
			current.push(0);
			
			if (t > types) {
				//System.out.println(types + " " + t);
				best.clear();
				best = current;
				types = t;
			}
			
			/*for (int j = 0; j < best.size(); j++)
				System.out.print(best.get(j) + " ");
			System.out.println("");*/
		}
		
		printResult(best);
	}
	
	
	private int find(ArrayList<BreweriesFull> list, int n, int km, Stack<Integer> current) {
		int t = breweries.get(n).getBeerTypes();
		current.push(n);
		for (int i = n+1; i < breweries.size()-1; i++) {
			if (km < 0) break;
			//current.push(i);
			km -= distanceMatrix[n][i];
			if (km - distanceMatrix[0][i] > 0) {
				t += find(breweries, i, km, current);
			}
		}
		return t;
	}
	
	
	/* Sorts all breweries to make list of possible to reach breweries
	 * @param route - stack of indexes of breweries
	 */
	public void printResult(Stack<Integer> route) {
		int distance = 0, types = 0;
		
		if (!route.isEmpty() && route.size() > 2) {
			System.out.println("Found " + (route.size()-2) + " beer breweries");
			for (int i = 0; i < route.size(); i++)
				System.out.println("> " + breweries.get(route.get(i)).getName());// + " " + route.get(i));
			
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
