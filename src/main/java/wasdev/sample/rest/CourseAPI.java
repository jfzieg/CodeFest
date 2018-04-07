

package wasdev.sample.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;

import wasdev.sample.Visitor;
import wasdev.sample.store.VisitorStore;
import wasdev.sample.store.VisitorStoreFactory;

@ApplicationPath("api")
@Path("/courses")
public class CourseAPI extends Appliction {
	
	//Our database store
	CourseStore store = CourseStoreFactory.getInstance();

  /**
   * Gets all Visitors.
   * REST API example:
   * <code>
   * GET http://localhost:9080/GetStartedJava/api/visitors
   * </code>
   * 
   * Response:
   * <code>
   * [ "Bob", "Jane" ]
   * </code>
   * @return A collection of all the Visitors
   */
    @GET
    @Path("/")
    @Produces({"application/json"})
    
    public String getVisitors() {
		
		if (store == null) {
			return "[]";
		}
		
		List<String> names = new ArrayList<String>();
		for (Visitor doc : store.getAll()) {
			String name = doc.getName();
			if (name != null){
				names.add(name);
			}
		}
		return new Gson().toJson(names);
    }
    
    /**
     * Creates a new Visitor.
     * 
     * REST API example:
     * <code>
     * POST http://localhost:9080/GetStartedJava/api/visitors
     * <code>
     * POST Body:
     * <code>
     * {
     *   "name":"Bob"
     * }
     * </code>
     * Response:
     * <code>
     * {
     *   "id":"123",
     *   "name":"Bob"
     * }
     * </code>
     * @param visitor The new Visitor to create.
     * @return The Visitor after it has been stored.  This will include a unique ID for the Visitor.
     */
    @POST
    @Produces("application/text")
    @Consumes("application/json")
    public String newToDo(Course course) {
      if(store == null) {
    	  return String.format("Hello %s!", course.getCourseName());
      }
      
      store.persist(course);
      return String.format("Hello %s! I've added you to the database.", course.getCourseName());

    }
