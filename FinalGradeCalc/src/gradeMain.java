import java.util.*;
public class gradeMain {
 static final double x=0.75;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=1;
		double total=0;
		double currCatPer=0;
		Scanner in = new Scanner(System.in);
		System.out.println("How many grade categories do you have other than than final exam grade?");
		int cats=in.nextInt();
		
		ArrayList<gradeCategory> gradeInfo=new ArrayList<gradeCategory>();
		
		while(i<=cats) {
			System.out.println("Input Category "+i+":");
			String cat=in.next();
			System.out.println("Input the grade you received for that category: ");
			double grade=in.nextDouble();
			System.out.println("Input the percent of your grade that the category counts for: ");
			double count=in.nextDouble();
			
			gradeInfo.add(new gradeCategory(cat, grade, count));
			i++;
		}//end loop
		
		for(int j=0; j<gradeInfo.size();j++) {
			gradeCategory currentCat=gradeInfo.get(j);
			double increase=(currentCat.userGrade*currentCat.countsFor);
			total+=increase;
			currCatPer+=currentCat.countsFor;
		}//end loop
		double leftOver=x-total;
		double examWorth=1-currCatPer;
		
		double scoreNeeded=leftOver/examWorth;
		scoreNeeded*=100;
		System.out.print("You need a ");
		System.out.printf("%.2f", scoreNeeded);
		System.out.print(" on your exam to get an C-.");
	}//end main

}//end gradeMain
