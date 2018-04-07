package application;

import java.util.*;

public class Student {
    private String name = null;
    private ArrayList<String> major;
    private ArrayList<String> minor;

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

    // compare to requirements and return fields missing or an empty array
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

}