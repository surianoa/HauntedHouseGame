package hhGame.model;

public class Item {
	private String name;
	private String description;
	private boolean found;
	
	public Item(String name, String description){
		this.name = name;
		this.description = description;
		found = false;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isFound() {
		return found;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
	
}
