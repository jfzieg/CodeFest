
package wasdev.sample.store;

public class CourseStoreFactory {
	
	private static CourseStore instance;
	static {
		CourseStore cvif = new CloudantCourseStore();
		if(cvif.getDB() != null){
			instance = cvif;
		}
	}
	
	public static CourseStore getInstance() {
		return instance;
	}

}
