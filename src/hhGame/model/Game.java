package hhGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private GameMap map;
	private Room current, last, antagonist;
	private Player protagonist;
	private boolean antOut;
	Random random;
	private boolean antagonistPresent;
	private final int MAX_TRIES = 10;
	String message;
	private boolean gameOver;
	private int numberOfItems; 
	List<Room> notValid;

	public Game(Level l){
		setup(l);
	}

	private void setup(Level l) {
		random = new Random();
		switch(l){
		case MANOR:
			map = new MansionMap();
			map.buildMap();
			break;
		default:
			map = new TestMap();
			map.buildMap();
			break;
		}
		
		placeItemsOnMap();
		numberOfItems = map.getMapItems().size();
		protagonist = new Player("Main", true);
		current = map.getStartingPoint();
		current.setPlayer(protagonist);
		last = null;
		antOut = false;
		antagonist = null;
		notValid = new ArrayList<>();
		notValid.add(map.getEndingPoint());
		notValid.add(map.getStartingPoint());
		notValid.add(current);
		antagonistPresent = false;
		message = "Welcome to the game";
		gameOver = false;
	}

	public void movePlayer(Direction move) {
		if(current.getNeighbor(move)!=null){
			last = current;
			current = current.getNeighbor(move);
			current.setPlayer(protagonist);
			last.setPlayer(null);
		}
		else{
			message = "There is not a room in that direction";
		}
	}
	/*
	 * Searches the current room for an iteam and adds it to the inventory if there is an item.
	 * Returns true if there was an item in the room, false if not.
	 */
	public boolean searchCurrentRoom(){
		if(current.getItem()!=null){
			protagonist.addItemToInventory(current.getItem());
			message = "You found the " + current.getItem().getName();
			current.setItem(null);
			
			if(!antagonistPresent){
				placeAntagonist();
				message += "\nYou have unleashed the antagonist. Don't get caugt.";
			}
			checkForAntagonist();
			return true;
		}
		message = "You found nothing here.";
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
		availableRooms.remove(map.getStartingPoint());
		availableRooms.remove(map.getEndingPoint());
		availableRooms.remove(current);
		
		int rand = random.nextInt(availableRooms.size()-1);		
		
		return availableRooms.get(rand);		
	}
	
	public void placeItemsOnMap(){
		List<Item> items = map.getMapItems();
		
		while(items.size()>0){
			Item i = items.get(items.size()-1);
 			Room temp = getRandomRoom();
			if(temp.getItem()==null){
				System.out.println("item placed in " + temp.getName());
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
				System.out.println("\nAn antagonist can be found in " + r.getName());
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
			if(current.hasAntagonist()){
				killPlayer();
			}else{
				moveAntagonist();
			}
			message = "You are now in the " + current.getName();
			checkWin();
			checkForAntagonist();
		}else{
			message = "There is no room in that direction. Try again";
		}		
	}
	
	private boolean checkForAntagonist() {
		for(Direction d: Direction.values()){
			if(current.getNeighbor(d)!=null){
				if(current.getNeighbor(d).hasAntagonist()){
					message+="\nThe antagonist is nearby. Watch your step.";
				}
			}
		}
			
		return false;
	}

	private void checkWin() {
		if(current.equals(map.getEndingPoint()) && protagonist.getInventory().size() == numberOfItems){
			message = "You win!";
			gameOver = true;
		}
		
	}

	private void printMessage() {
		System.out.println(message);
		if(!gameOver){
			System.out.println("Please type a command:");
			message = "";
		}		
	}

	public void moveAntagonist() {
		if(antagonistPresent){
			List<Direction> validMoves = getValidMoves(antagonist);
			Room temp = antagonist.getNeighbor(validMoves.get(random.nextInt(validMoves.size())));			
			temp.setAntagonist(true);
			antagonist.setAntagonist(false);
			antagonist = temp;
			System.out.println("The antagonist is now in " + antagonist.getName());
			System.out.flush();
		}
	}

	private List<Direction> getValidMoves(Room room) {
		List<Direction> valid = new ArrayList<>();
//		if(room.getNeighbor(Direction.NORTH)!=null && !room.getNeighbor(Direction.NORTH).hasAntagonist() && !notValid.contains(room) )
//			valid.add(Direction.NORTH);
//		if(room.getNeighbor(Direction.EAST)!=null && !room.getNeighbor(Direction.EAST).hasAntagonist() && !notValid.contains(room))
//			valid.add(Direction.EAST);
//		if(room.getNeighbor(Direction.SOUTH)!=null && !room.getNeighbor(Direction.SOUTH).hasAntagonist() &&  !notValid.contains(room))
//			valid.add(Direction.SOUTH);
//		if(room.getNeighbor(Direction.WEST)!=null && !room.getNeighbor(Direction.WEST).hasAntagonist()&& !notValid.contains(room))
//			valid.add(Direction.WEST);
//		Direction cur; 
//		for(int i = 0; i< valid.size(); i++){
//			cur = valid.get(i);
//			if(room.getNeighbor(cur).equals(current)){
//				valid.remove(cur);
//			}
//			if(room.getNeighbor(cur).equals(map.getStartingPoint())){
//				valid.remove(cur);
//			}
//			if(room.getNeighbor(cur).equals(map.getEndingPoint())){
//				valid.remove(cur);
//			}
//		}
		for(Direction d: Direction.values()){
			if(room.getNeighbor(d)!=null){
				if(!room.getNeighbor(d).hasAntagonist()){
					if(!room.getNeighbor(d).equals(map.getStartingPoint()) && !room.getNeighbor(d).equals(map.getEndingPoint()) && !room.getNeighbor(d).equals(current)){
						valid.add(d);
					}
				}
			}
		}
		
		return valid;		
	}

	private void killPlayer() {
		message = "You have encountered an antagonist. You are dead.";
		printMessage();
		gameOver = true;		
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
	
	public Room getAntagonist() {
		return antagonist;
	}

	public void setAntagonist(Room antagonist) {
		this.antagonist = antagonist;
	}
	

	public static void main(String[] args) {
		
		Game game = new Game(Level.MANOR);
		System.out.println("Welcome to the Maison Macabre. You are in the " + game.getCurrent().getName()+ "\n");
		Scanner scan = new Scanner(System.in);
		String turn = scan.nextLine();
		
		while(!game.gameOver){
			switch(turn.toUpperCase()){
				case "NORTH":
					game.move(Direction.NORTH);
					break;
				case "SOUTH":
					game.move(Direction.SOUTH);
					break;
				case "EAST":
					game.move(Direction.EAST);
					break;
				case "WEST":
					game.move(Direction.WEST);
					break;
				case "SEARCH":
					game.searchCurrentRoom();
					break;
				case "QUIT":
					game.gameOver = true;
				case "LOOK":
					System.out.println("The current room is "+ game.getCurrent().getName());
					break;
				default:
					System.out.println("Invalid input. Please type a direction or search to search the room.");
					break;
			}
			game.printMessage();
			turn = scan.nextLine();
		}
		System.out.println("Game over.");	
		scan.close();
		
		
	}

}
