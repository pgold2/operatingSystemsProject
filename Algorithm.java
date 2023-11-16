package semesterProject;

public class Algorithm {

	public static void main(String[] args) {
		int waitTimeSlaveA = 0;
		int waitTimeSlaveB = 0;

		sendToSlaveAccToJobType(waitTimeSlaveA, waitTimeSlaveB);

	}// closes main

	private static void sendToSlaveAccToJobType(int waitTimeSlaveA, int waitTimeSlaveB) {
		waitTimeSlaveA= 0; 
		waitTimeSlaveB = 0; 
		char jobType = ' ';
		if (jobType == 'A') {
			jobTypeIsA(waitTimeSlaveA, waitTimeSlaveB, jobType);
		}
		else {
		jobTypeIsB(waitTimeSlaveA, waitTimeSlaveB, jobType);
		}
	}// closes sendToSlaveAccToJobType()

	
	//sends job A to fastest slave 
	private static void jobTypeIsA(int waitTimeSlaveA, int waitTimeSlaveB, char jobType) {
		if (jobType == 'A') {
			if (waitTimeSlaveA + 2 < waitTimeSlaveB + 10) {
				// calls slave A
				waitTimeSlaveA = waitTimeSlaveA + 2;
			} else {
				// put job on slave B
				waitTimeSlaveB = waitTimeSlaveB + 10;
			}
		} else if (jobType == 'B') {
			if (waitTimeSlaveB + 2 < waitTimeSlaveA + 10) {
				// put job on slave B
				waitTimeSlaveB = waitTimeSlaveB + 2;
			} else {
				// put job on slave a
				waitTimeSlaveA = waitTimeSlaveA + 10;
			}

		}
	}
	
	//sends job B to fastest slave 
	private static void jobTypeIsB(int waitTimeSlaveA, int waitTimeSlaveB, char jobType) {
		if (jobType == 'B') {
			if (waitTimeSlaveB + 2 < waitTimeSlaveA + 10) {
				// calls slave A
				waitTimeSlaveB = waitTimeSlaveB + 2;
			} else {
				// put job on slave B
				waitTimeSlaveA = waitTimeSlaveA + 10;
			}
		} else if (jobType == 'A') {
			if (waitTimeSlaveA + 2 < waitTimeSlaveB + 10) {
				// put job on slave B
				waitTimeSlaveA = waitTimeSlaveA + 2;
			} else {
				// put job on slave a
				waitTimeSlaveB = waitTimeSlaveB + 10;
			}

		}
	}
}// closes class
