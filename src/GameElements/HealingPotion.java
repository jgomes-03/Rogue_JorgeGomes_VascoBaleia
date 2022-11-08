package GameElements;


import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement  {
	
	private Point2D position;

	public HealingPotion(Point2D position) {
		this.position = position;
	}
	
	@Override
	public String getName() {
		return "HealingPotion";
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

	@Override
	public boolean isPickable() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
