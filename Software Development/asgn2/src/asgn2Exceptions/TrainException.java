package asgn2Exceptions;

/**
 * A simple class for exceptions thrown by railway shunting and boarding
 * operations
 * 
 * @author Connor Livsey - n8510873
 */
@SuppressWarnings("serial")
public class TrainException extends Exception {

	/**
	 * Constructs a new TrainException object.
	 * 
	 * @param message
	 *            String: an informative message describing the cause of the
	 *            problem.
	 **/
	public TrainException(String message) {

		super("Train Exception: " + message);
	}

}