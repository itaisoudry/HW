package il.ac.tau.cs.sw1.ex9.starfleet;

public enum AnnualCost {

	Fighter(2500), Bomber(5000), Transport(3000), Exploration(4000);
	private int cost;

	private AnnualCost(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

}
