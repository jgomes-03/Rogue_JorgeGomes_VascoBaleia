package GameElements.Movable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import GameElements.Pickable.Key;
import GameElements.Pickable.Pickable;
import GameElements.Static.Door;
import GameElements.Static.InventoryBar;
import GameElements.Static.Lifebar;
import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Movable extends GameElement {

	private int hitpoints;
	private int damage;

	public Movable(Point2D position) {
		super(position);
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
		if (!isInsideWindow(nextPosition)) {
			return; // Checks if the Movable is inside GameBorders
		}
		if (selection.isEmpty()) {
			super.setPosition(nextPosition);
		} else if (this.getName().equals("Hero")) {
			if (selection.get(0) instanceof Pickable) {
				GameEngine.getInstance().getHero().pickToInventory(((Pickable) selection.get(0)));
				GameEngine.getInstance().addPlayerScore(5);
			} else if (selection.get(0).getName() == "DoorClosed") {
				for (Pickable p : GameEngine.getInstance().getHero().getInventory()) {
					if (p != null && p.getName().equals("Key")) {
						if (((Key) p).getKeycode().equals(((Door) selection.get(0)).getKeycode())) {
							((Door) selection.get(0)).openDoor();
							GameEngine.getInstance().addPlayerScore(15);
						}
					}
				}
			} else if (selection.get(0).getName().equals("DoorOpen")) {
				GameEngine.getInstance().nextRoom(((Door) selection.get(0)).getNextRoom(),
						((Door) selection.get(0)).getNextRoomPosition());

			} else if (selection.get(0).getName().equals("Treasure")) {
				GameEngine.getInstance().addPlayerScore(100);
				GameEngine.getInstance().gameWin();
			}
		}
		GameElement enemy = getEnemy(selection);
		if (enemy != null && enemy instanceof Movable)
			attack((Movable) enemy, damage);

	}

	public void attack(Movable m, int damage) {
		if (!m.isDeadOnNextAttack()) {
			if (m.getName().equals("Hero") && ((Hero) m).isArmored() && Math.random() > 0.5) {
				return;
			}
			m.hitpoints = m.hitpoints - damage;
		} else {
			if (getName().equals("Hero")) {
				if (m.getName().equals("Skeleton") )
					GameEngine.getInstance().addPlayerScore(10);
				if (m.getName().equals("Bat"))
					GameEngine.getInstance().addPlayerScore(15);
				if (m.getName().equals("Thug"))
					GameEngine.getInstance().addPlayerScore(20);
				if (m.getName().equals("Scorpio"))
					GameEngine.getInstance().addPlayerScore(25);
				if (m.getName().equals("Thief"))
					GameEngine.getInstance().addPlayerScore(30);
			}
			m.die();
			kill(m);
		}
	}

	public void die() {
		// Implement to specify actions of the killed Movable before actually killing (for example, in Thief, drop item)
	}

	public void kill(Movable ge) {
		if (!(ge.getName().equals("Hero")))
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

	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	private boolean isInsideWindow(Point2D nextPosition) {
		return nextPosition.getX() >= 0 && nextPosition.getX() < GameEngine.GRID_WIDTH && nextPosition.getY() >= 0
				&& nextPosition.getY() < GameEngine.GRID_HEIGHT ? true : false;
	}
}