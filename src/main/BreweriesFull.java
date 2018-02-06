package main;

/*
* Class for more detailed breweries information
*/
public class BreweriesFull {

	private int id = 0;
	private String name = "";
	private int beerTypes = 0;
	private double latitude = 0;
	private double longitude = 0;
	
	
	public BreweriesFull(int id, String name) {
		this.id = id;
		this.name = name;
	}


	public BreweriesFull(int id, String name, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public BreweriesFull(int id, String name, int beerTypes, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.beerTypes = beerTypes;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getBeerTypes() {
		return beerTypes;
	}


	public void setBeerTypes(int beerTypes) {
		this.beerTypes = beerTypes;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
