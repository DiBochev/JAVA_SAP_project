package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public final class IOFileManager {
	
	private IOFileManager(){
	}
	
	public static void loadMatrixAsynch(Matrix[] matrix){
		Thread[] threadArray = new Thread[matrix.length];
		for (int i = 0; i < threadArray.length; i++) {
			final int CurrentThread = i;
			threadArray[i] = new Thread(){
				public void run() {
					try {
						matrix[CurrentThread].setMatrix(loadMatrix(matrix[CurrentThread].getPath()));
						if (matrix[CurrentThread].getMatrix() == null) {
							throw new IOException("file not found or empty file");
						}
					} catch (IOException e) {
						e.getMessage();
					}
				}
			};
			threadArray[i].start();
		}
		for (Thread thread : threadArray) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static double[][] loadMatrix(String path) throws IOException {
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