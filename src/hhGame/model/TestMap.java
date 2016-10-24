package hhGame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestMap extends GameMap {
	
	
	@Override
	public void buildMap() {
		map = new HashMap<>();
		Room center = new Room("Center", "The middle", null);
		Room north = new Room("North", "The north", null);
		Room east = new Room("East", "The east", null);
		Room south = new Room("South", "not rising again", null);
		Room west = new Room("West", "not the best", null);
		Room nWest = new Room("North West", "another room",null);
		Room nEast = new Room("North East", "yet another", null);
		Room goal = new Room("Goal", "the finish line", null);
		
		center.setNeighbors(north, east, south, west);
		north.setNeighbors(goal, nEast, center, nWest);
		east.setNeighbors(nEast, null, null, center);;
		south.setNorth(center);
		west.setNeighbors(nWest, center, null, null);
		nWest.setNeighbors(null, north, west, null);
		nEast.setNeighbors(null, null, east, north);
		goal.setSouth(north);
		
		
		map.put(center.getName(), center);
		map.put(east.getName(), east);
		map.put(north.getName(), north);
		map.put(south.getName(), south);
		map.put(west.getName(), west);
		map.put(nWest.getName(), nWest);
		map.put(nEast.getName(), nEast);
		map.put(goal.getName(), goal);
		
	}

	@Override
	public Room getStartingPoint() {
		return map.get("South");
	}

	@Override
	public Room getEndingPoint() {
		return map.get("Goal");
	}

	@Override
	public List<Item> getMapItems() {
		List<Item> items = new ArrayList<>();
		Item key = new Item("key", "opens shit");
		Item alsokey =  new Item("another key", "opens even more shit");
		
		items.add(key);
		items.add(alsokey);
		
		return items;
	}
	


}
