package GameElements.Movable;

import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thug extends Movable  {
	
	
	public Thug(Point2D position) {
		super(position);
		super.setHitpoints(10);
		super.setDamage(3);
	}

	public String getName() {
		return "Thug";
	}
	
	@Override
	public int getLayer() {
		return 0;
	}
	
	@Override
	public boolean isTransposable() {
		return false;
	}

	
	@Override
	public void move(int keyPressed) {
		keyPressed = super.getKey(Direction.forVector(Vector2D.movementVector(getPosition(), GameEngine.getInstance().getHero().getPosition())));
		super.move(keyPressed);
	}
	
	@Override
	public void attack(Movable m, int damage) {
		if(Math.random()<0.3) {
			super.attack(m, damage);
		}
	}

}
