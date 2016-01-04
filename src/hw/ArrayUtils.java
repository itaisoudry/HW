package hw;

import java.util.Arrays;

public class ArrayUtils {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 };
		int[][] matrix = { {0},{1},{2},{3},{4} };
		int[][] secMat = { { 1, 0, 0 }, { 0, 2, 0 }, { 0, 0, 1 } };
		System.out.println(Arrays.toString(shiftArrayToTheRight(arr, 5)));
		//System.out.println(matrixTrace(matrix));
		System.out.println(Arrays.deepToString(matrixSwitchRows(matrix, 1, 2)));
		System.out.println(Arrays.deepToString(matrixScalarRow(matrix, 100, 0)));
		System.out.println(Arrays.deepToString(matrixMultiplication(matrix, secMat)));
	}

	public static int[] shiftArrayToTheRight(int[] array, int move) {
		int[] shiftedArr = new int[array.length];
		// If the number of moves is negative, return the array as is.
		if (move <= 0) {
			return array;
		}
		// This loop iterate over the given array
		for (int i = 0; i < array.length; i++) {
			// If the index + the number of moves to be made, is bigger than the
			// size of the array, use modulo(array.length) so the index will not
			// go out of bound, and the number will be set to its right place
			// according to the number of moves.
			if (i + move >= array.length) {
				shiftedArr[((i + move) % array.length)] = array[i];
			} else
				// If index + move is small than the array put the number form
				// the array in place i+move.
				shiftedArr[(i + move)] = array[i];
		}

		return shiftedArr;
	}

	public static int matrixTrace(int[][] m) {
		int sum = 0;
		// Iterate over the trace and sum it.
		for (int i = 0; i < m.length; i++) {
			sum += m[i][i];
		}
		return sum;
	}

	public static int[][] matrixSwitchRows(int[][] m, int i, int j) {
		int[][] switchedMat = new int[m.length][m[0].length];
		if (i == j) {
			return m;
		}
		// I used continue instead of if-elseif-else, more elegant, also it is
		// possible to first copy the entire matrix and only than switch the
		// rows, but its less "efficiant".
		for (int index = 0; index < m.length; index++) {
			if (index == i) {
				switchedMat[index] = Arrays.copyOf(m[j], m[j].length);
				;
				continue;
			}
			if (index == j) {
				switchedMat[index] = Arrays.copyOf(m[i], m[i].length);
				continue;
			}
			switchedMat[index] = m[index];
		}

		return switchedMat;
	}

	public static int[][] matrixScalarRow(int[][] m, int s, int j) {
		int[][] scalarMat = new int[m.length][m[0].length];
		for (int index = 0; index < m.length; index++) {
			scalarMat[index] = Arrays.copyOf(m[index],m[index].length);
		}
		// Copy the 2D array
		
		for (int i = 0; i < m.length; i++) {
			scalarMat[j][i] = scalarMat[j][i] * s;
		}
		return scalarMat;
	}

	public static int[][] matrixMultiplication(int[][] m, int[][] n) {
		int[][] multMat = new int[m[0].length][n.length];
		int sum = 0;
		// Multiply matrixes
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < n[0].length; j++) {
				for (int k = 0; k < m[0].length; k++) {
					// Sum the multiplying of the row and column
					sum += m[i][k] * n[k][j];
				}
				// Insert sum into the right place in the new matrix.
				multMat[i][j] = sum;
				sum = 0;
			}
		}
		return multMat;
	}

}
