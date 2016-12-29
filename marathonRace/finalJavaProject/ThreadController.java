package finalJavaProject;
/**
 *  This class is used for starting the Marathon between different ThreadRunners
 */
import java.util.List;



public class ThreadController {
	private ThreadController() {
		super();
	}

	private Class<ThreadController> className = ThreadController.class;
	private static ThreadController instance = null;
	
	/**
	 *  This method will start all the Runners or Runner Threads .
	 *  
	 * @param runnersList List<Runner> 
	 * @throws MarathonRaceException
	 * @return boolean
	 */
	public boolean startThreadsExecution(List<Runner> runnerList) throws MarathonRaceException{
       boolean executionCompleted = false;
	try{
		if (runnerList != null && !runnerList.isEmpty()) {
			RaceState raceState = new RaceState();
			System.out.println("Get set...Go!");
			for (Runner runner : runnerList) {
				ThreadRunner thread = new ThreadRunner(runner.getName(),
						runner.getRunnersMoveIncrement(),
						runner.getRestPercentage());
				thread.setRaceState(raceState);
				raceState.getRunnersStatusMap().put(runner.getName(), Constants.RACE_STARTED);
				thread.start();
			}
			
			while(true){
				if(raceState.getRunnersStatusMap().containsValue(Constants.RACE_STARTED)){
					continue;
				}
				else {
					executionCompleted = true;
					break;
				}
			}
			
		 }
        }
        catch (Exception ex) {
        	System.out.println("ClassName=" + className
					+ ",Method=startThreadsExecution:::" + ",Message:::"
					+ ex.getMessage() + ex);
			throw new MarathonRaceException("ClassName=" + className
					+ ",Method=startThreadsExecution:::" + ",Message:::"
					+ ex.getMessage() + ex);
		}

        return executionCompleted;
	}
	
	/**
	 *  This method is used for getting the ThreadController instance .
	 *  
	 */
	
	public static ThreadController getInstance(){
		if(instance==null){
			instance = new ThreadController();
		}
		return instance;
	}


}
