package pt.iscte.poo.GameStats;


import pt.iscte.poo.utils.Point2D;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile {

	private File room;
	private static final char MAPCHAR = '#';
	
	public ReadFromFile(File f) {
		this.room = f;
	}

	
	public List<Point2D> getWalls(){ //UM METODO QUE CHAMA O METODO DE FABRIC DO GAMEELEMENT
		try {
			List<Point2D> points = new ArrayList<>();
			Scanner roomRead = new Scanner(room);
			int i=0;
			while(roomRead.hasNext()) {
				char[] current = roomRead.nextLine().toCharArray();
				int j=0;
				for(char c : current) {
					if(c==MAPCHAR) points.add(new Point2D(j,i));
					j++;
				}
				i++;
			}
			roomRead.close();
			return points;
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
		return null;
	}
	
	//?????
	public List<Point2D> getEnemies(){
		try {
			List<Point2D> points = new ArrayList<>();
			Scanner roomRead = new Scanner(room);
			roomRead.useDelimiter(",");
			while(roomRead.hasNext()) {
				String current = roomRead.next();
				switch(current) {
				case "Skeleton":
					
				}
			}
			roomRead.close();
			return points;
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
		return null;
	}

}
