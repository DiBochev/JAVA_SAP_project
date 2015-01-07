package homework;


public class ThreadMultiplier implements Runnable{

	private Matrix left;
	private Matrix right;
	
	public ThreadMultiplier(Matrix left, Matrix right){
		this.left.setMatrix(left.getMatrix());
		this.right.setMatrix(right.getMatrix());
	}

	@Override
	public void run() {
		
		
	}
	
}
