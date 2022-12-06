package GameElements.Movable;

import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

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

	@Override
	public void move(int keyPressed) {
		keyPressed = super.getKey(Direction
				.forVector(Vector2D.movementVector(getPosition(), GameEngine.getInstance().getHero().getPosition())));
		super.move(keyPressed);
	}

	@Override
	public void attack(Movable m, int damage) {
		if (m instanceof Hero) {
			GameEngine.getInstance().getHero().setPoisoned(true);
			while (GameEngine.getInstance().getHero().getisPoisoned()) {
				super.attack(m, damage);
				//GameEngine.getInstance().getHero().
			}
		}
	}

}
