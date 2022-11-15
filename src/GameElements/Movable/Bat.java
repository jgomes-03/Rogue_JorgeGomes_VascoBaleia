package GameElements.Movable;

import java.awt.event.KeyEvent;

import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends Movable {

	public Bat(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Bat";
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
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isMovable() {
		return true;
	}

	@Override
	public void move(int keyPressed) {
		if (Math.random() > 0.5) {
			super.move(getRandomKey(Direction.random()));
		}
	}

	private int getRandomKey(Direction d) {
		switch (d) {
		case UP:
			return KeyEvent.VK_UP;
		case DOWN:
			return KeyEvent.VK_DOWN;
		case RIGHT:
			return KeyEvent.VK_RIGHT;
		case LEFT:
			return KeyEvent.VK_LEFT;
		default:
			return 0;
		}
	}

}
