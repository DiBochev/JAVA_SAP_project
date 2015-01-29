package homework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MatrixMultiplication {

	private MatrixMultiplication() {
	}

	private static double [][] Matrix;

	private static void multiplyCore(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber, final int numberOfCurrentThread) throws IllegalArgumentException {
		if (threadsNumber < 1) {
			throw new IllegalArgumentException("Cores number cannot be non-positive!");
		}
		Matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];
		for (int i = numberOfCurrentThread; i < leftMatrix.getRow(); i += threadsNumber) {
			for (int k = 0; k < leftMatrix.getCol(); k++) {
				for (int j = 0; j < rightMatrix.getCol(); j++) {
					Matrix[i][j] = Matrix[i][j] + leftMatrix.getMatrix()[i][k]* rightMatrix.getMatrix()[k][j];
				}
			}
		}
	}

	public static double[][] linearMultiply(Matrix leftMatrix, Matrix rightMatrix) {
		 multiplyCore(leftMatrix, rightMatrix, 1, 0);
		 return Matrix;
	}

	public static double[][] multiplyThreading(Matrix leftMatrix, Matrix rightMatrix) {
		multiplyThreading(leftMatrix, rightMatrix, leftMatrix.getRow());
		return Matrix;
	}

	public static double[][] multiplyThreadingDefaultCores(Matrix leftMatrix,Matrix rightMatrix) {
		multiplyThreading(leftMatrix, rightMatrix, Runtime.getRuntime().availableProcessors());
		return Matrix;
	}

	public static double[][] multiplyPool(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber) throws IllegalArgumentException {
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
		return Matrix;
	}

	public static double[][] multiplyThreading(Matrix leftMatrix, Matrix rightMatrix, int threadsNumber) throws IllegalArgumentException {
		if (threadsNumber <= 0) {
			throw new IllegalArgumentException("Cores number cannot be non-positive!");
		}
		Thread threads[] = new Thread[threadsNumber];
		Matrix = new double[leftMatrix.getRow()][rightMatrix.getCol()];

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
		return Matrix;
	}

}
