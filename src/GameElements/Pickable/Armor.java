package GameElements.Pickable;


import pt.iscte.poo.utils.Point2D;

public class Armor extends Pickable {
	
	public Armor(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "Armor";
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
