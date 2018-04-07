package application;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;

public class Course {
	private List<String> tags = new ArrayList<>();
	private String courseName;
	private String field;

	public Course(String courseName){
		this.courseName = courseName;
	}
	
	public Course(String courseName, String field){
		this.courseName = courseName;
		this.field = field;
	}
	
	public Course(String courseName, String field, String courseDescription) {
		this.courseName = courseName;
		this.field = field;
		this.generateTags(courseDescription);
	}
	
	public boolean searchTag(String s){
		if (tags.contains(s)){
			return true;
		}
		else return false;
	}
	
	public void addTag(String tag){
		tags.add(tag);
	}
	
	public void addTags(List<String> tags) {
		tags.addAll(tags);
	}
	
	public void generateTags(String courseDescription) {
		this.generateTags(courseDescription, 3);
	}

	public void generateTags(String courseDescription, int numTags) {
		List<String> concepts = NLU.getConcepts(courseDescription, numTags);
		this.addTags(concepts);
		for(int i=0; i<this.tags.size();i++) {
			for(int j=i+1; j<this.tags.size();j++) {
				if(this.tags.get(i) == this.tags.get(j)) {
					this.tags.remove(j);
				}
			}
		}
		System.out.print("New tags added to " + courseName + ": ");
		System.out.print(concepts);
		System.out.println();
	}
	
	public void generateField(String courseDescription) {
		this.field = NLU.getCatagory(courseDescription);
	}
	
	// works with text for most pdfs, doesn't work with text for Cybersecurity for some reason
	// since the text we get from Cybersecuryity is all messed up
	public static String getDescription(String text) {
		String textStrip = text.replaceAll("\r","");
		String textStrip1 = textStrip.replaceAll("\n{2}","\r");
		String textStrip2 = textStrip1.replaceAll("\n"," ");
		String textStrip3 = textStrip2.replaceAll("\r","\n");  
		String regex = "([dD]escription:?.{5,}\n)|([oO]verview:?.{5,}\n)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(textStrip3);
		if(matcher.find()) {
			String description = textStrip3.substring(matcher.start(), matcher.end());
			return description;
		}
		return "could not find description :(";
	}

	public List<String> getTags() {
		return this.tags;
	}

	public String getCourseName() {
		return this.courseName;
	}

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getField() {
		return this.field;
	}
    
    public static String pdfGetText(String pdfFilePath) {
    	Document pdf = PDF.open(pdfFilePath);
		StringBuilder text = new StringBuilder(1024);
		pdf.pipe(new OutputTarget(text));
		try {
			pdf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text.toString();
    }
    
}
