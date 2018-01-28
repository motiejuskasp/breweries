package main;

public class Beers {

	private int id = 0;
	private int brewery_id = 0;
	private String name = "";
	private int cat_id = 0;
	
	
	public Beers(int id, int brewery_id, String name) {
		super();
		this.id = id;
		this.brewery_id = brewery_id;
		this.name = name;
	}


	public Beers(int id, int brewery_id, String name, int cat_id) {
		super();
		this.id = id;
		this.brewery_id = brewery_id;
		this.name = name;
		this.cat_id = cat_id;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCat_id() {
		return cat_id;
	}


	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	
}
