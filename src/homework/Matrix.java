package homework;

public class Matrix {

	private int row;
	private int col;
	private double Matrix[][];
	
	public Matrix(){
		
	}
	
	public Matrix(int row, int col, double[][] is){
		setMatrix(is);
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public double[][] getMatrix() {
		return Matrix;
	}

	public void setMatrix(double[][] ds) {
		this.Matrix = ds;
	}
	
	public double[][] multiply(double[][] matrix){
		
		
		return null;
	}
	
//	public static int[][] multiply(int[][] a, int[][] b) {
//	       int rowsInA = a.length;
//	       int columnsInA = a[0].length; // same as rows in B
//	       int columnsInB = b[0].length;
//	       int[][] c = new int[rowsInA][columnsInB];
//	       for (int i = 0; i < rowsInA; i++) {
//	           for (int j = 0; j < columnsInB; j++) {
//	               for (int k = 0; k < columnsInA; k++) {
//	                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
//	               }
//	           }
//	       }
//	       return c;
//	   }
	
	public double[][] multiplyThreading(double[][] matrix){
		return null;
	}
	
}
