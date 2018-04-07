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
	
	public void generateTags(NaturalLanguageUnderstanding NLUservice, String courseDescription) {
		this.generateTags(NLUservice, courseDescription, 3);
	}
	
	public void generateTags(NaturalLanguageUnderstanding NLUservice, String courseDescription, int numTags) {
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
	
	public String getDescription(String text) {
		String regex = "([dD]escription.*?[\r\n])|([oO]verview.*[\r\n])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if(matcher.find()) {
			String description = text.substring(matcher.start(), matcher.end());
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
	
	public String getField() {
		return this.field;
	}
}
