package il.ac.tau.cs.sw1.ex6;

public class TestPolynomials {
	private static final int VALUE = 10140;

	public static void main(String[] args) {
		// 13b^2x^3z
		Monomial m1 = new Monomial(13);
		m1.setDegree('b', 2);
		m1.setDegree('x', 3);
		m1.setDegree('z', 1);
		System.out.println("m1 = " + m1);

		// 15
		Monomial m2 = new Monomial(15);
		System.out.println("m2 = " + m2);

		// -4b^2x^3z
		Monomial m3 = new Monomial(-4);
		m3.setDegree('b', 2);
		m3.setDegree('x', 3);
		m3.setDegree('z', 1);

		Monomial m4 = new Monomial(0);
		m4.setDegree('a', 0);
		m4.setDegree('b', 5);
		System.out.println(m4);

		System.out.println("m3 = " + m3);

		printError(!m1.hasSameDegrees(m2), m1 + " should not have the same degrees as " + m2);
		printError(m1.hasSameDegrees(m3), m1 + " should have the same degrees as " + m3);
		printError(m3.hasSameDegrees(m1), m3 + " should have the same degrees as " + m1);

		Polynomial p = new Polynomial(new Monomial[] { m1, m2 }); // 13b^2x^3z+15
		Polynomial p2 = new Polynomial(new Monomial[] { m3 });// -4b^2x^3z
		Polynomial p3 = p.add(p2);// 9b^2x^3z+15
		System.out.println(p3.toString());
		Polynomial p4 = p.multiply(p2);// -52b^4x^6z^2-60b^2x^3z
		System.out.println(p4.toString());
		System.out.println("p = " + p); // should be 13b^2x^3z+15 up to
										// reordering the monomials
		System.out.println("p2= " + p2); // should be -4b^2x^3z
		System.out.println("p3= " + p3); // should be 9b^2x^3z+15 up
											// to reordering the monomials
		System.out.println("p4= " + p4); // should be -52b^4x^6z^2-60b^2x^3z up
											// to reordering the monomials

		int[] assignment = { 0, 1, 6, 4, 3, 0, 0, 2, 3, 5, 2, 6, 3, 8, 7, 0, 0, 4, 2, 6, 0, 4, 1, 5, 1, 9 };
		Monomial x = new Monomial(1);
		x.setDegree('b', 1);
		x.setDegree('c', 1);
		System.out.println(x.evaluate(assignment));
		printError(p3.evaluate(assignment) == VALUE, "p3 value for b=1, x=5 and z=9  should be " + VALUE);
		System.out.println("p3 value = " + p3.evaluate(assignment));

	}

	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}

}
