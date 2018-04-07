
package wasdev.sample.store;

public class CourseStoreFactory {
	
	private static CourseStore instance;
	static {
		CloudantVisitorStore cvif = new CloudantVisitorStore();	
		if(cvif.getDB() != null){
			instance = cvif;
		}
	}
	
	public static CourseStore getInstance() {
		return instance;
	}

}
