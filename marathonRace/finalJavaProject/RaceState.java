package finalJavaProject;

/**
 * This is used to track the Race state in between the Runner Threads. 
 */
import java.util.Hashtable;


public class RaceState {
	private boolean raceWon = false;
	private Hashtable<String,String>  runnersStatusMap = new Hashtable<String,String>();

	/**
	 * @return the raceWon
	 */
	public boolean isRaceWon() {
		return raceWon;
	}

	/**
	 * @param raceWon
	 *            the raceWon to set
	 */
	public synchronized void setRaceWon(boolean raceWon) {
		this.raceWon = raceWon;

	}

	/**
	 * This method would be called by Runner when it completes its Race.
	 */
	public synchronized boolean finished(String threadName) {

		boolean raceFinisher = false;
		if (!isRaceWon()) {
			System.out.println("The race is over! " + "The " + threadName
					+ " is the winner.");
			setRaceWon(true);
			raceFinisher = true;
		}
		runnersStatusMap.put(threadName, Constants.RACE_COMPLETED);
         return raceFinisher;
	}

	/**
	 * @return the runnersStatusMap
	 */
	public Hashtable<String, String> getRunnersStatusMap() {
		return runnersStatusMap;
	}

	/**
	 * @param runnersStatusMap the runnersStatusMap to set
	 */
	public void setRunnersStatusMap(Hashtable<String, String> runnersStatusMap) {
		this.runnersStatusMap = runnersStatusMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RaceState [raceWon=" + raceWon + "]";
	}

}
