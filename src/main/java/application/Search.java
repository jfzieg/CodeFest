package application;
import org.json.*;
import wasdev.sample.rest.GitJobsApi;

import java.util.ArrayList;

public class Search {

    public void search(String keyword) throws Exception{
        GitJobsApi a = new GitJobsApi();
        JSONArray json = a.getJobs(keyword);
        JSONObject b = json.getJSONObject(0); //Indexes first result of the JSONArray
        System.out.println(b.get("description")); //Prints just the value associated with the description key

        ArrayList<Job> jobsList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            jobsList.add(new Job((String)json.getJSONObject(i).get("title"), (String)json.getJSONObject(i).get("company"), (String)json.getJSONObject(i).get("description")));
        }
    }


}
