package pt.iscte.poo.example;

import GameElements.*;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

	// Metodo Fabrica
	public static GameElement create(String code, Point2D point, String roomName, Point2D nextPosition, String keyCode) {
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
		case "Sword":
			return new Sword(point);
		case "Key":
			return new Key(point,keyCode);
		case "Door":
			return new Door(point,roomName,nextPosition,keyCode);
		}
		return null;
	}

	public abstract boolean isTransposable();
	public abstract boolean isPickable();

}
