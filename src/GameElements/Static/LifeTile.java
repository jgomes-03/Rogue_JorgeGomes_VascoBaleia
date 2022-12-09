package GameElements.Static;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.utils.Point2D;

public class LifeTile extends GameElement{
	
	String name;
	
	public LifeTile(Color color,Point2D position) {
		super(position);
		this.name = color.name;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public enum Color{
		Red("Red"), Green("Green"),GreenRed("GreenRed"),RedGreen("RedGreen");
		
	public String name;
		
	private Color(String name) {
		this.name = name;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isTransposable() {
		return false;
	}
	
}
