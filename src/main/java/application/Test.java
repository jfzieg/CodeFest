package application;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;

public class Test {
	public static void main(String[] args) {
		NaturalLanguageUnderstanding NLUservice = new NaturalLanguageUnderstanding(
				  "2018-03-16",
				  "3ef0284a-d3e1-43a5-bf2e-86e270c07996",
				  "6dhn05xkaU2z"
				);
		String courseDescription = "You will learn about the Object-Oriented Programming paradigm and gain knowledge of classes,\r\n" + 
				"encapsulation, polymorphism, inheritance, and virtual classes. You will use the C++\r\n" + 
				"programming language to design efficient, sustainable projects.";
		Course class1 = new Course(); 
		class1.generateTags(NLUservice, courseDescription);
	}
}
