package pt.iscte.poo.example;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import GameElements.Consumable.Consumable;
import GameElements.Movable.Hero;
import GameElements.Movable.Movable;
import GameElements.Pickable.Pickable;
import GameElements.Pickable.Sword;
import GameElements.Static.LifeTile;
import pt.iscte.poo.GameStats.GameScores;
import pt.iscte.poo.GameStats.Player;
import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class GameEngine implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;
	private File scoreBoardFile = new File("info/top_score.txt");
	private Player player;

	private static GameEngine INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

	private Hero hero;
	private int turns;
	private int currentRoom = 0;

	private ArrayList<Player> scoreBoard = new ArrayList<>();

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
		if(scoreBoardFile.exists() || scoreBoardFile.length()!=0) GameScores.readFromFile(scoreBoardFile);
		addHero();
		nextRoom("room0", getHero().getPosition());
		hero.updateHeroBars();
		while (player == null || player.getName() == null || player.getName().isEmpty() || player.getName().isBlank()) {
			player = new Player(gui.askUser("Introduza o seu nome"));
			if(player.getName() == null) {
				gui.dispose();
				gui.setMessage("Obrigado por jogar o Rogue das Conas");
				System.exit(0);
			} else if(player.getName().isEmpty() || player.getName().isBlank()) {
				gui.setMessage("Insira um nome válido");
			}
				
		}
		addObject(new Sword(new Point2D(0, 10)));
		gui.setStatusMessage("ROGUE - Turns: " + turns + " | Player: " + player.getName());
		gui.update();
	}
	
	public void GameOver() {
		scoreBoard.add(player);
		gui.dispose();
		gui.setMessage("Game Over " + player.getName() + "!");
		GameScores.writeToFile(scoreBoardFile);
		System.exit(0);
	}
	
	public void gameWin() {
		scoreBoard.add(player);
		gui.setMessage("PARABENS SEU CORNO DO CARALHO!!! SEU PRETO, MISOGENO!!!!");
		GameScores.writeToFile(scoreBoardFile);
		System.exit(1);
	}
	
	public void addPlayerScore(int value) {
		player.setScore(player.getScore()+value);
	}
	
	public ArrayList<Player> getScoreBoard() {
		return scoreBoard;
	}
	
	public void addToScoreBoard(Player p1) {
		scoreBoard.add(p1);
	}
	
	public void removeFromScoreBoard(Player p1) {
		scoreBoard.remove(p1);
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
	
	public void addToBar(GameElement ge) {
		if(ge instanceof LifeTile) {
			//roomList.get(currentRoom).LifeBarTiles.clear();
			roomList.get(currentRoom).LifeBarTiles.add((LifeTile) ge);
		}
		else if(ge instanceof Pickable) {
			//roomList.get(currentRoom).InventoryBarTiles.clear();
			roomList.get(currentRoom).InventoryBarTiles.add((Pickable) ge);
			
		}
		gui.addImage(ge);
	}

	public void removeObject(GameElement ge) {
		roomList.get(currentRoom).roomObjects.remove(ge);
		gui.removeImage(ge);
		//gui.removeImage(ge);
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
				}
				
			}
		} else addRoom(nextRoom);
		roomList.get(currentRoom).roomObjects.add(hero);
		for (GameElement ge : roomList.get(currentRoom).roomObjects) {
			gui.addImage(ge);
		}
		hero.updateHeroBars();
		hero.setPosition(heroNextPosition);
	}

	public void clearLifeBarTiles() {
		for(LifeTile lt: roomList.get(currentRoom).LifeBarTiles)
			gui.removeImage(lt);
		roomList.get(currentRoom).LifeBarTiles.clear();
	}
	
	public void clearInventoryBarTiles() {
		for(Pickable p: roomList.get(currentRoom).InventoryBarTiles)
			gui.removeImage(p);
		roomList.get(currentRoom).InventoryBarTiles.clear();
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
					hero.updateHeroBars();
					turns++;
					gui.setStatusMessage("ROGUE - Turns:" + turns + " | Player: " + player.getName());
					gui.update();
					return;
				}
				if (current instanceof Movable) {
					((Movable) current).move(key);
				}
			}
		} else if (key == KeyEvent.VK_1 || key == KeyEvent.VK_2 || key == KeyEvent.VK_3){
			hero.getInventoryBar().setSelectPointer(Pickable.getInventorySlot(key));
		} else if (key == KeyEvent.VK_D && hero.getInventory()[hero.getInventoryBar().getSelectPointer()-1] != null) { //DROP
				hero.dropFromInventory(hero.getInventory()[hero.getInventoryBar().getSelectPointer()-1]);
		} else if ((key == KeyEvent.VK_C)) {
				if(hero.getInventory()[hero.getInventoryBar().getSelectPointer()-1] instanceof Consumable) {
					((Consumable)hero.getInventory()[hero.getInventoryBar().getSelectPointer()-1]).consume(hero.getInventoryBar().getSelectPointer()-1);
					hero.updateHeroBars();		
			}
		}
		gui.setStatusMessage("ROGUE - Turns:" + turns + " | Player: " + player.getName());
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
