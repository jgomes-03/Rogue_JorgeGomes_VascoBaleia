package pt.iscte.poo.example;
import pt.iscte.poo.test.*;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;



public abstract class movable extends GameElement{
	public movable(Point2D position) {
		super(position);
	}


	public void move(int keyPressed) {
		Direction moveTo = Direction.directionFor(keyPressed);
		Vector2D moveVector = moveTo.asVector();
		if (canMove(moveVector)) {
			super.setPosition(getPosition().plus(moveVector)); // proxima position do heroi
		}
	}
	
	public abstract boolean canMove(Vector2D moveVector);

}
