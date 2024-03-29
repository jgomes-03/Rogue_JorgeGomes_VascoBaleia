package pt.iscte.poo.gameEngine;

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
	
	private static GameEngine INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

	private Hero hero;
	private int turns;
	private int currentRoom = 0;
	private Player player;

	private ArrayList<Player> scoreBoard = new ArrayList<>();

	private List<Room> roomList = new ArrayList<>();

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
			player = new Player(gui.askUser("Introduz o teu nome"));
			if(player.getName() == null) {
				gui.dispose();
				gui.setMessage("Obrigado por jogar o Rogue - ISCTE");
				System.exit(0);
			} else if(player.getName().isEmpty() || player.getName().isBlank()) {
				gui.setMessage("Insira um nome válido");
			}
		}
		updateGameHeader();
		gui.update();
	}
	
	public void GameOver() {
		addToScoreBoard(player);
		gui.dispose();
		gui.setMessage("Game Over " + player.getName() + "!");
		GameScores.writeToFile(scoreBoardFile);
		printTopScore();
		System.exit(0);
	}
	
	public void gameWin() {
		addToScoreBoard(player);
		gui.setMessage("Vitória!");
		GameScores.writeToFile(scoreBoardFile);
		printTopScore();
		System.exit(1);
	}
	
	public void updateGameHeader() {
		gui.setStatusMessage("ROGUE - Turns: " + turns + " | Player: " + player.getName() + " | Score: " + player.getScore());
	}
	
	public void printTopScore() {
		String result = "TOP 5 BEST SCORE OF ALL TIME\n";
		for(Player p : scoreBoard) {
			result += "\n" + (scoreBoard.indexOf(p)+1) + "º: " + p;
		}
		gui.setMessage(result);
	}
	
	public void addPlayerScore(int value) {
		player.setScore(player.getScore()+value);
		updateGameHeader();
	}
	
	public ArrayList<Player> getScoreBoard() {
		return scoreBoard;
	}
	
	public void addToScoreBoard(Player p1) {
		for(Player p : scoreBoard) {
			if(p.getName().equals(p1.getName())) {
				p.setScore(p1.getScore());
				return;
			}
		}
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
		getRoomList().get(currentRoom).roomObjects.add(ge);
		gui.addImage(ge);
	}
	
	public void removeObject(GameElement ge) {
		getRoomList().get(currentRoom).roomObjects.remove(ge);
		gui.removeImage(ge);
	}

	public void dropObject(Pickable p) {
		p.setPosition(hero.getPosition());
		addObject(p);
	}
	
	public void addToBar(GameElement ge) {
		if(ge instanceof LifeTile) {
			getRoomList().get(currentRoom).LifeBarTiles.add((LifeTile) ge);
		}
		else if(ge instanceof Pickable) {
			getRoomList().get(currentRoom).InventoryBarTiles.add((Pickable) ge);
			
		}
		gui.addImage(ge);
	}

	public void clearLifeBarTiles() {
		for(LifeTile lt: getRoomList().get(currentRoom).LifeBarTiles)
			gui.removeImage(lt);
		getRoomList().get(currentRoom).LifeBarTiles.clear();
	}
	
	public void clearInventoryBarTiles() {
		for(Pickable p: getRoomList().get(currentRoom).InventoryBarTiles)
			gui.removeImage(p);
		getRoomList().get(currentRoom).InventoryBarTiles.clear();
	}
	
	private void addRoom(String nextRoom) {
		File dir = new File("rooms/");
		for (int i = 0; i < dir.list().length; i++) {
			if (nextRoom.equals("room" + i)) {
				getRoomList().add(new Room("room" + i));
				Room next = null;
				for(Room r : GameEngine.getInstance().getRoomList()) {
					if(r.getName().equals(nextRoom)) {
						next = r;
					}
				}
				Room.generateMap(GameEngine.getInstance().getRoomList().get(GameEngine.getInstance().getRoomList().indexOf(next)));
				currentRoom = i;
				return;
			}
		}
	}

	public void nextRoom(String nextRoom, Point2D heroNextPosition) {
		gui.clearImages();
		if (!GameEngine.getInstance().getRoomList().isEmpty()) {
			for (int i = 0; i < GameEngine.getInstance().getRoomList().size(); i++) {
				if (GameEngine.getInstance().getRoomList().get(i).getName().equals(nextRoom)) {
					currentRoom = i;
					break;
					
				} else if(i==GameEngine.getInstance().getRoomList().size()-1){
					addRoom(nextRoom);	
				}
				
			}
		} else addRoom(nextRoom);
		getRoomList().get(currentRoom).roomObjects.add(hero);
		for (GameElement ge : getRoomList().get(currentRoom).roomObjects) {
			gui.addImage(ge);
		}
		hero.updateHeroBars();
		hero.setPosition(heroNextPosition);
	}

	@Override
	public void update(Observed source) {
		int key = ((ImageMatrixGUI) source).keyPressed();
		if (Direction.isDirection(key)) {
			hero.move(key);
			hero.PoisonDamage();	
			Iterator<GameElement> iterator = getRoomList().get(currentRoom).roomObjects.iterator();
			while (iterator.hasNext()) {
				GameElement current = iterator.next();
				if (current instanceof Movable && current.getName() != "Hero") {
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
					addPlayerScore(-10); //Punishment for using consumables
					return; //doesnt add round
			}
		}
		hero.updateHeroBars();
		turns++;
		updateGameHeader();
		gui.update();
	}
	

	public ArrayList<GameElement> selectBy(Predicate<GameElement> predicate) {
		ArrayList<GameElement> result = new ArrayList<>();
		Iterator<GameElement> iterator = getRoomList().get(currentRoom).roomObjects.iterator();
		while (iterator.hasNext()) {
			GameElement current = iterator.next();
			if (predicate.test(current)) {
				result.add(current);
			}
		}
		return result;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

}