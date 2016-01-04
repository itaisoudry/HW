package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class AbstractWeapons extends AbstractShip {
	private List<Weapon> weapons;

	public AbstractWeapons(String name, int comissionYear, float maximalSpeed, Set<CrewMember> crewMembers,
			List<Weapon> weapons) {
		super(name, comissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;

	}

	public int getFirePower() {
		// Basic fire power of a spaceship
		int sum = 10;
		for (Weapon weapon : weapons) {
			sum += weapon.getFirePower();
		}
		return sum;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("\tWeaponArray=" + this.getWeapons().toString() + "\n");

		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weapons == null) ? 0 : weapons.hashCode());
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
		AbstractWeapons other = (AbstractWeapons) obj;
		if (weapons == null) {
			if (other.weapons != null)
				return false;
		} else if (!weapons.equals(other.weapons))
			return false;
		return true;
	}

}
