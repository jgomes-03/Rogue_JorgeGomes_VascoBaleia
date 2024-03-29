package GameElements.Static;

import pt.iscte.poo.gameEngine.GameElement;
import pt.iscte.poo.gameEngine.GameEngine;
import pt.iscte.poo.utils.Point2D;
import GameElements.Movable.*;
import GameElements.Pickable.Pickable;

public class InventoryBar extends GameElement{

	Movable LivingElement;
	private int drawStartPoint;
	private int selectPointer;
	public Highlight visualSelectedPointer;
	
	public InventoryBar(Point2D position, Movable m) {
		super(position);
		LivingElement = m;
		drawStartPoint = position.getX()+1;
		setSelectPointer(1);
	}

	public void update() {
		GameEngine.getInstance().clearInventoryBarTiles();
		for(Pickable p : LivingElement.getInventory()) {
			if(p!=null) {
				p.setPosition(new Point2D(drawStartPoint+p.getItemIndex(), getPosition().getY()));
				GameEngine.getInstance().addToBar(p);
			}
		}
		if(!GameEngine.getInstance().getHero().inventoryIsEmpty() && GameEngine.getInstance().getHero().getInventory()[selectPointer-1] != null) {
			GameEngine.getInstance().addToBar(new Highlight(GameEngine.getInstance().getHero().getInventory()[selectPointer-1].getPosition()));
		}
	}
	
	
	@Override
	public String getName() {
		return "Inventory";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean isTransposable() {
		return false;
	}

	public void setSelectPointer(int selectPointer) {
		this.selectPointer = selectPointer;
	}

	public int getSelectPointer() {
		return selectPointer;
	}
	
}
