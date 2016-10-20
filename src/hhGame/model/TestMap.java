package hhGame.model;

import java.util.HashMap;

public class TestMap extends GameMap {
	
	
	@Override
	public void buildMap() {
		map = new HashMap<>();
		Room center = new Room("Center", "The middle", null);
		Room north = new Room("North", "The north", null);
		Room east = new Room("East", "The east", null);
		Room south = new Room("South", "not rising again", null);
		Room west = new Room("West", "not the best", null);
		
		center.setNeighbors(north, east, south, west);
		north.setNeighbors(null, null, center, null);
		east.setWest(center);
		south.setNorth(center);
		west.setEast(center);
		west.setItem(new Item("key", "Opens shit"));
		
		map.put(center.getName(), center);
		map.put(east.getName(), east);
		map.put(north.getName(), north);
		map.put(south.getName(), south);
		map.put(west.getName(), west);
		
	}

	@Override
	public Room getStartingPoint() {
		return map.get("South");
	}


}
