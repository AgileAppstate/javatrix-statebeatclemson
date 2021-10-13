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
	Matrix B = null;
	Matrix C = null;
	ArrayList<Matrix> mats = new ArrayList<Matrix>();

	while(runAgain) {
	    System.out.println();
	    System.out.println("----Matrix Menu----");
     	    System.out.println("1. Create");
            System.out.println("2. Edit");
	    System.out.println("3. Print");
	    System.out.println("4. Multiply");
	    System.out.println("5. Add");
	    System.out.println("6. Subtract");
	    System.out.println("7. Transpose");
	    System.out.println("8. Exit");
	    System.out.println("---Choose Option---");
	    choice = sc.nextInt();
	    sc.nextLine();
	    switch (choice) {
		case 1: //create matrix
			System.out.println("---Choose Option---");
     	    		System.out.println("1. Create matrix of zeros");
            		System.out.println("2. Create single-value matrix");
	    		System.out.println("3. Create matrix from 2D array");
	    		System.out.println("4. Create matrix from 1D packed array");
			System.out.println("5. Create identity matrix");
			System.out.println("6. Go Back");	
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

					System.out.println("Your matrix: ");
					A.print(9,2);

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
					System.out.println("Your matrix: ");
					A.print(9,2);

					break;
				case 5: 
					System.out.print("Enter dimension of identity matrix: ");
					r = sc.nextInt();
					sc.nextLine();
					A = Matrix.identity(r);
					mats.add(A);

					System.out.println("Your matrix: ");
					A.print(9,2);

					break;
				case 6:
					break;
				default:
					System.out.println("Invalid selection");
      			    		break; // This break is not really necessary
	
			}
			break;

		case 2:	//edit a matrix
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
 
			int index = 0;
			int i = 0;
			int j = 0;
			double s = 0;
			String cont = "";
			String corr = "";
			boolean again = true;
			
			while(again) {
				System.out.print("Which matrix do you want to edit? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    				index = sc.nextInt();
				sc.nextLine();

				A = mats.get(index);
				System.out.println("Chosen matrix: ");
				A.print(6,2);
					
				System.out.print("Correct (y/n)? ");
				corr = sc.next();
				sc.nextLine();
				
				if(corr.equalsIgnoreCase("y")) again = false;
				else again = true;
			}
			
			again = true;

			System.out.println("---Choose Option---");
     	    		System.out.println("1. Edit single value of matrix");
            		System.out.println("2. Edit row of matrix");
	    		System.out.println("3. Edit column of matrix");
	    		System.out.println("4. Edit entire matrix");
			System.out.println("5. Go Back");	
	   		System.out.println("-------------------");
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1: 
					while(again) {    		    
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

			    		    	System.out.print("Edit another value? (y/n): ");
			    		    	cont = sc.next();
			    		    	sc.nextLine();
	
			    		    	if(cont.equalsIgnoreCase("y")) {
			    			    again = true;
			    		    	}
			    		    	else again = false;
			    		    }
					break;
				case 2: 
					System.out.print("Enter row index to edit " + (A.getRowDimension() - A.getRowDimension()) + "-" + (A.getRowDimension()-1) + ": ");
			    		i = sc.nextInt();
			    		sc.nextLine();					
					
					double val = 0;
					int size = A.getRowDimension()-1;
					for (int c = 0; c <= size; c++) {
					    System.out.print("Enter value: ");
					    val = sc.nextDouble();
					    sc.nextLine();
					    A.set(i, c, val);
					}
					break;
				case 3:
					System.out.print("Enter column index to edit " + (A.getColumnDimension()- A.getColumnDimension()) + "-" + (A.getColumnDimension() - 1) + ": ");
			   		j = sc.nextInt();
			    		sc.nextLine();					
					
					val = 0;
					size = A.getColumnDimension()-1;
					for (int r = 0; r <= size; r++) {
					    System.out.print("Enter value: ");
					    val = sc.nextDouble();
					    sc.nextLine();
					    A.set(r, j, val);
					}
					break;
				case 4:
					int rowSize = A.getRowDimension();
					int colSize = A.getColumnDimension();
					for (int row = 0; row < rowSize; row++) {
    					    for (int col = 0; col < colSize; col++) {
					        System.out.print("Enter value for row " + (row+1) + ", column " + (col+1) + ": "); 
						val = sc.nextDouble();
						sc.nextLine();
						A.set(row, col, val);
    					    }
 					}
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid selection");
      			    		break; // This break is not really necessary
			    

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
		case 4: //multiply
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
			System.out.println("---Choose Option---");
     	    		System.out.println("1. Multiply matrix by scalar");
            		System.out.println("2. Multiply two matrices together");
			System.out.println("3. Go Back");	
	   		System.out.println("-------------------");
			choice = sc.nextInt();
			sc.nextLine();
			
			index = 0;
			again = true;
			int ind = 0;
			
			switch (choice) {
				case 1:
					System.out.println("Your stored matrices: ");
					for (Matrix m : mats) {				
					    System.out.println("-----Index " + ind + "-----");
				            m.print(6,2);
					    System.out.println("--------------------");
					    ind ++;
					}
					while(again) {
						System.out.print("Which matrix do you want to multiply by a scalar? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    						index = sc.nextInt();
						sc.nextLine();

						A = mats.get(index);
						System.out.println("Chosen matrix: ");
						A.print(6,2);
					
						System.out.print("Correct (y/n)? ");
						corr = sc.next();
						sc.nextLine();
				
						if(corr.equalsIgnoreCase("y")) again = false;
						else again = true;
					}
					System.out.print("Value to scale matrix by: ");
					double v = sc.nextDouble();
					sc.nextLine();
					
					A = A.times(v);
					System.out.println("Your new matrix: ");
					A.print(6,2);
					break;
				case 2:
					int index1 = 0;
					int index2 = 0;
					if (mats.size() <2) {
					    System.out.println("Please create two matrices first.");
					    break;
					}
					else {
						System.out.println("Your stored matrices: ");
						for (Matrix m : mats) {				
					   		System.out.println("-----Index " + ind + "-----");
				            		m.print(6,2);
					    		System.out.println("--------------------");
					    		ind ++;
						}
						again = true;
						while(again) {
							System.out.print("First matrix for multiplication? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    							index1 = sc.nextInt();
							sc.nextLine();

							A = mats.get(index1);
							System.out.println("Chosen matrix: ");
							A.print(6,2);
					
							System.out.print("Correct (y/n)? ");
							corr = sc.next();
							sc.nextLine();
				
							if(corr.equalsIgnoreCase("y")) again = false;
							else again = true;
						}

						again = true;
						while(again) {
							System.out.print("Second matrix for multiplication? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    							index2 = sc.nextInt();
							sc.nextLine();

							B = mats.get(index2);
							System.out.println("Chosen matrix: ");
							A.print(6,2);
					
							System.out.print("Correct (y/n)? ");
							corr = sc.next();
							sc.nextLine();
				
							if(corr.equalsIgnoreCase("y")) again = false;
							else again = true;
						}

						if(A.getColumnDimension() != B.getRowDimension()) {
							System.out.println("\n ERROR: Inner matrix dimension must agree");
							break;
						}
						else {

							C = A.times(B);
							System.out.println("Your new matrix: ");
							C.print(6,2);
						}				
					}
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid selection");
      			    		break; // This break is not really necessary
			}					
			break;
		case 5: //add
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
			System.out.println("---Choose Option---");
     	    		System.out.println("1. Add two matrices together");
            		System.out.println("2. Go Back");	
	   		System.out.println("-------------------");
			choice = sc.nextInt();
			sc.nextLine();
			
			index = 0;
			again = true;
			ind = 0;
			
			switch (choice) {
				case 1:
					int index1 = 0;
					int index2 = 0;
					if (mats.size() < 2) {
					    System.out.println("Please create two matrices first.");
					    break;
					}
					else {
						System.out.println("Your stored matrices: ");
						for (Matrix m : mats) {				
					   		System.out.println("-----Index " + ind + "-----");
				            		m.print(6,2);
					    		System.out.println("--------------------");
					    		ind ++;
						}
						again = true;
						while(again) {
							System.out.print("First matrix for addition? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    							index1 = sc.nextInt();
							sc.nextLine();

							A = mats.get(index1);
							System.out.println("Chosen matrix: ");
							A.print(6,2);
					
							System.out.print("Correct (y/n)? ");
							corr = sc.next();
							sc.nextLine();
				
							if(corr.equalsIgnoreCase("y")) again = false;
							else again = true;
						}

						again = true;
						while(again) {
							System.out.print("Second matrix for addition? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    							index2 = sc.nextInt();
							sc.nextLine();

							B = mats.get(index2);
							System.out.println("Chosen matrix: ");
							A.print(6,2);
					
							System.out.print("Correct (y/n)? ");
							corr = sc.next();
							sc.nextLine();
				
							if(corr.equalsIgnoreCase("y")) again = false;
							else again = true;
						}
						if(A.getColumnDimension() != B.getColumnDimension()) {
							System.out.println("\n ERROR: Matrices need to be the same size");
							break;
						}
						else if (A.getRowDimension() != B.getRowDimension()) {
							System.out.println("\n ERROR: Matrices need to be the same size");
							break;
						}
						else {
							C = A.plus(B);
							System.out.println("Your new matrix: ");
							C.print(6,2);
						}				
					}
					break;
				case 2:
					break;
				default:
					System.out.println("Invalid selection");
      			    		break; // This break is not really necessary
			}					
			break;
		case 6: //subtract
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
			System.out.println("---Choose Option---");
     	    		System.out.println("1. Subtract a matrix from another");
            		System.out.println("2. Go Back");	
	   		System.out.println("-------------------");
			choice = sc.nextInt();
			sc.nextLine();
			
			index = 0;
			again = true;
			ind = 0;
			
			switch (choice) {
				case 1:
					int index1 = 0;
					int index2 = 0;
					if (mats.size() < 2) {
					    System.out.println("Please create two matrices first.");
					    break;
					}
					else {
						System.out.println("Your stored matrices: ");
						for (Matrix m : mats) {				
					   		System.out.println("-----Index " + ind + "-----");
				            		m.print(6,2);
					    		System.out.println("--------------------");
					    		ind ++;
						}
						again = true;
						while(again) {
							System.out.print("First matrix for subtraction? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    							index1 = sc.nextInt();
							sc.nextLine();

							A = mats.get(index1);
							System.out.println("Chosen matrix: ");
							A.print(6,2);
					
							System.out.print("Correct (y/n)? ");
							corr = sc.next();
							sc.nextLine();
				
							if(corr.equalsIgnoreCase("y")) again = false;
							else again = true;
						}

						again = true;
						while(again) {
							System.out.print("Second matrix for subtraction? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    							index2 = sc.nextInt();
							sc.nextLine();

							B = mats.get(index2);
							System.out.println("Chosen matrix: ");
							A.print(6,2);
					
							System.out.print("Correct (y/n)? ");
							corr = sc.next();
							sc.nextLine();
				
							if(corr.equalsIgnoreCase("y")) again = false;
							else again = true;
						}
						if(A.getColumnDimension() != B.getColumnDimension() ) {
							System.out.println("\n ERROR: Matrices need to be the same size");
							break;
						}
						else if (A.getRowDimension() != B.getRowDimension()) {
							System.out.println("\n ERROR: Matrices need to be the same size");
							break;
						}
						else {
							C = A.minus(B);
							System.out.println("Your new matrix: ");
							C.print(6,2);
						}				
					}
					break;
				case 2:
					break;
				default:
					System.out.println("Invalid selection");
      			    		break; // This break is not really necessary
			}
			break;
		case 7: //transpose
			if (A == null) {
			    System.out.println("Please create a matrix first.");
			    break;
			}
			System.out.println("---Choose Option---");
     	    		System.out.println("1. Take the transpose of a Matrix");
            		System.out.println("2. Go Back");	
	   		System.out.println("-------------------");
			choice = sc.nextInt();
			sc.nextLine();
			
			index = 0;
			again = true;
			ind = 0;
			
			switch (choice) {
				case 1:
					int index1 = 0;
					if (mats.size() < 1) {
					    System.out.println("Please create a matrix first.");
					    break;
					}
					else {
						System.out.println("Your stored matrices: ");
						for (Matrix m : mats) {				
					   		System.out.println("-----Index " + ind + "-----");
				            		m.print(6,2);
					    		System.out.println("--------------------");
					    		ind ++;
						}
						again = true;
						while(again) {
							System.out.print("Matrix to transpose? " + (mats.size()-mats.size()) + "-" + (mats.size()-1) + ": ");
    							index1 = sc.nextInt();
							sc.nextLine();

							A = mats.get(index1);
							System.out.println("Chosen matrix: ");
							A.print(6,2);
					
							System.out.print("Correct (y/n)? ");
							corr = sc.next();
							sc.nextLine();
				
							if(corr.equalsIgnoreCase("y")) again = false;
							else again = true;
						}
						C = A.transpose();
						System.out.println("Your new matrix: ");
						C.print(6,2);				
					}
					break;
				case 2:
					break;
				default:
					System.out.println("Invalid selection");
      			    		break; // This break is not really necessary
			}
			break;
		case 8:
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

