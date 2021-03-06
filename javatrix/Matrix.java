package javatrix;
import java.text.*;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.*;
import java.lang.Object;

public class Matrix{
	/*-----Variables-----*/
	private double[][] A;
	private int m;
	private int n; 

	/*-----Constructors-----*/
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

	/*-----Get methods-----*/
	/**
	*Get a single element
	*@param i	row index
	*@param j	column index
	*@return A(i,j)
	*/
	public double get(int i, int j) {
	    return A[i][j];
	}
	
	public int getRowDimension() {
	    return m;
	}

	public int getColumnDimension() {
	    return n;
	}
	
	/**
	*Access the internal two-dimensional array
	*@returns pointer to the 2D array of matrix elements
	*/
	public double[][] getArray() {
	    return A;
	}
	
	/**
	*Get a submatrix
	*@param i0	initial row index
	*@param i1	final row index
	*@param j0	initial column index
	*@param j1	final column index
	*@return A(i0:i1, j0:j1)
	*@exception ArrayIndexOutOfBoundsException - Submatrix indices
	*/
	public Matrix getMatrix(int i0, int i1, int j0, int j1) { 
	    Matrix Z = new Matrix(i1-i0+1, j1-j0+1);
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
	
	/**
	*Get a submatrix 
	*@param r	array of row indices
	*@param c	array of column indices
	*@return A(r(:),c(:))
	*@exception ArrayIndexOutOfBoundsException - Submatrix indices
	*/
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

	/**
	*Get a submatrix
	*@param i0	initial row index
	*@param i1	final row index
	*@param c	array of column indices
	*@return A(i0:i1,c(:))
	*@exception ArrayIndexOutOfBoundsException - Submatrix indices
	*/
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
	
	/**
	*Get a submatrix
	*@param r	array of row indices
	*@param j0	initial column index
	*@param j1	final column index
	*@return A(r(:),j0:j1)
	*@exception ArrayIndexOutOfBoundsException - Submatrix indices
	*/
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

	/*-----Set methods-----*/
	/**Set a single element
	*@param i	row index
	*@param j	column index
	*@param s	A(i,j)
	*@exception	ArrayIndexOutOfBoundsException
	*/
	public void set (int i, int j, double s) {
      	    A[i][j] = s;
   	}
	
	/**
	*Set a submatrix
	*@param i0	initial row index
	*@param i1	final row index
	*@param j0	initial column index
	*@param j1	final column index
	*@param Z	A(i0:i1, j0:j1)
	*@exception ArrayIndexOutOfBoundsException - Submatrix indices
	*/
	public void setMatrix (int i0, int i1, int j0, int j1, Matrix Z) {
      	    try {
         	for (int i = i0; i <= i1; i++) {
            	    for (int j = j0; j <= j1; j++) {
               		A[i][j] = Z.get(i-i0,j-j0);
            	    }
         	}
      	    } 
	    catch(ArrayIndexOutOfBoundsException e) {
         	throw new ArrayIndexOutOfBoundsException("Submatrix indices");
      	    }
   	}
	
	/**
	*Set a submatrix
	*@param r	Array of row indices
	*@param c	Array of column indices
	*@param Z	A(r(:),c(:))
	*@exception	ArrayIndexOutOfBoundsException - Submatrix indices
	*/
	public void setMatrix (int[] r, int[] c, Matrix Z) {
      	    try {
         	for (int i = 0; i < r.length; i++) {
            	    for (int j = 0; j < c.length; j++) {
               		A[r[i]][c[j]] = Z.get(i,j);
            	    }
         	}
      	    } 
	    catch(ArrayIndexOutOfBoundsException e) {
         	throw new ArrayIndexOutOfBoundsException("Submatrix indices");
      	    }
   	}

	/**
	*Set a submatrix
	*@param r	Array of row indices
	*@param j0	initial column index
	*@param j1	final column index
	*@param Z	A(r(:), j0:j1)
	*@exception	ArrayIndexOutOfBoundsException - Submatrix indices
	*/
	public void setMatrix(int[] r, int j0, int j1, Matrix Z) {
	    try {
         	for (int i = 0; i < r.length; i++) {
            	    for (int j = j0; j <= j1; j++) {
               		A[r[i]][j] = Z.get(i,j-j0);
            	    }
         	}
      	    } 
	    catch(ArrayIndexOutOfBoundsException e) {
         	throw new ArrayIndexOutOfBoundsException("Submatrix indices");
     	    }
	}

	/**
	*Set a submatrix
	*@param i0	initial row index
	*@param i1	final row index
	*@param c	Array of column indices
	*@param Z	A(i0:i1, c(:))
	*@exception	ArrayIndexOutOfBoundsException - Submatrix indices
	*/
	public void setMatrix (int i0, int i1, int[] c, Matrix X) {
      	    try {
         	for (int i = i0; i <= i1; i++) {
            	    for (int j = 0; j < c.length; j++) {
               		A[i][c[j]] = X.get(i-i0,j);
            	    }
         	}
      	    } 
	    catch(ArrayIndexOutOfBoundsException e) {
         	throw new ArrayIndexOutOfBoundsException("Submatrix indices");
      	    }
   	}
	
	/*-----Print Methods-----*/
   	/**
	*Print the matrix to stdout. Line the elements up in columns with a Fortran-like 'Fw.d' style format.
	*@param w	column width
	*@param d	number of digits after the decimal
	*/
	public void print(int w, int d)
   	{
            // w is column width, d is the # of digits after the decimal
            print(new PrintWriter(System.out, true), w, d);
   	}
	
	/**
	*Print the matrix to the output stream. Line the elements up in columns with a Fortran-like 
	* 'Fw.d' style format.
	*@param output	output stream
	*@param w	column width
	*@param d	numbr of digits after the decimal
	**/
   	public void print(PrintWriter output, int w, int d) {
            DecimalFormat format = new DecimalFormat();
            format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
            format.setMinimumIntegerDigits(1);
            format.setMaximumFractionDigits(d);
            format.setMinimumFractionDigits(d);
            format.setGroupingUsed(false);
            print(output,format,w+2);
   	}

	/**
	*Print the matrix to stdout. Line up the elements up in columns. Use the format object, and right 
	* justify within columns of width characters. Note that is the matrix is to be read back in, you probably 
	* will want to use a NumberFormat that is set to US Locale.
	*@param 	format	formatting object for individual elements
	*@param 	width		field width for each column
   	*/
	public void print (NumberFormat format, int width) {
   	    print(new PrintWriter(System.out,true), format, width);
  	}

	/**
	*Print the matrix to the output stream. Line up the elements up in columns. Use the format object, and right 
	* justify within columns of width characters. Note that is the matrix is to be read back in, you probably 
	* will want to use a NumberFormat that is set to US Locale.
	*@param output 		the output stream
	*@param	format		formatting object to format matrix elements
	*@param width		column width
	*/
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
	
	/*-----Times methods-----*/
	/**
	*Multiply a matrix by a scalar, C=s*A
	*@param s	scalar
	*@return s*A
	*/
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
	
	/**
	*Linear algebraic matrix multiplication, A*B
	*@param B	another matrix
	*@return Matrix product, A*B
	*@exception	IllegalArgumentException("Matrix inner dimensions must agree")
	*/
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

    /**
     * Create and Return n-by-n identity matrix
     * @param n int dimension of the matrix
    */
    public static Matrix identity(int n) {
        Matrix I = new Matrix(n, n);
        for (int i = 0; i < n; i++)
            I.A[i][i] = 1;
        return I;
    }

    /**
     * Create and return Matrix C
     * @param B another Matrix
     * @return matrix sum, A + B
     */
    public Matrix plus(Matrix B) {
        if (this.m != B.m || this.n != B.n)
            throw new RuntimeException("Illegal matrix dimensions");
        Matrix C = new Matrix(m, n);
        for (int i = 0; i < m; i++) 
        {
            for (int j = 0; j < n; j++)
            {
                C.A[i][j] = this.A[i][j] + B.A[i][j];
            }
        }
        return C;
    }

	/**
     * Create and return Matrix C
     * @param B another Matrix
     * @return matrix minus, A - B
     */
    public Matrix minus(Matrix B) {
        if (this.m != B.m || this.n != B.n)
            throw new RuntimeException("Illegal matrix dimensions");
        Matrix C = new Matrix(m, n);
        for (int i = 0; i < m; i++) 
        {
            for (int j = 0; j < n; j++)
            {
                C.A[i][j] = this.A[i][j] - B.A[i][j];
            }
        }
        return C;
    }

    /**
     * Create and return the transpose of the calling Matrix
    */
    public Matrix transpose() {
        Matrix B = new Matrix(n, m);
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                B.A[j][i] = this.A[i][j];
            }
        }
        return B;
    }
}
