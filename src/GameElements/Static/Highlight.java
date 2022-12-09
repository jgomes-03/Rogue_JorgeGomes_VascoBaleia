package GameElements.Static;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Highlight extends GameElement{

	public Highlight(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Highlight";
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
