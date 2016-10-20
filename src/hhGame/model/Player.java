package hhGame.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	private boolean playable;
	private List<Item> inventory;
	
	public Player(String name, boolean playable){
		this.setName(name);
		this.setPlayable(playable);
		setInventory(new ArrayList<>());		
	}
	
	public Player(){
		setName("antagonist");
		setPlayable(false);
		setInventory(new ArrayList<>());
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public boolean isPlayable() {
		return playable;
	}

	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addItemToInventory(Item i ){
		inventory.add(i);
	}
	
	

}
