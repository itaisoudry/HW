package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter {
	private static int numOfStealth = 0;

	public StealthCruiser(String name, int comiisionYear, float maximalSpeed, Set<CrewMember> crewMembers,
			List<Weapon> weapons) {
		super(name, comiisionYear, maximalSpeed, crewMembers, weapons);
		numOfStealth++;
	}

	// Overload
	public StealthCruiser(String name, int comiisionYear, float maximalSpeed, Set<CrewMember> crewMembers) {
		super(name, comiisionYear, maximalSpeed, crewMembers, Arrays.asList((new Weapon("Laser Cannons", 10, 100))));
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int sum = super.getAnnualMaintenanceCost();
		sum += 100 * numOfStealth;
		return sum;
	}

}
