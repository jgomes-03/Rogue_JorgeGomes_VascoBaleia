package GameElements.Static;

import GameElements.Pickable.Pickable;
import pt.iscte.poo.utils.Point2D;

public class Highlight extends Pickable{

	public Highlight(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Highlight";
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
