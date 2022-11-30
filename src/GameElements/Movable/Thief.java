package GameElements.Movable;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thief extends Movable  {
	
	/*public Skeleton(int hitpoints, int damage) {
		super(hitpoints, damage);
		this.setHitpoints(GameSettings.skeleton_hitpoints);
		this.setDamage(GameSettings.skeleton_damage);
	}*/
	
	public Thief(Point2D position) {
		super(position);
		super.setHitpoints(10);
	}

	public String getName() {
		return "Thief";
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

}
