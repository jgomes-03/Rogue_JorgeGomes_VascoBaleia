package GameElements;

import java.util.ArrayList;
import pt.iscte.poo.example.EngineExample;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.Room;
import pt.iscte.poo.example.movable;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends GameElement implements movable {

	private int hitpoints = 10;

	public Hero(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Hero";
	}

	public void move(int keyPressed) {
		Direction moveTo = Direction.directionFor(keyPressed);
		Vector2D moveVector = moveTo.asVector();
		if (canMove(moveVector) ) {
			super.setPosition(getPosition().plus(moveVector)); // proxima position do heroi
		}
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
	
	public boolean canMove(Vector2D moveVector) {
		Point2D nextPosition = this.getPosition().plus(moveVector);
		/*
		if(currentRoom.getRoomLayout().get(nextPosition.getY()*EngineExample.GRID_HEIGHT + nextPosition.getX()).isTransposable()) {
			for(GameElement element : currentRoom.getRoomObjects()) {
				if(element.getPosition().equals(nextPosition) && !element.isTransposable()) {
					return false;
				} else return true;
			}
		}*/
		
		
		ArrayList<GameElement> selection = EngineExample.getInstance().selectBy(s -> s.getPosition().equals(nextPosition) && !s.isTransposable());
		if(selection.isEmpty()) {
			System.out.println("EMPTY");
			return true;
		} else selection.forEach(s -> System.out.println(s));
		return false;
		
	}

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
