public class Matrix{
	//Constructors
	public Matrix(int m, int n) {
	    this.m = m;
	    this.n = n;
	    A = new double[m][n];	
	}
	public Matrix(int m, int n, double s) {
	    this.m = m;
	    this.n = n;
	    A = new double[m][n];
	    for (int i = 0; i < m; i++) {
		for (int j = 0 ; j < n; j++) {
		    A[i][j] = s;
	   	}
	    }
	}
	public Matrix(double[][] A) {
	    m = A.length;
	    n = A[0].length;
	    for (int i = 0; i < m; i++) {
		if (A[i].length != n) {
		    throw new IllegalArgumentException("All rows must have the same length.");
		}
	    }
	    this.A=A;
	}
	public Matrix(double[][] A, int m, int n) {
	    this.A = A;
	    this.m = m;
	    this.n = n;
	}
	public Matrix(double[] vals, int m) {
	    this.m = m;
	    n = (m != 0 ? vals.length/m : 0);
	    if (m*n != vals.length) {
		throw new IllegalArgumentException("Array length must be a multiple of m.");
	    }
	    A = new double[m][n];
	    for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
		    A[i][j] = vals[i+j*m];
		}
	    }
	}


}
