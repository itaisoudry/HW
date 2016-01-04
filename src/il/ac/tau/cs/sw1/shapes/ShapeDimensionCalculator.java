package il.ac.tau.cs.sw1.shapes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ShapeDimensionCalculator {

	public static void main(String[] args) throws IOException {

		Shape[] shapes = getShapesFromUser();
		writeShapesToFile("shapes_output.txt", shapes);

	}

	public static Shape[] getShapesFromUser() {
		boolean whileFlag = true;
		int shapesIndex = 0, x, y, radius, semiMajorAxis, semiMinorAxis, width, height;
		String shapeChoice = "";
		Scanner user = new Scanner(System.in);
		Shape[] shapes = new Shape[20];
		while (shapesIndex < 20 && whileFlag) {
			System.out.println("Please Choose Shape Type:");
			System.out.println("E – Ellipse");
			System.out.println("R – Rectangle");
			System.out.println("C – Circle");
			System.out.println("X - Exit");
			shapeChoice = user.next();

			switch (shapeChoice) {
			case "E":
				System.out.println("Please enter X coordinates: ");
				x = user.nextInt();
				System.out.println("Please enter Y coordinates: ");
				y = user.nextInt();
				System.out.println("Please enter semi-major axis length: ");
				semiMajorAxis = user.nextInt();
				System.out.println("Please enter semi-minor axis length: ");
				semiMinorAxis = user.nextInt();
				shapes[shapesIndex] = new Ellipse(x, y, semiMajorAxis, semiMinorAxis);
				System.out.println("Shape added: [" + shapes[shapesIndex].getDetails() + "]");
				shapesIndex++;
				break;
			case "R":
				System.out.println("Please enter X coordinates: ");
				x = user.nextInt();
				System.out.println("Please enter Y coordinates: ");
				y = user.nextInt();
				System.out.println("Please enter width: ");
				width = user.nextInt();
				System.out.println("Please enter height: ");
				height = user.nextInt();
				shapes[shapesIndex] = new Rectangle(x, y, width, height);
				System.out.println("Shape added: [" + shapes[shapesIndex].getDetails() + "]");
				shapesIndex++;
				break;
			case "C":
				System.out.println("Please enter X coordinates: ");
				x = user.nextInt();
				System.out.println("Please enter Y coordinates: ");
				y = user.nextInt();
				System.out.println("Please enter radius: ");
				radius = user.nextInt();

				shapes[shapesIndex] = new Circle(x, y, radius);
				System.out.println("Shape added: [" + shapes[shapesIndex].getDetails() + "]");
				break;
			case "X":
				whileFlag = false;
				break;
			default:
				System.out.println("Unknown command, please try again.");
			}
		}
		for (Shape shape : shapes) {
			if (shape == null) {
				continue;
			}
		}
		return shapes;
	}

	public static void writeShapesToFile(String outputFilename, Shape[] shapes) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilename));
		float areaSum = 0, perimeterSum = 0;
		int index = 0;
		bufferedWriter.write("Shape Dimension Calculator");
		bufferedWriter.write("\r\n");
		while (shapes[index] != null && index < shapes.length) {

			bufferedWriter.write(shapes[index].getDetails());
			bufferedWriter.write("\r\n");
			bufferedWriter.write("Area: " + String.format("%.02f", shapes[index].getArea()) + ", Perimeter: "
					+ String.format("%.02f", shapes[index].getPerimeter()));
			bufferedWriter.write("\r\n\r\n");
			areaSum += shapes[index].getArea();
			perimeterSum += shapes[index].getPerimeter();
			index++;
		}
		bufferedWriter.write(getNumberOfShapes(shapes));
		bufferedWriter.write("Total Area sum: " + String.format("%.02f", areaSum));
		bufferedWriter.write("\r\n");
		bufferedWriter.write("Total Perimiter sum: " + String.format("%.02f", perimeterSum));

		bufferedWriter.close();
	}

	/*
	 * This method will builder the string the contains the number of each shape
	 * that in shapes.
	 */
	private static String getNumberOfShapes(Shape[] shapes) {
		StringBuilder builder = new StringBuilder();
		int index = 0, circles = 0, ellipses = 0, rectangles = 0;
		while (shapes[index] != null && index < shapes.length) {
			if (shapes[index].getClass().getSimpleName().equals("Rectangle")) {
				rectangles++;
			}
			if (shapes[index].getClass().getSimpleName().equals("Circle")) {
				circles++;
			}
			if (shapes[index].getClass().getSimpleName().equals("Ellipse")) {
				ellipses++;
			}
			index++;
		}
		builder.append("Total number of shapes: " + index);
		builder.append("\r\n");
		builder.append("Number of Circles: " + circles);
		builder.append("\r\n");
		builder.append("Number of Ellipses: " + ellipses);
		builder.append("\r\n");
		builder.append("Number of Rectangles: " + rectangles);
		builder.append("\r\n");

		return builder.toString();
	}

}
