package finalJavaProject;
/**
 * This Exception Class is meant for wrapping all Application Exceptions.
 */

public class MarathonRaceException extends Exception  {
	private static final long serialVersionUID = 3040542691053831208L;

    public MarathonRaceException(String message){
		super(message);
	}
	public MarathonRaceException(String message,Throwable ex){
		super(message, ex);
	}

}
