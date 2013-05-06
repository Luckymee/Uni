package asgn1Solution;

import asgn1Question.IApplicant;

/**
 * Applicant Class Implementation
 * 
 * @author Connor Livsey
 * @author n8510873
 * @version 1.0
 */
public class Applicant implements IApplicant {

    private static final double LOWER_BOUND = 0.0;
    private static final double UPPER_BOUND = 1.0;
    private int id;
    private double qualityScore;

    /**
     * Constructor for Applicant.
     * 
     * @param id
     *            int
     * @param qualityScore
     *            double
     * @throws IllegalArgumentException
     */
    public Applicant(int id, double qualityScore)
	    throws IllegalArgumentException {
	setId(id);
	setQualityScore(qualityScore);
    }

    /**
     * Method getId.
     * 
     * @return int
     * @see asgn1Question.IApplicant#getId()
     */
    @Override
    public int getId() {
	return id;
    }

    /**
     * Method setId.
     * 
     * @param appId
     *            int
     * @see asgn1Question.IApplicant#setId(int)
     */
    @Override
    public void setId(int appId) {
	id = appId;
    }

    /**
     * Method getQualityScore.
     * 
     * @return double
     * @see asgn1Question.IApplicant#getQualityScore()
     */
    @Override
    public double getQualityScore() {
	return qualityScore;
    }

    /**
     * Method setQualityScore.
     * 
     * @param qualityScore
     *            double
     * @throws IllegalArgumentException
     * @see asgn1Question.IApplicant#setQualityScore(double)
     */
    @Override
    public void setQualityScore(double qualityScore)
	    throws IllegalArgumentException {
	if ((qualityScore >= LOWER_BOUND) && (qualityScore <= UPPER_BOUND)) {
	    this.qualityScore = qualityScore;
	} else {
	    throw new IllegalArgumentException(
		    "Invalid input: Quality Score must be between 0.0 and 1.0");
	}
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
	String decimalFormat = Double.toString(this.qualityScore);
	int length = decimalFormat.length();
	if (length > 5) { // Only cut long numbers
	    decimalFormat = decimalFormat.substring(0, 5);
	}
	// Formatted string
	return String.format("Applicant [ID:%s QS:%s]", id, decimalFormat);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

}