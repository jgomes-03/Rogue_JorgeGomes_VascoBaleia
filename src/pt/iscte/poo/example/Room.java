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
							new Point2D(j, i));
					roomLayout.add(currentElement);
					roomTiles.add(currentElement);
					j++;
				}
				i++;
			}
			while (roomRead.hasNext() && i<16) {
				String cuurentLine = roomRead.nextLine();
				if(i!=10) {
					Scanner lineRead = new Scanner(cuurentLine);
					lineRead.useDelimiter(",");
					GameElement currentElement = GameElement.create(lineRead.next(),
							new Point2D(lineRead.nextInt(), lineRead.nextInt()));
					roomObjects.add(currentElement);
					roomTiles.add(currentElement);
				}
				i++;
			}
			/*while (roomRead.hasNext()) {
				String cuurentLine = roomRead.nextLine();
				if(i!=10) {
					Scanner lineRead = new Scanner(cuurentLine);
					lineRead.useDelimiter(",");
					String name = lineRead.next();
					int px = lineRead.nextInt();
					int py = lineRead.nextInt();
					String nextRoom = lineRead.next();
					int nextPx = lineRead.nextInt();
					int nextPy = lineRead.nextInt();
					String keyCode = lineRead.next();
					GameElement currentElement = GameElement.create(name,
							new Point2D(px, py));
					gameElements.add(currentElement);
					tileList.add(currentElement);
				}
				i++;
			}*/
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
