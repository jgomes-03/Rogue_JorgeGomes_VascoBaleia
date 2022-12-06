package GameElements.Movable;

import GameElements.Consumable.HealingPotion;
import GameElements.Pickable.Armor;
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
		// inventory[0]= new Key(new Point2D(2,2),"KEY0");
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

	@Override
	public boolean isTransposable() {
		return false;
	}

	public Pickable[] getInventory() {
		return inventory;
	}
	
	public InventoryBar getInventoryBar() {
		return inventoryBar;
	}

//	public int getItemIndex(Pickable p) {
//		for (int i = 0; i < inventory.length; i++) {
//			if (p.equals(inventory[i])) {
//				return i;
//			}
//		}
//		return -1;
//	}

	public void pickToInventory(Pickable p) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				inventory[i] = p;
				p.pick();
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
				inventory[i] = null;
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
	
	public void heal() {
		if(super.getHitpoints()+HealingPotion.HEAL_VALUE >= MAX_LIFE) {
			 super.setHitpoints(MAX_LIFE);
		} else super.setHitpoints(super.getHitpoints()+HealingPotion.HEAL_VALUE);
	}
	
	public boolean isArmored() {
		for(Pickable p : inventory) {
			if(p instanceof Armor) return true;
		}
		return false;
	}
	

}
