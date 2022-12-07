package GameElements.Movable;


import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Scorpio extends Movable {

	public Scorpio(Point2D position) {
		super(position);
		super.setHitpoints(3);
	}

	@Override
	public String getName() {
		return "Scorpio";
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
