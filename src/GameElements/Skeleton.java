package GameElements;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Skeleton extends GameElement /*implements movable*/  {
	
	private Point2D position;
	
	/*public Skeleton(int hitpoints, int damage) {
		super(hitpoints, damage);
		this.setHitpoints(GameSettings.skeleton_hitpoints);
		this.setDamage(GameSettings.skeleton_damage);
	}*/
	
	public Skeleton(Point2D position) {
		this.position = position;
	}

	public String getName() {
		return "Skeleton";
	}
	
	@Override
	public Point2D getPosition() {
		return this.position;
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
	

	
}
