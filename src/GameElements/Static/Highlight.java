package GameElements.Static;

import GameElements.Pickable.Pickable;
import pt.iscte.poo.utils.Point2D;

public class Highlight extends Pickable{

	public Highlight(Point2D position) {
		super(position);
	}

//	public Highlight(int index) {
//		getInventory()[Pickable.getInventorySlot(index);
//	}
//	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Highlight";
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		return false;
	}

}
