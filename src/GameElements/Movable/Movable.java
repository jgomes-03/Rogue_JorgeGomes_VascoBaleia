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

	public void move(int keyPressed) {
		Vector2D moveVector = (Direction.directionFor(keyPressed)).asVector();
		Point2D nextPosition = this.getPosition().plus(moveVector);
		System.out.println("Object: " + this.getName() + " Position: " + nextPosition);
		ArrayList<GameElement> selection = GameEngine.getInstance()
				.selectBy(s -> s.getPosition().equals(nextPosition) && (!s.isTransposable())) ;
		if(this instanceof Skeleton) {
			System.out.println(selection);
		}
		if (selection.isEmpty()) {
			super.setPosition(nextPosition);
		} else {
			GameElement enemy = getEnemy(nextPosition);
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

	public GameElement getEnemy(Point2D position) {
		List<GameElement> habitatnatural = GameEngine.getInstance()
				.selectBy(s -> s.getPosition().equals(position));
		if (habitatnatural.isEmpty()) {
			System.out.println(habitatnatural);
		} else {
			for(GameElement ge : habitatnatural) {
				if(ge instanceof Movable) return ge;
			}
		}
		return null;
			
	}
}