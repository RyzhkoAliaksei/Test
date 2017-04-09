package grsu.epam.utilitycompany.app.service.exception;

public class DateIsEmptyException  extends Exception {

	
	private static final long serialVersionUID = 1L;
	public  DateIsEmptyException() {
	}
	/**
	 * Constructor that accepts a message
	 * 
	 * @param message
	 */
	public  DateIsEmptyException(String message) {
		super(message);
	}
}
