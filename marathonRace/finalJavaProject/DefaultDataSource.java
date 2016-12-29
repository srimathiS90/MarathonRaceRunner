package finalJavaProject;
/**
 * This Class is for fetching default data.
 */

import java.util.ArrayList;
import java.util.List;



public class DefaultDataSource implements DataSource {

	/**
	 *  This method will fetch Runners data from data sources.
	 *  
	 * @param  path String
	 * @throws MarathonRaceException
	 * @return List<Runner>
	 */
	@Override
	public List<Runner> fetchRunners(String path) throws MarathonRaceException {
		
		List<Runner> runnersList = new ArrayList<Runner>();
		
		Runner tortoise = new Runner();
		tortoise.setName("Tortoise");
		tortoise.setRunnersMoveIncrement("10");
		tortoise.setRestPercentage("0");
		
		Runner hare = new Runner();
		hare.setName("Hare");
		hare.setRunnersMoveIncrement("100");
		hare.setRestPercentage("90");
		
		runnersList.add(tortoise);
		runnersList.add(hare);
		
		return runnersList;
	}

}

