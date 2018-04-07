import java.util.List;

public class Course {
	private List<String> tags; 
	
	public Course(){
		
	}
	
	public boolean searchTag(String s){
		if (tags.contains(s)){
			return true;
		}
		else return false;
	}
	
	public void addTag(String tag){
		tags.add(tag);
	}
}
