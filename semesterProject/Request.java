package semesterProject;
import java.io.Serializable;

public class Request implements Serializable {
	private String jobID;
	private String jobType;

	private int clientID;
	
	//constructor 
	public Request(String jobID, String jobType, int clientID) {
		this.jobID = jobID;
		this.jobType = jobType;
		this.clientID = clientID;
	}

	public Request(String jobID, String jobType){
		this.jobID = jobID;
		this.jobType = jobType;
	}


	//getters
	public String getJobID() {
		return jobID;
	}
	
	public String getJobType() {
		return jobType;
	}
	
	//setters
	public void setID(int ID) {
		this.jobID= jobID;
	}
	
	public void setJobType(char jobType) {
		this.jobType= String.valueOf(jobType);
	}



	public int getClientID() {
		return clientID;
	}

	@Override
	public String toString() {
		return "Job type: " + this.jobType + " Job ID: " + this.jobID + " Client ID: " + clientID;
	}

}
