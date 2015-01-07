package homework;


public class Matrix {

	private double Matrix[][];
	
	public Matrix(){
		
	}
	
	public Matrix(double[][] multiply) {
		setMatrix(multiply);
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

	public void setMatrix(double[][] ds) {
		this.Matrix = ds;
	}
	
	public double[][] multiply(double[][] rightMatrix) {
	       int columnsInRightMatrix = rightMatrix[0].length;
	       double[][] temp = new double[getRow()][columnsInRightMatrix];
	       for (int i = 0; i < getRow(); i++) {
	    	   for (int k = 0; k < getCol(); k++) {
	    		   for (int j = 0; j < columnsInRightMatrix; j++) {
	            	   temp[i][j] = temp[i][j] + Matrix[i][k] * rightMatrix[k][j];
	               }
	           }
	       }
	       return temp;
	   }
	
	 public void multiplyThreading(double[][] leftMatrix, double[][] rightMatrix) {
		 
		 int rowsInA = leftMatrix.length;
		 int columnsInA = leftMatrix[0].length;
	     int columnsInB = rightMatrix[0].length;
	     Matrix = new double[rowsInA][columnsInB];
	       
	       for (int i = 0; i < rowsInA; i++) {
	    	   final int i2 = i;
	    	   new Thread(){
	    		   public void run(){
	    			   for (int k = 0; k < columnsInA; k++) {
	    				   for (int j = 0; j < columnsInB; j++) {
	    					   Matrix[i2][j] = Matrix[i2][j] + leftMatrix[i2][k] * rightMatrix[k][j];
	    				   }
	    			   }
	    		   }
	    	   }.start();
	       }
	   }
	
//	public void multiplyThreading(double[][] leftMatrix, double[][] rightMatrix){
//		 int columnsInRightMatrix = rightMatrix[0].length;
//		 int i = 0;
//		 int j = 0;
//		 for (i = 0; i < leftMatrix.length; i++) {
//			 for (j = 0; j < columnsInRightMatrix; j++) {
//				 final int i2 = i;
//				 final int j2 = j;
//				 new Thread(){
//					 public void run(){
//						 for (int k = 0; k < leftMatrix[0].length; k++) {
//							 Matrix[i2][j2] = Matrix[i2][j2] + Matrix[i2][k] * leftMatrix[k][j2];
//						 }
//					 }
//				 }.start();
//			 }
//		 }
//		
//	}

	public void multiplyPosition(int i, int j, int col, double[][] left, double right[][]){
		double temp = 0;
		for (int l = 0; l < col; l++) {
			temp = temp + left[i][l] * right[l][j];
		}
		Matrix[i][j] = temp;
	}
//
//	@Override
//	public void run() {
//		multiplyPosition(0, 0, 0, Matrix, Matrix);
//		
//		
//	}
	
	
}
