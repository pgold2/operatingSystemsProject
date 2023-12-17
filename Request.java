package semesterProject;
import java.io.Serializable;

public class Request implements Serializable {
	private int ID; 
	private char jobType; 
	
	//constructor 
	public Request(int ID, char jobType) {
		
	}

	//getters
	public int getID() {
		return ID;
	}
	
	public char getJobType() {
		return jobType;
	}
	
	//setters
	public void setID(int ID) {
		this.ID= ID; 
	}
	
	public void setJobType(char jobType) {
		this.jobType= jobType; 
	}
}
