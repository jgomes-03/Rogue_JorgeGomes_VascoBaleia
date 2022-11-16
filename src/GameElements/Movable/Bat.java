package GameElements.Movable;

import java.awt.event.KeyEvent;
import pt.iscte.poo.example.*;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends Movable {

	public Bat(Point2D position) {
		super(position);
		super.setHitpoints(3);
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
			super.move(getKey(Direction.random()));
		}
	}
	
//	@Override
//	public void attack(GameElement ge) {
//		if(Math.random() > 0.5) {
//			super.attack(ge);
//		}
//	}

}
