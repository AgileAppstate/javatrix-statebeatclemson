package javatrix;
import java.text.*;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.*;

public class Matrix{
	//variables
	private static double[][] A;
	private static int m;
	private static int n; 
    
    public boolean equals(Matrix B) {
        if (B.m != this.m)
            return false;
        if (B.n != this.n)
            return false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (B.A[i][j] != this.A[i][j])
                    return false;
            }
        }
        return true;
    }

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

   public static void print(int w, int d)
   {
        // w is column width, d is the # of digits after the decimal
        print(new PrintWriter(System.out, true), w, d);
   }

   public static void print(PrintWriter output, int w, int d) {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(d);
        format.setMinimumFractionDigits(d);
        format.setGroupingUsed(false);
        print(output,format,w+2);
   }

   public static void print (NumberFormat format, int width) {
   	print(new PrintWriter(System.out,true), format, width);
   }

   public static void print(PrintWriter output, NumberFormat format, int width) {
	output.println();
	for (int i = 0; i < m ; i++) {
		for (int j = 0; j < n; j++) {
		    String s = format.format(A[i][j]);
		    int padding = Math.max(1, width-s.length());
			for (int k = 0; k <padding; k++)
			    output.print(" ");
		    output.print(s);
		}
		output.println();
	    }
	    output.println();
   	}

	public double[][] getArray() {
	    return A;
	}
	
	public Matrix times(double s)
	{
	    Matrix Z = new Matrix(m,n);
	    double[][] C = Z.getArray();
	    for (int i=0; i < m; i++) {
		for (int j = 0; j < n; j++) {
		    C[i][j] = s * A[i][j];
		}
	    }
	    return Z;			
	}
	
	public Matrix times(Matrix B) { 
	    if (B.m != n) {
		throw new IllegalArgumentException("Matrix inner dimensions must agree.");
	    }
	    Matrix Z = new Matrix(m,B.n);
	    double[][] C = Z.getArray();
	    double[] BcolJ = new double[n];
	    for (int j = 0; j < B.n; j++) {
		for (int k = 0; k < n; k++) {
		    BcolJ[k] = B.A[k][j];
		}
		for (int i = 0; i < m; i++) {
		    double [] ArowI = A[i];
		    double s = 0;
		    for (int k = 0; k < n; k++) {
			s += ArowI[k]*BcolJ[k];
		    }
		    C[i][j] = s;
		}
	    }
	    return Z;
	}
	
	public double get (int i, int j) {
	    return A[i][j];
	}
	
	public Matrix getMatrix(int i0, int i1, int j0, int j1) { 
	    Matrix Z = new Matrix(i1-i0+1. j1-j0+1);
	    double[][] B =Z.getArray();
	    try {
		for (int i = i0; i <= i1; i++) {
		    for (int j = j0; j <= j1; j++) {
			B[i-i0][j-j0] = A[i][j];
		    }
		}
	    }
	    catch (ArrayIndexOutOfBoundsException e) {
		throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	    }
	    return Z;
	}
	
	public Matrix getMatrix(int[] r, int[] c) {
	    Matrix Z = new Matrix(r.length, c.length);
	    double[][] B = Z.getArray();
	    try {
		for (int i = 0; i < r.length; i++) {
		    for (int j = 0; j < c.length; j++) {
			B[i][j] = A[r[i]][c[j]];
		    }
		}
	    }
	    catch (ArrayIndexOutOfBoundsException e) {
		throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	    }
	    return Z;
	} 

	public Matrix getMatrix(int i0, int i1, int[] c) {
	    Matrix Z = new Matrix(i1-i0+1, c.length);
	    double[][] B = Z.getArray();
	    try {
		for (int i = i0; i <= i1; i++) {
		    for (int j = 0; j < c.length; j++) {
			B[i-i0][j] = A[i][c[j]];
		    }
		}
	    }
	    catch (ArrayIndexOutOfBoundsException e) {
		throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	    }
	    return Z;
	}

	public Matrix getMatrix(int[] r, int j0, int j1) {
	    Matrix Z = new Matrix(r.length, j1-j0+1);
	    double[][] B = Z.getArray();
	    try {
		for (int i = 0; i < r.length; i++) {
		    for (int j = j0; j <= j1; j++) {
			B[i][j-j0] = A[r[i]][j];
		    }
		}
	    }
	    catch (ArrayIndexOutOfBoundsException e) {
		throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	    }
	    return Z;
	} 

}
