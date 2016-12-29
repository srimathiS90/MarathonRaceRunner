package finalJavaProject;
/**
 *  This Interface exposes the methods for fetching data from different Data Sources 
 *  as per different implementations at run time.
 */
import java.util.List;



public interface DataSource {

	/**
	 *  This method will fetch Runners data from data sources.
	 *  
	 * @param  path String
	 * @throws MarathonRaceException
	 * @return List<Runner>
	 */
	public List<Runner> fetchRunners(String path)throws MarathonRaceException;
	
}
