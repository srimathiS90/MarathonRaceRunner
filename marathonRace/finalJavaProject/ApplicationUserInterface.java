package finalJavaProject;

/**
 *  This class is meant for providing a User friendly interface to the users of the Application , where the user can 
 *  choose the data sources for providing Runners related Data.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class ApplicationUserInterface {
	private Class<ApplicationUserInterface> className = ApplicationUserInterface.class;
	private static List<String> choiceValueList = null;
	private BufferedReader bufferRead = null;
	
	/**
	 *  This method will display the User friendly interface in the below mentioned format.
	 *  
	 *  Welcome to the Marathon Race Runner Program
	   
        Select your data source:
        1. Derby database
        2. XML file
        3. Text file
        4. Default two runners
        5. Exit
        
        Enter your choice:
        
        @throws MarathonRaceException
	 */
	
	public void displayUserInterface() throws MarathonRaceException {
		try {
			boolean exit = false;
			List<Runner> runnerslist = null;
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				runnerslist = null;
				System.out.println("Welcome to the Marathon Race Runner Program\n");
				System.out.println("Select your data source:");
				System.out.println("1. Derby database");
				System.out.println("2. XML file");
				System.out.println("3. Text file");
				System.out.println("4. Default two runners");
				System.out.println("5. Exit");
				System.out.println("Enter your choice: ");
				//Read the Choice from User
				String choice = bufferRead.readLine();
				Validations valid = new Validations();
				if (getAllUserChoiceEnumValues().contains(choice)) {
				    int ch = Integer.valueOf(choice).intValue();
					switch (ch) {
					case 1:
						runnerslist =DataSourceController.getInstance().invokeDataSource(Constants.DERBYDB_DATASOURCE, null);
						break;
					case 2:
						System.out.println("Enter XML file name: ");
						String xmlFileName= bufferRead.readLine();
						String xmlPath = getDataFilePath()+File.separator+xmlFileName;
						if(valid.checkValidFile(xmlPath,Constants.XMLFILE_EXTENTION))
						{
						runnerslist = DataSourceController.getInstance().invokeDataSource(Constants.XMLFILE_DATASOURCE, xmlPath);
						}
						else
						{
							System.out.println("Invalid File Name or File does not exists:"+xmlPath);
						}
						break;
					case 3:
						System.out.println("Enter TXT file name: ");
						String txtFileName= bufferRead.readLine();
						String txtPath = getDataFilePath()+File.separator+txtFileName;
						if(valid.checkValidFile(txtPath,Constants.TXTFILE_EXTENTION))
						{
						runnerslist = DataSourceController.getInstance().invokeDataSource(Constants.TEXTFILE_DATASOURCE, txtPath);
						}
						else
						{
							System.out.println("Invalid File Name or File does not exists:"+txtPath);
						}
						break;
					case 4:
						runnerslist = DataSourceController.getInstance().invokeDataSource(Constants.DEFAULT_DATASOURCE,null);
						break;
					case 5:
						System.out.println("Exiting Application . . .");
						exit = true;
					}
				} else {
					System.out.println("Invalid Option choosen::" + choice);
				}
				if(exit){
					break;
				}
				
				if (valid.validateRunnersList(runnerslist)) {
					startThreads(runnerslist);
				} else if(runnerslist==null || runnerslist.isEmpty()) {
					System.out
					.println("The Runners Data cannot be retrieved , please try Again.....");
				}
				else{
					System.out
							.println("The Data retrieved from the Data Source is not valid or properly presented to process further.");
				}
				System.out.println("Press Enter key to continue . . . ");
				//Input from User to continue
				bufferRead.readLine();
			}
		} catch (Exception ex) {
			System.out.println("ClassName=" + className
					+ ",Method=displayUserInterface:::" + ",Message:::"
					+ ex.getMessage() + ex);
			throw new MarathonRaceException("ClassName=" + className
					+ ",Method=displayUserInterface:::" + ",Message:::"
					+ ex.getMessage() + ex);
		}
		finally{
			if (bufferRead != null) {
				try {
					bufferRead.close();
				} catch (IOException ex) {
					System.out.println("ClassName=" + className
							+ ",Method=displayUserInterface:::" + ",Message:::"
							+ ex.getMessage() + ex);
					throw new MarathonRaceException("ClassName=" + className
							+ ",Method=displayUserInterface:::" + ",Message:::"
							+ ex.getMessage() + ex);
				}
			}
		}
	}

	/**
	 *  This method will give all the possible User Choices available for selecting data sources for providing data 
	 *  related to Runners.
	 *  
	 */
	private List<String> getAllUserChoiceEnumValues() {
		if(choiceValueList==null){
		choiceValueList = new ArrayList<String>();
		UserChoice[] values = UserChoice.values();
		for (UserChoice choice : values) {
			choiceValueList.add(choice.getValue() + "");
		}
		}
		return choiceValueList;
	}
	
	/**
	 *  This method will start all the Runners or Runner Threads .
	 *  
	 * @param runnersList List<Runner> 
	 * @throws MarathonRaceException
	 */
	private void startThreads(List<Runner> runnersList) throws MarathonRaceException{
		ThreadController.getInstance().startThreadsExecution(runnersList);
	}
	
	/**
	 *  This method will get the location path of the data files required for Runners related data. 
	 *  
	 */
	private static File getDataFilePath()
    {
        File basePath = new File("").getAbsoluteFile();
        if(basePath.getName().equals("bin"))
        {
            basePath = basePath.getParentFile();
        }
        File result = new File(basePath, Constants.DATAFILELOCATION);
        return result;
    }

}
