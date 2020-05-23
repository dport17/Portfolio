import java.util.*;
public class gradeCategory {
	String categoryName;
	double userGrade;
	double countsFor;
	
	public gradeCategory(String cat, double grade, double portion) {
		this.categoryName=cat;
		this.userGrade=grade/100.0;
		this.countsFor=portion/100.0;
	}
	
	public String toString() {
		return categoryName;
	}
}//end gradeCategory