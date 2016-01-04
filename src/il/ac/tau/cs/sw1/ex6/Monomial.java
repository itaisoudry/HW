package il.ac.tau.cs.sw1.ex6;

import java.util.Arrays;

/**
 * Represents a multiplication of variables in a-z with an integral coefficient
 */
public class Monomial {
	private int coefficient;
	private int[] monoms;

	/**
	 * @post this.getCoefficient() == coefficient
	 * @post for every v, 'a'<=v<='z', isVariable(v) == false
	 */
	public Monomial(int coefficient) {
		this.coefficient = coefficient;
		this.monoms = initializeMonom();

	}

	/**
	 * Initializing an array of int to 0. Each int in the array represents a
	 * letter from 'a' to 'z'.
	 * 
	 * @return int[] monom
	 */
	private int[] initializeMonom() {
		int[] monom = new int[26];
		for (int i = 0; i < monom.length; i++) {
			monom[i] = 0;
		}

		return monom;
	}

	/**
	 * @return the coefficient of this monomial
	 */
	public int getCoefficient() {
		return this.coefficient;
	}

	/**
	 * @post getCoefficient() == coefficient
	 */
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * @return true iff the input is a variable of this monomial (and appears in
	 *         toString).
	 */
	public boolean isVariable(char variable) {
		int index = (int) variable - 97;
		if (monoms[index] > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @pre isVariable(variable)
	 * @return the degree of variable in this monomial
	 */
	public int getDegree(char variable) {
		int index = (int) variable - 97;
		return monoms[index];
	}

	/**
	 * @pre degree >= 0
	 * @pre 'a'<=variable<='z'
	 * @post getDegree(variable) = degree
	 */
	public void setDegree(char variable, int degree) {
		int index = (int) variable - 97;
		monoms[index] = degree;
	}

	/**
	 * @pre other!= null
	 * @return true iff the set of variables and the degree of each variable is
	 *         the same for this and other.
	 */
	public boolean hasSameDegrees(Monomial other) {
		int[] otherMonoms = other.getMonoms();
		//If the arrays are equal - the monom has the same degree as the other.
		if (Arrays.equals(otherMonoms, monoms)) {
			return true;
		}
		return false;
	}

	/**
	 * @pre assignment != null
	 * @pre assignment.length == 26
	 * @return the result of assigning assignment[0] to a, assignment[1] to b
	 *         etc., and computing the value of this Monomial
	 */
	public int evaluate(int[] assignment) {
		int sum = coefficient;
		//Iterate over assignments and user power
		for (int i = 0; i < assignment.length; i++) {
			int value = (int) Math.pow(assignment[i], monoms[i]);
			if (value > 1) {
				sum *= value;
			}
		}

		return sum;
	}

	/**
	 * Returns a string representation of this monomial by the mathematical
	 * convention. I.e., the coefficient is first (if not 1), then every
	 * variable in an alphabetic order followed by ^ and its degree (if > 1).
	 * For example, 13b^2x^3z
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		// Incase coefficient equals zero.
		if (coefficient == 0) {
			return "0";
		}
		// If coefficient is -1 append '-'
		if (coefficient == -1) {
			builder.append("-");
		}
		// If coefficient is bigger than 1 - append it ( if it is equal to 1
		// don't append anything)
		if (coefficient != 1) {
			builder.append(coefficient);
		}
		for (int i = 0; i < monoms.length; i++) {
			if (monoms[i] == 0) {
				continue;
			}
			builder.append((char) (i + 97));
			if (monoms[i] > 1) {
				builder.append("^");
				builder.append(monoms[i]);
			}
		}

		return builder.toString();
	}

	/**
	 * Returns a "safe" copy of this monomial, i.e., if the copy is changed,
	 * this will not change and vice versa
	 * 
	 * @throws CloneNotSupportedException
	 */
	public Monomial getCopy() {
		Monomial monom = new Monomial(this.coefficient);
		monom.setMonoms(this.monoms);
		return monom;
	}

	/**
	 * Returns the int[] of monoms for this monomial.
	 * 
	 * @return int[] monoms
	 */
	public int[] getMonoms() {
		return monoms;
	}

	public void setMonoms(int[] monoms) {
		this.monoms = monoms;
	}

}
