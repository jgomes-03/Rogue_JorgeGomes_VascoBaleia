package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.List;

import GameElements.Hero;
import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;


public class EngineExample implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;
	
	private static EngineExample INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	
	private Hero hero;
	private int turns;
	
	List<Room> roomList = new ArrayList<>();
	
	
	public static EngineExample getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EngineExample();
		return INSTANCE;
	}
	

	private EngineExample() {		
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
	}

	public void start() {
		addRooms();
		addObjects();
		gui.addImages(roomList.get(0).roomTiles);
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}
	
	
	
	private void addObjects() {
		hero = new Hero(new Point2D(1,1));
		gui.addImage(hero);
	}
	
	private void addRooms() {
		roomList.add(new Room("rooms/room0.txt"));
	}
	//
	@Override
	public void update(Observed source) {
		
		int key = ((ImageMatrixGUI) source).keyPressed();
			if(Direction.isDirection(key)){
				hero.move(key,roomList.get(0));
				turns++;
			}
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}
}
