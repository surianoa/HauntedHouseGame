package hhGame.model;

public class Room {
	private String name;
	private String description;
	private Item item;
	private boolean visited;
	private String north, south, east, west;
	
	public Room(String name, String description, Item item){
		this.setName(name);
		this.setDescription(description);
		this.setItem(item);
		setVisited(false);
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}
	
	

	
	
}
