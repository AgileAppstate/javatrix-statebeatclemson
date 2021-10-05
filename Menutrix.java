import javatrix.*;
import java.util.*;

public class Menutrix {
    public static void main(String[] args) {
	//renamed to menutrix		
        //menu to choose functionality
	Scanner sc = new Scanner(System.in);
	int choice = 0;
	boolean runAgain = true;
	Matrix A = null;
	ArrayList<Matrix> mats = new ArrayList<Matrix>();

	while(runAgain) {
	    System.out.println("---choose option---");
     	    System.out.println("1. Create matrix");
            System.out.println("2. Edit matrix");
	    System.out.println("3. Print matrix");
	    System.out.println("4. Exit");	
	    System.out.println("-------------------");
	    choice = sc.nextInt();
	    sc.nextLine();
	    switch (choice) {
	    	//create matrix
		case 1:
			System.out.println("---choose option---");
     	    		System.out.println("1. Create matrix of zeros");
            		System.out.println("2. Create single-value matrix");
	    		System.out.println("3. Create matrix from 2D array");
	    		System.out.println("4. Create matrix from 1D packed array");
			System.out.println("5. Create identity matrix");	
	   		System.out.println("-------------------");
			choice = sc.nextInt();
			sc.nextLine();

			switch(choice) {
				case 1:
					int r = 0;
					int c = 0;
					System.out.print("Enter number of rows: ");
					r = sc.nextInt();
					sc.nextLine();
					
					System.out.print("Enter number of columns: ");
					c = sc.nextInt();
					sc.nextLine();

					A = new Matrix(r, c);
					mats.add(A);
				
					System.out.println("Your matrix: ");
					A.print(9,2);
					break;
				case 2: 
					double v = 0.0;
					System.out.print("Enter number of rows: ");
					r = sc.nextInt();
					sc.nextLine();
					
					System.out.print("Enter number of columns: ");
					c = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter value: ");
					v = sc.nextDouble();
					sc.nextLine();

					A = new Matrix(r, c, v);
					mats.add(A);
				
					System.out.println("Your matrix: ");
					A.print(9,2);
					break;
				case 3: 
					System.out.print("Enter number of rows: ");
					r = sc.nextInt();
					sc.nextLine();
					
					System.out.print("Enter number of columns: ");
					c = sc.nextInt();
					sc.nextLine();
					
					double[][] vals = new double[r][c];
					
					for (int row = 0; row < vals.length; row++) {
    					    for (int col = 0; col < vals[row].length; col++) {
					        System.out.print("Enter value for row " + (row+1) + ", column " + (col+1) + ": "); 
						vals[row][col] = sc.nextDouble();
						sc.nextLine();
    					    }
 					}
					A = new Matrix(vals);
					mats.add(A);
					break;
				case 4:
					System.out.print("Enter number of rows: ");
					r = sc.nextInt();
					sc.nextLine();
 					
					double[] vals1 = new double[r];
					for (int row = 0; row < vals1.length; row++) {
					    System.out.print("Enter value for row " + (row+1) + ": ");
					    vals1[row] = sc.nextDouble();
					    sc.nextLine();
					}
					A = new Matrix(vals1, r);
					mats.add(A);
					break;
				default:
					System.out.println("Invalid selection");
      			    		break; // This break is not really necessary
	
			}
			break;
		case 2:
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
 
			int index = 0;
			int i = 0;
			int j = 0;
			double s = 0;
			String cont = "";
			boolean again = true;
			
			    System.out.print("Which matrix do you want to edit? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    			    index = sc.nextInt();
			    sc.nextLine();

			while(again) {
			    A = mats.get(index);
			    System.out.println("Chosen matrix: ");
			    A.print(6,2);
			
			    System.out.print("Enter row index to edit " + (A.getRowDimension() - A.getRowDimension()) + "-" + (A.getRowDimension()-1) + ": ");
			    i = sc.nextInt();
			    sc.nextLine();
			
			    System.out.print("Enter column index to edit " + (A.getColumnDimension()- A.getColumnDimension()) + "-" + (A.getColumnDimension() - 1) + ": ");
			    j = sc.nextInt();
			    sc.nextLine();

			    System.out.print("Enter value: ");
			    s = sc.nextDouble();
			    sc.nextLine();
			
			    A.set(i, j, s);

			    System.out.print("Continue editing? (y/n): ");
			    cont = sc.next();
			    sc.nextLine();
	
			    if(cont.equalsIgnoreCase("y")) {
			    	again = true;
			    }
			    else again = false;	
			}
			System.out.println("Your new matrix: ");
			A.print(6,2); 
			break;
		case 3:
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
			else {
			    
			    System.out.println("Your stored matrices: ");
			    for (Matrix m : mats) {				
				System.out.println("--------------------");
				m.print(6,2);
				System.out.println("--------------------");
			    }
			    break;
			}
		case 4:
			System.exit(0);
			break;
		default:
     	    		System.out.println("Invalid selection");
      	    		break; // This break is not really necessary
    	    }
	}

	sc.close();
    }    	
}

