package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.utils.Point2D;

public class LifeTile extends GameElement{
	
	enum Color{
		Red,
		Green;
		
	public String Clname;
		
	Color(String cname) {
		name = cname;
		}
	}
	
	public LifeTile(Color name) {
		super(position);
		this.name = name;
		}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name.toString();
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
