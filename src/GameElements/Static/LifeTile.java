package GameElements.Static;

import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class LifeTile implements ImageTile{
	
	Point2D position;
	String name;
	
	public LifeTile(Color color) {
		this.name = color.name;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public enum Color{
		RED("red"), GREEN("green"),GreenRed("GreenRed");
		
	public String name;
		
	private Color(String name) {
		this.name = name;
		}
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
