package GameElements.Movable;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Thug extends GameElement /*implements movable*/  {
	
	/*public Skeleton(int hitpoints, int damage) {
		super(hitpoints, damage);
		this.setHitpoints(GameSettings.skeleton_hitpoints);
		this.setDamage(GameSettings.skeleton_damage);
	}*/
	
	public Thug(Point2D position) {
		super(position);
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
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}
}
