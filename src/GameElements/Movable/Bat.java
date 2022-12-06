package GameElements.Movable;


import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bat extends Movable {

	public Bat(Point2D position) {
		super(position);
		super.setHitpoints(3);
		super.setDamage(1);
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

	public boolean isMovable() {
		return true;
	}

	@Override
	public void move(int keyPressed) {
		if (Math.random() > 0.5) {
			super.move(getKey(Direction.random()));
		}
	}
	

	@Override
	public void attack(Movable m, int damage) {
		if(m instanceof Hero && Math.random() > 0.5)
			super.attack(m, damage);
	}

}
