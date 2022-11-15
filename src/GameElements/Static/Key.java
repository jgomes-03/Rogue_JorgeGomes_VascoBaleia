package GameElements.Static;


import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement  {
	
	private String keyCode = null;

	public Key(Point2D position,String keyCode) {
		super(position);
		this.keyCode = keyCode;
	}

	
	@Override
	public String getName() {
		return "Key";
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	public String getKeycode() {
		return keyCode;
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
