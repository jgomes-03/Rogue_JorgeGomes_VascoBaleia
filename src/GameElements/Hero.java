package GameElements;

import pt.iscte.poo.example.EngineExample;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.Room;
import pt.iscte.poo.example.movable;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends GameElement implements movable {

	private Point2D position;
	private int hitpoints = 10;

	public Hero(Point2D position) {
		this.position = position;
	}

	@Override
	public String getName() {
		return "Hero";
	}

	public int getHitpoints() {
		return this.hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public void move(int keyPressed) {
		Direction moveTo = Direction.directionFor(keyPressed);
		Vector2D moveVector = moveTo.asVector();
		if (canMove(moveVector) ) {
			position = position.plus(moveVector); // proxima position do heroi
		}
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean canMove(Vector2D moveVector,Room currentRoom) {
		Point2D nextPosition = position.plus(moveVector);
		if(currentRoom.getRoomLayout().get(nextPosition.getY()*EngineExample.GRID_HEIGHT + nextPosition.getX()).isTransposable()) {
			for(GameElement element : currentRoom.getRoomObjects()) {
				if(element.getPosition().equals(nextPosition) && !element.isTransposable()) {
					return false;
				} else return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
