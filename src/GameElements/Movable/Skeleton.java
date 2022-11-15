package GameElements.Movable;

import pt.iscte.poo.example.Engine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.movable;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends GameElement implements movable  {
		
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

	public void move(int keyPressed) {
		if(Engine.getInstance().getTurns()%2==0 || Engine.getInstance().getTurns()!=0) {
			Direction moveTo = Direction.random();
			Vector2D moveVector = moveTo.asVector();
			super.setPosition(getPosition().plus(moveVector)); // proxima position do heroi
		}
	}
}
