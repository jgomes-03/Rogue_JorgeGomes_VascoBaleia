package GameElements.Pickable;


import java.awt.event.KeyEvent;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Point2D;

public abstract class Pickable extends GameElement {

	public Pickable(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	public void pick(int i) {
		if (this != null) {
			GameEngine.getInstance().getHero().getInventory()[i] = this;
			GameEngine.getInstance().removeObject(this);
		}
	}

	public void drop(int item) {
		GameEngine.getInstance().dropObject(GameEngine.getInstance().getHero().getInventory()[item]);
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