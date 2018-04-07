package application;

import java.util.List;

public class Student {
	private String name = null;
	private List<String> fields;
	private List<Course> courses;
	
	public Student() {
		this.name = "";
	}
	
	public String getName() {
		return name;
	}
	
	public void addCourse(Course c){
		courses.add(c);
	}
	
	public void setName(String visitorName) {
		this.name = visitorName;
	}
	
	

}
