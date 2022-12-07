package pt.iscte.poo.GameStats;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if(o1.getScore()==o2.getScore()) {
			Comparator<String> nameComparator = String.CASE_INSENSITIVE_ORDER;
			return nameComparator.compare(o2.getName(),o1.getName());
		}
		return o2.getScore()-o1.getScore();
	}

}
