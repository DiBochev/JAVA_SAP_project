package homework;

import java.util.ArrayList;
import java.util.Arrays;


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
	 
	 public void multiplyThreading(double[][] leftMatrix, double[][] rightMatrix, int threadsNumber) {
		 
		 int rowsInA = leftMatrix.length;
		 int columnsInA = leftMatrix[0].length;
	     int columnsInB = rightMatrix[0].length;
	     Matrix = new double[rowsInA][columnsInB];
	     ArrayList<Thread> threads = new ArrayList<Thread>();
	     
	     //creating the threads
	     for (int i = 0; i < threadsNumber; i++) {
	    	 final int i2 = i;
	    	 threads.add(new Thread(){
	    		 
	    		 private int numberOfThread = i2;
	    		 public void run(){
	    			 for (int i = numberOfThread; i < rowsInA; i+= threadsNumber) {
	    				 for (int k = 0; k < columnsInA; k++) {
	    		    		   for (int j = 0; j < columnsInB; j++) {
	    		    			   Matrix[i][j] = Matrix[i][j] + leftMatrix[i][k] * rightMatrix[k][j];
	    		    		   }
	    		    	   }
//	    		    	   if ((i + threadsNumber) > rowsInA) {
//	    		    		   break;
//	    		    	   }
	    			 }
	    		 }	 
	    	 });
	     }
	     
	     for (Thread thread : threads) {
			thread.start();
		}
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
