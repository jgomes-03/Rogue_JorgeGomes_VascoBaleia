package GameElements;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement  {
	private Point2D position;

	public Wall(Point2D position) {
		this.position = position;
	}

	@Override
	public String getName() {
		return "Wall";
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
		return false;
	}
	
	

}
