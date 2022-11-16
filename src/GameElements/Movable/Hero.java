package GameElements.Movable;

import java.util.ArrayList;

import javax.swing.DropMode;

import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import GameElements.Static.*;

public class Hero extends Movable {

	private ArrayList<GameElement> inventory;
	
	
	public Hero(Point2D position) {
		super(position);
		inventory = new ArrayList<>();
		super.setHitpoints(10);
	}

	@Override
	public String getName() {
		return "Hero";
	}


	
	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}
	

	public void addInventory(GameElement ge) {
		if (ge != null) {
			inventory.add(ge);
			GameEngine.getInstance().removeObject(ge);
		}
	}
	
	public void dropInventory(GameElement ge) {
		if(ge!=null) {
			inventory.remove(ge);
			ge.setPosition(GameEngine.getInstance().getHeroPosition().plus(new Vector2D(1,0)));
			GameEngine.getInstance().addObject(ge);
		}
	}
	
//	@Override
//	public void attack(GameElement ge) {
//		for(GameElement i : inventory) {
//			if(i.getName().equals("Sword")){
//				
//			}
//		}
//	}
	
	public void nextRoom() {
		
	}
	
	
}
