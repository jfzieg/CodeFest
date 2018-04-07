package application;
import org.json.*;
import wasdev.sample.rest.GitJobsApi;

import java.util.ArrayList;
import java.util.HashMap;

import static application.Student.sortByValues;

public class Search {

    public HashMap<Job, Integer> search(String keyword) throws Exception{
        GitJobsApi a = new GitJobsApi();
        JSONArray json = a.getJobs(keyword);
        JSONObject b = json.getJSONObject(0); //Indexes first result of the JSONArray
        System.out.println(b.get("description")); //Prints just the value associated with the description key

        ArrayList<Job> jobsList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            jobsList.add(new Job((String)json.getJSONObject(i).get("title"), (String)json.getJSONObject(i).get("company"), (String)json.getJSONObject(i).get("description")));
        }

        HashMap<Job, Integer> rankedJobs = new HashMap<>();
        
        for (Job j : jobsList) {
            int hits = 0;
            for (String studSkill : Student.getSkills()) {
                for (String tag : j.getTags()){
                    if (tag.equalsIgnoreCase(studSkill)) {
                        hits++;
                    }
                }
            }

            rankedJobs.put(j, hits);
        }
        rankedJobs = sortByValues(rankedJobs);
        return rankedJobs;
    }




}
