/*******************************************************************************
 * Copyright (c) 2017 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package wasdev.sample.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import application.Course;
import application.Student;
import com.google.gson.Gson;

import wasdev.sample.store.CloudantStudentStore;
import wasdev.sample.store.Storable;
import wasdev.sample.store.StudentStoreFactory;

@ApplicationPath("api")
@Path("/visitors")
public class StudentAPI extends Application {
	
	//Our database store
	CloudantStudentStore store = StudentStoreFactory.getInstance();

  /**
   * Gets all Visitors.
   * REST API example:
   * <code>
   * GET http://localhost:9080/GetStartedJava/api/student
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
    public String getStudents() {
		
		if (store == null) {
			return "[]";
		}
		
		List<String> names = new ArrayList<String>();
		for (Student doc : store.getAll()) {
			String name = doc.getName();
			if (name != null){
				names.add(name);
			}
		}
		return new Gson().toJson(names);
    }

    /**
     * Gets all Visitors.
     * REST API example:
     * <code>
     * GET http://localhost:9080/GetStartedJava/api/student
     * </code>
     *
     * Response:
     * <code>
     * [ "Bob", "Jane" ]
     * </code>
     * @return A collection of all the Visitors
     */
    @GET
    @Path("/students/{id}")
    @Produces({"application/json"})
    public String getStudent(@PathParam("id") String name) {

        if (store == null) {
            return "[]";
        }

        Student student = store.get(name);

        List<String> majors = student.getMajor();
        List<String> minors = student.getMinor();
        List<Course> courses = student.getCourses();

//        List<List<Object>> fields = new ArrayList(majors, minors, courses );


//        for (Student doc : store.getAll()) {
//            String name = doc.getName();
//            if (name != null){
//                names.add(name);
//            }
//        }
        return new Gson().toJson(majors);
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
     * @param student The new Student to create.
     * @return The Visitor after it has been stored.  This will include a unique ID for the Visitor.
     */
    @POST
    @Produces("application/text")
    @Consumes("application/json")
    public String newToDo(Student student) {
      if(store == null) {
    	  return String.format("Hello %s!", student.getName());
      }
      store.persist(student);
      return String.format("Hello %s! I've added you to the database.", student.getName());

    }

}