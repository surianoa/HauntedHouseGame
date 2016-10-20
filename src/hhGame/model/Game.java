package hhGame.model;

public class Game {
	
	private Room current, last;
	private GameMap map;
	private Player protagonist, antagonist;
	private boolean antOut;

	public Game(Level l){
		setup(l);
	}

	private void setup(Level l) {
		switch(l){
		default:
			map = new TestMap();
			map.buildMap();
			break;
		}
		
		protagonist = new Player("Main", true);
		current = map.getStartingPoint();
		current.setPlayer(protagonist);
		last = null;
		antOut = false;
		antagonist = null;
	}

	public void movePlayer(Direction move) {
		last = current;
		if(current.getNeighbor(move)!=null){
			current = current.getNeighbor(move);
			current.setPlayer(protagonist);
			last.setPlayer(null);
		}		
	}
	/*
	 * Searches the current room for an iteam and adds it to the inventory if there is an item.
	 * Returns true if there was an item in the room, false if not.
	 */
	public boolean searchCurrentRoom(){
		if(current.getItem()!=null){
			protagonist.addItemToInventory(current.getItem());
			current.setItem(null);
			return true;
		}
		return false;
	}
	
	public Room getCurrent() {
		return current;
	}

	public void setCurrent(Room current) {
		this.current = current;
	}

	public Room getLast() {
		return last;
	}

	public void setLast(Room last) {
		this.last = last;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	public Player getProtagonist() {
		return protagonist;
	}

	public void setProtagonist(Player protagonist) {
		this.protagonist = protagonist;
	}

	public Player getAntagonist() {
		return antagonist;
	}

	public void setAntagonist(Player antagonist) {
		this.antagonist = antagonist;
	}

	public boolean isAntOut() {
		return antOut;
	}

	public void setAntOut(boolean antOut) {
		this.antOut = antOut;
	}
	

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
