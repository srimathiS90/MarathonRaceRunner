package finalJavaProject;

/**
 * This class is meant for accommodating multiple Runners.
 */

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Runners")

public class Runners {
	List<Runner> runnerslist ;

	/**
	 * @return the runnerslist
	 */
	public List<Runner> getRunnerslist() {
		return runnerslist;
	}

	/**
	 * @param runnerslist the runnerslist to set
	 */
	@XmlElement(name="Runner")
	public void setRunnerslist(List<Runner> runnerslist) {
		this.runnerslist = runnerslist;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Runners [runnerslist=" + runnerslist + "]";
	}

}
