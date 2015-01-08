package homework;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		Matrix left = new Matrix();
		Matrix right = new Matrix();
		Matrix resultSAP = new Matrix();
		Matrix result = new Matrix();
		Matrix result2 = new Matrix();
		
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
		result2.multiplyThreading(left.getMatrix(), right.getMatrix());
		System.out.println("time for threads multiply: " + (System.currentTimeMillis() - temp));
		
		
		System.out.println("result and resultSAP " + result.equals(resultSAP));
		System.out.println("result2 and resultSAP " + result2.equals(resultSAP));
		System.out.println("result and result2 " + result.equals(result2));
		
		
//		try {
//			load.saveMatrix("D:\\result2", result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	
		
	}
}
