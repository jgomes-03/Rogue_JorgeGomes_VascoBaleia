package pt.iscte.poo.GameStats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import pt.iscte.poo.gameEngine.GameEngine;

public class GameScores {

	public static void writeToFile(File scoreBoardFile) {
		GameEngine.getInstance().getScoreBoard().sort(new PlayerComparator());
		int aux =0;
		try {
			PrintWriter out = new PrintWriter(scoreBoardFile);
			for(Player p : GameEngine.getInstance().getScoreBoard()) {
				if(aux==5) break;
				out.println(aux+1 + "º: " + p);
				aux++;
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readFromFile(File scoreBoardFile) {
		try {
			Scanner in = new Scanner(scoreBoardFile);
			while (in.hasNextLine()) {
				String[] currentLine = in.nextLine().split(" with a score of: "); //The split divides the first scan from the file to: 1º: + PlayerName.
				String name = currentLine[0].split(": ")[1];
				GameEngine.getInstance().addToScoreBoard(new Player(name, Integer.parseInt(currentLine[1])));
			}
			/*for(Player p : GameEngine.getInstance().getScoreBoard()) {
				if(p == null) p.setName("None");
			}*/
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
