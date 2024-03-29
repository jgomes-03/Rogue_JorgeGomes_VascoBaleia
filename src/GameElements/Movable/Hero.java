package GameElements.Movable;

import GameElements.Consumable.HealingPotion;
import GameElements.Pickable.Armor;
import GameElements.Pickable.Pickable;
import GameElements.Static.InventoryBar;
import GameElements.Static.Lifebar;
import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Point2D;

public class Hero extends Movable {

	private Pickable[] inventory;
	private static final int MAX_SIZE = 3;
	private static final int MAX_LIFE = 10;
	private Lifebar healthBar;
	private InventoryBar inventoryBar;
	private boolean isPoisoned;

	public Hero(Point2D position) {
		super(position);
		super.setDamage(1);
		inventory = new Pickable[MAX_SIZE];
		super.setHitpoints(MAX_LIFE);
		healthBar = createLifebar(new Point2D(0, GameEngine.GRID_HEIGHT), this);
		inventoryBar = createInventoryBar(new Point2D(6, GameEngine.GRID_HEIGHT), this);
	}

	@Override
	public String getName() {
		return "Hero";
	}

	@Override
	public int getLayer() {
		return 2;
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


	public void pickToInventory(Pickable p) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				inventory[i] = p;
				p.pick();
				inventoryBar.update();
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

	public boolean getIsPoisoned() {
		return isPoisoned;
	}

	public boolean setPoisoned(boolean b) {
		return isPoisoned = b;		
	}

	public void updateHeroBars() {
		healthBar.update();
		inventoryBar.update();
	}

	public void heal() {
		if (super.getHitpoints() + HealingPotion.HEAL_VALUE >= MAX_LIFE) {
			super.setHitpoints(MAX_LIFE);
		} else
			super.setHitpoints(super.getHitpoints() + HealingPotion.HEAL_VALUE);
		setPoisoned(false);

	}

	public void PoisonDamage() {
		if (getIsPoisoned() == true) {
			setHitpoints(getHitpoints() - 1);
			return;
		}
	}

	public boolean isArmored() {
		for (Pickable p : inventory) {
			if (p instanceof Armor)
				return true;
		}
		return false;
	}
	
	public boolean inventoryIsEmpty() {
		for(int i=0;i<inventory.length;i++) {
			if(inventory[i]!=null) return false;
		}
		return true;
	}

}
