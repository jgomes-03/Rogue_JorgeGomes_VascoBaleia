package GameElements.Pickable;


import java.awt.event.KeyEvent;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Point2D;

public abstract class Pickable extends GameElement {

	public Pickable(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	public void pick() {
		if (this != null) {
			GameEngine.getInstance().removeObject(this);
			GameEngine.getInstance().getHero().setPosition(this.getPosition());
		}
	}

	public void drop(int item) {
		GameEngine.getInstance().dropObject(GameEngine.getInstance().getHero().getInventory()[item]);
	}
	
	public int getItemIndex() {
	for (int i = 0; i < GameEngine.getInstance().getHero().getInventory().length; i++) {
		if (this.equals(GameEngine.getInstance().getHero().getInventory()[i])) {
			return i;
		}
	}
	return -1;
}
	
	public static int getInventorySlot(int key) {
		switch(key) {
		case KeyEvent.VK_1:
			return 1;
		case KeyEvent.VK_2:
			return 2;
		case KeyEvent.VK_3:
			return 3;
		default:
			return -1;
		}
	}

}