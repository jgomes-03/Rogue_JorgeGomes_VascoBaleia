package GameElements.Static;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement  {

	public Wall(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Wall";
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
