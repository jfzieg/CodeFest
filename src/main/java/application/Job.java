package application;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import org.json.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Job {

    public String title;
    public String location;
    public String description;

    public ArrayList<String> tags;

    public Job(String title, String location, String description) {
        this.title = title;
        this.location = location;
        this.description = description;


        this.generateTags(description);
    }

    public void addTag(String tag){
        tags.add(tag);
    }

    public void generateTags(String courseDescription) {
        this.generateTags(courseDescription, 5);
    }

    public void generateTags(String courseDescription, int numTags) {
        List<ConceptsResult> concepts = NLU.getConcepts(courseDescription, numTags);
        System.out.print("New tags added to " + title + ": ");
        for (int i = 0; i < numTags; i++) {
            if (i != 0) {System.out.print(", ");}
            String newTag = concepts.get(i).getText();
            this.addTag(newTag);
            System.out.print(newTag);
        }
        System.out.println();
    }

    public ArrayList<String> getTags() {
        return tags;
    }










}
