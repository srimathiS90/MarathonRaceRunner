/**
 *  This POJO Classifies the Runner specific attributes and behavior.
 */
package finalJavaProject;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="Runner")
public class Runner {

	private String name;
	private String runnersMoveIncrement;
	private String restPercentage;
	
	public Runner() {
		super();
	}
	
	/**
	 * @param name
	 * @param runnersMoveIncrement
	 * @param restPercentage
	 */
	public Runner(String name, String runnersMoveIncrement,
			String restPercentage) {
		super();
		this.name = name;
		this.runnersMoveIncrement = runnersMoveIncrement;
		this.restPercentage = restPercentage;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	@XmlAttribute(name="Name")
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the runnersMoveIncrement
	 */
	public String getRunnersMoveIncrement() {
		return runnersMoveIncrement;
	}
	/**
	 * @param runnersMoveIncrement the runnersMoveIncrement to set
	 */
	@XmlElement(name="RunnersMoveIncrement")
	public void setRunnersMoveIncrement(String runnersMoveIncrement) {
		this.runnersMoveIncrement = runnersMoveIncrement;
	}
	/**
	 * @return the restPercentage
	 */
	public String getRestPercentage() {
		return restPercentage;
	}
	/**
	 * @param restPercentage the restPercentage to set
	 */
	@XmlElement(name="RestPercentage")
	public void setRestPercentage(String restPercentage) {
		this.restPercentage = restPercentage;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Runner other = (Runner) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Runner [name=" + name + ", runnersMoveIncrement="
				+ runnersMoveIncrement + ", restPercentage=" + restPercentage
				+ "]";
	}
}
