package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Point2D;
import GameElements.Movable.Movable;
import GameElements.Static.LifeTile;
import GameElements.Static.LifeTile.Color;

public class Lifebar extends GameElement {

	Movable LivingElement;
	
	public Lifebar(Point2D position, GameElement g) {
		super(position);
		LivingElement = (Movable)g;
		}

	
	public void update() {
		GameEngine.getInstance().clearLifebar();
		String color = "";
		for(int i=0;i<5;i++) {
			if(i < (LivingElement.getHitpoints()-1)*0.5) {
				color = "Green";
			} else if (i > (LivingElement.getHitpoints()-1)*0.5){
				color = "Red";
			} else color = "GreenRed";
			//LifeTile Lipton = new LifeTile(Color.valueOf(color));
			Point2D tile_pos = new Point2D(i,getPosition().getY()); //new Point2D(i,GameEngine.GRID_HEIGHT);
			GameEngine.getInstance().addObject(GameElement.create(color,tile_pos,null,null,null));
		}
		if(LivingElement.isDeadOnNextAttack())
			GameEngine.getInstance().GameOver();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Lifebar";
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		return false;
	}


}
