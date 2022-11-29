package GameElements.Movable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DropMode;

import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import GameElements.Pickable.Pickable;
import GameElements.Static.*;

public class Hero extends Movable {

	private ArrayList<Pickable> inventory;
	private static final int MAX_SIZE = 3;
	private static final int MAX_LIFE = 10;
	private Lifebar health;
	
	public Hero(Point2D position) {
		super(position);
		inventory = new ArrayList<>();
		super.setHitpoints(MAX_LIFE);
		health = createLifebar(position, this);
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
		for(int i=0;i<MAX_SIZE;i++) {
			if(inventory.isEmpty() || inventory.get(i)==null) {
				inventory.add(p);
				p.pick();
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
	
	
	
	public void updateLifeBar() {
		health.update();
	}
	
	
}
