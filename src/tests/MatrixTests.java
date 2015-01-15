package tests;

import static org.junit.Assert.*;
import homework.Matrix;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixTests {

	private static Matrix testMatrix;
	
	@BeforeClass
	public static void InitializeMatrix(){
		testMatrix = new Matrix();
	}
	
	@After
	public void resetTestMatrix(){
		testMatrix.setMatrix(null);
		testMatrix.setPath(null);
	}
	
	
	@Test
	public void setPath(){
		testMatrix.setPath("some string");
		assertSame(testMatrix.getPath(), "some string");
		assertNotNull(testMatrix.getPath());
	}
	
	@Test
	public void getRow(){
		double[][] array ={{ 3, 2, 3 },{ 5, 9, 8 },};
		testMatrix.setMatrix(array);
		assertTrue(testMatrix.getRow() == 2);
	}
	
	@Test
	public void getCol(){
		double[][] array ={{ 3, 2, 3 },{ 5, 9, 8 },};
		testMatrix.setMatrix(array);
		assertTrue(testMatrix.getCol() == 3);
	}
	
	@Test
	public void getArray(){
		double[][] array ={{ 3, 2, 3 },{ 5, 9, 8 },};
		testMatrix.setMatrix(array);
		assertArrayEquals(array, testMatrix.getMatrix());
	}
	
	
}
