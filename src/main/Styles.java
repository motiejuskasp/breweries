package main;

public class Styles {

	private int id = 0;
	private int cat_id = 0;
	private String style_name = "";
	
	
	public Styles(int id, int cat_id, String style_name) {
		super();
		this.id = id;
		this.cat_id = cat_id;
		this.style_name = style_name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCat_id() {
		return cat_id;
	}


	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}


	public String getStyle_name() {
		return style_name;
	}


	public void setStyle_name(String style_name) {
		this.style_name = style_name;
	}
	
}
