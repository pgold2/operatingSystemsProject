package semesterProject;
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

		SlaveWaitTime waitTimeSlaveA = new SlaveWaitTime(0);
		SlaveWaitTime waitTimeSlaveB = new SlaveWaitTime(0);
		//char jobType = ' ';
		if (jobHandler == 'A') {
			jobTypeIsA(waitTimeSlaveA, waitTimeSlaveB, jobHandler);
		}
		else {
			jobTypeIsB(waitTimeSlaveA, waitTimeSlaveB, jobHandler);
		}
	}// closes sendToSlaveAccToJobType()


	//sends job A to fastest slave
	private static void jobTypeIsA(SlaveWaitTime waitTimeSlaveA, SlaveWaitTime waitTimeSlaveB, char jobHandler) {
		if (jobHandler == 'A') {
			if (waitTimeSlaveA.getCurrValue() + 2 < waitTimeSlaveB.getCurrValue() + 10) {
				// calls slave A
				waitTimeSlaveA.setCurrValue(waitTimeSlaveA.getCurrValue() + 2);
				System.out.println("Wait time for slave A is: " + waitTimeSlaveA.getCurrValue());
				SlaveA slaveA = new SlaveA(jobHandler);
				slaveA.doJob();
				waitTimeSlaveA.setCurrValue(waitTimeSlaveA.getCurrValue() - 2);
				System.out.println("Wait time for slave A is: " + waitTimeSlaveA.getCurrValue());
			} else {
				// put job on slave B
				waitTimeSlaveB.setCurrValue(waitTimeSlaveB.getCurrValue() + 10);
				System.out.println("Wait time for slave B is: " + waitTimeSlaveB.getCurrValue());
				SlaveB slaveB = new SlaveB(jobHandler);
				slaveB.doJob();
				waitTimeSlaveB.setCurrValue(waitTimeSlaveB.getCurrValue() - 10);
				System.out.println("Wait time for slave B is: " + waitTimeSlaveB.getCurrValue());
			}
		} else if (jobHandler == 'B') {
			if (waitTimeSlaveB.getCurrValue() + 2 < waitTimeSlaveA.getCurrValue() + 10) {
				// put job on slave B
				waitTimeSlaveB.setCurrValue(waitTimeSlaveB.getCurrValue() + 2);
				System.out.println("Wait time for slave B is: " + waitTimeSlaveB.getCurrValue());
				SlaveB slaveB = new SlaveB(jobHandler);
				slaveB.doJob();
				waitTimeSlaveB.setCurrValue(waitTimeSlaveB.getCurrValue() - 2);
				System.out.println("Wait time for slave B is: " + waitTimeSlaveB.getCurrValue());
			} else {
				// put job on slave a
				waitTimeSlaveA.setCurrValue(waitTimeSlaveA.getCurrValue() + 10);
				System.out.println("Wait time for slave A is: " + waitTimeSlaveA.getCurrValue());
				SlaveA slaveA = new SlaveA(jobHandler);
				slaveA.doJob();
				waitTimeSlaveA.setCurrValue(waitTimeSlaveA.getCurrValue() + 10);
				System.out.println("Wait time for slave A is: " + waitTimeSlaveA.getCurrValue());
			}

		}
	}

	//sends job B to fastest slave
	private static void jobTypeIsB(SlaveWaitTime waitTimeSlaveA, SlaveWaitTime waitTimeSlaveB, char jobType) {
		if (jobType == 'B') {
			if (waitTimeSlaveB.getCurrValue() + 2 < waitTimeSlaveA.getCurrValue() + 10) {
				// calls slave B
				waitTimeSlaveB.setCurrValue(waitTimeSlaveB.getCurrValue() + 2);
			} else {
				// put job on slave A
				waitTimeSlaveA.setCurrValue(waitTimeSlaveA.getCurrValue() + 10);
			}
		} else if (jobType == 'A') {
			if (waitTimeSlaveA.getCurrValue() + 2 < waitTimeSlaveB.getCurrValue() + 10) {
				// put job on slave A
				waitTimeSlaveA.setCurrValue(waitTimeSlaveA.getCurrValue() + 2);
			} else {
				// put job on slave B
				waitTimeSlaveB.setCurrValue(waitTimeSlaveB.getCurrValue() + 10);
			}

		}
	}
}// closes class
