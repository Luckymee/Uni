package asgn1Question;

/**
 * Abstract representation of the Applicant for the Hiring Problem.
 * 
 * <a href="http://en.wikipedia.org/wiki/Secretary_problem">Wikipedia problem
 * description</a>
 * 
 * You need to create a class called Applicant which implements this interface.
 * Your class will be called by the provided GUI to actually run the game.
 * 
 * Special Note: This comment would not normally appear in a interface like
 * this, but we require that you implement a specific constructor for the
 * Applicant class:
 * 
 * --- copy javadoc here to applicant class; edit tags to make them work ---<br>
 * Creates a new Applicant with the specified id and quality score. <br>
 * Throws an IllegalArgumentException if an out of bounds score is provided.<br>
 * <br>
 * param id - int Applicant id number <br>
 * param qualityScore - double quality score in [0.0,1.0] <br>
 * throws IllegalArgumentException if qualityScore outside this range<br>
 * <br>
 * public Applicant(int id, double qualityScore) <br>
 * 
 */

public interface IApplicant {

    /**
     * Simple getter for the applicant ID
     * 
     * @return int containing the applicant ID
     * 
     */
    public int getId();

    /**
     * Simple setter for the applicant ID
     * 
     * @param int containing the applicant ID
     * 
     */
    public void setId(int appId);

    /**
     * Getter for applicant's existing quality score
     * 
     * @return double containing quality score in the interval [0.0,1.0]
     */
    public double getQualityScore();

    /**
     * Accept the current applicant.
     * 
     * @param double qualityScore for the applicant in [0.0,1.0]
     * @throws IllegalArgumentException
     *             if score is not in the range [0.0,1.0]
     */
    public void setQualityScore(double qualityScore)
	    throws IllegalArgumentException;

}
