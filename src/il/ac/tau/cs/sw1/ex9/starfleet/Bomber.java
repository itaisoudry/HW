package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends AbstractWeapons {
	private int numOfTechs = 0;

	public Bomber(String name, int comiisionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons,
			int numOfTechnicians) {
		super(name, comiisionYear, maximalSpeed, crewMembers, weapons);
		this.numOfTechs = numOfTechnicians;
	}

	public int getNumOfTechnicians() {
		return numOfTechs;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		// Yearly maintenance cost
		int sum = AnnualCost.Bomber.getCost();
		for (Weapon weapon : super.getWeapons()) {
			sum += weapon.getAnnualMaintenanceCost();
		}
		sum += this.getMaximalSpeed() * 1000;
		sum = sum - (sum * (numOfTechs / 10));
		return sum;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("/t/t/tNumberOfTechnicians=" + this.getNumOfTechnicians() + "/n");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numOfTechs;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bomber other = (Bomber) obj;
		if (numOfTechs != other.numOfTechs)
			return false;
		return true;
	}
}
