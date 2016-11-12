package hhGame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MansionMap extends GameMap {

	@Override
	public void buildMap() {
		map = new HashMap<>();
		Room porch = new Room("Porch", "Entryway leading up to a large gilded door", null);
		Room foyer = new Room("Foyer", "Large room with marble tiled floor and ominous suits of armor", null);
		Room sitting = new Room("Sitting Room", "Small room with cozy arm chairs.", null);
		Room lounge = new Room("Lounge", "A candlelit room with a bar lined with dusty bottles", null);
		Room gStair = new Room("Grand Staircase", "A large marble staircase lined with portraits of previous tennants.", null);
		Room veranda = new Room("Veranda", "Large patio overlooking the grounds. The family plot is visible in the distance",null);
		Room ballroom = new Room("Ballroom", "Spacious ballroom with large windows and looming chandelier", null);
		Room dining = new Room("Dining Room", "Room with a long table with 13 place settings. A embers smolder in the stone fireplace", null);
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
		Room billard = new Room("Games Room", "A game table lit with an overhead green lamp dominates the roomm. You notice a dart board with your picture on the wall", null);
		Room chamber = new Room("Draining Chamber", "There are large hooks hanging from the ceiling with large basins underneath.\n You are overcome with the nauseating scent of blood. Could this be fresh? You "
				+ "notice \n a small passage behind a crate to the north.", null);
		Room bedroom = new Room("Master Suite", "Room with a large canopied bed. Ornate furniture lines the walls.", null);
		Room bathroom = new Room("Bathroom", "A large claw-footed bathtub is in the center of the room. There is a dull brown ring around the inside of the tub.\n The air tastes slightly metalic"
				+ "You notice a small door behind a curtain to the north." , null);
		
		
		porch.setNeighbors(foyer, null, null, null);
		lounge.setNeighbors(ballroom, foyer, null, null);
		foyer.setNeighbors(gStair, sitting, porch, lounge);
		sitting.setNeighbors(dining, null, null, foyer);
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
		cellar.setNeighbors(null, chamber, greenhouse, kitchen);
		anteroom.setNeighbors(null, library, gallery, bedroom);
		library.setNeighbors(null, study, hall, anteroom);
		study.setNeighbors(null, billard, parlor, library);
		billard.setNeighbors(null, null, null, study);
		chamber.setNeighbors(bathroom, null, null, cellar);
		bedroom.setNeighbors(bathroom, anteroom, null, null);
		bathroom.setNeighbors(chamber, null, bedroom, null);
		
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
		map.put(chamber.getName(), chamber);
		
		
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
		Item knife = new Item("Knife", "Small knife with glided handle. The blade gleams with a bright red substance...");
		Item lipstick = new Item("Lipstick", "Tube of blood red lipstick.");
		
		items.add(necklace);
		items.add(brooch);
		items.add(journal);
		items.add(knife);
		items.add(lipstick);
		
		return items;
		
	}

	@Override
	public String getIntro() {
		return "Mansion. This large estate is haunted by the ghost of Countess Elizabeth Báthory de Ecsed, known to many as the Bloody Countess.\n"
				+ "In life, the Bloody Countess would kill young women and bathe in their blood because she believed it would retain her youth. \n"
				+ "Her spirit lingers in this estate waiting to be awoken. Find her 5 prized possessions and make it to the Mausoleum to \nbanish her spirit to the"
				+ " other side. Be careful you don't run into the ghost of the countess.\nEven in death, her thirst for blood was not quenched...\n\n";
	}

	@Override
	public String getAntagonistName() {
		return "Spirit of the Bloody Countess";
	}

	@Override
	public String getWinMessage() {
		return "The door to the Mausoleum creaks open. You set up the five items that belonged to the Bloody Countess on the altar \n"
				+ "and light the candles. The flames flicker as you start reciting the incantation. A chill fills the room, she's near...\n"
				+ "You steel yourself and continue with the incantation. Blood starts running down the walls and you can see your breath.\n"
				+ "You look up and see a figure floating through the wall. A woman in a large gown, tattered and stained with blood. Frantically,\n"
				+ "you start the last verse. She's getting closer. You notice she is holding a knife and has a maniacal smile smattered across her face\n"
				+ "She makes a swipe at you but you narowly dodge it and run across the room, slipping in the blood that is now covering the floors.\n"
				+ "She turns and starts coming for you again. You quickly utter the last line of the incantation. All that is left is to place the talisman\n"
				+ " in the middle of the altar. You hear deranged laughter and she lunges for you again. You dive for the altar feeling the knife graze your\n"
				+ " arm, slamming the talisman onto the altar. A bright blue light erupts from the talisman as a vortex appears around it.\n"
				+ "She emits a blood-curdling shriek and struggles against the pull of the vortexvmaking every last attempt to dig her knife into you.\n"
				+ "you throw yourself against the wall and watch the chaos as the Bloody Countess puts up her last stuggle. With one last shriek, she is consumed by\n"
				+ "the portal. The talisman shudders and glows red for a moment and then is still. You look around and see that the blood is gone and the candles \n"
				+ "are all blown out. You let out a sigh of relief knowing the countess has been sealed away for good."; 
	}

	@Override
	public String getLoseMessage() {
		return "The temperature in the room drops. The lights go out. You reach for your flashlight. You fumble with it but finaly are able to light it. You look around\n"
				+ "the room. Nothing appears to be out of the ordinary, but you can see your breath. You turn to leave the room and catch the glint of a blade out of the corner\n"
				+ "of your eye. You swerve to dodge it but it's too late. The blade is dragged across your throat. In your last moments you see a glowing figure above you dripping in\n"
				+ "blood and laughing maniacally. Your vision closes in until everything goes dark....";
	}

}
