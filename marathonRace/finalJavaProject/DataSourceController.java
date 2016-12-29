package finalJavaProject;
/**
 *  This Class is meant for choosing the Data Source and fetching the data from same 
 *  depending on the option chosen by the User.
 */
import java.util.List;

//Redirecting the control to respective datasource
public class DataSourceController {
	
	private static DataSourceController instance = null;
	
	private DataSourceController() {
		super();
	}

	/**
	 *  This method will invoke the respective data sources implementations .
	 *  
	 * @param  dataSource String
	 * @param  path String
	 * @throws MarathonRaceException
	 * @return List<Runner>
	 */
	public  List<Runner> invokeDataSource(String dataSource,String path) throws MarathonRaceException{
		
		DataSource dtsource = null;
		List<Runner> runnersList = null;
		if(Constants.XMLFILE_DATASOURCE.equalsIgnoreCase(dataSource)){
			dtsource = new XMLFileDataSource();
		}
		else if(Constants.TEXTFILE_DATASOURCE.equalsIgnoreCase(dataSource)){
			dtsource = new TextFileDataSource();
		}
		else if(Constants.DEFAULT_DATASOURCE.equalsIgnoreCase(dataSource)){
			dtsource = new DefaultDataSource();
		}
		else if(Constants.DERBYDB_DATASOURCE.equalsIgnoreCase(dataSource)){
			dtsource = new DerbyDBDataSource();
		}
		
		if(dtsource!=null){
			runnersList = dtsource.fetchRunners(path);
		}
		return runnersList;
	}
	
	/**
	 *  This method is used for getting the DataSourceController instance .
	 *  
	 */
	public static DataSourceController getInstance(){
		
		if(instance==null){
			instance = new DataSourceController();
		}
		return instance;
	}

}
