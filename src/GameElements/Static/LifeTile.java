package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class LifeTile extends GameElement{
	
	private type name;
	
	public static enum type{
		Red,
		Green,
	}
	
	public LifeTile(type name, Point2D position) {
		super(position);
		this.name = name;
		}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
		}
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
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
