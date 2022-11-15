package GameElements.Movable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GameElements.Static.Door;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Movable extends GameElement {
	public Movable(Point2D position) {
		super(position);
	}

	public void move(int keyPressed) {
		Direction moveTo = Direction.directionFor(keyPressed);
		Vector2D moveVector = moveTo.asVector();
		Point2D nextPosition = this.getPosition().plus(moveVector);
		ArrayList<GameElement> selection = GameEngine.getInstance()
				.selectBy(s -> s.getPosition().equals(nextPosition) && (!s.isTransposable() || s instanceof Door));
		if (selection.isEmpty()) {
			if(GameEngine.getInstance().getHeroPosition().equals(super.getPosition().plus(moveVector)))
				return; //If is in Hero view
			super.setPosition(getPosition().plus(moveVector)); // proxima position do heroi
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
}
