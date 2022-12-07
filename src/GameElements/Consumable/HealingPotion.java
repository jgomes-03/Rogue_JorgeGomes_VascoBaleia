package GameElements.Consumable;

import GameElements.Pickable.Pickable;
import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends Pickable implements Consumable  {
	
	public static final int HEAL_VALUE = 5; 
	
	public HealingPotion(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "HealingPotion";
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void consume(int item) {
		GameEngine.getInstance().getHero().heal();
		GameEngine.getInstance().getHero().getInventory()[item] = null;
	}
}
