package GameElements.Static;

import pt.iscte.poo.utils.Point2D;
import GameElements.Movable.*;
import GameElements.Pickable.Pickable;
import pt.iscte.poo.example.*;

public class InventoryBar extends GameElement{

	Movable LivingElement;
	int pointer;
	
	public InventoryBar(Point2D position, Movable m) {
		super(position);
		LivingElement = m;
		pointer = position.getX();
		//update();
	}

	public void update() {
		GameEngine.getInstance().clearInventoryBar();
		int i = pointer;
		for(Pickable p : LivingElement.getInventory()) {
			if(p!=null) {
				p.setPosition(new Point2D(i, getPosition().getY()));
				GameEngine.getInstance().addtoBar(p);
				i++;
			}
		}
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Inventory";
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
