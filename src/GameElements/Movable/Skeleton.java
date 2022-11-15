package GameElements.Movable;

import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends Movable  {
		
	/*public Skeleton(int hitpoints, int damage) {
		super(hitpoints, damage);
		this.setHitpoints(GameSettings.skeleton_hitpoints);
		this.setDamage(GameSettings.skeleton_damage);
	}*/
	
	public Skeleton(Point2D position) {
		super(position);
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
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void move(int keyPressed) {
		if(GameEngine.getInstance().getTurns()%2==0) {
			super.move(keyPressed);
		}
	}
}
