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
import GameElements.Pickable.Sword;
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
		addHero();
		nextRoom("room0", getHero().getPosition());
		hero.updateHeroBars();
		PlayerName = gui.askUser("Introduza o seu nome");
		if (PlayerName == null) {
			gui.dispose();
			gui.setMessage("Deverá escolher um username para puder jogar");
			System.exit(0);
			return;
		}
		addObject(new Sword(new Point2D(0, 10)));
		gui.setStatusMessage("ROGUE - Turns: " + turns + " | Player: " + PlayerName);
		gui.update();
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
		gui.removeImage(ge);
	}

	public void dropObject(Pickable p) {
		p.setPosition(hero.getPosition());
		addObject(p);
	}

	private void addRoom(String nextRoom) {
		File dir = new File("rooms/");
		for (int i = 0; i < dir.list().length; i++) {
			if (nextRoom.equals("room" + i)) {
				roomList.add(new Room("room" + i));
				Room next = null;
				for(Room r : GameEngine.getInstance().roomList) {
					if(r.getName().equals(nextRoom)) {
						next = r;
					}
				}
				Room.generateMap(GameEngine.getInstance().roomList.get(GameEngine.getInstance().roomList.indexOf(next)));
				currentRoom = i;
				return;
			}
		}
	}

	public void nextRoom(String nextRoom, Point2D heroNextPosition) {
		gui.clearImages();
		if (!GameEngine.getInstance().roomList.isEmpty()) {
			for (int i = 0; i < GameEngine.getInstance().roomList.size(); i++) {
				if (GameEngine.getInstance().roomList.get(i).getName().equals(nextRoom)) {
					currentRoom = i;
					break;
					
				} else if(i==GameEngine.getInstance().roomList.size()-1){
					addRoom(nextRoom);
					hero.updateHeroBars();
				}
				
			}
		} else addRoom(nextRoom);
		roomList.get(currentRoom).roomObjects.add(hero);
		for (GameElement ge : roomList.get(currentRoom).roomObjects) {
			gui.addImage(ge);
		}
		hero.setPosition(heroNextPosition);
	}

	public void clearLifeBar() {
		roomList.get(currentRoom).lifeBar.clear();
	}
	
	public void clearInventoryBar() {
		roomList.get(currentRoom).inventoryBar.clear();
	}

	@Override
	public void update(Observed source) {
		int key = ((ImageMatrixGUI) source).keyPressed();
		if (Direction.isDirection(key)) {
			Iterator<GameElement> iterator = roomList.get(currentRoom).roomObjects.iterator();
			while (iterator.hasNext()) {
				GameElement current = iterator.next();
				if (current instanceof Hero) {
					hero.move(key);
					turns++;
					gui.setStatusMessage("ROGUE - Turns:" + turns);
					gui.update();
					return;
				}
				if (current instanceof Movable) {
					((Movable) current).move(key);
				}
			}
		} else if ((key == KeyEvent.VK_1 || key == KeyEvent.VK_2 || key == KeyEvent.VK_3)
				&& hero.getInventory()[Pickable.getInventorySlot(key) - 1] != null) {
			hero.dropFromInventory(hero.getInventory()[Pickable.getInventorySlot(key) - 1]);
		}
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
