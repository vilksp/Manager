package lt.management.oms.exceptions;

public class TaskNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException() {
		
	}
	
	@Override
	public String toString() {
		return "Task is not found";
	}

}
