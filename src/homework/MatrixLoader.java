package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public final class MatrixLoader {
	
	private MatrixLoader(){
	}
	
	public static double[][] loadMatrix(String path) throws IOException {
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
	
	public static void saveMatrix(String path, Matrix result) throws IOException{
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
		dos.writeInt(result.getRow());
		dos.writeInt(result.getCol());
		double[][] array = result.getMatrix();
		for (int i = 0; i < result.getRow(); i++) {
			for (int j = 0; j < result.getCol(); j++) {
				dos.writeDouble(array[i][j]);
			}
		}
		dos.close();
	}
}