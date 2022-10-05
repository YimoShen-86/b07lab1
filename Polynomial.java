import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
		private double[] coefficients;
		private int[] exponents;
		
		public Polynomial(){
			this.coefficients = new double[1];
			this.exponents = new int[1];
			this.coefficients[0] = 0;
			this.exponents[0] = 0;
		}
		
		public Polynomial(double[] x, int[] y){
			coefficients = x;
			exponents = y;
		}
		
		public Polynomial add(Polynomial a) {
			
			double[] x = this.coefficients;
			int[] x1 = this.exponents;
			double[] y = a.coefficients;
			int[] y1 = a.exponents;
			int degree = x1.length;
			
			if(x1 == null || y1 == null) {
				return null;
			}
			
			if(x1.length < y1.length){
                  for(int i = 0; i < y1.length; i++) {
				for(int j = 0; j < x1.length; j++) {
					if(x1[i] != y1[j]) {
						degree = degree + 1;
					}
				}
			}
                  }
			
			double[] coefficientSum = new double[degree];
			int[] newExponent = new int[degree];
			
			for(int i = 0; i < y1.length; i++) {
				newExponent[i] = y1[i];
			}
			int index = y1.length;
			for(int i = 0; i < x1.length; i++) {
				boolean alreadyExist = false;
				for(int j = 0; j < y1.length; j++){
					if(x1[i] == newExponent[j]) {
						alreadyExist = true;
						break;
					}
				}
				if(alreadyExist) {
					continue;
				}else {
					newExponent[index] = x1[i];
					index++;
				}
			}
			
			for(int i = 0; i < newExponent.length; i++) {
				if(newExponent[i] > newExponent[i+1]) {
					int temp = newExponent[i];
					newExponent[i] = newExponent[i+1];
					newExponent[i+1] = temp;
					i = -1;
				}
			}
			
			for(int i = 0; i < newExponent.length; i++) {
				for(int j = 0; j < x1.length; j++) {
					if(x1[j] == newExponent[i]) {
						coefficientSum[i] = coefficientSum[i] + x[j];
					}else {
						continue;
					}
				}
				
				for(int k = 0; k < x1.length; k++) {
					if(y1[k] == newExponent[i]) {
						coefficientSum[i] = coefficientSum[i] + y[k];
					}else {
						continue;
					}
				}
			}
			
			return new Polynomial(coefficientSum, newExponent);
			
		}
		
		
		public double evaluate(double x) {
			double result = 0;
			double[] y = this.coefficients;
			int[] y1 = this.exponents;
			for(int i = 0; i < y.length; i++) {
				result = result + y[i]*(Math.pow(x,y1[i]));
			}
			
			return result;
		}
		
		public boolean hasRoot(double x) {
			if(evaluate(x) == 0) {
				return true;
			}else {
				return false;
			}
		}
		
		public Polynomial multiply(Polynomial a) {
			double[] x = this.coefficients;
			int[] x1 = this.exponents;
			double[] y = a.coefficients;
			int[] y1 = a.exponents;
			
			
			int[] newExponent = new int[x1.length * y1.length];
			int index = 0;
			int numberOfDups = 0;
			while(index < x1.length * y1.length) {
				for(int i = 0; i < x1.length; i++) {
					for(int j = 0; j < y1.length; j++) {
						if(check(newExponent, x1[i]*y1[j])) {
							numberOfDups++;
							continue;
						}else {
							newExponent[index] = x1[i]*y1[j];
							index++;
						}
					}
				}
			}
			
			int[] newExponent1 = new int[x1.length * y1.length - numberOfDups];
			for(int i = 0; i < newExponent1.length; i++) {
				newExponent1[i] = newExponent[i];
			}
			
			
			for(int i = 0; i < newExponent1.length; i++) {
				if(newExponent1[i] > newExponent1[i+1]) {
					int temp = newExponent1[i];
					newExponent1[i] = newExponent1[i+1];
					newExponent1[i+1] = temp;
					i = -1;
				}
			}
			
			double[] coefficient = new double[newExponent1.length];
			int index1 = 0;
			while(index1 < newExponent1.length) {
				for(int i = 0; i < x1.length; i++) {
					for(int j = 0; j < y1.length; j++) {
						if(x1[i]*y1[j] == newExponent1[index1]) {
							coefficient[index1] = coefficient[index1] + x[i]*y[j];
						}
					}
				}
				index1++;
			}
			
			return new Polynomial(coefficient, newExponent);
		}
		
		private boolean check(int[] a, int b) {
			for (int i = 0; i < a.length; i++) {
				if (b == a[i]) {
					return true;
				}
			}
			return false;
		}
		
		Polynomial(File x) throws IOException{
			FileReader file = new FileReader("x");
			BufferedReader buffer = new BufferedReader(file);
			String content = buffer.readLine();
			
			if(content == null || content == "") {
				this.coefficients = new double[1];
				this.exponents = new int[1];
				this.coefficients[0] = 0;
				this.exponents[0] = 0;
			}else {
				String[] result = content.split("\\-|\\+");
				String[] result1 = content.split("x");
				
				this.coefficients = new double[result.length];
				this.exponents = new int[result.length];
				
				if((result[0] == "")&&(!(result[0].contains("x")))) {
					Double num = Double.parseDouble(result[1]);
					coefficients[0] = (-1)*num;
					int i = 1;
					while(i < coefficients.length) {
						String str1 = result[i];
						String str2 = result1[i];
						 {
							
						}
					}
					
				}
			}
				
			
		}
		
		
		public void saveToFile(String str) {
			double[] x = this.coefficients;
			int[] x1 = this.exponents;
			String[] poly = new String[x.length];
			for(int i = 0; i < x.length; i++) {
				if(x1[i] == 0) {
					String s = Double.toString(x[i]);
					poly[i] = s;
				}else {
					String s1 = Double.toString(x[i]);
					String s2 = Integer.toString(x1[i]);
					poly[i] = s1 + "x" + s2;
				}
			}
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < poly.length; i++) {
			    sb.append(poly[i]);
			}
			String content = sb.toString();
			
			String filePath = "C:\\"+str+".txt";
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(filePath);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			try {
				fileWriter.write(content);
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				if(fileWriter != null) {
					try {
						fileWriter.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		    
		}
		
}
