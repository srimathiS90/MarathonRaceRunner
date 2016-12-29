package finalJavaProject;
/**
 * This Class is developed for invoking or starting the Application.
 */

/**
 * @author Srimathi
 *
 */


public class ApplicationInvoker {
	public static void main(String args[]){
		ApplicationUserInterface userInterface = new ApplicationUserInterface();
		try {
			userInterface.displayUserInterface();
		} catch (MarathonRaceException ex) {
			System.out.println("ClassName=" + ApplicationInvoker.class
					+ ",Method=main:::" + ",Message:::"
					+ ex.getMessage() + ex);
		}
	}


}
