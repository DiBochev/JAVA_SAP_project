package tests;

import static org.junit.Assert.*;
import homework.Matrix;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixTests {

	private static Matrix testMatrix;
	private static double[][] array ={{ 3, 2, 3 },{ 5, 9, 8 },};
	
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
		testMatrix.setMatrix(array);
		assertTrue(testMatrix.getRow() == 2);
	}
	
	@Test
	public void getCol(){
		testMatrix.setMatrix(array);
		assertTrue(testMatrix.getCol() == 3);
	}
	
	@Test
	public void getArray(){
		testMatrix.setMatrix(array);
		assertArrayEquals(array, testMatrix.getMatrix());
	}
	
	@Test 
	public void equals(){
		Matrix m = new Matrix();
		m.setMatrix(array);
		testMatrix.setMatrix(array);
		assertTrue(testMatrix.equals(m));
	}
	
	@Test 
	public void equals2(){
		testMatrix.setMatrix(array);
		assertTrue(testMatrix.equals(testMatrix));
	}
	@Test 
	public void equals3(){
		Matrix m = new Matrix();
		testMatrix.setMatrix(array);
		assertFalse(testMatrix.equals(m));
	}
	
	@Test 
	public void equals4(){
		Matrix m = new Matrix();
		double[][] arr = {{ 3, 2, 3 },{ 5, 9, 10 },};
		m.setMatrix(arr);
		testMatrix.setMatrix(array);
		assertFalse(testMatrix.equals(m));
	}
	
}
