package hhGame.model;

public class Room {
	private String name;
	private String description;
	private Item item;
	private Player player;
	private boolean visited;
	private boolean antagonist;
	private Room north, south, east, west;
	
	public Room(String name, String description, Item item){
		this.setName(name);
		this.setDescription(description);
		this.setItem(item);
		setVisited(false);
		antagonist = false;
	}
	
	public Room getNeighbor(Direction move) {
		switch(move){
			case NORTH:
				return north;
			case EAST:
				return east;
			case SOUTH:
				return south;
			case WEST:
				return west;
			default:
				return null;
		}
	}
	
	public boolean hasAntagonist(){
		return antagonist;
	}
	
	public void setAntagonist(boolean a){
		antagonist = a;
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

	public Room getNorth() {
		return north;
	}

	public void setNorth(Room north) {
		this.north = north;
	}

	public Room getSouth() {
		return south;
	}

	public void setSouth(Room south) {
		this.south = south;
	}

	public Room getEast() {
		return east;
	}

	public void setEast(Room east) {
		this.east = east;
	}

	public Room getWest() {
		return west;
	}

	public void setWest(Room west) {
		this.west = west;
	}
	
	public void setNeighbors(Room north, Room east, Room south, Room west ){
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
