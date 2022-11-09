package GameElements;


import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Bat extends GameElement  {
	
	public Bat(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "Bat";
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

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
