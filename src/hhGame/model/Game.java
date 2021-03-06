package hhGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private GameMap map;
	private Room current, last, antagonist;
	private String antagonistName;
	private Player protagonist;
	private boolean antOut;
	private Random random;
	private boolean antagonistPresent;
	private boolean closeToPlayer;
	private final int MAX_TRIES = 10;
	String message;
	private boolean gameOver;
	private int numberOfItems; 
	List<Room> notValid;
	private boolean debug = false;

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
		antagonistName = map.getAntagonistName();
		notValid = new ArrayList<>();
		notValid.add(map.getEndingPoint());
		notValid.add(map.getStartingPoint());
		notValid.add(current);
		antagonistPresent = false;
		message = "Please type a direction or command. For help, enter help";
		gameOver = false;
		closeToPlayer = false;
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
			message = "You found the " + current.getItem().getName() + ": " + current.getItem().getDescription();
			current.setItem(null);
			
			if(!antagonistPresent){
				placeAntagonist();
				message += "\nYou have unleashed the "+antagonistName+". Don't get caugt.";
			}
			moveAntagonist();
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
				if(debug){
					System.out.println("item placed in " + temp.getName());
				}
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
				if(debug){
					System.out.println("\nAn antagonist can be found in " + r.getName());
				}				
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
				
				message = "You are now in the " + current.getName()+" - " + current.getDescription();
				checkWin();
				moveAntagonist();
				closeToPlayer = checkForAntagonist();
			}
			
		}else{
			message = "There is no room in that direction. Try again";
		}		
	}
	
	private boolean checkForAntagonist() {
		for(Direction d: Direction.values()){
			if(current.getNeighbor(d)!=null){
				if(current.getNeighbor(d).hasAntagonist()){
					message+="\n"+map.getWarningMessage();
					return true;
				}
			}
		}
			
		return false;
	}

	private void checkWin() {
		if(current.equals(map.getEndingPoint()) && protagonist.getInventory().size() == numberOfItems){
			message = map.getWinMessage();
			gameOver = true;
			printMessage();
		}
		
	}

	private void printMessage() {
		System.out.println(message);
		if(!gameOver){
			printRoom();
			System.out.println("Please type a command:");
			message = "";
		}		
	}

	private void printRoom() {
		if(current.getNorth()!=null){
			System.out.println("  _____  _____   ");
		}
		else{
			System.out.println("  ____________   ");
		}
		
		if(current.getItem()!=null){
			System.out.println("  |         O|   ");
		}
		else{
			System.out.println("  |          |  ");
		}
		System.out.println("  |          |  ");
		
		if(current.getEast()!= null && current.getWest() != null){
			System.out.println("");
		}
		else if(current.getWest()!=null){
			System.out.println("             |  ");
		}
		else if(current.getEast()!=null){
			System.out.println("  |            ");
		}
		else{
			System.out.println("  |          |  ");
		}
			
		System.out.println("  |          |  ");
		
		if(current.getSouth()!=null){
			System.out.println("  |____  ____|   ");
		}
		else{
			System.out.println("  |__________|   ");
		}
		
		System.out.println("\n   Room: " + current.getName());
		
	}

	public void moveAntagonist() {
		if(antagonistPresent){
			Room temp;
			if(closeToPlayer){
				List<Direction> validMoves = getValidMoves(antagonist);
				temp = antagonist.getNeighbor(validMoves.get(random.nextInt(validMoves.size())));	
			}
			else{
				temp = getRandomRoom();
			}
					
			temp.setAntagonist(true);
			antagonist.setAntagonist(false);
			antagonist = temp;
			if(debug){
				System.out.println("The antagonist is now in " + antagonist.getName());
			}
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
		message = map.getLoseMessage();
		gameOver = true;	
		printMessage();			
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
		System.out.println("Welcome to the " + game.getMap().getIntro());
		Scanner scan = new Scanner(System.in);
		String turn;// = scan.nextLine();
		
		while(!game.gameOver){
			game.printMessage();
			turn = scan.nextLine();
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
					break;
				case "LOOK":
					System.out.println("The current room is "+ game.getCurrent().getName());
					break;
				case "CHECK":
					System.out.print("You have found ");
					if(game.protagonist.getInventory().size()<1){
						System.out.println("nothing.");
					}
					for(Item i : game.protagonist.getInventory()){
						System.out.print("the " + i.getName() + ", ");
					}
					System.out.println("");
					break;
					
				case "HELP":
					System.out.println("Move by typing a valid direction: North, East, South, West.");
					System.out.println("Search a room for items by typing 'search'");
					System.out.println("To get a recap of your surroundings, type 'look'");
					System.out.println("To see what you have in your inventory, type 'check'");
					System.out.println("To give up on the current quest, type 'quit'");
					System.out.println("To see these instructions again, type 'help'");
					break;
				default:
					System.out.println("Invalid input. For instructions, type 'help'");
					break;
			}
		}
//		System.out.println("Game over.");	
		scan.close();
		
		
	}

}
