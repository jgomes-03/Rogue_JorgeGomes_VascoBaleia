package GameElements.Pickable;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends Pickable  {
	
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
}
