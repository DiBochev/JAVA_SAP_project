package homework;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		Matrix left = new Matrix();
		Matrix right = new Matrix();
		Matrix resultSAP = new Matrix();
		Matrix result = new Matrix();
		Matrix asynch = new Matrix();
		Matrix asynchWithThreadChoise = new Matrix();
		
		MatrixLoader load = new MatrixLoader();
		
		try {
			resultSAP.setMatrix(load.loadMatrix("D:\\resultSAP"));
			left.setMatrix(load.loadMatrix("D:\\left"));
			right.setMatrix(load.loadMatrix("D:\\right"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long temp = System.currentTimeMillis();
		result.setMatrix(left.multiply(right.getMatrix()));
		System.out.println("time for linear multiply: " + (System.currentTimeMillis() - temp));
		
		
		temp = System.currentTimeMillis();
		asynch.multiplyThreading(left.getMatrix(), right.getMatrix());
		System.out.println("time for threads multiply: " + (System.currentTimeMillis() - temp));
		
		
		temp = System.currentTimeMillis();
		asynchWithThreadChoise.multiplyThreading(left.getMatrix(), right.getMatrix(), 8);
		System.out.printf("time for %d threads multiply: " + (System.currentTimeMillis() - temp), 8);
		System.out.println();
		
		System.out.println("result and resultSAP " + result.equals(resultSAP));
		System.out.println("asynch and resultSAP " + asynch.equals(resultSAP));
		System.out.println("result and asynch " + result.equals(asynch));
		System.out.println("asynchWithThreadChoise and resultSAP " + asynchWithThreadChoise.equals(resultSAP));
		
		
		try {
			load.saveMatrix("D:\\result2", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
	}
}
