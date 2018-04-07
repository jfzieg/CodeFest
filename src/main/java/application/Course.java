package application;

import java.util.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

public class Course {
	private List<String> tags = new ArrayList<>();
	
	public Course(){
		
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
		for (int i = 0; i < numTags; i++) {
			this.addTag(response.getConcepts().get(i).getText());
		}
		System.out.println(this.tags);
	}

}
