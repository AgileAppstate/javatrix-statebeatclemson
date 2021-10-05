package javatrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintWriter;
import java.text.*;
import java.lang.IllegalArgumentException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.*;


public class MatrixTest {
    
    public double[][] zeros = {{0.,0.,0},{0.,0.,0.},{0.,0.,0.}};   
    @Test
    public void testConst1() {
	    Matrix A = new Matrix(zeros);
	    A.print(9, 4);
        Matrix B = new Matrix(3, 3);
	    B.print(9, 4);
	    // Check results
	    boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (A.get(i, j) != B.get(i, j))
                    eq = false;
            }
        }
        if (eq)
	        System.err.println("Test Constructor 1: PASSED\n");
	    else
	        System.out.println("Result: ERROR\n");
    }
    
    @Test
    public void testConst2() {
        Matrix A = new Matrix(zeros);
        Matrix B = new Matrix(3, 3, 0);
        boolean eq = true;
        for (int i = 0; i< 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (A.get(i, j) != B.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Test Constructor 2: PASSED\n");
        else
            System.err.println("Result: ERROR\n");
    }

    @Test
    public void testConst3() {
        Matrix A = new Matrix(zeros);
        Matrix B = new Matrix(zeros, 3, 3);
        boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j< 3; j++)
            {
                if (A.get(i, j) != B.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Test Constructor 3: PASSED\n");
        else
            System.err.println("Result: ERROR\n");
    }
    
    @Test
    public void testConst4() {
        Matrix A = new Matrix(zeros);
        double[] z4 = {0., 0., 0., 0., 0., 0., 0., 0., 0.};
        Matrix B = new Matrix(z4, 3);
        boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (A.get(i, j) != B.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Test Constructor 4: PASSED\n");
        else
            System.err.println("Result: ERROR\n");
    }
    
    @Test
    public void testPrint1() {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(4);
        format.setMinimumFractionDigits(4);
        format.setGroupingUsed(false);
	    String expected = "\n";
        double[][] A = {{0., 0., 0.}, {0., 0., 0.}, {0., 0., 0.}};
        Matrix m = new Matrix(A);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String s = format.format(A[i][j]);
                int padding = Math.max(1, 11-s.length());
                for (int k = 0; k < padding; k++)
                    expected += " ";
                expected += s;
            }
            expected += "\n";
        }
        expected += "\n";
        String testOutput = null;
	String testFailed = null;

	// Save current System.out and set to new stream we can read.
	PrintStream origOut = System.out;
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream newOut = new PrintStream(baos);
	System.setOut(newOut);

	// Conduct test of main method
	try {
	    m.print(9, 4);
	}
	catch (Exception e) {
	    testFailed = "Exception thrown unexpectedly";
	}

	// Cleanup
	// Get all the stuff the method wrote to System.out, and reset it.
	System.out.flush();
	testOutput = baos.toString();
	System.setOut(origOut);

	// Check results
	if (testOutput == null)
	    if (testFailed ==  null)
		testFailed = "output to System.out expected";
	    else
		testFailed += "; output to System.out expected";
	else if (testOutput.length() == 0)
	    if (testFailed ==  null)
		testFailed = "output to System.out expected";
	    else
		testFailed += "; output to System.out expected";
	else if (! expected.equals(testOutput)) {
	    if (testFailed ==  null)
		testFailed = "Incorrect output generated.";
	    else
		testFailed += "; incorrect output generated.";
	    testFailed += "\nExpected output: \"" + expected;
	    testFailed += "\nGenerated output: \"" + testOutput + "\n";
	}

	// Show results
	System.err.println("\nTest: \"Print\" ");
	if (testFailed != null) {
	    System.err.println("Result: ERROR");
	    System.err.println("Feedback: " + testFailed);
	}
	else
	    System.err.println("Result: PASSED\n");

	assertEquals(testFailed, null);

    }

    @Test
    public void testIdentity() {
    	double[][] A = {{1., 0., 0.}, {0., 1., 0.}, {0., 0., 1.}};
        Matrix m = new Matrix(A);
	    Matrix B = Matrix.identity(3);
	    boolean eq = true;
        for (int i = 0; i< 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (m.get(i, j) != B.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Test Identity 1: PASSED\n");
        else
            System.err.println("Result: ERROR\n");
    }

    @Test
    public void testIdentity2() {
        double[][] A = {{1., 0., 0.}, {1., 1., 0.}, {0., 0., 1.}};
        Matrix m = new Matrix(A);
        Matrix B = Matrix.identity(3);
        boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (m.get(i, j) != B.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Result: ERROR, Matrixes should not be equal\n");
        else
            System.err.println("Test Not Identity: PASSED\n");
    }

    @Test
    public void testAdd1() {
        double[][] A = {{2., 2., 2.}, {2., 2., 2.}, {2., 2., 2.}};
        Matrix correct = new Matrix(A);
        Matrix m1 = new Matrix(3, 3, 1);
        Matrix m2 = new Matrix(3, 3, 1);
        Matrix res = m1.add(m2);
        boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (res.get(i, j) != correct.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Test Add1: PASSED\n");
        else
            System.err.println("RESULT: FAILED. Matrix doesn't contain expected values\n");
    }

    @Test
	public void testAdd2() {
        double[][] A = {{2., 2., 2.}, {2., 2., 2.}, {2., 2., 2.}};
        Matrix correct = new Matrix(A);
        Matrix m1 = new Matrix(3, 3, 1);
        Matrix m2 = new Matrix(3, 3, 2);
        Matrix res = m1.add(m2);
        boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (res.get(i, j) != correct.get(i, j))
                    eq = false;
            }
        }
        if (!eq)
            System.err.println("Test Add2: PASSED\n");
        else
            System.err.println("RESULT: FAILED. Sum matrix contains incorrect values\n");
    }

	@Test
    public void testSub1() {
        Matrix correct = new Matrix(3, 3);
        Matrix m1 = new Matrix(3, 3, 1);
        Matrix m2 = new Matrix(3, 3, 1);
        Matrix res = m1.sub(m2);
        boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (res.get(i, j) != correct.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Test Sub1: PASSED\n");
        else
            System.err.println("RESULT: FAILED. Matrix doesn't contain expected values\n");
    }

    @Test
	public void testSub2() {
        double[][] A = {{5., 1., 2.}, {7., 4., 2.}, {3., 6., 2.}};
        double[][] B = {{4., 0., 1.}, {6., 3., 1.}, {2., 5., 1.}};
        Matrix m1 = new Matrix(A);
        Matrix m2 = new Matrix(B);
        Matrix res = m1.sub(m2);
        Matrix correct = new Matrix(3, 3, 1);
        boolean eq = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (res.get(i, j) != correct.get(i, j))
                    eq = false;
            }
        }
        if (eq)
            System.err.println("Test Sub2: PASSED\n");
        else
            System.err.println("RESULT: FAILED. Sum matrix contains incorrect values\n");
    }

}
