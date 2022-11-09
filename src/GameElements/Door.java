package GameElements;


import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement  {

	private String keyCode;
	private String nextRoom;
	private Point2D nextRoomPosition;

	public Door(Point2D position,String nextRoom,Point2D nextRoomPosition,String keyCode) {
		super(position);
		this.keyCode = keyCode;
		this.nextRoom = nextRoom;
		this.nextRoomPosition = nextRoomPosition;
	}
	
	@Override
	public String getName() {
		return "DoorClosed";
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
	
}
