package hhGame.model;

import java.util.HashMap;

public abstract class GameMap {
	HashMap<String, Room> map;
	
	public Room getRoom(String name){
		if(map != null){
			return map.get(name);
		}
		return null;
	}
	
	
	public abstract void buildMap();	
	public abstract Room getStartingPoint();
		
}
