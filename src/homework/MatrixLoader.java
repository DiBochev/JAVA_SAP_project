package homework;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class MatrixLoader {

	public MatrixLoader(){
	}
	
	public double[][] loadMatrix(String path) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(path));;
		double array[][];
		int row = dis.readInt();
		int col = dis.readInt();
		array = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				array[i][j] = dis.readDouble();
			}
		}
		dis.close();
		return array;
	}	
	
	public void saveMatrix(String path) throws IOException{
		
	}
}