/*
 * Copyright IBM Corp. 2017
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
 */
package wasdev.sample.store;

import java.util.Collection;

import application.Student;
import com.cloudant.client.api.Database;

import application.Student;

/**
 * Defines the API for a ToDo store.
 *
 */
public interface Storable {

  	/**
	 * Get the target db object.
	 * 
	 * @return Database.
  	 * @throws Exception 
	 */
  public Database getDB();
  
  	/**
	 * Gets all Students from the store.
	 * 
	 * @return All Students.
  	 * @throws Exception 
	 */
  public Collection<Student> getAll();

  /**
   * Gets an individual ToDo from the store.
   * @param id The ID of the ToDo to get.
   * @return The ToDo.
   */
  public Student get(String id);

  /**
   * Persists a Student to the store.
   * @param vi The ToDo to persist.
   * @return The persisted ToDo.  The ToDo will not have a unique ID..
   */
  public Student persist(Student vi);

  /**
   * Updates a ToDo in the store.
   * @param id The ID of the Student to update.
   * @param vi The Student with updated information.
   * @return The updated Student.
   */
  public Student update(String id, Student vi);

  /**
   * Deletes a ToDo from the store.
   * @param id The ID of the Student to delete.
   */
  public void delete(String id);
  
  /**
   * Counts the number of Students
   * @return The total number of Students.
 * @throws Exception 
   */
  public int count() throws Exception;
}
