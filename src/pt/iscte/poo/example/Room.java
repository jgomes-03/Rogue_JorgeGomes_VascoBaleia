package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageTile;
//import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {
	List<GameElement> roomObjects = new ArrayList<>();
	List<GameElement> roomLayout = new ArrayList<>();
	List<ImageTile> roomTiles = new ArrayList<>();
	String roomName;

	public Room(String roomName) {
		this.roomName = roomName;
		generateMap();
	}

	public void generateMap() {
		try {
			Scanner roomRead = new Scanner(new File(roomName));
			int i = 0;

			// reads the map
			while (roomRead.hasNext() && i < EngineExample.GRID_HEIGHT) {
				String current = roomRead.nextLine();
				int j = 0;
				for (int k = 0; k < current.length(); k++) {
					GameElement currentElement = GameElement.create(String.valueOf(current.charAt(k)),
							new Point2D(j, i),null,null,null);
					roomLayout.add(currentElement);
					roomTiles.add(currentElement);
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
						if (lineRead.hasNext()) {
							// DOOR
							currentElement = GameElement.create(name, new Point2D(px, py), keyOrNextRoom,
									new Point2D(lineRead.nextInt(), lineRead.nextInt()), lineRead.next());
						} else {
							currentElement = GameElement.create(name, new Point2D(px, py), null,null,keyOrNextRoom);
						}
					} else {
						currentElement = GameElement.create(name, new Point2D(px, py),null,null,null);
					}
					lineRead.close();
					roomObjects.add(currentElement);
					roomTiles.add(currentElement);
				}
				i++;

			}
			roomRead.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
	}

	public List<GameElement> getRoomLayout() {
		return roomLayout;
	}

	public List<GameElement> getRoomObjects() {
		return roomObjects;
	}

}
