package homework;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class MatrixLoader {
	
	private int col;
	private int row;
	
	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public MatrixLoader(){
	}
	
	public double[][] loadMatrix(String path) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(path));;
		double array[][];
		row = dis.readInt();
		col = dis.readInt();
		array = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				array[i][j] = dis.readDouble();
			}
		}
		return array;
	}	
}