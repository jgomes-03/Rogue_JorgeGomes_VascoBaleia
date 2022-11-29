package pt.iscte.poo.example;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import GameElements.Movable.Hero;
import GameElements.Movable.Movable;
import GameElements.Pickable.Pickable;
import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class GameEngine implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;

	private static GameEngine INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

	private Hero hero;
	private int turns;
	private int currentRoom = 0;
	
	private String PlayerName;

	List<Room> roomList = new ArrayList<>();

	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	private GameEngine() {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT + 1);
		gui.go();
	}

	public void start() {
		addRooms();
		addHero();
		nextRoom();
		hero.updateLifeBar();
		PlayerName = gui.askUser("Introduza o seu nome");
		gui.setStatusMessage("ROGUE - Turns: " + turns + " | Player: " + PlayerName);
		gui.update();
		// addObject(new Lifebar((super.,new Point2D(0,11)));
	}
	
	public void GameOver() {
		gui.dispose();
		gui.setMessage("Game Over " + PlayerName + "!");
	}

	public int getTurns() {
		return turns;
	}

	public Hero getHero() {
		return hero;
	}

	private void addHero() {
		hero = new Hero(new Point2D(1, 1));
		gui.addImage(hero);
	}

	public void addObject(GameElement ge) {
		roomList.get(currentRoom).roomObjects.add(ge);
		gui.addImage(ge);
	}

	public void removeObject(GameElement ge) {
		roomList.get(currentRoom).roomObjects.remove(ge);
		gui.removeImage(ge);
	}

	
	public void dropObject(Pickable p) {
		p.setPosition(hero.getPosition());
		addObject(p);
	}

	private void addRooms() {
		File dir = new File("rooms/");
		for (int i = 0; i < dir.list().length; i++) {
			roomList.add(new Room("rooms/room" + i + ".txt"));
		}
	}

	public void nextRoom() {
		Room.generateMap(GameEngine.getInstance().roomList.get(GameEngine.getInstance().currentRoom));
		roomList.get(currentRoom).roomObjects.add(hero);
		for (GameElement ge : roomList.get(currentRoom).roomObjects) {
			gui.addImage(ge);
		}
		// addObject(hero);
		hero.setPosition(new Point2D(1, 1));
	}

	private void previousRoom() {
		currentRoom--;
		Room.generateMap(roomList.get(currentRoom));
	}
	
	public void clearLifebar() {
		roomList.get(currentRoom).lifeBar.clear();
	}

	@Override
	public void update(Observed source) {
		int key = ((ImageMatrixGUI) source).keyPressed();
		if (Direction.isDirection(key)) {
			Iterator<GameElement> iterator = roomList.get(currentRoom).roomObjects.iterator();
			while (iterator.hasNext()) {
				GameElement current = iterator.next();
				if (current instanceof Movable) {
					((Movable) current).move(key);
				}
			}
		} 
		else if(key == KeyEvent.VK_1 || key == KeyEvent.VK_2 || key == KeyEvent.VK_3) {
			hero.dropFromInventory(hero.getInventory().get(key));
		}
		turns++;
		hero.updateLifeBar();
		gui.setStatusMessage("ROGUE - Turns:" + turns);
		gui.update();
	}

	public ArrayList<GameElement> selectBy(Predicate<GameElement> predicate) {
		ArrayList<GameElement> result = new ArrayList<>();
		Iterator<GameElement> iterator = roomList.get(currentRoom).roomObjects.iterator();
		while (iterator.hasNext()) {
			GameElement current = iterator.next();
			if (predicate.test(current)) {
				result.add(current);
			}
		}
		return result;
	}
	
}
