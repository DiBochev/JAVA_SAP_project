package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import homework.IOFileManager;
import homework.Matrix;

import org.junit.Test;

public class IOTest {

	
	@Test
	public void readFileTestWronPpath(){
		Throwable e = null;
		Matrix[] array = {new Matrix()};
		try{
			IOFileManager.loadMatrixAsynch(array);
		}catch(Exception ex){
			e = ex;
		}
		assertTrue(e instanceof IllegalArgumentException); 
	}
	
	@Test
	public void readWriteFileTest(){
		Matrix left = new Matrix();
		left.setPath("D:\\left");
		
		Matrix[] array = {new Matrix()};
		array[0].setPath("D:\\left");
		double[][] simpleMatrix ={{ 3, 2, 3 },{ 5, 9, 8 },};
		array[0].setMatrix(simpleMatrix);
		
		try {
			IOFileManager.loadMatrixAsynch(array);
			
			left.setMatrix(array[0].getMatrix());
			
			IOFileManager.saveMatrix("D:\\test", array[0]);
			
			array[0].setPath("D:\\test");
			IOFileManager.loadMatrixAsynch(array);
			
			
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		assertArrayEquals(left.getMatrix(), array[0].getMatrix());
		
		
	}

}
