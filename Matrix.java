import java.text.*;
import java.io.PrintWriter;
import java.util.Locale;

public class Matrix 
{
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
