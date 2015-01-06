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
			left.setCol(load.getCol());
			left.setRow(load.getRow());
			
			right.setMatrix(load.loadMatrix("D:\\right"));
			right.setCol(load.getCol());
			right.setRow(load.getRow());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long temp = System.currentTimeMillis();
		result.setMatrix(left.multiply(right.getMatrix()));
		System.out.println(System.currentTimeMillis() - temp);
		
		temp = System.currentTimeMillis();
		result.setMatrix(left.multiplyThreading(right.getMatrix()));
		System.out.println(System.currentTimeMillis() - temp);
	}
}
