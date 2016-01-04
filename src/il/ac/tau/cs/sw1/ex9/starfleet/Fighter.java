package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fighter extends AbstractWeapons {

	public Fighter(String name, int comissionYear, float maximalSpeed, Set<CrewMember> crewMembers,
			List<Weapon> weapons) {
		super(name, comissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		// Yearly maintenance cost
		int sum = AnnualCost.Fighter.getCost();
		for (Weapon weapon : super.getWeapons()) {
			sum += weapon.getAnnualMaintenanceCost();
		}
		sum += (int) (this.getMaximalSpeed()) * 1000;
		return sum;
	}

	public static void main(String[] args) {
		Set<CrewMember> crew = new HashSet<>();
		crew.add(new CrewWoman("test", 1, 1));

		Fighter figther = new Fighter("name", 1, 1, crew, Arrays.asList(new Weapon("Test", 1, 1)));
		System.out.println(figther.toString());
	}
}
