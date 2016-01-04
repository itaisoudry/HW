package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ShipComperator implements Comparable<Spaceship>, Comparator {
	Collection<Spaceship> spaceShips;

	public ShipComperator(Collection<Spaceship> spaceShips) {
		this.spaceShips = spaceShips;
	}

	@Override
	public int compare(Object a, Object b) {
		Integer x1 = ((Spaceship) a).getFirePower();
		Integer x2 = ((Spaceship) b).getFirePower();
		int sComp = x1.compareTo(x2);

		if (sComp != 0) {
			return -sComp;
		} else {
			x1 = ((Spaceship) a).getCommissionYear();
			x2 = ((Spaceship) b).getCommissionYear();
			return -(x1.compareTo(x2));
		}
	}

	@Override
	public int compareTo(Spaceship o) {

		return 1;
	}

}
