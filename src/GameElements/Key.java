package GameElements;


import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement  {
	
	private Point2D position;
	private String keyCode;

	public Key(Point2D position) {
		this.position = position;
	}
	
	@Override
	public String getName() {
		return "Key";
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
		return false;
	}
}
