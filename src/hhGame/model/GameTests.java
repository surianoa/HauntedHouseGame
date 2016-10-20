package hhGame.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class GameTests {
	Game test = new Game(Level.TEST);
	
	@Test
	public void setupTest(){
		assertNotNull(test.getMap());
		assertNotNull(test.getProtagonist());
		assertEquals("South",test.getMap().getStartingPoint().getName() );
		assertEquals(test.getProtagonist(), test.getMap().getStartingPoint().getPlayer());
	}
	
	@Test
	public void playerMoveTest(){
		assertNull(test.getLast());
		test.movePlayer(Direction.SOUTH);
		assertNull(test.getLast());
		assertEquals(test.getMap().getRoom("South"), test.getCurrent());
		
		test.movePlayer(Direction.NORTH);	
		assertEquals("South", test.getLast().getName());
		assertEquals("Center", test.getCurrent().getName());		
	}
	
//  Test no longer needed bc items are no longer hard coded
//	@Test
//	public void inventoryTest(){
//		test.movePlayer(Direction.NORTH);
//		assertEquals("Center", test.getCurrent().getName());
//		assertEquals(0, test.getProtagonist().getInventory().size());
//		test.movePlayer(Direction.WEST);
//		assertNotNull(test.getCurrent().getItem());
//		assertEquals("key", test.getCurrent().getItem().getName() );
//		assertTrue(test.searchCurrentRoom());
//		assertEquals(1, test.getProtagonist().getInventory().size());
//		assertEquals("key", test.getProtagonist().getInventory().get(0).getName());
//		assertNull(test.getCurrent().getItem());
//		assertFalse(test.searchCurrentRoom());		
//	}
	
	@Test
	public void randomRoomTest(){
		Room temp = test.getRandomRoom();
		assertNotEquals(test.getCurrent(), temp);
		assertNotEquals(test.getMap().getStartingPoint(), temp);
		assertNotEquals(test.getMap().getEndingPoint(), temp);
		
		test.movePlayer(Direction.NORTH);
		temp = test.getRandomRoom();
		assertNotEquals(test.getCurrent(), temp);
		assertNotEquals(test.getMap().getStartingPoint(), temp);
		assertNotEquals(test.getMap().getEndingPoint(), temp);		
	}
	
	@Test
	public void testItemPlacement(){
		assertEquals(0,test.testNumberOfItemsOnMap());
		test.placeItemsOnMap();
		assertEquals(2,test.testNumberOfItemsOnMap());
	}
	
	@Test
	public void testAntagonistPlacement(){
		assertEquals(0, test.testNumberOfAntagonists());
		test.placeAntagonist();
		assertEquals(1, test.testNumberOfAntagonists());
	}
	
	
	
	
}
