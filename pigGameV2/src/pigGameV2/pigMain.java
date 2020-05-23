/*
 * pigMain.java
 * Name: Devin Porter
 * Username: porterd3
 * Assignment: 4
 * 
 * Program welcomes user to the game of pig! Program initializes the computer's score and the user's score to 0.
 * The program also creates a Die object named steve which can be manipulated to have a different face value whenever the 
 * roll() method is called. There also exists a getter in the Die class which retrieves the Die's value.
 * 
 * While the total user score and the total computer score are both less than 100, the program makes calls to two methods 
 * which are passed the user's and the computer's current total points and allows the user to play and earn more points on each 
 * "turn." This is implemented with a loop.
 * 
 * Within the userTurn method, a loop is executed which continually rolls steve until either the user chooses to stop playing or
 * steve.getFaceValue()==1. In the second case, the user will lose any points gained on that turn. Also, the loop will stop if the 
 * total score >= 100. 
 * 
 * Within the computerTurn method, the program will continuously roll the die and award points to the computer on each turn until
 * the turn score becomes >=20 or the computer rolls 1 or the computer's total score >=100.  
 * 
 * Once one of the scores becomes greater than or equal to 100, the program outputs "You win!" or "The computer wins!", 
 * depending on which occurred first. 
 *
 */
package pigGameV2;
import java.util.Scanner;
import java.util.Random;
public class pigMain {
	
	final static Scanner in=new Scanner (System.in);
	final static Random gen=new Random();
	static Die steve=new Die();
	static int userTurn=1;
	static int compTurn=1;
	public static void main(String[] args) {
		//does the user want to play?
		System.out.println("Welcome to Pig! You have a fair die. If you roll a 1, your turn is over and you lose all points earned on that turn.");
		System.out.println("If you roll any other value, it is added to your total score once you choose to hold. Each time you ");
		System.out.println("roll, you run the risk of rolling a 1 and losing all points gained on that turn. You play against the ");
		System.out.println("computer, which can only score up to 25 points a turn. Once its total reaches or eclipses 20, its turn ends.");
		System.out.println("Would you like to play? (Y/N)");
		
		String play=in.next();
		play=play.toUpperCase();
		
		//if not, stop.
		if(!play.equals("Y")) {
			System.out.println("Bye!");
			return;
		}
		
		//Inform the user that their turn is about to be executed.
		System.out.println("You get to go first!");
		System.out.println();
		
		//initialize total scores.
	    int totalCompScore=0;
		int totalUserScore=0;

		//continuously give user and computer a turn until one wins. 
		 while (totalCompScore<100&&totalUserScore<100) {
			 totalUserScore=userTurn(totalUserScore);
			 System.out.println();
			 System.out.println("Your total score is "+totalUserScore);
			 System.out.println();
			 totalCompScore=computerTurn(totalCompScore);
			 System.out.println();
			 System.out.println("The computer's total score is "+totalCompScore);
			 System.out.println();
		 }//end loop
		 
		 //display the winner
		 if (totalCompScore>=100) {
			 System.out.println("The computer wins!");
		 }
		 else {
			 System.out.println("You win!");
		 }
	}//end main
	
	public static int userTurn(int score) {
		//keep track of whose turn it is, and track turns in chronological order.
		System.out.println("-----------------------------------------------------------------");
		System.out.println("YOUR TURN #"+userTurn);
		System.out.println("-----------------------------------------------------------------");
		
		//initialize the turn score, a boolean for the loop, and a string to store the user's decision to roll or hold.
		int turnScore=0;
		boolean done=false;
		String response="r";
		
		//continuously roll die until user holds or rolls a one.
		while(!done) {
			
			//call roll method to roll die.
			steve.roll();
			
			//call getFaceValue to return value on die and display it to the user.
			System.out.println("You rolled "+steve.getFaceValue());
			
			//if the face value is 1, the user loses all points for that turn and loses their turn.
			if (steve.getFaceValue()==1) {
				turnScore=0;
				System.out.println("Your turn score is 0 and your total score is "+score);
				break;
			}//end if
			
			//otherwise, the program keeps looping through the broader while loop so long as the user wants to roll.
			else {
				//add to turn score if the face value is anything other than 1.
				turnScore+=steve.getFaceValue();
				if((turnScore+score)>=100) {
					System.out.println("You scored "+(score+turnScore)+"!");
					break;
				}
				//display the turn score and the total score, then invite the user to roll again and display what the total score
				//will be if they choose to hold.
				System.out.println("Your turn score is "+turnScore+" and your total score is "+score+".");
				System.out.println();
				System.out.println("Would you like to play again? ('h' to hold and 'r' to roll again.)");
				System.out.println("If you hold, your score will be "+(score+turnScore));
				response=in.next();
				response=response.toLowerCase();
				
				//if the response is invalid, get it again, endlessly.
				while (!(response.equals("h")||response.equals("r"))){
					System.out.println("Sorry, your response was invalid. Please enter a new response: ");
					response=in.next();
				}//end inner loop
				if(response.equals("h")) {
					done=true;
				}
				else {
					continue;
				}
			}//end else
			
		}//end while loop
		//increment the turn number.
		userTurn++;
		return turnScore+score;
	}//end userTurn
	
	
	public static int computerTurn(int score) {
		//Separate the computer's turn from the user's.
		System.out.println("-----------------------------------------------------------------");
		System.out.println("COMPUTER'S TURN #"+compTurn);
		System.out.println("-----------------------------------------------------------------");
		//initialize the turn score, a boolean for the loop, and a string to store the user's decision to roll or hold.
		int turnScore=0;
		boolean done=false;
		
		//loop through computer's turn, rolling the die until the turn score is >=20 or the roll is 1.
		while(!done) {
			//roll steve
			steve.roll();
			//display value of steve
			System.out.println("The computer rolled: "+steve.getFaceValue());
			
			//if the turn score is more than 20 or the total score is >=100, the program will leave the method.
			if(turnScore>=20||(turnScore+score)>=100) {
				done=true;
				System.out.println("The computer scored "+turnScore+" points this round.");
			}//end if
			else if(steve.getFaceValue()==1) {
				System.out.println("The computer lost its turn!");
				turnScore=0;
				done=true;
			}
			else {
				turnScore+=steve.getFaceValue();
			}
			
		}//end loop
		//increment computer turn tracker
		compTurn++;
		return turnScore+score;
	}//end computerTurn

}//end pigGame
