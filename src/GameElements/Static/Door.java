package GameElements.Static;


import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement  {

	private String keyCode;
	private String nextRoom;
	private Point2D nextRoomPosition;
	private boolean isClosed;

	public Door(Point2D position,String nextRoom,Point2D nextRoomPosition,String keyCode) {
		super(position);
		this.keyCode = keyCode;
		this.nextRoom = nextRoom;
		this.nextRoomPosition = nextRoomPosition;
		isClosed = true;
	}
	
	public String getName() {
		if(isClosed) {
			return "DoorClosed";
		} else return "DoorOpen";
	}
	
	
	public String getKeycode() {
		return keyCode;
	}

	public int getLayer() {
		return 0;
	}
	
	public void openDoor() {
		isClosed = false;
//		Key k = (Key)ge;
//		Door d = (Door)element;
//		if(k.getKeycode().equals(d.getKeycode())) d.openDoor();
//		Engine.getInstance().removeObject(element);
//		Engine.getInstance().addObject(element);
		//Make this a void, and if opens successfully return true, 
		//else return false and perhaps throw an error in the GUI or
		//play a sound? could be unimplemented even
	}
	
	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		/*if(isClosed) {
			return false;
		} else return true;*/
		return false;
	}

	public String getNextRoom() {
		return nextRoom;
	}

	public Point2D getNextRoomPosition() {
		return nextRoomPosition;
	}
	
	
}
