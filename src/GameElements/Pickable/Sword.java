package GameElements.Pickable;



import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.utils.Point2D;

public class Sword extends Pickable  {
	
	public Sword(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "Sword";
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
	public void pick(int i) {
		super.pick(i);
		GameEngine.getInstance().getHero().setDamage(GameEngine.getInstance().getHero().getDamage()*2);
	}
	
	@Override
	public void drop(int i) {
		super.drop(i);
		GameEngine.getInstance().getHero().setDamage(GameEngine.getInstance().getHero().getDamage()/2);
	}
	
	


}
