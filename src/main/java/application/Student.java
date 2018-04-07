package application;

import java.util.*;

public class Student {
	private String name = null;
	private ArrayList<String> major;
	private ArrayList<String> minor;
	
	private ArrayList<String> fields;
	private ArrayList<Integer> fieldRanks;
	private ArrayList<Course> courses;
	
	public Student() {
		this.name = "";
	}
	
	public Student(String s, ArrayList<String> majors, ArrayList<String> minors) {
		this.name = s;
		this.major = majors;
		this.minor = minors;
	}
	
	public String getName() {
		return name;
	}
	
	//Allows input of majors delimited by comma and space, like "Computer Science, Math"
	public void enterMajors(String s) {
		major = new ArrayList<String>();
		String[] majorArray = s.split(", ");
		for (String ss : majorArray) {
			major.add(ss);
		}
	}
	
	public void enterMinors(String s) {
		minor = new ArrayList<String>();
		String[] minorArray = s.split(", ");
		for (String ss : minorArray) {
			minor.add(ss);
		}
	}
	
	public void addCourse(Course c){
		courses.add(c);
		String field = c.getField();
		if (fields.contains(field)){
		    fieldRanks.get( fields.indexOf(field) ) += 1;
        }
        else {
		    fields.add(c.getField());
		    fieldRanks.
        }

	}
	
	public void setName(String visitorName) {
		this.name = visitorName;
	}

	public ArrayList<String> deleteDuplicateFields(ArrayList<String> longList) {
	    Set<String> deleter = new HashSet<>();
	    deleter.addAll(longList);
	    ArrayList<String> shortList = new ArrayList<>();
	    shortList.addAll(deleter);
	    return shortList;
    }

	public void rankFields() {

	    int[] fieldRanking = new int[fields.size()];

	    for (int i = 0; i < fields.size(); i++) {
	        for (String s2 : fields) {
	            if (fields.get(i).equalsIgnoreCase(s2)){
	                fieldRanking[i]++;
                }
            }
        }

        int max = 0;
	    int highestIndex = -1;
        for (int rank : fieldRanking) {
	        if (rank > max) {
	            max = rank;
	            highestIndex =
            }
        }

    }
	

}