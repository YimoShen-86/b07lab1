public class Polynomial{
	
	private double[] coefficients;
	
	public Polynomial(){
		this.coefficients = new double[1];
		this.coefficients[0] = 0;
	}
	
	public Polynomial(double[] x){
		coefficients = x;
	}
	
	public Polynomial add(Polynomial a) {
		
		double[] x = this.coefficients;
		double[] y = a.coefficients;
		int degree = Math.max(x.length, y.length);
		double[] coefficientSum = new double[degree];
		for(int i = 0; i < x.length; i++) {
			coefficientSum[i] = coefficientSum[i] + this.coefficients[i];
		}
		for(int i = 0; i < y.length; i++) {
			coefficientSum[i] = coefficientSum[i] + a.coefficients[i];
		}
		
		return new Polynomial(coefficientSum);
	
		
	}
	
	
	public double evaluate(double x) {
		double result = 0;
		double[] y = this.coefficients;
		for(int i = 0; i < y.length; i++) {
			result = result + y[i]*(Math.pow(x,i));
		}
		
		return result;
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x) == 0)
			return true;
		else
			return false;
	}
}