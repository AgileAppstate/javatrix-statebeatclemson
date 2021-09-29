package javatrix;
import java.text.*;
import java.io.PrintWriter;
import java.util.Locale;

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

   public void print(int w, int d)
   {
        // w is column width, d is the # of digits after the decimal
        print(new PrintWriter(System.out, true), w, d);
   }

   public void print(PrintWriter output, int w, int d) {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(d);
        format.setMinimumFractionDigits(d);
        format.setGroupingUsed(false);
        print(output,format,w+2);
   }

   public void print (NumberFormat format, int width) {
   	print(new PrintWriter(System.out,true), format, width);
   }

   public void print(PrintWriter output, NumberFormat format, int width) {
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

}
