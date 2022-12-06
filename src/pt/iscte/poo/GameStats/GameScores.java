package pt.iscte.poo.GameStats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import pt.iscte.poo.example.GameEngine;

public class GameScores {

	

	public static void writeToFile(File scoreBoardFile) {
		GameEngine.getInstance().getScoreBoard().sort(new PlayerComparator());
		int aux =0;
		try {
			PrintWriter out = new PrintWriter(scoreBoardFile);
			for(Player p : GameEngine.getInstance().getScoreBoard()) {
				if(aux==5) break;
				out.println(p.getName() +"," + p.getScore());
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
				GameEngine.getInstance().addToScoreBoard(new Player(in.next(), in.nextInt()));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
