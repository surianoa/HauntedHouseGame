package hhGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.omg.PortableInterceptor.DISCARDING;

public class Game {
	
	private Room current, last, antagonist;
	private GameMap map;
	private Player protagonist;
	private boolean antOut;
	Random random;
	private boolean antagonistPresent;
	private final int MAX_TRIES = 10;

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
		random = new Random();
		antagonistPresent = false;
	}

	public void movePlayer(Direction move) {
		if(current.getNeighbor(move)!=null){
			last = current;
			current = current.getNeighbor(move);
			current.setPlayer(protagonist);
			last.setPlayer(null);
		}
		else{
			System.out.println("not valid move");
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
			if(!antagonistPresent){
				placeAntagonist();
			}
			return true;
		}
		return false;
	}
	
	public void placeAntagonist() {
		antagonistPresent = true;
		int tries = 0;
		do{
			antagonist = getRandomRoom();
			tries++;
		}while(antagonist.hasAntagonist() || tries<MAX_TRIES);
		antagonist.setAntagonist(true);
			
	}
	
	public boolean checkNeighbors(){
		
		for(Direction d: Direction.values()){
			if(current.getNeighbor(d).hasAntagonist()){
				return true;
			}
		}
		
		return false;
	}

	/*
	 * returns a random room on the map that is not the start, end or current room
	 */
	public Room getRandomRoom() {
		ArrayList<Room> availableRooms = new ArrayList<Room>(map.getRoomsInMap());
		System.out.println("");
		availableRooms.remove(map.getStartingPoint());
		availableRooms.remove(map.getEndingPoint());
		availableRooms.remove(current);
		
		int rand = random.nextInt(availableRooms.size());
		
		
		return availableRooms.get(rand);		
	}
	
	public void placeItemsOnMap(){
		List<Item> items = map.getMapItems();
		
		while(items.size()>0){
			Item i = items.get(items.size()-1);
			Room temp = getRandomRoom();
			if(temp.getItem()==null){
				temp.setItem(i);
				items.remove(i);
			}
		}
	}
	
	public int testNumberOfItemsOnMap(){
		int count = 0;
		for(Room r: map.getRoomsInMap()){
			if(r.getItem()!= null){
				System.out.println(r.getItem().getName() + "can be found in " + r.getName());
				count++;
			}
		}
		return count;
	}
	
	public int testNumberOfAntagonists(){
		int count = 0;
		for(Room r: map.getRoomsInMap()){
			if(r.hasAntagonist()){
				System.out.println("An antagonist can be found in " + r.getName());
				count++;
			}
		}
		return count;
	}
	
	public void move(Direction d){
		//check that move is valid
		//check to see if move is into room w/ antagonist
		//if so, game over
		//else move check to see if end point and has all items 
		if(isValidMove(d)){
			movePlayer(d);
			if(current.getNeighbor(d).hasAntagonist()){
				killPlayer();
			}else{
				moveAntagonist();
			}
			
		}
		
	}
	
	private void moveAntagonist() {
		if(antagonistPresent){
			List<Direction> validMoves = getValidMoves(antagonist);
			Room temp;
			int tries = 0;
			boolean success = false;
			do{
				temp = antagonist.getNeighbor(validMoves.get(random.nextInt(validMoves.size())));
				if(!temp.hasAntagonist()){
					success = true;
				}
				tries++;
			}while(!success || tries<MAX_TRIES);
			
			temp.setAntagonist(true);
			antagonist.setAntagonist(false);
		}
	}

	private List<Direction> getValidMoves(Room room) {
		List<Direction> valid = new ArrayList<>();
		if(room.getNeighbor(Direction.NORTH)!=null)
			valid.add(Direction.NORTH);
		if(room.getNeighbor(Direction.EAST)!=null)
			valid.add(Direction.EAST);
		if(room.getNeighbor(Direction.SOUTH)!=null)
			valid.add(Direction.SOUTH);
		if(room.getNeighbor(Direction.WEST)!=null)
			valid.add(Direction.WEST);
		
		return valid;		
	}

	private void killPlayer() {
		System.out.println("You have encountered an antagonist. You are dead.");
	}

	private boolean isValidMove(Direction d) {
		if(current.getNeighbor(d)!=null)
			return true;
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
