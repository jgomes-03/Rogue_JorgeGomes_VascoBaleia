package pt.iscte.poo.GameStats;

public class Player {
	
	private String name;
	private int score;
	
	public Player(String name,int score){
		this.setName(name);
		this.setScore(score);
	}
	
	public Player(String name){
		this.setName(name);
		score=0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
