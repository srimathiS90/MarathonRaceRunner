package finalJavaProject;
/**
 * This Class is developed to fetch Runners Data from a XML file.
 */


import java.io.File;
import java.util.List;


public class XMLFileDataSource implements DataSource{

	private Class<XMLFileDataSource> className = XMLFileDataSource.class;
	
	/**
	 *  This method will fetch Runners data from data sources.
	 *  
	 * @param  path String
	 * @throws MarathonRaceException
	 * @return List<Runner>
	 */
	@Override
	public List<Runner> fetchRunners(String path) throws MarathonRaceException{
		
		List<Runner> runnersList = null;
		try{
			if(path!=null && !path.isEmpty()){
				File file = new File(path);
				 if(!file.exists()){
				     throw new MarathonRaceException("File not found ::::"+file.getAbsolutePath());
				 }
				Runners runners = XMLJaxb.readObject(Runners.class, file);
				runnersList = runners.getRunnerslist();
			}
			else{
				throw new MarathonRaceException("ClassName="+className+ ",Method=fetchRunners:::"+",Message:::XML File Path either null or empty");	
			}
		}
		catch (Exception e) {
			   System.out.println("ClassName="+className+ ",Method=writeObject:::"+",Message:::"+e.getMessage()+ e);
	           throw new MarathonRaceException("ClassName="+className+ ",Method=fetchRunners:::"+",Message:::"+e.getMessage()+ e); 
		}
		return runnersList;
	}

}
