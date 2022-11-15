package GameElements.Movable;

import java.util.ArrayList;

import GameElements.Static.Door;
import pt.iscte.poo.example.Engine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.Room;
import pt.iscte.poo.example.movable;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends GameElement implements movable {

	private ArrayList<GameElement> inventory;

	public Hero(Point2D position) {
		super(position);
		inventory = new ArrayList<>();
	}

	@Override
	public String getName() {
		return "Hero";
	}
	
	public void move(int keyPressed) {
		Direction moveTo = Direction.directionFor(keyPressed);
		Vector2D moveVector = moveTo.asVector();
		if (canMove(moveVector)) {
			super.setPosition(getPosition().plus(moveVector)); // proxima position do heroi
		}
	}

	public void addInventory(GameElement ge) {
		if (ge != null) {
			inventory.add(ge);
			Engine.getInstance().removeObject(ge);
//			ArrayList<GameElement> selection = Engine.getInstance().selectBy(s -> s.getName()=="DoorClosed");
//			for(GameElement element : selection) {
//				if(ge instanceof Key && element instanceof Door) {
//					Key k = (Key)ge;
//					Door d = (Door)element;
//					if(k.getKeycode().equals(d.getKeycode())) d.openDoor();
//					Engine.getInstance().removeObject(element);
//					Engine.getInstance().addObject(element);
//					
//				}
//			}
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
		ArrayList<GameElement> selection = Engine.getInstance().selectBy(s -> s.getPosition().equals(nextPosition) && (!s.isTransposable() || s instanceof Door));
		if(selection.isEmpty()) {
			return true;
		} else {
			for(GameElement ge : selection) {
				if(ge.isPickable()) {
					addInventory(ge);
					return true;
				} else if(ge.getName().equals("DoorOpen")) {
					Engine.getInstance().nextRoom();
				}
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
