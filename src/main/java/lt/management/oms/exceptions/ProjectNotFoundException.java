package lt.management.oms.exceptions;

public class ProjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectNotFoundException() {
	}
	
	@Override
	public String toString() {
		return "Project is not found";
	}

}
