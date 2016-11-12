package hhGame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MansionMap extends GameMap {

	@Override
	public void buildMap() {
		map = new HashMap<>();
		Room porch = new Room("Porch", "Entryway leading up to a large gilded door", null);
		Room foyer = new Room("Foyer", "Large room with marge tiled floor and ominous suits of armor", null);
		Room sitting = new Room("Sitting Room", "Small room with cozy arm chairs.", null);
		Room lounge = new Room("Lounge", "A candlelit room with a bar lined with dusty bottles", null);
		Room gStair = new Room("Grand Staircase", "A large marble staircase lined with portraits of previous tennants.", null);
		Room veranda = new Room("Veranda", "Large patio overlooking the grounds. The family plot is visible in the distance",null);
		Room ballroom = new Room("Ballroom", "Spacious ballroom with large windows and looming chandelier", null);
		Room dining = new Room("Dining Room", "Room with a long table with 13 placesettings. A embers smolder in the stone fireplace", null);
		Room nook = new Room("Nook", "Quaint room looking out to the greenhouse.", null);
		Room greenhouse = new Room("Greenhouse", "Room made of glass, filled with exotic plants", null);
		Room grounds = new Room("Grounds", "Sprawling grounds lined with trees and winding paths", null);
		Room maze = new Room("Hedge Maze", "Maze with tall walls of hedges. You can hear a fountian spashing somewhere inside", null);
		Room graves = new Room("Family plot", "Graves are scattered about. Some look to date all the way back to the 1600s", null);
		Room mausoleum = new Room("Mausoleum", "A forboding buulding with a wrought-iron gate", null);
		Room gallery = new Room("Gallery","Convoluted room filled with odd paintings and sculptures", null);
		Room hall = new Room("Hall", "Long hallway with many doors", null);
		Room parlor = new Room("Parlor", "Small room with low tables and chairs", null);
		Room kitchen = new Room("Kitchen", "Low ceiling room with many pots and pans. A fire crackles in the large stove", null);
		Room cellar = new Room("Cellar", "Dark room with many boxes. You can hear something rustling in the corner", null);
		Room anteroom = new Room("Anteroom", "A small room with a few chairs", null);
		Room library = new Room("Library", "A room filled with books, floor to ceiling. A small fire is burning in the fireplace", null);
		Room study = new Room("Study","A small room with tables and lamps. A drawing board sits in the corner covered in pencil shavings", null);
		Room billard = new Room("Billiards Room", "A game table lit with an overhead green lamp dominates the roomm. You notice a dart board with your picture on the wall", null);
		
		porch.setNeighbors(foyer, null, null, null);
		lounge.setNeighbors(ballroom, null, null, foyer);
		foyer.setNeighbors(gStair, sitting, porch, lounge);
		sitting.setNeighbors(dining, foyer, null, null);
		mausoleum.setNeighbors(null, graves, null, null);
		graves.setNeighbors(null, null, grounds, mausoleum);
		maze.setNeighbors(grounds, null, null, null);
		grounds.setNeighbors(graves, veranda, maze, null);
		veranda.setNeighbors(null, ballroom, null,grounds);
		ballroom.setNeighbors(gallery, gStair, lounge, veranda);
		gStair.setNeighbors(hall, dining, foyer, ballroom);
		dining.setNeighbors(parlor, nook, sitting, gStair);
		nook.setNeighbors(kitchen, greenhouse, null, dining);
		greenhouse.setNeighbors(cellar, null, null, nook);
		gallery.setNeighbors(anteroom, hall, ballroom, null);
		hall.setNeighbors(library, parlor, gStair, gallery);
		parlor.setNeighbors(study, kitchen, dining, hall);
		kitchen.setNeighbors(null, cellar, nook,parlor);
		cellar.setNeighbors(null, null, greenhouse, nook);
		anteroom.setNeighbors(null, library, gallery, null);
		library.setNeighbors(null, study, hall, anteroom);
		study.setNeighbors(null, billard, parlor, library);
		billard.setNeighbors(null, null, null, study);
		
		map.put(porch.getName(), porch);
		map.put(foyer.getName(), foyer);
		map.put(sitting.getName(), sitting);
		map.put(lounge.getName(), lounge);
		map.put(gStair.getName(), gStair);
		map.put(veranda.getName(), veranda);
		map.put(ballroom.getName(), ballroom);
		map.put(dining.getName(), dining);
		map.put(nook.getName(), nook);
		map.put(greenhouse.getName(), greenhouse);
		map.put(grounds.getName(), grounds);
		map.put(maze.getName(), maze);
		map.put(graves.getName(), graves);
		map.put(mausoleum.getName(), mausoleum);
		map.put(gallery.getName(), gallery);
		map.put(hall.getName(), hall);
		map.put(parlor.getName(), parlor);
		map.put(kitchen.getName(), kitchen);
		map.put(cellar.getName(), cellar);
		map.put(anteroom.getName(), anteroom);
		map.put(library.getName(), library);
		map.put(study.getName(), study);
		map.put(billard.getName(), billard);
		
		
	}

	@Override
	public Room getStartingPoint() {
		return map.get("Porch");
	}

	@Override
	public Room getEndingPoint() {
		return map.get("Mausoleum");
	}

	@Override
	public List<Item> getMapItems() {
		List<Item> items = new ArrayList<>();
		
		Item necklace = new Item("Necklace", "Old ornate necklace set with a large ruby");
		Item brooch = new Item("Brooch", "Brooch set with a deep green Emerald");
		Item journal = new Item("Journal", "Worn out journal filled with strange markings");
		Item knife = new Item("Knife", "Small knife with glided handle");
		Item lipstick = new Item("Lipstick", "Tube of blood red lipstick.");
		
		items.add(necklace);
		items.add(brooch);
		items.add(journal);
		items.add(knife);
		items.add(lipstick);
		
		return items;
		
	}

}
