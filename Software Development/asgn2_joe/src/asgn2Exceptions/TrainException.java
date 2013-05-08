package asgn2Exceptions;

@SuppressWarnings("serial")
public class TrainException extends Exception {

	public TrainException(String message) {
		super("Train Exception: " + message);
	}

}
