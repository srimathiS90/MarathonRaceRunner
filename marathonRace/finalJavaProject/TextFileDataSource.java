package finalJavaProject;
/**
 * This Class is for fetching data from Text file Data Source.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class TextFileDataSource implements DataSource {

	private Class<TextFileDataSource> className = TextFileDataSource.class;
	
	/**
	 *  This method will fetch Runners data from data sources.
	 *  
	 * @param  path String
	 * @throws MarathonRaceException
	 * @return List<Runner>
	 */
	@Override
	public List<Runner> fetchRunners(String path) throws MarathonRaceException {
		
		BufferedReader br = null;
		String line = "";
		String txtFileDelimiter = Constants.TXTFILE_DELIMITER;
		List<Runner> runnersList = new ArrayList<Runner>();
		
		try {
			if(path==null || path.isEmpty()){
				throw new MarathonRaceException("ClassName="+className+ ",Method=fetchRunners:::"+",Message:::XML File Path either null or empty");
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		
			while ((line = br.readLine()) != null) {
			    // use space as separator
				String[] columns = line.split(txtFileDelimiter);
				Runner runner = null;
				if(columns!=null && columns.length>0){
					runner = new Runner(columns[0], columns[1], columns[2]);
					runnersList.add(runner);
				}
			}
	 
		} catch (FileNotFoundException ex) {
			System.out.println("ClassName=" + className
					+ ",Method=fetchRunners:::" + ",Message:::"
					+ ex.getMessage() + ex+",file ="+path);
			throw new MarathonRaceException("ClassName=" + className
					+ ",Method=fetchRunners:::" + ",Message:::"
					+ ex.getMessage() + ex+",file="+path);
		} catch (IOException ex) {
			System.out.println("ClassName=" + className
					+ ",Method=fetchRunners:::" + ",Message:::"
					+ ex.getMessage() + ex);
			throw new MarathonRaceException("ClassName=" + className
					+ ",Method=fetchRunners:::" + ",Message:::"
					+ ex.getMessage() + ex);
		} catch(Exception ex){
			System.out.println("ClassName=" + className
					+ ",Method=fetchRunners:::" + ",Message:::"
					+ ex.getMessage() + ex);
			throw new MarathonRaceException("ClassName=" + className
					+ ",Method=fetchRunners:::" + ",Message:::"
					+ ex.getMessage() + ex);
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ex) {
					System.out.println("ClassName=" + className
							+ ",Method=fetchRunners:::" + ",Message:::"
							+ ex.getMessage() + ex);
					throw new MarathonRaceException("ClassName=" + className
							+ ",Method=fetchRunners:::" + ",Message:::"
							+ ex.getMessage() + ex);
				}
			}
		}
		return runnersList;
	}

}
