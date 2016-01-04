package il.ac.tau.cs.sw1.ex6;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {
	private ArrayList<Monomial> polynom;
	private int monomialsCount;

	/**
	 * Creates a polynomial with (safe copies of) the given monomials
	 * 
	 * @pre monomials != null
	 * @pre for all i, 0 <= i < monmials.length : monomials[i] != null
	 * @post for all i, 0 <= i < monmials.length : monomials[i].getCoefficient()
	 *       == getMonomial(i).getCoefficient()
	 * @post for all i,v, 0 <= i < monmials.length, 'a'<=v<='z' :
	 *       monomials[i].getDegree(v) == getMonomial(i).getDegree(v)
	 */
	public Polynomial(Monomial[] monomials) {
		this.polynom = buildPolynom(monomials);
		this.monomialsCount = polynom.size();
	}

	// Builds a polynom out of the monomials array
	private ArrayList<Monomial> buildPolynom(Monomial[] monomials) {
		ArrayList<Monomial> poly = new ArrayList<Monomial>();
		for (int i = 0; i < monomials.length; i++) {
			poly.add(monomials[i]);
		}
		return poly;
	}

	/**
	 * @return the number of monomials in this polynomial
	 */
	public int getMonomialCount() {
		return monomialsCount;
	}

	/**
	 * @pre 0<=index < getMonomialCount()
	 * @return a safe copy of the monomial at the given index
	 */
	public Monomial getMonomial(int index) {
		return polynom.get(index).getCopy();
	}

	/**
	 * @pre other != null
	 * @post Creates a new Polynomial which is the sum of this polynomial and
	 *       other. E.g., the sum of 13b^2x^3z+15 and -4b^2x^3z is 9b^2x^3z+15
	 */
	public Polynomial add(Polynomial other) {
		ArrayList<Monomial> poly = new ArrayList<Monomial>();
		// Iterate over the polynomes and add the monoms
		for (Monomial monomOther : other.getPoly()) {
			for (Monomial monomThis : this.polynom) {
				if (monomOther.hasSameDegrees(monomThis)) {
					int coefficient = monomOther.getCoefficient() + monomThis.getCoefficient();
					Monomial newMonom = new Monomial(coefficient);
					newMonom.setMonoms(monomThis.getCopy().getMonoms());
					poly.add(newMonom);
				} else {
					poly.add(monomThis);
				}
			}
		}
		Monomial[] monoms = poly.toArray(new Monomial[poly.size()]);
		Polynomial polynom = new Polynomial(monoms);
		return polynom;

	}

	/**
	 * @pre other != null
	 * @post Creates a new Polynomial which is the product of this polynomial
	 *       and other. E.g., the product of 13b^2x^3z+15 and -4b^2x^3z is
	 *       -52b^4x^6z^2-60b^2x^3z
	 */
	public Polynomial multiply(Polynomial other) {
		// Iterate over the polynomes and multiply the monoms.
		ArrayList<Monomial> poly = new ArrayList<Monomial>();
		for (Monomial monomOther : other.getPoly()) {
			for (Monomial monomThis : this.polynom) {
				int coefficient = monomOther.getCoefficient() * monomThis.getCoefficient();
				Monomial newMonom = new Monomial(coefficient);
				newMonom.setMonoms(addMonomsDegrees(monomOther, monomThis));
				poly.add(newMonom);
			}
		}
		Monomial[] monoms = poly.toArray(new Monomial[poly.size()]);
		Polynomial polynom = new Polynomial(monoms);
		return polynom;
	}

	private int[] addMonomsDegrees(Monomial otherMonom, Monomial thisMonom) {
		// Using Arrays.copyOf to avoid copy of address.
		int[] monomsThis = Arrays.copyOf(thisMonom.getCopy().getMonoms(), thisMonom.getCopy().getMonoms().length);
		int[] monomsOther = Arrays.copyOf(otherMonom.getCopy().getMonoms(), otherMonom.getCopy().getMonoms().length);
		for (int i = 0; i < monomsThis.length; i++) {
			monomsThis[i] += monomsOther[i];
		}
		return monomsThis;
	}

	/**
	 * @pre assignment != null
	 * @pre assignment.length == 26
	 * @return the result of assigning assignment[0] to a, assignment[1] to b
	 *         etc., and computing the value of this Polynomial
	 */
	public int evaluate(int[] assignment) {
		int sum = 0;
		for (Monomial monom : polynom) {

			sum += monom.evaluate(assignment);
		}
		return sum;
	}

	/**
	 * Returns a string representation of this polynomial by the mathematical
	 * convention, but without performing normalization (summing of monomials).
	 * I.e., each monomial is printed according to Monomial.toString(), for
	 * example 13b^2x^3z+15-4b^2x^3z
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Monomial monom : polynom) {
			builder.append(monom.toString());
		}
		return builder.toString();
	}

	public ArrayList<Monomial> getPoly() {
		return polynom;
	}

}
