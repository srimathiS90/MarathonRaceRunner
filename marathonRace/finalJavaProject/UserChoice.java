package finalJavaProject;
/**
 * This enum is developed for defining the constants related to User choices 
 * available for data source selection.
 */

public enum UserChoice
{
	DERBYDBD(1), XMLFILE(2), TEXTFILE(3), 
	DEFAULTDATA(4),EXIT(5);

  private int code;

  private UserChoice(int c) { this.code = c; }

  public int getValue()
  {
    return this.code;
  }
}
