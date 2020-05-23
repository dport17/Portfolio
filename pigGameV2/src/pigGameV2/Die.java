/*
 * Die.java 
 * Name: Devin Porter
 * Username: porterd3
 * Assignment: 4
 * 
 * Description: This class simulates the function of a single die. It provides methods to roll the die and return the face value 
 * of the die. Each time a new Die object is initialized, it is assigned to a value between 
 */
package pigGameV2;
import java.util.Random;
public class Die {
	
	private int faceVal;
	Random gen=new Random();
	
	public Die() {
		faceVal=gen.nextInt(6)+1;
	}
	public int roll() {
		faceVal=gen.nextInt(6)+1;
		return faceVal;
	}
	
	public int getFaceValue() {
		 return faceVal;
	}
	
	
}
