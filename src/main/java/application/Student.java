package application;

import org.json.JSONArray;
import org.json.JSONObject;
import wasdev.sample.rest.GitJobsApi;

import java.util.*;

public class Student {
    private String name = null;
    private ArrayList<String> major;
    private ArrayList<String> minor;
    private ArrayList<String> skills;

    private HashMap<String, Integer> fields;

    private ArrayList<Course> courses;

    public Student() {
        this.name = "";
    }

    public Student(String s, ArrayList<String> majors, ArrayList<String> minors) {
        this.name = s;
        this.major = majors;
        this.minor = minors;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    //Allows input of majors delimited by comma and space, like "Computer Science, Math"
    public void enterMajors(String s) {
        major = new ArrayList<String>();
        String[] majorArray = s.split(", ");
        for (String ss : majorArray) {
            major.add(ss);
        }
    }

    public void enterMinors(String s) {
        minor = new ArrayList<String>();
        String[] minorArray = s.split(", ");
        for (String ss : minorArray) {
            minor.add(ss);
        }
    }

    public void addCourse(Course c) {
        courses.add(c);
        String field = c.getField();
        if (!fields.containsKey(field)) {
            fields.put(field, 1);
            skills.addAll(c.getTags());
        } else {
            fields.put(field, fields.get(field) + 1);
        }

    }

    public void setName(String visitorName) {
        this.name = visitorName;
    }

    public ArrayList<String> deleteDuplicateFields(ArrayList<String> longList) {
        Set<String> deleter = new HashSet<>();
        deleter.addAll(longList);
        ArrayList<String> shortList = new ArrayList<>();
        shortList.addAll(deleter);
        return shortList;
    }

    public ArrayList<String> rankFields() {
        fields = sortByValues(fields);
        ArrayList<String> sortedList = new ArrayList<>();
        sortedList.addAll(fields.keySet());
        return sortedList;
    }

    public void getTopFields() {
        for (String s : rankFields()){
            System.out.println(s + ": " + fields.get(s));
        }
    }

    // compare fields to requirements and return fields missing or an empty array
    public ArrayList<String> missingFields(ArrayList<String> reqs) {
        int looprun=0;
        Set<String>fieldKeys=fields.keySet();
        ArrayList<String> fieldsAL=new ArrayList<String>(fieldKeys);
        while (looprun<reqs.size()){
            if(fieldsAL.contains(reqs.get(looprun))){
                reqs.remove(looprun);
            }else{
                looprun++;
            }
        }
        return reqs;
    }

    // compare skills to requirements and return skills missing or an empty array
    public ArrayList<String> missingSkills(ArrayList<String> reqs) {
        int looprun=0;
        while (looprun<reqs.size()){
            if(skills.contains(reqs.get(looprun))){
                reqs.remove(looprun);
            }else{
                looprun++;
            }
        }
        return reqs;
    }

    public static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Job, Integer> jobSearch(String keyword) throws Exception{
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
            for (String studSkill : this.getSkills()) {
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

    public void recommendSkills(String keyword) throws Exception{
        GitJobsApi a = new GitJobsApi();
        JSONArray json = a.getJobs(keyword);
        JSONObject b = json.getJSONObject(0); //Indexes first result of the JSONArray
        System.out.println(b.get("description")); //Prints just the value associated with the description key

        ArrayList<Job> jobsList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            jobsList.add(new Job((String)json.getJSONObject(i).get("title"), (String)json.getJSONObject(i).get("company"), (String)json.getJSONObject(i).get("description")));
        }

        HashMap<String, Integer> popularSkills = new HashMap<>();
        for (Job j : jobsList) {
            for (String s : j.getTags()) {
                if (popularSkills.containsKey(s)) {
                    popularSkills.put(s, popularSkills.get(s) + 1);
                }
                else {
                    popularSkills.put(s, 1);
                }
            }
        }

        HashMap<String, Integer> rankedSkills = sortByValues(popularSkills);

        ArrayList<String> topSkills = new ArrayList<>();
        topSkills.addAll(rankedSkills.keySet());

        System.out.println("These skills are in the highest demand:");
        for (int i = 0; i < 10; i++) {
            System.out.println(topSkills.get(i) + ": " + rankedSkills.get(topSkills.get(i)) + "jobs mentioned this.");
        }

    }



}