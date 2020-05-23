/*
 * postfix.java
 * Name: Devin Porter
 * Username: porterd3
 * Assignment: 10
 * 
 * Description:
 * This program has a method postfixOf that takes an infix expression and outputs its postfix. It ensures that there are not multiple operands in a row in the infix, and also ensures that the infix begins with
 * an alphanumeric value.
 * 
 * This postfixOf method has helper methods isOperand and isLower. isOperand is passed a character and returns true if the character is a valid operand. Otherwise, returns false. The isLower method takes two character
 * values which are operands, and if the first value is of lower precedence than the second value, it returns true. Otherwise, returns false.
 */
import java.util.*;

public class postfix {
	public static void main(String[] args) {
		try {
			//get the infix expression
			Scanner in=new Scanner(System.in);
			System.out.println("Input an infix expression. (Note: If you input an operand that is not actually an operand, the program will just ignore and skip over it.)");
			
			String infix=in.nextLine();
			String post=postfixOf(infix);
			System.out.println(post);
		}
		
		catch(InputMismatchException e) {System.out.println(e.getMessage());}
		
		catch(Exception e) {System.out.println("Really not sure what happened here but you should start over.");}
	}//end main
	
	public static String postfixOf(String infix) {
		//get rid of all the whitespace.
		infix=infix.replaceAll("\\s", "");
		
		//initialize postfix that will be returned and the stack of operands. 
		String postfix="";
		Stack<Character> opStack = new Stack<Character>();
		
		//check and make sure the expression does not begin with an operand or something else illegal.
		if(!(Character.isLetter(infix.charAt(0))||Character.isDigit(infix.charAt(0))||infix.charAt(0)==' ')) {
			throw new InputMismatchException("The expression must begin with an alphanumeric value.");
		}//end if
		
		//loop through the infix expression
		for(int i=0; i<infix.length(); i++) {
			
			//assign a variable to the character under scrutiny for ease of access.
			char curr=infix.charAt(i);
			
			//this makes sure that there are not two consecutive operands.
			if(i>0) {
				char last=infix.charAt(i-1);
				
				if(isOperand(curr)&&isOperand(last)) {
					System.out.println("------------------");
					System.out.println("- THAT'S ILLEGAL -");
					System.out.println("------------------");
					throw new InputMismatchException("You cannot have more than 1 consecutive operand.");
				}//end innner if
			}//end if block
			
			//if the character is an operand, check for a variety of other conditions. Act accordingly. 
			if (isOperand(curr)){
				if(!opStack.isEmpty()) {
					
					//For example, if the value on top of the stack is of the same precedence as the value
					//under scrutiny, just add the value that's on top of the stack to the expression and push
					//curr on to the stack.
					if ((curr=='+'&&opStack.peek()=='-')||curr=='-'&&opStack.peek()=='+'||curr=='/'&&opStack.peek()=='*'||curr=='*'&&opStack.peek()=='/') {
						postfix=postfix+opStack.pop();
						opStack.push(curr);
					}
					
					//however, if curr is of lower precedence than the value on top of the stack, add that
					//value to the postfix, popping it from the stack, and continue to check for this 
					//condition until the stack is either empty or the curr value is no longer 
					//of lower precedence than the value on top of the stack. At that point, push the curr value 
					//onto the stack.
					
					else if(isLower(curr, opStack.peek())) {
						while(!opStack.isEmpty()&&isLower(curr, opStack.peek())) {
							postfix=postfix+opStack.pop();
						}
						opStack.push(curr);
					}
					
					//on the other hand, if curr is of higher precedence, just add it to the stack.
					else {opStack.push(curr);}
				}//end inner if block
				
				//if the stack actually is empty, push curr to it. 
				else {opStack.push(curr);}
			}//end if block
			
			//if curr is a letter or digit, just append it to the current postfix expression.
			else if (Character.isLetter(curr)||Character.isDigit(curr)) {postfix=postfix+curr;}
			
			//if there is an empty space or curr is not a letter or not a digit or not an operand, 
			//do nothing to the stack or postfix, just keep looping.
			else {
				System.out.println("The program is ignoring an illegal operand here. The legal operands include +, -, ^, *, /.");
				continue;}
		}//end for loop
		
		//once the whole infix has been looped through, add the rest of the operands in the stack to the end of
		//the postfix.
		while(!opStack.isEmpty()) {postfix=postfix+opStack.pop();}
		
		
		//return the postfix
		return postfix;
		
	}//end postfixOf
	
	
	//checks if a character is an operand.
	public static boolean isOperand(char c) {
		return (c=='+'||c=='-'||c=='^'||c=='*'||c=='/');
	}//end isOperand
	
	//checks if a character is of lower precedence than another.
	public static boolean isLower(char x, char y) {
		if ((x=='+'||x=='-')&&(y=='/'||y=='*')) {
			return true;
		}
		
		else if (y=='^') {
			return true;
		}
		
		else {
			return false;
		}
	}//end isLower
	
}//end postfix
