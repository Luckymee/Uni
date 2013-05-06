package asgn1Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import asgn1Question.HiringException;
import asgn1Question.IHiringGame;

/**
 * This class is used to start the Hiring game.
 * 
 * @author Connor Livsey
 * @author n8510873
 * @version 1.0
 */
public class HiringGame implements IHiringGame {

    private ArrayList<Applicant> applicantList;
    private final ArrayList<Applicant> duplicateList = new ArrayList<Applicant>();
    private Applicant currentApplicant;
    private Applicant bestApplicant;
    private boolean applicantAccept;
    private double bestQualityScore;
    private double[] qualityScoreArray;

    /**
     * Method newGame.
     * 
     * @param maxApplicants
     *            int
     * @param random
     *            Random
     * @throws HiringException
     * @see asgn1Question.IHiringGame#newGame(int, Random)
     */
    @Override
    public void newGame(int maxApplicants, Random random)
	    throws HiringException {

	// Reset lists if exist at start of game.
	if (bestApplicant != null) {
	    applicantList.clear();
	    duplicateList.clear();
	}

	// Zero or less Applicants
	if (maxApplicants <= 0) {
	    throw new HiringException(
		    "Invalid number of applicants : >1 Required");
	}

	if (maxApplicants <= 0 && random == null) {
	    throw new HiringException("Game Requires : RNG SEED & Applicants");
	}

	// New Game, not accepted
	this.applicantAccept = false;

	applicantList = new ArrayList<Applicant>(maxApplicants);

	// Construct applicant list
	for (int i = 0; i < maxApplicants; ++i) {

	    double applicantScore = random.nextDouble();
	    Applicant applicantNew = new Applicant(i, applicantScore);
	    applicantList.add(applicantNew);

	    if (this.applicantList.get(i).getQualityScore() < 0
		    || this.applicantList.get(i).getQualityScore() > 1) {
		throw new HiringException("Invalid Quality Score");
	    }
	}
	this.duplicateList.addAll(applicantList);
    }

    /**
     * Method getNextApplicant.
     * 
     * @throws HiringException
     * @see asgn1Question.IHiringGame#getNextApplicant()
     */
    @SuppressWarnings("unused")
    @Override
    public Applicant getNextApplicant() throws HiringException {

	if (this.isAccepted()) { // Get after game ended
	    throw new HiringException("Applicant : Already Selected");
	}

	if (this.applicantList == null) { // No applicants in list
	    throw new HiringException("No applicants : Null");
	}

	if (this.applicantList.size() <= 0) { // Invalid applicant amount
	    throw new HiringException(
		    "Invalid number of applicants : >1 Required");
	}
	// Randomise list
	Collections.shuffle(this.applicantList);

	for (int i = 0; i < this.applicantList.size(); ++i) {
	    currentApplicant = this.applicantList.get(i);

	    if (this.currentApplicant == null) { // No applicant
		throw new HiringException("No applicants : Null");
	    }
	    if (this.applicantList.size() > 1) {
		this.applicantList.remove(i); // Remove unchosen applicant from
					      // list
	    } else if (this.applicantList.size() == 1) {
		this.applicantAccept = true; // Auto accept last applicant
	    }
	    return currentApplicant;
	}
	return currentApplicant;
    }

    /**
     * Method isAccepted.
     * 
     * @return boolean
     * @throws HiringException
     * @see asgn1Question.IHiringGame#isAccepted()
     */
    @Override
    public boolean isAccepted() throws HiringException {

	if (this.applicantList == null) { // No Applicants
	    throw new HiringException("No Applicants.");
	}
	return this.applicantAccept;
    }

    /**
     * Method acceptApplicant.
     * 
     * @throws HiringException
     * @see asgn1Question.IHiringGame#acceptApplicant()
     */
    @Override
    public void acceptApplicant() throws HiringException {

	if (this.applicantList.size() <= 0) { // Game not Started
	    throw new HiringException("Game not started : No Applicants");
	}

	if (this.currentApplicant == null) { // No Applicants
	    throw new HiringException("No applicant selected");
	}

	this.applicantAccept = true;

    }

    /**
     * Method isBestApplicant.
     * 
     * @return boolean
     * @throws HiringException
     * @see asgn1Question.IHiringGame#isBestApplicant()
     */
    @Override
    public boolean isBestApplicant() throws HiringException {

	if (!this.applicantAccept) { // Check for non-accept case
	    throw new HiringException("No applicant accepted");
	}

	if (this.applicantList.size() <= 0) { // Game should not have started
	    throw new HiringException("Game not started : No applicant");
	}

	if (this.currentApplicant == null) { // No applicants
	    throw new HiringException("No applicant : null");
	}

	// Check all quality scores
	qualityScoreArray = new double[this.duplicateList.size()];
	for (int i = 0; i < this.duplicateList.size(); ++i) {
	    qualityScoreArray[i] = this.duplicateList.get(i).getQualityScore();
	}

	// Sort quality score to locate best.
	Arrays.sort(qualityScoreArray);
	this.bestQualityScore = qualityScoreArray[qualityScoreArray.length - 1];

	// Compare best quality score with each applicant
	for (int i = 0; i < this.duplicateList.size(); ++i) {
	    // Potential rounding errors : Float Comparison
	    if (this.duplicateList.get(i).getQualityScore() == this.bestQualityScore) {
		this.bestApplicant = this.duplicateList.get(i);

	    }
	}

	if (this.bestApplicant == null) {
	    throw new HiringException("No best applicant");
	}

	if (this.bestApplicant == this.currentApplicant) {
	    return true; // Best Applicant
	} else {
	    return false; // Normal Applicant
	}

    }

    /**
     * Method getBestApplicant.
     * 
     * @return Applicant
     * @throws HiringException
     * @see asgn1Question.IHiringGame#getBestApplicant()
     */
    @Override
    public Applicant getBestApplicant() throws HiringException {

	if (this.bestApplicant == null) { // No applicants
	    throw new HiringException("No best applicant");
	}

	if (this.applicantList.size() <= 0) { // Game not started
	    throw new HiringException("Game not started : No applicant");
	}

	return this.bestApplicant;
    }

}