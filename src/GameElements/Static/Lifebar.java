package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Point2D;

public class Lifebar extends GameElement {

	public Lifebar(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	public void initLifebar() {
		for(int i = 0; i<GameEngine.GRID_WIDTH;i++) {
				//addObject(GameElement.create("Green", new Point2D(i,11)), null, null, null);
				new Green(new Point2D(i,11)));
				//addImage();
			}
		}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Lifebar";
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
