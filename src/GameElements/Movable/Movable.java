package GameElements.Movable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import GameElements.Pickable.Key;
import GameElements.Pickable.Pickable;
import GameElements.Static.Door;
import GameElements.Static.InventoryBar;
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
		setDamage(1);
	}

	public static Lifebar createLifebar(Point2D point, Movable g) {
		return new Lifebar(point, g);
	}
	
	public static InventoryBar createInventoryBar(Point2D point, Movable g) {
		return new InventoryBar(point, g);
	}

	public void setHitpoints(int h) {
		hitpoints = h;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int d) {
		this.damage = d;
	}

	public void move(int keyPressed) {
		Vector2D moveVector = (Direction.directionFor(keyPressed)).asVector();
		Point2D nextPosition = this.getPosition().plus(moveVector);
		ArrayList<GameElement> selection = GameEngine.getInstance()
				.selectBy(s -> s.getPosition().equals(nextPosition) && (!s.isTransposable()));
		if (selection.isEmpty()) {
			super.setPosition(nextPosition);
		} else if (this instanceof Hero) {
			if (selection.get(0) instanceof Pickable) {
				GameEngine.getInstance().getHero().pickToInventory(((Pickable) selection.get(0)));
			} else if (selection.get(0) instanceof Door) {
				if (selection.get(0).getName() == "DoorClosed") {
					for (Pickable p : GameEngine.getInstance().getHero().getInventory()) {
						if (p instanceof Key) {
							if (((Key) p).getKeycode().equals(((Door) selection.get(0)).getKeycode())) {
								((Door) selection.get(0)).openDoor();

							}
						}
					}
					return;
				} else {
					GameEngine.getInstance().nextRoom(((Door) selection.get(0)).getNextRoom(),
							((Door) selection.get(0)).getNextRoomPosition());
					return;
				}
			}
			System.out.println(GameEngine.getInstance().getHero().getHitpoints());
		} 
			GameElement enemy = getEnemy(selection);
			if (enemy != null && enemy instanceof Movable)
				attack((Movable) enemy, damage);
		
	}

	public void attack(Movable m, int damage) {
		if (!m.isDeadOnNextAttack()) {
			m.hitpoints = m.hitpoints - damage;
		} else
			kill(m);
		// if(m instanceof Hero)((Hero) m).updateLifeBar();
	}

	public void kill(Movable ge) {
		if (!(ge instanceof Hero))
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
		return getHitpoints() <= 1 ? true : false;
	}

	public Pickable[] getInventory() {
		return null;
	}
}