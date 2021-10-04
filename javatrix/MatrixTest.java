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
	        System.err.println("Result: PASSED\n");
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
            System.err.println("Result: PASSED\n");
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
            System.err.println("Result: PASSED\n");
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
            System.err.println("Result: PASSED\n");
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
	System.err.println("\nTest: \"java RazzleDazzle 1\" ");
	if (testFailed != null) {
	    System.err.println("Result: ERROR");
	    System.err.println("Feedback: " + testFailed);
	}
	else
	    System.err.println("Result: PASSED\n");

	assertEquals(testFailed, null);

    }
}
