package il.ac.tau.cs.sw1.shapes;

public class Rectangle implements Shape {
	private int x;
	private int y;
	private int width;
	private int height;

	@Override
	public float getArea() {

		return ((float) this.width) * this.height;
	}

	@Override
	public float getPerimeter() {

		return (float) (2 * (this.width) + (this.height));
	}

	@Override
	public String getDetails() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rectangle: X=");
		builder.append(this.x);
		builder.append(", Y=");
		builder.append(this.y);
		builder.append(", Width=");
		builder.append(this.width);
		builder.append(", Height=");
		builder.append(this.height);

		return builder.toString();
	}

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/*
	 * Getters and Setters
	 * 
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
