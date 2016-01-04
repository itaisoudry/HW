package il.ac.tau.cs.sw1.shapes;

public class Circle implements Shape {
	private int x;
	private int y;
	private int radius;

	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public float getArea() {
		return (float) (Math.PI * Math.pow(radius, 2));
	}

	@Override
	public float getPerimeter() {

		return (float) (2 * Math.PI * radius);
	}

	@Override
	public String getDetails() {
		StringBuilder builder = new StringBuilder();
		builder.append("Circle: X=");
		builder.append(this.x);
		builder.append(", Y=");
		builder.append(this.y);
		builder.append(", Radius=");
		builder.append(this.radius);
		return builder.toString();
	}

	/*
	 * Getters and Setters
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
