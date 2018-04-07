package wasdev.sample.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.io.*;
import java.net.*;

import org.json.*;

public class GitJobsApi{
	public GitJobsApi() {
		
	}
	
	/**
	 * Main getJobs method returns JSONArray of jobs with specified tag
	 * @param searchBy: The tag that you want to search for
	 * @return JSONArray of results
	 */
	public static JSONArray getJobs(String searchBy) throws Exception{
		String url = "https://jobs.github.com/positions.json?description=";
		url += searchBy;
		return readJsonFromUrl(url);
	}
	
	/**
	 * This method reads all lines from the HTTP Get request
	 * @Param rd: bufferedreader object 
	 * @return whole response as string
	 */
	public static String readAll(BufferedReader rd) throws IOException {
	    	StringBuilder sb = new StringBuilder();
	    	int cp;
	    	String line;
		      while ((line = rd.readLine()) != null) {
		         sb.append(line);
		      }
		      rd.close();
	    	return sb.toString();
	  }

	
	/**
	 * This method reads content from HTTP Get request and converts it to a JSONArray (array of JSON objects)
	 * @param url: the url of the get request
	 * @return Whole response in the form of a JSONArray 
	 */
	public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
		  InputStream is = new URL(url).openStream();
		  try {
			  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			  String jsonText = readAll(rd);
			  //jsonText = jsonText.replace("[", "").replace("]", "");
			  JSONArray json = new JSONArray(jsonText);
			  return json;
	   		} finally {
	   			is.close();
	   		}
	  }
   	
}