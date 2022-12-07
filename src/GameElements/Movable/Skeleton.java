package GameElements.Movable;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends Movable  {
		
	public Skeleton(Point2D position) {
		super(position);
		super.setHitpoints(5);
		super.setDamage(1);
	}

	public String getName() {
		return "Skeleton";
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
		if(GameEngine.getInstance().getTurns()%2==0) {
			super.move(keyPressed);
		}
	}
	
	@Override
	public void attack(Movable m, int damage) {
		if(m instanceof Hero)
			super.attack(m, damage);
	}
}
