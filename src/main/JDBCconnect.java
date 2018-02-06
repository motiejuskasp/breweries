package main;

import java.sql.*;
import java.util.ArrayList;

/*
* Class for methods that connect to DB
*/
public class JDBCconnect {
	
	// JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private final String DB_URL = "jdbc:mysql://localhost/open_beer_db";

    // Database credentials
    private final String USER = "root";
    private final String PASS = "";
    
    // Connection and sql variables
    private Connection conn = null;
    private Statement stmt = null;
    private String sql;
    
    // Data
    private Calculations calc = new Calculations();
    private ArrayList<Breweries> breweries = new ArrayList<>();
    private ArrayList<BreweriesFull> breweriesFull = new ArrayList<>();
    

    /* Selects, sorts and returns list of possible to reach breweries
	 * @param latitude - latitude of starting point
	 * @param longitude - longitude of starting point
	 * @param distance - maximum travel distance
	 * @return - list of breweries
	 */
    public ArrayList<BreweriesFull> getFinalBreweries(double latitude, double longitude, int distance) {
    	// approximate max distance from starting point
    	final double limit = distance/2 + distance*0.05;
    	
    	try{
    		// Register JDBC driver
    		Class.forName(JDBC_DRIVER);
    		
    		// Open a connection
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
   	      	
    		// Execute a query
    		stmt = conn.createStatement();
    		
    		getBreweries();
    		sortBreweries(latitude, longitude, limit);
    		getBeerTypes();
    		
    		// Clean-up environment
    		stmt.close();
    		conn.close();
    	}catch(SQLException se){
    		//Handle errors for JDBC
    		System.out.println("JDBC error");
    		se.printStackTrace();
    	}catch(Exception e){
    		//Handle errors for Class.forName
    		System.out.println("Class error");
    		e.printStackTrace();
    	}finally{
    		//finally block used to close resources
    		try{
    			if(stmt!=null)
    				stmt.close();
    		}catch(SQLException se2){
    		}
    		try{
    			if(conn!=null)
    				conn.close();
    		}catch(SQLException se){
    			se.printStackTrace();
    		}//end finally try
    	}//end try
    	
		return breweriesFull;
    }
    
    
    // Gets breweries data from DB
    private void getBreweries() {
    	try{
    		sql = "SELECT id, name FROM breweries";
    		ResultSet rs = stmt.executeQuery(sql);
   	      	
    		while(rs.next()){
    			int id  = rs.getInt("id");
    			String name = rs.getString("name");
   	         	
    			Breweries b = new Breweries(id, name);
    			breweries.add(b);
    			
    		}
    		rs.close();
    	}catch(Exception e){
    	}finally{
    	}//end try
    }
    
    
    /* Sorts all breweries to make list of possible to reach breweries
	 * @param latitude - latitude of starting point
	 * @param longitude - longitude of starting point
	 * @param limit - approximately maximum distance from starting point
	 */
    private void sortBreweries(double latitude, double longitude, double limit) {
    	try{
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		for (int i = 0; i < breweries.size(); i++) {    			
    			sql = "SELECT latitude, longitude FROM geocodes WHERE brewery_id = '" + breweries.get(i).getId() + "'";
    			rs = stmt.executeQuery(sql);
    			
    			if (rs.next()) {
    				double latitude2 = rs.getDouble("latitude");
    				double longitude2 = rs.getDouble("longitude");
    				
    				if (calc.haversineDistance(latitude, latitude2, longitude, longitude2) < limit) {
    					BreweriesFull bf = new BreweriesFull(breweries.get(i).getId(), breweries.get(i).getName(), latitude2, longitude2);
    					breweriesFull.add(bf);
    				}
    			}
    			
    		}
    		rs.close();
    	}catch(Exception e){
    	}finally{
    	}//end try
    }
    
    
    // Gets beer type count for each brewery
    private void getBeerTypes() {
    	try{
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		for (int i = 0; i < breweriesFull.size(); i++) {
    			
    			sql = "SELECT COUNT(id) as types FROM beers WHERE brewery_id = '" + breweriesFull.get(i).getId() + "'";
    			rs = stmt.executeQuery(sql);
    			if (rs.next()) {
    				int types = rs.getInt("types");
    				breweriesFull.get(i).setBeerTypes(types);
    			}
    			
    		}
    		rs.close();
    	}catch(Exception e){
    	}finally{
    	}//end try
    }
    
}
