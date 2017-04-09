package grsu.epam.utilitycompany.app.service.exception;

public class IncorrectPeriodTimeException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IncorrectPeriodTimeException() {
	}
	/**
	 * Constructor that accepts a message
	 * 
	 * @param message
	 */
	public IncorrectPeriodTimeException(String message) {
		super(message);
	}
}
