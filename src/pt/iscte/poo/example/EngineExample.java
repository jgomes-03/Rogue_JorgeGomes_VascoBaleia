package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

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
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}
	
	
	
	private void addObjects() {
		hero = new Hero(new Point2D(1,1));
		gui.addImage(hero);
	}
	
	private void addRooms() {
		roomList.add(new Room("rooms/room0.txt"));
		for(GameElement ge : roomList.get(0).roomObjects) {
			gui.addImage(ge);
		}
	}
	//
	@Override
	public void update(Observed source) {
		
		int key = ((ImageMatrixGUI) source).keyPressed();
			if(Direction.isDirection(key)){
				hero.move(key);
				turns++;
			}
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}
	
	public ArrayList<GameElement> selectBy(Predicate<GameElement> predicate){
		ArrayList<GameElement> result = new ArrayList<>();
		Iterator<GameElement> iterator = roomList.get(0).roomObjects.iterator();
		while(iterator.hasNext()) {
			GameElement current = iterator.next();
			if(predicate.test(current)) {
				result.add(current);
			}
		}
		return result;
	}
	
}
