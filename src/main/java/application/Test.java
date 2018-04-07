package application;


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
	}
}
