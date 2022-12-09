package GameElements.Static;


import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement  {
	
	public Treasure(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "Treasure";
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
