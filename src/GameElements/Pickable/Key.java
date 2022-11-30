package GameElements.Pickable;
import pt.iscte.poo.utils.Point2D;

public class Key extends Pickable  {
	
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
}
