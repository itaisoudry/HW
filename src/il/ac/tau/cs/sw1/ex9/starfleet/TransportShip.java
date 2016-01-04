package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends AbstractShip {
	private int cargoCapacity;
	private int passengerCapacity;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cargoCapacity;
		result = prime * result + passengerCapacity;
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
		TransportShip other = (TransportShip) obj;
		if (cargoCapacity != other.cargoCapacity)
			return false;
		if (passengerCapacity != other.passengerCapacity)
			return false;
		return true;
	}

	public TransportShip(String name, int commisionYear, float maximalSpeed, Set<CrewMember> crewMembers,
			int cargoCapacity, int passengerCapacity) {
		super(name, commisionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int sum = AnnualCost.Transport.getCost();
		sum += 5 * cargoCapacity;
		sum += 3 * passengerCapacity;
		return sum;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("\tCargoCapacity=" + this.cargoCapacity + "\n");
		builder.append("\tPassengerCapacity=" + this.passengerCapacity + "\n");
		return builder.toString();
	}
}
