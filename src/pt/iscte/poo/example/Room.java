package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import GameElements.Pickable.Key;
import GameElements.Static.Door;
import GameElements.Static.LifeTile;
import pt.iscte.poo.gui.ImageTile;
//import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {
	List<GameElement> roomObjects = new ArrayList<>();
	List<LifeTile> lifeBar = new ArrayList<>();
	List<GameElement>  inventoryBar = new ArrayList<>();

	private String roomName;

	public Room(String roomName) {
		this.roomName = roomName;
	}
	
	public String getName() {
		return roomName;
	}
	
	public void generateCurrentMap() {
		try {
			Scanner roomRead = new Scanner(new File("rooms/" + roomName+ ".txt"));
			int i = 0;

			// reads the map
			while (roomRead.hasNext() && i < GameEngine.GRID_HEIGHT) {
				String current = roomRead.nextLine();
				int j = 0;
				for (int k = 0; k < current.length(); k++) {
					GameElement currentElement = GameElement.create(String.valueOf(current.charAt(k)),
							new Point2D(j, i), null, null, null);
					roomObjects.add(currentElement);
					j++;
				}
				i++;
			}

			while (roomRead.hasNext()) {
				String currentLine = roomRead.nextLine();
				if (i != 10) {
					Scanner lineRead = new Scanner(currentLine);
					lineRead.useDelimiter(",");
					String name = lineRead.next();
					int px = lineRead.nextInt();
					int py = lineRead.nextInt();
					GameElement currentElement = null;
					if (lineRead.hasNext()) {
						String keyOrNextRoom = lineRead.next();
						if (lineRead.hasNext()) { //ITS A DOOR
							int pxD = lineRead.nextInt();
							int pyD = lineRead.nextInt();
							if (lineRead.hasNext()) {
								String keyCode = lineRead.next();
								// DOOR W/ KEY
								currentElement = GameElement.create(name, new Point2D(px, py), keyOrNextRoom,
										new Point2D(pxD, pyD), keyCode);
							} else {
								// DOOR WITHOUT/ KEY
								currentElement = GameElement.create(name, new Point2D(px, py), keyOrNextRoom,
										new Point2D(pxD, pyD), null);
							}
						} else {
							currentElement = GameElement.create(name, new Point2D(px, py), null, null, keyOrNextRoom);
						}
					} else {
						currentElement = GameElement.create(name, new Point2D(px, py), null, null, null);
					}
					lineRead.close();
					roomObjects.add(currentElement);
				}
				i++;

			}
			roomRead.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
	}
	
	public static void generateMap(Room room) {
		room.generateCurrentMap();
	}

}
