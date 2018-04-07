import com.ibm.watson.developer_cloud.natural_language_understanding.v1.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

public class Class {
	public String name;
	public String field;
	public String level;
	public String[] tags;
	
	Class(String name, String field, String level) {
		this.name = name;
		this.field = field;
		this.level = level;
	}
	
	public static void getTags(String courseDescription) {
		NaturalLanguageUnderstanding NLUservice = startNLUservice();
		ConceptsOptions concepts = new ConceptsOptions.Builder()
				.limit(3).build();
		Features features = new Features.Builder()
				.concepts(concepts).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(courseDescription).features(features).build();
		AnalysisResults response = NLUservice.analyze(parameters).execute();
		System.out.println(response.getConcepts());
	}
	
	private static startNLUservice() {
		return new NaturalLanguageUnderstanding(
				  "2018-03-16",
				  "6dc0cf71-c1ef-4c0d-a2b1-989742aa5877",
				  "4fyitpGXB48Y"
				);
	}
}
