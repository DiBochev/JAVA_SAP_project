package homework;

import java.io.IOException;


public class Main {
	
	public static void main(String[] args) {
		Matrix left = new Matrix();
		Matrix right = new Matrix();
		Matrix result = new Matrix();
		Matrix asynch = new Matrix();
		Matrix asynchWithThreadChoise = new Matrix();
		Matrix asynchWithThreadDefaultCores = new Matrix();
		Matrix asynchWithPool = new Matrix();
		String pathToLeftMatrix = "D:\\left";
		String pathToRightMatrix = "D:\\right";
		
		
		System.out.println("loading files");
		long time = System.currentTimeMillis();
		left.setPath(pathToLeftMatrix);
		right.setPath(pathToRightMatrix);
		Matrix[] matrix = {left, right};
		try {
			IOFileManager.loadMatrixAsynch(matrix);
		} catch (IllegalArgumentException | IOException e1) {
			Thread.currentThread().setDaemon(true);
			e1.printStackTrace();
		}
		System.out.println("loaded files for: " + (System.currentTimeMillis() - time) + " ms");
		
		
		
		System.out.println("start linear multiply");
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			result.linearMultiply(left, right);						
		}
		System.out.println("time for linear multiply: " + ((System.currentTimeMillis() - time) / 10));
		
		
		
		System.out.println("start thread multiply (threds numer = left matrix row number)");
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			asynch.multiplyThreading(left, right);						
		}
		System.out.println("time for threads multiply: " + (System.currentTimeMillis() - time) / 10);
		
		
		
		System.out.printf("start threads multiply for %d threads\n", 4);
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			asynchWithThreadChoise.multiplyThreading(left, right, 4);						
		}
		System.out.printf("time for %d threads multiply: " + (System.currentTimeMillis() - time) / 10 + "\n", 4);


		
		System.out.println("start multiply (number of cores = number of threads");
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			asynchWithThreadDefaultCores.multiplyThreadingDefaultCores(left, right);						
		}
		System.out.println("time for default threads multiply: " + (System.currentTimeMillis() - time) / 10);
		
		
		
		System.out.printf("start multiply with pool for %d threads\n", 8);
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			try{
				asynchWithPool.multiplyPool(left, right, 8);						
			}catch(IllegalArgumentException e){
				e.printStackTrace();
			}
		}
		System.out.println("time for pool: " + (System.currentTimeMillis() - time));

		
		
		try {
			IOFileManager.saveMatrix("D:\\result2", result);
			System.out.println("result saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end program");
	}
}
