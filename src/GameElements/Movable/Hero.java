package GameElements.Movable;

import GameElements.Pickable.Pickable;
import GameElements.Static.InventoryBar;
import GameElements.Static.Lifebar;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Point2D;

public class Hero extends Movable {

	private Pickable[] inventory;
	private static final int MAX_SIZE = 3;
	private static final int MAX_LIFE = 10;
	private Lifebar healthBar;
	private InventoryBar inventoryBar;

	public Hero(Point2D position) {
		super(position);
		inventory = new Pickable[MAX_SIZE];
		super.setHitpoints(MAX_LIFE);
		healthBar = createLifebar(new Point2D(0, GameEngine.GRID_HEIGHT), this);
		inventoryBar = createInventoryBar(new Point2D(6, GameEngine.GRID_HEIGHT), this);
		 //inventory[0]= new Key(new Point2D(2,2),"KEY0");
		// inventory.add(new Sword(new Point2D(2,2)));
	}

	@Override
	public String getName() {
		return "Hero";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	public Pickable[] getInventory() {
		return inventory;
	}

	@Override
	public boolean isTransposable() {
		return false;
	}

	public void pickToInventory(Pickable p) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i]==null) {
				inventory[i] = p;
				p.pick(i);
				inventoryBar.update();
				System.out.println("Picked:" + p);
				return;
			}
		}
	}

	public void dropFromInventory(Pickable p) {
		for (int i = 0; i < inventory.length; i++) {
			if (p.equals(inventory[i])) {
				p.drop(i);
				inventory[i]=null;
				return;
			}
		}
	}


	@Override
	public boolean isDeadOnNextAttack() {
		return getHitpoints() < 1 ? true : false;
	}

	public void updateHeroBars() {
		healthBar.update();
		inventoryBar.update();
	}
	

}
