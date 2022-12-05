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
				p.setPosition(new Point2D(pointer+p.getItemIndex(), getPosition().getY()));
				GameEngine.getInstance().addtoBar(p);
				i++;
			}
		}
	}
	
	@Override
	public String getName() {
		return "Inventory";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean isTransposable() {
		return false;
	}
	
}
