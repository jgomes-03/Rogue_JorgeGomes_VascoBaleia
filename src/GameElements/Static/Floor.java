package GameElements.Static;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Floor extends GameElement {


	public Floor(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Floor";
	}


	@Override
	public int getLayer() {
		return 0;
	}
	
	@Override
	public boolean isTransposable() {
		return true;
	}


	
}