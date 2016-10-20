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
		assertEquals("South", test.getLast().getName());
		assertEquals(test.getMap().getRoom("South"), test.getCurrent());
		
		test.movePlayer(Direction.NORTH);	
		assertEquals("South", test.getLast().getName());
		assertEquals("Center", test.getCurrent().getName());		
	}
	
	@Test
	public void inventoryTest(){
		test.movePlayer(Direction.NORTH);
		assertEquals("Center", test.getCurrent().getName());
		assertEquals(0, test.getProtagonist().getInventory().size());
		test.movePlayer(Direction.WEST);
		assertNotNull(test.getCurrent().getItem());
		assertEquals("key", test.getCurrent().getItem().getName() );
		assertTrue(test.searchCurrentRoom());
		assertEquals(1, test.getProtagonist().getInventory().size());
		assertEquals("key", test.getProtagonist().getInventory().get(0).getName());
		assertNull(test.getCurrent().getItem());
		assertFalse(test.searchCurrentRoom());
		
	}
}
