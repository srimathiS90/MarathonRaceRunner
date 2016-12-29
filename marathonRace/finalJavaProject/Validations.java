package finalJavaProject;
/**
 *  This class is meant for validating user Inputs such as filename, file existence and Data source contents.
 */
import java.io.File;
import java.util.List;



public class Validations {
	/**
	 *  This method will check whether the filenames are valid or not and whether they exist in the mentioned location.
	 *  @param path String
	 *  @return boolean
	 */
	
	public boolean checkValidFile(String path,String extention)
	{
		if(path!=null && !path.isEmpty() && path.endsWith(extention))
		{
			File file =new File(path);
			if(file.exists())
			return true;
		}
		return false;
		
	}
	
	/**
	 *  This method will validate the RunnerList fetched from various data sources.
	 *  
	 * @param  List<Runner> runnerList
	 * @return boolean
	 */

	public boolean validateRunnersList(List<Runner> runnerList)
	{
		boolean status = true;
		if (runnerList != null && !runnerList.isEmpty()) {
			for (Runner runner : runnerList) {
					if (isValidRunner(runner)){
						continue;
					}
					else {
						status = false;
						break;
					}
			}
		}
		else {
			status = false;
		}
		return status;
		
	}
	
	/**
	 *  This method will validate the RunnerObject in the RunnerList
	 *  
	 * @param  Runner runner
	 */
	private static boolean isValidRunner(Runner runner){

        boolean status = false;
        if (runner!=null){
        	if (runner.getName() != null && !runner.getName().isEmpty()
					&& runner.getRestPercentage() != null
					&& !runner.getRestPercentage().isEmpty()
					&& runner.getRunnersMoveIncrement() != null
					&& !runner.getRunnersMoveIncrement().isEmpty()) {
                try{
                	
                        Integer.valueOf(runner.getRunnersMoveIncrement());
                        Integer.valueOf(runner.getRestPercentage());
                        status = true;
                }
                catch (NumberFormatException ex) {
                        status = false;
                }
        	}
        }
        return status;
}
}

