package pt.iscte.poo.gameEngine;

import GameElements.Consumable.HealingPotion;
import GameElements.Movable.*;
import GameElements.Pickable.*;
import GameElements.Static.*;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

	private Point2D position;

	public GameElement(Point2D position) {
		this.position = position;
	}

	// Metodo Fabrica
	public static GameElement create(String code, Point2D point, String roomName, Point2D nextPosition,
			String keyCode) {
		switch (code) {
		case "#":
			return new Wall(point);
		case " ":
			return new Floor(point);
		case "Hero":
			return new Hero(point);
		case "Skeleton":
			return new Skeleton(point);
		case "Bat":
			return new Bat(point);
		case "Scorpio":
			return new Scorpio(point);
		case "Sword":
			return new Sword(point);
		case "Key":
			return new Key(point, keyCode);
		case "Door":
			return new Door(point, roomName, nextPosition, keyCode);
		case "HealingPotion":
			return new HealingPotion(point);
		case "Armor":
			return new Armor(point);
		case "Thug":
			return new Thug(point);
		case "Thief":
			return new Thief(point);
		case "Treasure":
			return new Treasure(point);
		}
		return null;
	}

	public abstract boolean isTransposable();

	public Point2D getPosition() {
		return this.position;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}
}
