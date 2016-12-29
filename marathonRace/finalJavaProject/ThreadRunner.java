package finalJavaProject;
/**
 *  This Class is the implementation of ThreadRunner.
 */
import java.util.Random;



public class ThreadRunner extends Thread {

	private String name;
	private String runnersMoveIncrement;
	private String restPercentage;
	private int distanceCovered;
	private boolean stopWork;
	private RaceState raceState;

	public ThreadRunner() {
		super();
	}

	/**
	 * @param name
	 * @param runnersMoveIncrement
	 * @param restPercentage
	 */
	public ThreadRunner(String name, String runnersMoveIncrement,
			String restPercentage) {
		super();
		this.name = name;
		this.runnersMoveIncrement = runnersMoveIncrement;
		this.restPercentage = restPercentage;
	}

	@Override
	public void run() {
		try {
			
			Random random = new Random();
			int minValue = Constants.THREADRUNNER_RESTPERCENTAGE_MINVALUE;
			int maxValue = Constants.THREADRUNNER_RESTPERCENTAGE_MAXVALUE;
			Thread.currentThread().setName(this.name);
            boolean isRaceFinisher = false;
			while (distanceCovered < Constants.MARATHON_RACE_DISTANCE
					&& !isStopWork()) {
				Thread.sleep(Constants.THREADRUNNER_SLEEP_TIME_MILLISEC);
				if (getRaceState().isRaceWon()) {
					setStopWork(true);
					break;
				} else {
					int randomGenerated = random.nextInt(maxValue - minValue
							+ 1)
							+ minValue;
					if (randomGenerated <= Integer.valueOf(restPercentage)
							.intValue()) {
						continue;
					} else {
						distanceCovered = distanceCovered
								+ Integer.valueOf(runnersMoveIncrement)
										.intValue();
						System.out.println(Thread.currentThread().getName()
								+ " : " + distanceCovered);
						if (distanceCovered == Constants.MARATHON_RACE_DISTANCE
								|| distanceCovered > Constants.MARATHON_RACE_DISTANCE) {
							System.out.println(Thread.currentThread().getName()
									+ " : " + "I finished!");
							isRaceFinisher = getRaceState().finished(Thread.currentThread().getName());
						}
					}
				}
			}
			if (getRaceState().isRaceWon() && !isStopWork() && !isRaceFinisher) {
				setStopWork(true);
			}
		} catch (InterruptedException e) {
			System.out.println("Exception Occured ::::" + e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Exception Occured ::::" + e.getMessage());
		}
	}


	/**
	 * @return the runnersMoveIncrement
	 */
	public String getRunnersMoveIncrement() {
		return runnersMoveIncrement;
	}

	/**
	 * @param runnersMoveIncrement
	 *            the runnersMoveIncrement to set
	 */
	public void setRunnersMoveIncrement(String runnersMoveIncrement) {
		this.runnersMoveIncrement = runnersMoveIncrement;
	}

	/**
	 * @return the restPercentage
	 */
	public String getRestPercentage() {
		return restPercentage;
	}

	/**
	 * @param restPercentage
	 *            the restPercentage to set
	 */
	public void setRestPercentage(String restPercentage) {
		this.restPercentage = restPercentage;
	}

	/**
	 * @return the stopWork
	 */
	private boolean isStopWork() {
		return stopWork;
	}

	/**
	 * @param stopWork
	 *            the stopWork to set
	 */
	private void setStopWork(boolean stopWork) {
		System.out.println(Thread.currentThread().getName() + " : "
				+ "You beat me fair and square.");
		this.stopWork = stopWork;
		getRaceState().getRunnersStatusMap().put(Thread.currentThread().getName(), Constants.RACE_COMPLETED);
	}

	/**
	 * @return the raceState
	 */
	public RaceState getRaceState() {
		return raceState;
	}

	/**
	 * @param raceState the raceState to set
	 */
	public void setRaceState(RaceState raceState) {
		this.raceState = raceState;
	}

}

