package homework;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("some change");
		
		Matrix left = new Matrix();
		Matrix right = new Matrix();
		Matrix resultSAP = new Matrix();
		Matrix result = new Matrix();
		Matrix asynch = new Matrix();
		Matrix asynchWithThreadChoise = new Matrix();
		Matrix asynchWithPool = new Matrix();
		
		
		System.out.println("loading files");
		long temp = System.currentTimeMillis();
		left.setPath("D:\\left");
		right.setPath("D:\\right");
		resultSAP.setPath("D:\\resultSAP");
		Matrix[] matrix = {left, right, resultSAP};
		loadMatrixAsynch(matrix);
		System.out.println("loaded files for: " + (System.currentTimeMillis() - temp) + " ms");
		
		
		temp = System.currentTimeMillis();
		result.setMatrix(left.multiply(right.getMatrix()));			
		System.out.println("time for linear multiply: " + ((System.currentTimeMillis() - temp)/10));
		
		
		temp = System.currentTimeMillis();
		asynch.multiplyThreading(left.getMatrix(), right.getMatrix());			
		System.out.println("time for threads multiply: " + (System.currentTimeMillis() - temp));
		
		
		temp = System.currentTimeMillis();
		asynchWithThreadChoise.multiplyThreading(left.getMatrix(), right.getMatrix(), 8);			
		System.out.printf("time for %d threads multiply: " + (System.currentTimeMillis() - temp), 8);
		System.out.println();
		
		
		temp = System.currentTimeMillis();
		asynchWithPool.multiplyPool(left.getMatrix(), right.getMatrix(), 8);			
		System.out.println("time for pool: " + (System.currentTimeMillis() - temp));
		
		
		
		System.out.println();
		System.out.println("result and resultSAP " + result.equals(resultSAP));
		System.out.println("asynch and resultSAP " + asynch.equals(resultSAP));
		System.out.println("result and asynch " + result.equals(asynch));
		System.out.println("asynchWithThreadChoise and resultSAP " + asynchWithThreadChoise.equals(resultSAP));
		System.out.println("asynch with pool and SAP " + asynchWithPool.equals(resultSAP));
		
		
		
		try {
			MatrixLoader.saveMatrix("D:\\result2", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void loadMatrixAsynch(Matrix[] matrix){
		Thread[] t = new Thread[matrix.length];
		for (int i = 0; i < t.length; i++) {
			final int i2 = i;
			t[i] = new Thread(){
				public void run() {
					try {
						matrix[i2].setMatrix(MatrixLoader.loadMatrix(matrix[i2].getPath()));
						if (matrix[i2].getMatrix() == null) {
							throw new IOException("file not found or empty file");
						}
					} catch (IOException e) {
						e.getMessage();
					}
				}
			};
			t[i].start();
		}
		for (Thread thread : t) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
