package GameElements.Static;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.utils.Point2D;


public class Red extends GameElement {

	public Red(Point2D position) {
		super(position);
		}

	@Override
	public String getName() {
		return "Red";
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
