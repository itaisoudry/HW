package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class ExplorationShip extends AbstractShip {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numOfResearchLabs;
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
		ExplorationShip other = (ExplorationShip) obj;
		if (numOfResearchLabs != other.numOfResearchLabs)
			return false;
		return true;
	}

	private int numOfResearchLabs;

	public ExplorationShip(String name, int commisionYear, float maximalSpeed, Set<CrewMember> crewMembers,
			int numOfResearchLabs) {
		super(name, commisionYear, maximalSpeed, crewMembers);
		this.numOfResearchLabs = numOfResearchLabs;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int sum = AnnualCost.Exploration.getCost();
		sum += 2500 * numOfResearchLabs;
		return sum;
	}

	public int getNumOfResearchLabs() {
		return numOfResearchLabs;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("\tNumberOfResearchLabs=" + this.numOfResearchLabs + "\n");

		return builder.toString();
	}

}
