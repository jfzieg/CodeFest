package application;

import java.io.IOException;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;

public class Test {
	public static void main(String[] args) {
		String courseDescription = "You will learn about the Object-Oriented Programming paradigm and gain knowledge of classes,\r\n" + 
				"encapsulation, polymorphism, inheritance, and virtual classes. You will use the C++\r\n" + 
				"programming language to design efficient, sustainable projects.";
		Course class1 = new Course("CS205", "CS"); 
		class1.generateTags(courseDescription);
		
		String pdfFilePath = "resources/CS124.pdf";
		Document pdf = PDF.open(pdfFilePath);
		StringBuilder text = new StringBuilder(1024);
		pdf.pipe(new OutputTarget(text));
		try {
			pdf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(text);
		
//		String text = "CS206: Evolutionary Robotics\r\n" + 
//				"					\r\n" + 
//				"Lectures: 		207 Votey, 			8:30-9:45am Tues/Thurs	(Schedule)\r\n" + 
//				"					\r\n" + 
//				"Instructor: 		Josh Bongard			josh.bongard@uvm.edu\r\n" + 
//				"Office Hours: 		Mondays, 3-4pm 		At www.twitch.tv/doctorjoshuvm\r\n" + 
//				"and Tuesdays, 10-11am 	In my office (205 Farrell Hall)\r\n" + 
//				"					\r\n" + 
//				"Teaching Assistant:	Kevin Gottfried		kgottfri@uvm.edu \r\n" + 
//				"Office Hours:		Thursdays, 11am - 1 pm 	B9 Mansfield House (in the basement)\r\n" + 
//				"					\r\n" + 
//				"Description: This course will explore the automated design of autonomous machines using evolutionary algorithms. The course will cover relevant topics in evolutionary computation, artificial neural networks, robotics, biomechanics, and simulation. Students will conduct a major programming project that will span the course and thus provide hands-on experience with the topics covered. Undergraduates will use their developed system to perform a pre-specified evolutionary robotics experiment; graduate students will formulate their own research hypothesis and use their system to test that hypothesis.\r\n" + 
//				"					\r\n" + 
//				"Textbook: Floreano, D. & Mattiussi, C. (2008) Bio-Inspired Artificial Intelligence: Theories, Methods, and Technologies, MIT Press. (Will be available on loan in the library.)\r\n" + 
//				"					\r\n" + 
//				"Supplementary (Optional) Textbook: Pfeifer, R. & Bongard, J. (2007) How The Body Shapes the Way we Think: A New View of Intelligence, MIT Press. (Available on hold in the library.)\r\n" + 
//				"					\r\n" + 
//				"Additional readings from the current literature will be provided.\r\n" + 
//				"Prerequisites: Junior standing and programming experience, or instructor permission.\r\n" + 
//				"					\r\n" + 
//				"Grading Scheme: The late policy for this class is as follows: material one day late, 25% deduction; two days late, 50% deduction; three days late, 100% reduction.\r\n" + 
//				"					\r\n" + 
//				"Ten programming assignments (10×4=40%): Over the span of ten weeks, each student will gradually build a software system that allows them to conduct an evolutionary robotics experiment. This system will be composed of 10 software modules. Each programming assignment will involve implementing one of these modules. Note: Because the modules will form a final, integrated system, if you fail to hand in one assignment, you must hand it in along with the new assignment the following week.\r\n" + 
//				"					\r\n" + 
//				"Quizzes (25%): After each class, a multiple-choice quiz will be posted on BlackBoard. This quiz should only take five minutes, assuming you attended lecture and completed the reading for that day. The quiz must be taken by 11:59pm that day.\r\n" + 
//				"					\r\n" + 
//				"Final project (30%): Over the final four (or nine) weeks of the semester, each student will use their software system to perform an evolutionary robotics experiment. Several weekly deliverables will be submitted, a written report describing the experiment will be handed in at the end of the semester, and an oral presentation will be given during the exam period.\r\n" + 
//				"					\r\n" + 
//				"Participation (5%): Class participation counts toward your final grade. Students are permitted to miss up to and including three classes without being required to provide justification. Missed classes beyond that must be cleared with the instructor.\r\n" + 
//				"\r\n" + 
//				"";
		System.out.println(Course.getDescription(text.toString()));
	}
}
