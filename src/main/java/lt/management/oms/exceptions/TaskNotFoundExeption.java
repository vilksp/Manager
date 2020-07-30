package lt.management.oms.exceptions;

public class TaskNotFoundExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundExeption() {
		
	}
	
	@Override
	public String toString() {
		return "Task is not found";
	}

}
