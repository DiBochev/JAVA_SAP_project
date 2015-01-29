package homework;

import java.util.Arrays;

public class Matrix {

	private double Matrix[][];
	private String path;

	public Matrix() {

	}

	public int getRow() {
		return Matrix.length;
	}

	public int getCol() {
		return Matrix[0].length;
	}

	public double[][] getMatrix() {
		return Matrix;
	}

	public void setMatrix(double[][] array) {
		this.Matrix = array;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean equals(Matrix m) {
		if (this == m)
			return true;
		if (m == null)
			return false;
		if (getClass() != m.getClass())
			return false;
		Matrix other = (Matrix) m;
		if (!Arrays.deepEquals(Matrix, other.Matrix))
			return false;
		return true;
	}

}
