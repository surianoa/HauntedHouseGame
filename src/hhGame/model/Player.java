package hhGame.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	private boolean antagonist;
	private List<Item> inventory;
	
	public Player(String name, boolean antagonist){
		this.setName(name);
		this.setAntagonist(antagonist);
		setInventory(new ArrayList<>());		
	}
	
	public Player(){
		setName("antagonist");
		setAntagonist(true);
		setInventory(new ArrayList<>());
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public boolean isAntagonist() {
		return antagonist;
	}

	public void setAntagonist(boolean playable) {
		this.antagonist = playable;
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
