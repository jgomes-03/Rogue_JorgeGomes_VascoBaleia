package GameElements.Pickable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Pickable extends GameElement {

	public Pickable(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	public static final int dropKey = KeyEvent.VK_G;

	public void pick() {
		GameEngine.getInstance().getHero().addInventory(this);
		GameEngine.getInstance().removeObject(this);
	}

	public void drop(int keyPressed) {
		if(keyPressed == dropKey) {
			GameEngine.getInstance()
		}
	}

}