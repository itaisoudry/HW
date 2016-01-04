package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class AbstractShip implements Spaceship {
	private String name;
	private int commisionYear;
	private float maximalSpeed;
	private Set<CrewMember> crewMembers;
	private int firePower;

	public AbstractShip(String name, int commisionYear, float maximalSpeed, Set<CrewMember> crewMembers) {
		this.name = name;
		this.commisionYear = commisionYear;
		this.maximalSpeed = maximalSpeed;
		this.crewMembers = crewMembers;
		this.firePower = 10;
	}

	public String getName() {
		return name;
	}

	public int getCommisionYear() {
		return commisionYear;
	}

	public float getMaximalSpeed() {
		return maximalSpeed;
	}

	public Set<CrewMember> getCrewMembers() {
		return crewMembers;
	}

	public int getCommissionYear() {
		return commisionYear;
	}

	public int getFirePower() {
		return firePower;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + "\n");
		builder.append("\tName=" + this.getName() + "\n");
		builder.append("\tCommissionYear=" + this.getCommissionYear() + "\n");
		builder.append("\tMaximalSpeed=" + this.getMaximalSpeed() + "\n");
		builder.append("\tFirePower=" + this.getFirePower() + "\n");
		builder.append("\tCrewMembers=" + this.getCrewMembers().size() + "\n");
		builder.append("\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n");

		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commisionYear;
		result = prime * result + ((crewMembers == null) ? 0 : crewMembers.hashCode());
		result = prime * result + firePower;
		result = prime * result + Float.floatToIntBits(maximalSpeed);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractShip other = (AbstractShip) obj;
		if (commisionYear != other.commisionYear)
			return false;
		if (crewMembers == null) {
			if (other.crewMembers != null)
				return false;
		} else if (!crewMembers.equals(other.crewMembers))
			return false;
		if (firePower != other.firePower)
			return false;
		if (Float.floatToIntBits(maximalSpeed) != Float.floatToIntBits(other.maximalSpeed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
