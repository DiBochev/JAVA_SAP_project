package homework;

import java.io.IOException;


public class Main {
	
	public static void main(String[] args) {
		Matrix left = new Matrix();
		Matrix right = new Matrix();
		Matrix resultSAP = new Matrix();
		Matrix result = new Matrix();
		Matrix asynch = new Matrix();
		Matrix asynchWithThreadChoise = new Matrix();
		Matrix asynchWithThreadDefaultCores = new Matrix();
		Matrix asynchWithPool = new Matrix();
		String pathToLeftMatrix = "D:\\left";
		String pathToRightMatrix = "D:\\right";
		String pathToResultSAPMatrix = "D:\\resultSAP";
		
		
		System.out.println("loading files");
		long time = System.currentTimeMillis();
		left.setPath(pathToLeftMatrix);
		right.setPath(pathToRightMatrix);
		resultSAP.setPath(pathToResultSAPMatrix);
		Matrix[] matrix = {left, right, resultSAP};
		IOFileManager.loadMatrixAsynch(matrix);
		System.out.println("loaded files for: " + (System.currentTimeMillis() - time) + " ms");
		
		
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			result.linearMultiply(left, right);						
		}
		System.out.println("time for linear multiply: " + ((System.currentTimeMillis() - time) / 10));
		
		
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			asynch.multiplyThreading(left, right);						
		}
		System.out.println("time for threads multiply: " + (System.currentTimeMillis() - time) / 10);
		
		
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			asynchWithThreadChoise.multiplyThreading(left, right, 4);						
		}
		System.out.printf("time for %d threads multiply: " + (System.currentTimeMillis() - time) / 10, 4);
		System.out.println();
		
		
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			asynchWithThreadDefaultCores.multiplyThreadingDefaultCores(left, right);						
		}
		System.out.println("time for default threads multiply: " + (System.currentTimeMillis() - time) / 10);
		
		
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			asynchWithPool.multiplyPool(left.getMatrix(), right.getMatrix(), 8);						
		}
		System.out.println("time for pool: " + (System.currentTimeMillis() - time));
		
		
		System.out.println("\n");
		System.out.println("result and resultSAP " + result.equals(resultSAP));
		System.out.println("asynch and resultSAP " + asynch.equals(resultSAP));
		System.out.println("result and asynch " + result.equals(asynch));
		System.out.println("asynchWithThreadChoise and resultSAP " + asynchWithThreadChoise.equals(resultSAP));
		System.out.println("asynch with pool and SAP " + asynchWithPool.equals(resultSAP));
		
		try {
			IOFileManager.saveMatrix("D:\\result2", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
