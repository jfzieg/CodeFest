package application;

import java.util.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Course {
	private List<String> tags = new ArrayList<>();
	private String courseName;
	private String field;

	public Course(String courseName, String field){
		this.courseName = courseName;
		this.field = field;
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
	
	public void generateTags(String courseDescription) {
		this.generateTags(courseDescription, 3);
	}

	public void generateTags(String courseDescription, int numTags) {
		NaturalLanguageUnderstanding NLUservice = startNLUservice();
		ConceptsOptions concepts = new ConceptsOptions.Builder()
				.limit(numTags).build();
		Features features = new Features.Builder()
				.concepts(concepts).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(courseDescription).features(features).build();
		AnalysisResults response = NLUservice.analyze(parameters).execute();
		System.out.print("New tags added to " + courseName + ": ");
		for (int i = 0; i < numTags; i++) {
			String newTag = response.getConcepts().get(i).getText();
			this.addTag(newTag);
			System.out.print(newTag + ",");
		}
		System.out.println();
		System.out.println(this.tags);
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
		System.out.println(textStrip3);
		if(matcher.find()) {
			String description = textStrip3.substring(matcher.start(), matcher.end());
			return description;
		}
		return "nope";
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

	private static NaturalLanguageUnderstanding startNLUservice() {
		return new NaturalLanguageUnderstanding(
				  "2018-03-18",
				  "6dc0cf71-c1ef-4c0d-a2b1-989742aa5877",
				  "4fyitpGXB48Y"
				);
	}
}
