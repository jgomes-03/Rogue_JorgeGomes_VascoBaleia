package GameElements.Static;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Point2D;
import GameElements.Movable.Movable;
import GameElements.Static.LifeTile.*;

public class Lifebar extends GameElement {

	Movable LivingElement;
	
	public Lifebar(Point2D position, GameElement g) {
		super(position);
		LivingElement = (Movable)g;
		}

	
	public void update() {
		GameEngine.getInstance().clearLifeBarTiles();
		String tileColor = "";
		for(int i=0;i<5;i++) {
			if(i < (LivingElement.getHitpoints()-1)*0.5) {
				tileColor = "Green";
			} else if (i > (LivingElement.getHitpoints()-1)*0.5){
				tileColor = "Red";
			} else tileColor = "GreenRed";
			Point2D tile_pos = new Point2D(i,getPosition().getY()); 
			LifeTile lf = new LifeTile(Color.valueOf(tileColor),tile_pos);
			GameEngine.getInstance().addToBar(lf);
		}
		if(LivingElement.isDeadOnNextAttack())
			GameEngine.getInstance().GameOver();
	}
	
	@Override
	public String getName() {
		return "Lifebar";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean isTransposable() {
		return false;
	}


}
