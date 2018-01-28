package main;

public class Categories {

	private int id = 0;
	private String cat_name = "";
	
	
	public Categories(int id, String cat_name) {
		super();
		this.id = id;
		this.cat_name = cat_name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCat_name() {
		return cat_name;
	}


	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
}
