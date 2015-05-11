package homework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MatrixMultiplication {

	private MatrixMultiplication() {
	}

	private static double [][] matrix;

	private static void multiplyCore(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber, final int numberOfCurrentThread) throws IllegalArgumentException {
		if (threadsNumber < 1) {
			throw new IllegalArgumentException("Cores number cannot be non-positive!");
		}
		matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];
		for (int i = numberOfCurrentThread; i < leftMatrix.getRow(); i += threadsNumber) {
			for (int k = 0; k < leftMatrix.getCol(); k++) {
				for (int j = 0; j < rightMatrix.getCol(); j++) {
					matrix[i][j] = matrix[i][j] + leftMatrix.getMatrix()[i][k]* rightMatrix.getMatrix()[k][j];
				}
			}
		}
	}

	public static double[][] linearMultiply(Matrix leftMatrix, Matrix rightMatrix) {
		 multiplyCore(leftMatrix, rightMatrix, 1, 0);
		 return matrix;
	}

	public static double[][] multiplyThreading(Matrix leftMatrix, Matrix rightMatrix) {
		multiplyThreading(leftMatrix, rightMatrix, leftMatrix.getRow());
		return matrix;
	}

	public static double[][] multiplyThreadingDefaultCores(Matrix leftMatrix,Matrix rightMatrix) {
		multiplyThreading(leftMatrix, rightMatrix, Runtime.getRuntime().availableProcessors());
		return matrix;
	}

	public static double[][] multiplyPool(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber) throws IllegalArgumentException {
		if (threadsNumber < 1) {
			throw new IllegalArgumentException("Cores number cannot be non-positive or 0!");
		}
		matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];
		ExecutorService pool = Executors.newFixedThreadPool(threadsNumber);

		for (int i = 0; i < threadsNumber; i++) {
			final int i2 = i;
			pool.submit(new Runnable() {
				private int numberOfThread = i2;

				public void run() {
					multiplyCore(leftMatrix, rightMatrix, threadsNumber,
							numberOfThread);
				}
			});
		}
		pool.shutdown();
		while (!pool.isTerminated()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return matrix;
	}

	public static double[][] multiplyThreading(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber) throws IllegalArgumentException {
		if (threadsNumber <= 0) {
			throw new IllegalArgumentException("Cores number cannot be non-positive!");
		}
		Thread threads[] = new Thread[threadsNumber];
		matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];

		// creating the threads
		for (int i = 0; i < threadsNumber; i++) {
			final int numberOfCurrentThread = i;
			threads[i] = new Thread() {
				public void run() {
					multiplyCore(leftMatrix, rightMatrix, threadsNumber, numberOfCurrentThread);
				}
			};
		}
		// start every threads
		for (Thread thread : threads) {
			thread.start();
		}
		// join every threads
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
		return matrix;
	}

}
