package GameElements;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Floor extends GameElement {

	private Point2D position;

	public Floor(Point2D position) {
		this.position = position;
	}

	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	@Override
	public boolean isTransposable() {
		// TODO Auto-generated method stub
		return true;
	}

}