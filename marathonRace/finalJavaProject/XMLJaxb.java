package finalJavaProject;
/**
 * This Class is meant for converting XML to POJO.
 */
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



public class XMLJaxb
{
	private static Class<XMLJaxb> className = XMLJaxb.class;
	
    /**
	 * This method is for converting XML file to POJO.
	 * @throws MarathonRaceException
	 * @param file File
	 * @param objType Class<T>
	 */
    public static <T> T readObject(Class<T> objType, File file) throws MarathonRaceException
    {
        JAXBContext context;
        T obj = null;
        try
        {
            context = JAXBContext.newInstance(objType);
            Unmarshaller u = context.createUnmarshaller();
            obj = (T) u.unmarshal(file);
           
        }
        catch (JAXBException e)
        {
        	System.out.println("ClassName="+className+ ",Method=readObject:::"+",Message:::"+"Invalid XML File Format");
            throw new MarathonRaceException("ClassName="+className+ ",Method=readObject:::"+",Message:::"+"Invalid XML File Format");
        }
        return obj; 
    }
}
