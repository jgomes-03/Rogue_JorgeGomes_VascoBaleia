package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;


public class GreenRed extends GameElement {

	public GreenRed(Point2D position) {
		super(position);
		}

	@Override
	public String getName() {
		return "GreenRed";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean isTransposable() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return false;
	}
}
