import javatrix.*;

public class Testtrix {
    public static void main(String[] args) {
        double[][] vals = {{1.,2.,3},{4.,5.,6.},{7.,8.,9.}};

        Matrix A = new Matrix(vals);
        Matrix x = new Matrix(3, 1, 1.);
        Matrix b = A.times(x);

        A.print(9,4);
        System.out.println("x");
        x.print(9,4);
        System.out.println("=");
        b.print(9,4);

	/* b should be {6, 15, 24} */
   }
}

