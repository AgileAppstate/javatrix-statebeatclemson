public class Matrix{
	//Constructors
	Matrix(int m, int n) {
	    this.m = m;
	    this.n = n;
	    mat = new double[m][n];	
	}
	Matrix(int m, int n, double s) {
	    this.m = m;
	    this.n = n;
	    mat = new double[m][n];
	    for (int i = 0; i < m; i++) {
		for (int j = 0 ; j < n; j++) {
		    mat[i][j] = s;
	   	}
	    }
	}

}
