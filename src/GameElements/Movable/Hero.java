package GameElements.Movable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DropMode;

import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import GameElements.Pickable.Pickable;
import GameElements.Pickable.Sword;
import GameElements.Static.*;

public class Hero extends Movable {

	private ArrayList<Pickable> inventory;
	private static final int MAX_SIZE = 3;
	private static final int MAX_LIFE = 10;
	private Lifebar healthbar;
	private InventoryBar inventorybar;
	
	public Hero(Point2D position) {
		super(position);
		inventory = new ArrayList<>();
		super.setHitpoints(MAX_LIFE);
		healthbar = createLifebar(new Point2D(0,GameEngine.GRID_HEIGHT), this);
		inventorybar = new InventoryBar(new Point2D(6,GameEngine.GRID_HEIGHT),this);
		inventory.add(new Sword(new Point2D(2,2)));
		inventory.add(new Sword(new Point2D(2,2)));
	}

	@Override
	public String getName() {
		return "Hero";
	}
	
	
	@Override
	public int getLayer() {
		return 1;
	}
	
	public ArrayList<Pickable> getInventory(){
		return inventory;
	}

	@Override
	public boolean isTransposable() {
		return false;
	}

	public void pickToInventory(Pickable p) {
		for(Pickable i : inventory) {
			if(inventory.isEmpty() || (inventory.indexOf(i)<MAX_SIZE && i!=null)) {
				inventory.add(p);
				p.pick();
				inventorybar.update();
				System.out.println("Picked:" + p);
				return;
			}
		}
	}
	
	public void dropFromInventory(Pickable p) {
		for(int i=0;i<inventory.size();i++) {
			if(p.equals(inventory.get(i))){
				p.drop(i);
				inventory.remove(i);
				return;
			}
		}
	}
	
	public void nextRoom() {
		
	}
	
	
	@Override
	public boolean isDeadOnNextAttack() {
		return getHitpoints()<1?true:false;
	}
	@Override
	public void attack(GameElement ge,int d) {
		for(Pickable i : inventory) {
			if(i instanceof Sword) {
				super.attack(ge,getDamage()*2);
				return;
			}
		}
		super.attack(ge, getDamage());
	}
	
	
	public void updateLifeBar() {
		healthbar.update();
	}
	
	
}
