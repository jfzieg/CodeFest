package application;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		String courseDescription = "You will learn about the Object-Oriented Programming paradigm and gain knowledge of classes,\r\n" + 
				"encapsulation, polymorphism, inheritance, and virtual classes. You will use the C++\r\n" + 
				"programming language to design efficient, sustainable projects.";
		Course class1 = new Course("CS120", "CS"); 
		class1.generateTags(courseDescription);
		

		Course class2 = new Course("CS124", "CS"); 
		String pdfFilePath = "resources/CS124.pdf";
		String text = Course.pdfGetText(pdfFilePath);
		class2.generateTags(Course.getDescription(text.toString()));
		
		NLU.getCatagory(courseDescription);
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcom to 5th Year");
		System.out.println("Please enter your name.");
		System.out.print("First:");
		String fName = sc.nextLine();
		String lName = sc.nextLine();
		System.out.println("What would you like to do?");
		System.out.println("(1)Add class\n(2) ");
	}
}
