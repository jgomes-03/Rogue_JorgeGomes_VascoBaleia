package GameElements.Movable;

import pt.iscte.poo.example.movable;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends movable  {
	
	public Bat(Point2D position) {
		super(position);
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

	@Override
	public boolean canMove(Vector2D moveVector) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
