package homework;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		Matrix left = new Matrix();
		Matrix right = new Matrix();
		Matrix result = new Matrix();
		
		MatrixLoader load = new MatrixLoader();
		try {
			left.setMatrix(load.loadMatrix("D:\\left"));
			right.setMatrix(load.loadMatrix("D:\\right"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long temp = System.currentTimeMillis();
		result.setMatrix(left.multiply(right.getMatrix()));
		System.out.println("time for linear multiply: " + (System.currentTimeMillis() - temp));
		
		temp = System.currentTimeMillis();
		result.multiplyThreading(left.getMatrix(), right.getMatrix());
		System.out.println("time for linear thread multiply: " + (System.currentTimeMillis() - temp));
	
		try {
			load.saveMatrix("D:\\result2", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
