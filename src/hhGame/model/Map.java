package hhGame.model;

import java.util.HashMap;

public abstract class Map {
	private HashMap<String, Room> map;
	
	
	public Map(){
		map = new HashMap<>();
	}
	
	public abstract boolean buildMap();
	
	public Room getRoom(String name){
		return map.get(name);
	}
	
	
}

