public class Matrix{
	//variables
	private double[][] A;
	private int m;
	private int n; 

	/* Constructors */

	/**
 	*Construct an m-by-n matrix of zeros
	*@param m    Number of rows
	*@param n    Number of columns
	*/ 
	public Matrix(int m, int n) {
	    this.m = m;
	    this.n = n;
	    A = new double[m][n];	
	}
	/**
 	*Construct an m-by-n constant matrix
	*@param m    Number of rows
	*@param n    Number of columns
	*@param s    Value to fill matrix
 	*/ 
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
	/**
 	*Construct a matrix from a 2D array
	*@param A    2D array of doubles
	*@exception  IllegalArgumentException All rows must have same length
	*/	
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
	/**
 	*Construct a matrix quickly without checking arguments
	*@param A    2D array of doubles
	*@param m    Number of rows
	*@param n    Number of columns
 	*/
	public Matrix(double[][] A, int m, int n) {
	    this.A = A;
	    this.m = m;
	    this.n = n;
	}
	/**
 	*Construct a matrix from 1D packed array
	*@param vals    1D array of doubles, packed by columns
	*@param m       Number of ros
	*@exception     IllegalArgumentException Array length must be multiple of m. 
 	*/
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
