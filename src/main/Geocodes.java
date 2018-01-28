package main;

public class Geocodes {

	private int id = 0;
	private int brewery_id = 0;
	private String latitude = "";
	private String longitude = "";
	private String accuracy = "";
	
	
	public Geocodes(int id, int brewery_id, String latitude, String longitude, String accuracy) {
		super();
		this.id = id;
		this.brewery_id = brewery_id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBrewery_id() {
		return brewery_id;
	}


	public void setBrewery_id(int brewery_id) {
		this.brewery_id = brewery_id;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongtitude(String longitude) {
		this.longitude = longitude;
	}


	public String getAccuracy() {
		return accuracy;
	}


	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	
	
}
