import javatrix.*;
import java.util.*;

public class Testtrix {
    public static void main(String[] args) {
		
        //menu to choose functionality
	Scanner sc = new Scanner(System.in);
	int choice = 0;
	boolean runAgain = true;
	Matrix A = null;
	ArrayList<Matrix> arr = new ArrayList<Matrix>();

	while(runAgain) {
	    System.out.println("---choose option---");
     	    System.out.println("1. create matrix");
            System.out.println("2. Edit matrix");
	    System.out.println("3. Print matrix");
	    System.out.println("4. Exit");	
	    choice = sc.nextInt();
	    sc.nextLine();
	    switch (choice) {
	    	//create matrix
		case 1:
			int r = 0;
			int c = 0;
			String n = "";
			System.out.print("Enter number of rows: ");
			r = sc.nextInt();
			sc.nextLine();
			
			System.out.print("Enter number of columns: ");
			c = sc.nextInt();
			sc.nextLine();
			
			A = new Matrix(r, c);
			arr.add(A);
			
			System.out.println("Your matrix: ");
			A.print(9,2);
			break;
		case 2: 
			break;
		case 3:
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
			else {
			    System.out.println("Your matrix");
			    A.print(9,2);
			    break;
			}
		case 4:
			System.exit(0);
			break;
		default:
     	    		System.out.println("Invalid selection");
      	    		break; // This break is not really necessary
    	    }
	    System.out.println("Continue? y or n:");
	    String letter = sc.nextLine();
	    if (letter.equalsIgnoreCase("y")) {
		runAgain = true;
	    }
	    else runAgain = false;	
	}

	sc.close();
    }    	
}

