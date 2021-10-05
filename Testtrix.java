import javatrix.*;
import java.util.Scanner;

public class Testtrix {
    public static void main(String[] args) {

	
        //menu to choose functionality
	Scanner sc = new Scanner(System.in);
	int choice = 0;
	
	System.out.println("---choose option---");
	System.out.println("1. create matrix");
	
	choice = sc.nextInt();
	sc.nextLine();
	switch (choice) {
	
	//create matrix
	case 1:
		int r = 0;
		int c = 0;
		System.out.print("Enter number of rows: ");
		r = sc.nextInt();
		sc.nextLine();
			
		System.out.print("Enter number of columns: ");
		r = sc.nextInt();
		sc.nextLine();

		Matrix A = new Matrix(r, c, 1.);
		
		System.out.println(A.get(0,0));
		System.out.println("Your matrix: ");
		A.print(3,2);
		break;

	default:
     	    System.out.println("Invalid selection");
      	    break; // This break is not really necessary
    	}

	sc.close();
    }

    	
}

