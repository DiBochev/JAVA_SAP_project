package homework;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Matrix {

	private double Matrix[][];
	private String path;
	
	public Matrix(){
		
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

	private void multiplyCore(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber, final int numberOfCurrentThread) throws IllegalArgumentException{
		if (threadsNumber < 1) {
            throw new IllegalArgumentException("Cores number cannot be non-positive!");
        }
		for (int i = numberOfCurrentThread; i < leftMatrix.getRow(); i+= threadsNumber) {
			for (int k = 0; k < leftMatrix.getCol(); k++) {
				for (int j = 0; j < rightMatrix.getCol(); j++) {
					Matrix[i][j] = Matrix[i][j] + leftMatrix.getMatrix()[i][k] * rightMatrix.getMatrix()[k][j];
				}
			}
		}
	}	 
	
	public void linearMultiply(Matrix leftMatrix, Matrix rightMatrix) {
	    Matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];
	    multiplyCore(leftMatrix, rightMatrix, 1, 0);
	}	
	
	public void multiplyThreading(Matrix leftMatrix, Matrix rightMatrix) {
		multiplyThreading(leftMatrix, rightMatrix, leftMatrix.getRow());
	}
	
	public void multiplyThreadingDefaultCores(Matrix leftMatrix, Matrix rightMatrix){
		 multiplyThreading(leftMatrix, rightMatrix, Runtime.getRuntime().availableProcessors());
	}
	 
	public void multiplyPool(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber)throws IllegalArgumentException {
		if (threadsNumber < 1) {
            throw new IllegalArgumentException("Cores number cannot be non-positive or 0!");
        }
	     Matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];
	     ExecutorService pool = Executors.newFixedThreadPool(threadsNumber);
	     
	     for (int i = 0; i < threadsNumber; i++) {
	    	 final int i2 = i;
	    	 pool.submit(new Runnable() {
	    		 private int numberOfThread = i2;
	    		 public void run() {
	    			 for (int i = numberOfThread; i < leftMatrix.getRow(); i+= threadsNumber) {
	    				 for (int k = 0; k < leftMatrix.getCol(); k++) {
	    					 for (int j = 0; j < rightMatrix.getCol(); j++) {
	    						 Matrix[i][j] = Matrix[i][j] + leftMatrix.getMatrix()[i][k] * rightMatrix.getMatrix()[k][j];
	    					 }
	    				 }
	    			 }
	    		 }
	    	 });
	     }
	     pool.shutdown();
	     while (!pool.isTerminated()){ 
	    	 try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	     }
	 }
	
	public void multiplyThreading(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber)throws IllegalArgumentException {
		if (threadsNumber <= 0) {
            throw new IllegalArgumentException("Cores number cannot be non-positive!");
        } 
		Thread threads[] = new Thread[threadsNumber];
		Matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];
	     
	     //creating the threads
		for (int i = 0; i < threadsNumber; i++) {
			final int numberOfCurrentThread = i;
			threads[i] = new Thread(){
				public void run(){
					multiplyCore(leftMatrix, rightMatrix, threadsNumber, numberOfCurrentThread);	 
				}
			};
	     }
	     //start every threads
	     for (Thread thread : threads) {
			thread.start();
	     }
	     //join every threads
	     for (Thread thread : threads) {
	    	 try {
	    		 thread.join();
	    	 } catch (InterruptedException e) {
	    		 e.getMessage();
	    	 }
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
