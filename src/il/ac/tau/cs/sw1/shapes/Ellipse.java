package il.ac.tau.cs.sw1.shapes;

public class Ellipse implements Shape {
	private int x;
	private int y;
	private int semiMajorAxis;
	private int semiMinorAxis;

	// x and y are the coordinates of the ellipse center
	// semiMajorAxis is the ‘large radius’ of the ellipse
	// semiMinorAxis is the ‘small radius’ of the ellipse
	public Ellipse(int x, int y, int semiMajorAxis, int semiMinorAxis) {
		this.x = x;
		this.y = y;
		this.semiMajorAxis = semiMajorAxis;
		this.semiMinorAxis = semiMinorAxis;
	}

	@Override
	public float getArea() {
		return (float) (Math.PI * this.semiMajorAxis * this.semiMinorAxis);
	}

	@Override
	public float getPerimeter() {
		return (float) (Math.PI * (this.semiMajorAxis + this.semiMinorAxis));
	}

	@Override
	public String getDetails() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ellipse: X=");
		builder.append(this.x);
		builder.append(", Y=");
		builder.append(this.y);
		builder.append(", SemiMajorAxis=");
		builder.append(this.semiMajorAxis);
		builder.append(", SemiMinorAxis=");
		builder.append(this.semiMinorAxis);
		return builder.toString();
	}

}
