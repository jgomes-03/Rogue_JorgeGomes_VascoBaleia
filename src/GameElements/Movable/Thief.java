package GameElements.Movable;

import GameElements.Pickable.Pickable;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thief extends Movable  {
	
	private static final int MAX_INVENTORY_SIZE = 2;
	private static final int MAX_LIFE = 5;
	
	private Pickable[] inventory;
	
	public Thief(Point2D position) {
		super(position);
		super.setHitpoints(MAX_LIFE);
		inventory = new Pickable[MAX_INVENTORY_SIZE];
	}

	public String getName() {
		return "Thief";
	}
	
	@Override
	public int getLayer() {
		return 0;
	}
	
	@Override
	public boolean isTransposable() {
		return false;
	}

	
	@Override
	public void move(int keyPressed) {
		keyPressed = super.getKey(Direction.forVector(Vector2D.movementVector(getPosition(), GameEngine.getInstance().getHero().getPosition())));
		super.move(keyPressed);
	}

	@Override
	public void attack(Movable m, int damage) {
		if(m instanceof Hero) {
			steal();
		}
	}
		
	public void die() {
		for(int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
			inventory[i].setPosition(getPosition());
			GameEngine.getInstance().addObject(inventory[i]);
		}
	}	
}
	
	public Pickable[] getInventory() {
			return inventory;
		}

	public void steal() { 
			for (int i = 0; i < inventory.length; i++) {
				Pickable aux = GameEngine.getInstance().getHero().getInventory()[i];
				if (inventory[i] == null && aux !=null) {
					inventory[i] = aux;
					GameEngine.getInstance().getHero().getInventory()[i] = null;
					System.out.println("Gamei " + inventory[i].toString());
					return;
				}
			}
		}

	
	

	
	
}