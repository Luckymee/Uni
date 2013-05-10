package asgn2Exceptions;

/**
 * Class representing any exception in the Train Simulation. Throw a new
 * instance of this class on any invalid condition.
 * 
 * @author Connor Livsey - n8510873
 */
@SuppressWarnings("serial")
// We don't care about binary i/o here
public class TrainException extends Exception {

    /**
     * Constructor for TrainExceptions.
     * 
     * @param message
     *            An informative message about the invalid condition.
     */
    public TrainException(String message) {

	super("TrainException: " + message);
    }

}
