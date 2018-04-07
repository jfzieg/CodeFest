package application;

import java.util.List;

public class Student {
	private String name = null;
	private List<String> major;
	private List<String> minor;
	
	private List<String> fields;
	private List<Course> courses;
	
	public Student() {
		this.name = "";
	}
	
	public Student(String s, List<String> majors, List<String> minors) {
		this.name = s;
		this.major = majors;
		this.minor = minors;
	}
	
	public String getName() {
		return name;
	}
	
	//Allows input of majors delimited by comma and space, like "Computer Science, Math"
	public void enterMajors(String s) {
		major = new List<String>;
		String[] majorArray = s.split(", ");
		for (String ss : majorArray) {
			major.add(ss);
		}
	}
	
	public void enterMinors(String s) {
		minor = new List<String>;
		String[] minorArray = s.split(", ");
		for (String ss : minorArray) {
			minor.add(ss);
		}
	}
	
	public void addCourse(Course c){
		courses.add(c);
	}
	
	public void setName(String visitorName) {
		this.name = visitorName;
	}
	
	

}