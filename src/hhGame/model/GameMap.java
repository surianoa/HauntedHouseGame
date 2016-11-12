package hhGame.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public abstract class GameMap {
	HashMap<String, Room> map;
	
	public Room getRoom(String name){
		if(map != null){
			return map.get(name);
		}
		return null;
	}
	
	public Collection<Room> getRoomsInMap(){		
		return map.values();
	}
	
	
	public abstract void buildMap();	
	public abstract Room getStartingPoint();
	public abstract Room getEndingPoint();
	public abstract List<Item> getMapItems();
	public abstract String getIntro();
	public abstract String getAntagonistName();

	public abstract String getWinMessage();
	public abstract String getLoseMessage();
}
