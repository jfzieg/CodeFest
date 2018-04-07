import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import junit.framework.Assert;
import org.junit.Test;
import org.json.*;
import java.io.IOException;



public class MyTest{
	@Test
	public void test1() throws IOException{
		
		GitJobsApi a = new GitJobsApi();
		JSONArray json = a.readJsonFromUrl("https://jobs.github.com/positions.json?description=java"); 
		JSONObject b = json.getJSONObject(0); //Indexes first result of the JSONArray
		System.out.println(b.get("description")); //Prints just the value associated with the description key
	    
	}
	
	
	@Test
	public void test2() {
		NaturalLanguageUnderstanding NLUservice = new NaturalLanguageUnderstanding(
				  "2018-03-16",
				  "3ef0284a-d3e1-43a5-bf2e-86e270c07996",
				  "6dhn05xkaU2z"
				);
		String courseDescription = "You will learn about the Object-Oriented Programming paradigm and gain knowledge of classes,\r\n" + 
				"encapsulation, polymorphism, inheritance, and virtual classes. You will use the C++\r\n" + 
				"programming language to design efficient, sustainable projects.";
		Class.getTags(NLUservice, courseDescription);
		
	}
}
