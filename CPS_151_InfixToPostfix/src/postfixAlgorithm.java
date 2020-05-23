import java.util.*;
public class postfixAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		//get the infix expression
		Scanner in=new Scanner(System.in);
		System.out.println("Input an infix expression with spaces between each letter and operand.");
		System.out.println("Note: A lack of spaces will result in an incorrect postfix expression.");
	
		String infix=in.nextLine();
		outputPostfix(infix);
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Really not sure what happened here but you should start over.");
		}
	}
	public static void outputPostfix(String infix) {
		if(!Character.isLetter(infix.charAt(0))){
			throw new InputMismatchException("Please try again. You cannot begin the express with an "
					+ "operand or space.");
		}
		//initialize the expression to be output
		String post="";
		
		//make a stack for the operands
		Stack<Character> operandStack=new Stack<Character>();
		Stack<Character> extraStack=new Stack<Character>();
		//make a scanner to scan the infix expression
		Scanner infixScan=new Scanner(infix);
		
		//read the infix expression left to right. 
		while(infixScan.hasNext()) {
			String str=infixScan.next();
			char c=str.charAt(0);
			
			if(!isOperand(c)) {
				post=post+c+" ";
			}
			
			else {
				if(!operandStack.isEmpty()&&!isLower(c, operandStack.peek())) {
					operandStack.push(c);
				}
				else {
					while(!operandStack.isEmpty()&&isLower(c, operandStack.peek())) {
						extraStack.push(operandStack.pop());
						
					}
					operandStack.push(c);
					while(!extraStack.isEmpty()) {
						operandStack.push(extraStack.pop());
					}//end while
				}//end else
			}//end else
		}//end while block
		
		
		
		while(!operandStack.isEmpty()) {
			post=post+operandStack.pop()+" ";
		}
		
		System.out.println("The postfix conversion is "+ post);
	}//end outputPostfix
	
	public static boolean isOperand(char c) {
		return (c=='+'||c=='-'||c=='^'||c=='*'||c=='/');
	}
	
	public static boolean isLower(char x, char y) {
		if (x==y) {
			return false;
		}
		else if ((x=='+'||x=='-')&&(y=='/'||y=='*')) {
			return true;
		}
		
		else if(x=='/'&&y=='*') {
			return true;
		}
		
		else if((x=='+'||x=='-'||x=='/')&&(y=='*')) {
			return true;
		}
		
		else if (y=='^') {
			return true;
		}
		
		else {
			return false;
		}
	}
}
