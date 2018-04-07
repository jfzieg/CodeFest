package application;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import java.util.HashMap;

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
	public void prepJson() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name",this.name);
		data.put("email", this.email);
		data.put("major",this.major);
		for(int i = 0, i<courses.size(),i++) {
			data.put("course",this.courses[i]);
		}
		for int(i = 0, i<fields.size(),i++) {
			data.put("field",this.fields[i]);
			
		}
	}
	

}