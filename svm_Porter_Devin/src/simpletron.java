import java.util.*;
public class simpletron {
final static int READ=10;
final static int WRITE=11;
final static int LOAD=20;
final static int STORE=21;
final static int ADD=30;
final static int SUBTRACT=31;
final static int DIVIDE=32;
final static int MULTIPLY=33;
final static int REMAINDER=34;
final static int BRANCH=40;
final static int BRANCHNEG=41;
final static int BRANCHZERO=42;
final static int HALT=43;
static int accumulator=0;
static int ic;
static int ir;
static int i=0;
final static int[] memory=new int[1000];
static Scanner in = new Scanner (System.in);

/*
 * Contract:
 * simpleTron: array; number
 * 
 * Purpose Statement:
 * Program declares and populates an array of commands that are fetched, decoded, and then executed. 
 * 
 * Examples:
 * Array: [1009, 1010, 2009, 3110, 4107, 1109, 4300, 0000, 0000, 0, 0, ..., 0]
 * User input: 45, 62
 * Output: 62
 * 
 * Array; [1007, 1008, 2007, 3008, 2109, 1109, 1109, 4300, 0000, 0000, 0000, 0, 0, ..., 0]
 * User input: 7, 8
 * Output: 15 
 * 
 * Algorithm
 * Program declares and populates an array. Program then passes the array to a method that loops through the array and follows 
 * the commands within it.
 * 
 * Program outputs the result of the commands. 
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		memory[0]=1009;
		memory[1]=1010;
		memory[2]=2009;
		memory[3]=3110;
		memory[4]=4107;
		memory[5]=1109;
		memory[6]=4300;
		memory[7]=1110;
		memory[8]=4300;
		memory[9]=0000;
		memory[10]=0000;
		memory[11]=0000;
		
		arrayPrinter(memory);
		
		simpleTron(memory);
		System.out.println();
		
		System.out.print("Final array is: ");
		arrayPrinter(memory);
		
	}//end main
	
	/*
	 * arrayPrinter(array)->void
	 * 
	 * Method prints out each non-zero element of the array.
	 * 
	 * 
	 */
	static void arrayPrinter(int[] array) {
		int i=0;
		while(i<memory.length) {
			if(memory[i]==0) {
				i++;
				continue;
			}
			System.out.print(memory[i]+" ");
			i++;
		}
	}
	
	/*
	 * simpleTron(int[])->void
	 * 
	 * Program iterates through the array passed to it, fetching, decoding, and executing each command contained within it. 
	 * 
	 * ex1. Array:[1009, 1010, 2009, 3110, 4107, 1109, 4300, 0000, 0000, 0, 0, ..., 0]
	 * 		User input is 24 and 23. 
	 * 		Outputs is 23. 
	 * 
	 * ex2. Array; [1007, 1008, 2007, 3008, 2109, 1109, 1109, 4300, 0000, 0000, 0000, 0, 0, ..., 0]
	 * 		User input: 7, 8
	 * Output: 15 
	 * 
	 */
	public static void simpleTron(int[] array) {
		System.out.println();
		int operator=0;
		int operand=0;
		
		while(i<array.length && operator!=43) {
				ir=array[i];
				operator=ir/100;
				operand=ir%100;
				
				
			   
				if(operator==LOAD) {
			    	load(operand);
			    	
			    }
			    else if(operator==READ) {
			    	read(operand);
			    	
			    }
			    else if(operator==HALT) {
			    	System.out.println("*** Simpletron Execution Terminated ***");
			    	return;
			    }
			    else if(operator==WRITE) {
			    	System.out.println();
			    	write(operand);
			    	
			    }
			    else if(operator==ADD) {
			    	add(operand);
			    	
			    }
			    else if(operator==SUBTRACT) {
			    	subtract(operand);
			    	
			    }
			    else if(operator==DIVIDE) {
			    	divide(operand);
			    }
			    else if(operator==MULTIPLY) {
			    	multiply(operand);
			    }
			    else if(operator==REMAINDER) {
			    	remainder(operand);
			    }
			    else if(operator==BRANCH) {
			    	i=operand;
			    	branch(operand);
			    	continue;
			    }
			    else if(operator==BRANCHZERO) {
			    	if (accumulator==0) {
			    		branch(operand);
			    		i=operand;
			    		continue;
			    	}
			    }
			    else if(operator==BRANCHNEG) {
			    	if (accumulator<0) {
			    		branch(operand);
			    		i=operand;
			    		continue;
			    	}
			    }   
			i++;
			}//end loop
		
		}//end simpleTron
	/*
	 * load(int)->void
	 * 
	 * Methods takes a value at an integer position in the memory and assigns the accumulator variable to it. 
	 * 
	 * ex1. location=4; accumulator=75
	 * ex2. location=7; accumulator=23
	 * ex3. location=67; accumulator=3056
	 * ex4. location=-7; OutOfBounds exception. 
	 */
	static void load(int location) {
		accumulator=memory[location]; 
	}//end load
	/*
	 * read(int)->void
	 * 
	 * Method reads a user input integer value and places it at an integer position in the memory.
	 * 
	 * ex1.location=4; memory[4]=76
	 * ex2. location=7; memory[7]=23
	 * ex3. location=67; memory[67]=45
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void read(int location) {
		System.out.println("Give me an integer");
		int x=in.nextInt();
		memory[location]=x;
		//System.out.println("The integer at position "+location+" is "+x);
	}//end read
	/*
	 * write(int)->void
	 * 
	 * Method writes the value at the given position to the screen.
	 * 
	 * ex1.location=4; memory[4]=76, prints 76
	 * ex2. location=7; memory[7]=23, prints 23
	 * ex3. location=67; memory[67]=45, prints 45
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void write(int location) {
		System.out.println("Writing...");
		System.out.println(memory[location]);
	}//end write
	/*
	 * add(int)->void
	 * 
	 * Method adds the value at a given position in the memory to the value in the accumulator and stores the result in the accumulator.
	 * 
	 * ex1.location=4; memory[4]=76, accumulator becomes 85. 
	 * ex2. location=7; memory[7]=23, accumulator becomes 42.
	 * ex3. location=67; memory[67]=45, accumulator becomes 90.
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void add(int location) {
		System.out.println("Adding...");
		accumulator+=memory[location];
	}//end add
	/*
	 * store(int)->void
	 * 
	 * Method stores the value at a given position in the memory in the accumulator. 
	 * 
	 * ex1.location=4; memory[4]=76, accumulator=76.
	 * ex2. location=7; memory[7]=23, accumulator=23.
	 * ex3. location=67; memory[67]=45, accumulator=45.
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void store(int location) {
		System.out.println("Storing accumulator in position "+location);
		memory[location]=accumulator;
	}//end store
	/*
	 * subtract(int)->void
	 * 
	 * Method subtracts the value at a given position in the memory from the accumulator and puts the result in the accumulator. 
	 * 
	 * ex1.location=4; memory[4]=76, accumulator=70. 
	 * ex2. location=7; memory[7]=23, accumulator=15.
	 * ex3. location=67; memory[67]=45, accumulator=32.
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void subtract(int location) {
		System.out.println("Subtracting...");
		accumulator-=memory[location];
	}//end subtract
	/*
	 * divide(int)->void
	 * 
	 * Method divides the value at a given position in the memory from the accumulator and puts the result in the accumulator.
	 * 
	 * ex1.location=4; memory[4]=76, accumulator=38
	 * ex2. location=7; memory[7]=23, accumulator=11
	 * ex3. location=67; memory[67]=45, accumulator=9
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void divide(int location) {
		accumulator/=memory[location];
	}//end divide
	/*
	 * multiply(int)->void
	 * 
	 * Method multiplies the value at a given position in the memory from the accumulator and puts the result in the accumulator.
	 * 
	 * ex1.location=4; memory[4]=76, accumulator=152
	 * ex2. location=7; memory[7]=23, accumulator=69
	 * ex3. location=67; memory[67]=45, accumulator=180
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void multiply(int location) {
		accumulator=accumulator*memory[location];
	}//end multiply
	/*
	 * remainder(int)->void
	 * 
	 * Method divides the value at a given position in the memory from the accumulator and puts the result in the accumulator.
	 * 
	 * ex1.location=4; memory[4]=76, accumulator=0.
	 * ex2. location=7; memory[7]=23, accumulator=1
	 * ex3. location=67; memory[67]=45, accumulator=7
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void remainder(int location) {
		accumulator%=memory[location];
	}//end remainder
	/*
	 * branch(int)->void
	 * 
	 * Method branches to the position specified by the given location.
	 * 
	 * ex1.location=4; instruction register becomes 4. 
	 * ex2. location=7; instruction register becomes 7.
	 * ex3. location=67; instruction register becomes 67. 
	 * ex4. location=-7; OutOfBounds exception.
	 */
	static void branch(int location) {
		ir=memory[location];
	}//end branch
	
}//end homework8
