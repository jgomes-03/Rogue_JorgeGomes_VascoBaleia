package GameElements.Movable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import GameElements.Pickable.Pickable;
import GameElements.Static.Door;
import GameElements.Static.Lifebar;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Movable extends GameElement {

	private int hitpoints;
	private int damage;

	public Movable(Point2D position) {
		super(position);
		damage = 1;
	}

	public static Lifebar createLifebar(Point2D point, Movable g) {
		return new Lifebar(point,g);
}
	
	public void setHitpoints(int h) {
		hitpoints = h;
	}
	
	/*public void setDamage(int d) {
		damage=d;
	} Not sure if need it yet*/

	public int getHitpoints() {
		return hitpoints;
	}
	
	public int getDamage() {
		return damage;
	}

	public void move(int keyPressed) {
		Vector2D moveVector = (Direction.directionFor(keyPressed)).asVector();
		Point2D nextPosition = this.getPosition().plus(moveVector);
		ArrayList<GameElement> selection = GameEngine.getInstance()
				.selectBy(s -> s.getPosition().equals(nextPosition) && (!s.isTransposable()));
		if (selection.isEmpty()) {
			super.setPosition(nextPosition);
		} else if(selection.get(0) instanceof Pickable && this instanceof Hero) {
			GameEngine.getInstance().getHero().pickToInventory(((Pickable)selection.get(0)));
		} else {
			Movable enemy = (Movable)getEnemy(selection);
			if (enemy != null && enemy instanceof Movable) {
					attack(enemy,damage);
			}
		}
	}

	public void attack(GameElement ge, int damage) {
		if (ge instanceof Movable) {
			if(!((Movable)ge).isDeadOnNextAttack())	
				((Movable) ge).hitpoints = ((Movable) ge).hitpoints-damage;
			else
				kill((Movable)ge);
			if(ge instanceof Skeleton) System.out.println(((Skeleton) ge).getHitpoints()); // DEBUG
		}
	}


	public void kill(Movable ge) {
		if(!(ge instanceof Hero))
			GameEngine.getInstance().removeObject(ge);
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
	
	public boolean isDeadOnNextAttack() {
		return getHitpoints()<=1?true:false;
	}
	
}