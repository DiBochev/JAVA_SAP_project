package tests;

import static org.junit.Assert.*;
import homework.Matrix;
import homework.MatrixMultiplication;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class MultiplyTest {
	
	private static Matrix simpleTestMatrix1;
	private static Matrix simpleTestMatrix2;
	private static Matrix resultMatrix;
	private static double[][] left ={{ 3, 2, 3 },{ 5, 9, 8 },};
	private static double right[][] ={{ 4, 7 },{ 9, 3 },{ 8, 1 },};
	private static double expectedResult[][] = {{54, 30},{165, 70}};
	private static Matrix testResult;

	@Test
	public void linearMultiplyTest() {
		testResult.setMatrix(expectedResult);
		simpleTestMatrix1.setMatrix(left);
		simpleTestMatrix2.setMatrix(right);
		resultMatrix.setMatrix(MatrixMultiplication.linearMultiply(simpleTestMatrix1, simpleTestMatrix2));
		assertArrayEquals(resultMatrix.getMatrix(), testResult.getMatrix());
	}
	
	@Test
	public void multiplyThreadingTest(){
		testResult.setMatrix(MatrixMultiplication.multiplyThreading(simpleTestMatrix1, simpleTestMatrix2));
		assertArrayEquals(resultMatrix.getMatrix(), testResult.getMatrix());
	}
	
	@Test
	public void multiplyThreadingPoolTest(){
		testResult.setMatrix(MatrixMultiplication.multiplyPool(simpleTestMatrix1, simpleTestMatrix2, 8));
		assertArrayEquals(resultMatrix.getMatrix(), testResult.getMatrix());
	}
	
	@Test
	public void multiplyThreadingTestWithThreadsNumber(){
		testResult.setMatrix(MatrixMultiplication.multiplyThreading(simpleTestMatrix1, simpleTestMatrix2, 2));
		assertArrayEquals(resultMatrix.getMatrix(), testResult.getMatrix());
	}
	
	@Test
	public void multiplyThreadingDefaultCores(){
		testResult.setMatrix(MatrixMultiplication.multiplyThreadingDefaultCores(simpleTestMatrix1, simpleTestMatrix2));
		assertArrayEquals(resultMatrix.getMatrix(), testResult.getMatrix());
	}
	
	@After
	public void resetResult(){
		testResult.setMatrix(null);
		
	}
	
	@Test
	public void multiplyThreadingTestWithErrorThreadsNumber(){
		Throwable e = null;
		try{
			testResult.setMatrix(MatrixMultiplication.multiplyThreading(simpleTestMatrix1, simpleTestMatrix2, 0));
		}catch(IllegalArgumentException ex){
			e = ex;
		}
		assertTrue(e instanceof IllegalArgumentException);
		
	}
	
	@BeforeClass
	public static void InitializeMatrix(){
		simpleTestMatrix1 = new Matrix();
		simpleTestMatrix2 = new Matrix();
		resultMatrix = new Matrix();
		simpleTestMatrix1.setMatrix(left);
		simpleTestMatrix2.setMatrix(right);
		resultMatrix.setMatrix(expectedResult);
		testResult = new Matrix();
	}
}