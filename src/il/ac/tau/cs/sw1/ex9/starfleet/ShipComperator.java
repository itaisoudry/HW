package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;

public class ShipComperator implements Comparable<Spaceship> {
	List<Spaceship> spaceShips;

	public ShipComperator(List<Spaceship> spaceShips) {
		this.spaceShips = spaceShips;
	}

	public int compareTo(Spaceship a, Spaceship b) {
		Integer x1 = a.getFirePower();
		Integer x2 = b.getFirePower();
		int sComp = x1.compareTo(x2);

		if (sComp != 0) {
			return sComp;
		} else {
			x1 = a.getCommissionYear();
			x2 = b.getCommissionYear();
			return x1.compareTo(x2);
		}

	}

	@Override
	public int compareTo(Spaceship o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
