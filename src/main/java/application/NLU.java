package application;

import java.util.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

public class NLU {
	public static NaturalLanguageUnderstanding startNLUservice() {
		return new NaturalLanguageUnderstanding(
				  "2018-03-18",
				  "6dc0cf71-c1ef-4c0d-a2b1-989742aa5877",
				  "4fyitpGXB48Y"
				);
	}
	
	public static List<String> getConcepts(String text, int num) {
		NaturalLanguageUnderstanding NLUservice = NLU.startNLUservice();
		ConceptsOptions concepts = new ConceptsOptions.Builder()
				.limit(num).build();
		Features features = new Features.Builder()
				.concepts(concepts).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(text).features(features).build();
		AnalysisResults response = NLUservice.analyze(parameters).execute();
		List<String> conceptStrings = new ArrayList<String>();
		for (int i=0; i<num; i++) {
			conceptStrings.add(response.getConcepts().get(i).toString());
		}
		return conceptStrings;
	}
}
