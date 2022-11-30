package GameElements.Movable;

import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends Movable  {
		
	public Skeleton(Point2D position) {
		super(position);
		super.setHitpoints(5);
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
	public void attack(GameElement ge, int damage) {
		if(ge instanceof Hero)
			super.attack(ge, damage);
	}
}
