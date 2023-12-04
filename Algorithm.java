
public class Algorithm {
	private static char jobHandler;

	//empty constructor
	public Algorithm() {

	}
	//constructor
	public Algorithm(char jobHandler) {
		this.jobHandler = jobHandler;
	}
	public static void main(String[] args) {

		getsJobType(jobHandler);
		//sendToSlaveAccToJobType(jobHandler);

	}// closes main

	public static void getsJobType(char jobHandler ) {
		sendToSlaveAccToJobType(jobHandler);
	}

	private static void sendToSlaveAccToJobType(char jobHandler) {

		int waitTimeSlaveA= 0;
		int waitTimeSlaveB = 0;
		//char jobType = ' ';
		if (jobHandler == 'A') {
			jobTypeIsA(waitTimeSlaveA, waitTimeSlaveB, jobHandler);
		}
		else {
			jobTypeIsB(waitTimeSlaveA, waitTimeSlaveB, jobHandler);
		}
	}// closes sendToSlaveAccToJobType()


	//sends job A to fastest slave
	private static void jobTypeIsA(int waitTimeSlaveA, int waitTimeSlaveB, char jobHandler) {
		if (jobHandler == 'A') {
			if (waitTimeSlaveA + 2 < waitTimeSlaveB + 10) {
				// calls slave A
				waitTimeSlaveA = waitTimeSlaveA + 2;
			} else {
				// put job on slave B
				waitTimeSlaveB = waitTimeSlaveB + 10;
			}
		} else if (jobHandler == 'B') {
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
}// closes class
