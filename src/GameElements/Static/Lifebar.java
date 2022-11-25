package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Point2D;
import GameElements.Static.LifeTile;

public class Lifebar extends GameElement {

	public Lifebar(Point2D position) {
		super(position);
		for(int i = 0; i<GameEngine.GRID_WIDTH;i++) {
			//addObject(GameElement.create("Green", new Point2D(i,11)), null, null, null);
			GameEngine.getInstance().addObject(new LifeTile(type.RED,new Point2D(i,11)));
		}
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

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}

}
