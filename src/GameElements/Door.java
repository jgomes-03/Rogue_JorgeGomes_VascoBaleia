package GameElements;


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
	}
	
	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		if(isClosed) {
			return false;
		} else return true;
	}

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
