package GameElements.Movable;

import java.util.ArrayList;

import GameElements.Static.Door;
import pt.iscte.poo.example.GameEngine;
import pt.iscte.poo.example.GameElement;
import pt.iscte.poo.example.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends Movable {

	private ArrayList<GameElement> inventory;

	public Hero(Point2D position) {
		super(position);
		inventory = new ArrayList<>();
	}

	@Override
	public String getName() {
		return "Hero";
	}


	
	@Override
	public int getLayer() {
		return 1;
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
	

	public void addInventory(GameElement ge) {
		if (ge != null) {
			inventory.add(ge);
			GameEngine.getInstance().removeObject(ge);
//			ArrayList<GameElement> selection = Engine.getInstance().selectBy(s -> s.getName()=="DoorClosed");
//			for(GameElement element : selection) {
//				if(ge instanceof Key && element instanceof Door) {
//					Key k = (Key)ge;
//					Door d = (Door)element;
//					if(k.getKeycode().equals(d.getKeycode())) d.openDoor();
//					Engine.getInstance().removeObject(element);
//					Engine.getInstance().addObject(element);
//					
//				}
//			}
		}
	}
	
}
