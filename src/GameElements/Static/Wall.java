package GameElements.Static;

import pt.iscte.poo.example.GameElement;
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

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
