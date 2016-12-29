package finalJavaProject;
/**
 * This Class is for fetching data from Derby DB Data Source.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DerbyDBDataSource implements DataSource {
	
	/**
	 *  This method will connect to the Derby Data Source.
	 *  
	 
	 * @throws MarathonRaceException
	 */
	
	private Connection connect() throws MarathonRaceException
    {
        Connection connection = null;
        try
        {
            String dbDirectory = Constants.DBSOURCEPATH;
            System.setProperty(Constants.DERBY_SYSTEM_HOME, dbDirectory);
            String url = Constants.MARATHON_DB_URL;
            String user = Constants.MARATHON_DB_USERNAME;
            String password = Constants.MARATHON_DB_PASSWORD;
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException ex)
        {
          System.out.println("Exception occured :::::"+ex.getMessage());
          throw new MarathonRaceException("Error getting database Connection::: " + ex.getMessage());
        }
        return connection;
    }

	/**
	 *  This method will fetch Runners data from DB data source.
	 *  
	 * @param  path String
	 * @throws MarathonRaceException
	 * @return List<Runner>
	 */
	@Override
	public List<Runner> fetchRunners(String path) throws MarathonRaceException {
		ArrayList<Runner> runners = new ArrayList<Runner>();
		Connection connection = connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
        {
            String query = "SELECT RunnersName, RunnersSpeed, RestPrecentage "
                         + "FROM RunnersInfo";
             ps = connection.prepareStatement(query);
             rs = ps.executeQuery();

            while(rs.next())
            {
                String name = rs.getString("RunnersName");
                int speed = rs.getInt("RunnersSpeed");
                int rest = rs.getInt("RestPrecentage");

                Runner p = new Runner(name, Integer.toString(speed), Integer.toString(rest));
                runners.add(p);
            }
        }
        catch(SQLException sqlEx)
        {
        	System.out.println("Exception occured :::::"+sqlEx.getMessage());
            throw new MarathonRaceException("Exception occured :::::"+sqlEx.getMessage(), sqlEx);
        }
        finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
        	}
        	catch (SQLException sqlEx) {
        		System.out.println("Exception occured :::::"+sqlEx.getMessage());
        		throw new MarathonRaceException("Exception occured :::::"+sqlEx.getMessage(), sqlEx);
			}
        }
        return runners;
	}

}
