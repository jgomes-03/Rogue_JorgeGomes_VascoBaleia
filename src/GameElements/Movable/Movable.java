package GameElements.Movable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import GameElements.Static.Door;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Movable extends GameElement {

	private int hitpoints;

	public Movable(Point2D position) {
		super(position);
	}

	public void setHitpoints(int h) {
		hitpoints = h;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void move(int keyPressed) {
		Vector2D moveVector = (Direction.directionFor(keyPressed)).asVector();
		Point2D nextPosition = this.getPosition().plus(moveVector);
		ArrayList<GameElement> selection = GameEngine.getInstance()
				.selectBy(s -> s.getPosition().equals(nextPosition) && (!s.isTransposable()));
		if (selection.isEmpty()) {
			super.setPosition(nextPosition);
		} else {
			GameElement enemy = getEnemy(selection);
			if (enemy != null && enemy instanceof Movable) {
				attack(enemy);
			}
		}
	}

	public void attack(GameElement ge) {
		if (ge instanceof Movable) {
			((Movable) ge).hitpoints--;
			System.out.println(((Movable) ge).hitpoints); // DEBUG
		}
	}

	public int getKey(Direction d) {
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

	public GameElement getEnemy(ArrayList<GameElement> selection) {
		for (GameElement ge : selection) {
			if (ge instanceof Movable)
				return ge;
		}
		return null;

	}
}