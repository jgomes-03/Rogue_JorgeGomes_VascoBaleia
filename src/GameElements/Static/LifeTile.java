package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class LifeTile extends GameElement{
	
	public enum type{
		Red,
		Green,
	}
	
	public LifeTile(Point2D position) {
		super(position);
		}

	@Override
	public String getName(String type) {
		// TODO Auto-generated method stub
		switch(type) {
			case "Green":
				return this.type.Green;
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
