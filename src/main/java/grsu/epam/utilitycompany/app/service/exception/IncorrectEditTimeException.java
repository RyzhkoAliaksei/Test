package grsu.epam.utilitycompany.app.service.exception;

public class IncorrectEditTimeException extends Exception {

	
	private static final long serialVersionUID = 1L;
	public IncorrectEditTimeException() {
	}
	/**
	 * Constructor that accepts a message
	 * 
	 * @param message
	 */
	public IncorrectEditTimeException(String message) {
		super(message);
	}
}
