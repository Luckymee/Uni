package asgn1Solution;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn1Question.HiringException;
import asgn1Question.IApplicant;

/**
 * The class <code>HiringGameTest</code> contains tests for the class
 * <code>{@link HiringGame}</code>.
 * 
 * 
 * @author Connor livsey
 * @author n8510873
 * @version $Revision: 1.0 $
 */
public class HiringGameTest {

    private final int VALID_SEED = 100;
    private final int DIFF_VALID_SEED = 50;
    private final int VALID_MAX_APPLICANTS = 5;
    private final Random randomValidSeed = new Random(VALID_SEED);
    private final Random diffRandomValidSeed = new Random(DIFF_VALID_SEED);

    /**
     * Simplify Java println();
     * 
     * @param line
     *            A valid String for printing.
     */
    private static void println(String line) {
	System.out.println(line);
    }

    /**
     * Run the testHiringGame() constructor test.
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testHiringGame() throws Exception {
	HiringGame result = new HiringGame();
	assertNotNull(result);
    }

    /**
     * Run the void testValidIsBestApplicantBeforeAcceptApplicant() method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testValidIsBestApplicantBeforeAcceptApplicant()
	    throws HiringException {
	HiringGame fixture = new HiringGame();

	try {
	    fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);
	    fixture.getNextApplicant();
	    fixture.isBestApplicant();
	    fail("Failed to capture Exception");
	} catch (HiringException expected) {
	    println("Exception: Called isBestApplicant before newGame");
	}
    }

    /**
     * Run the void testNewGameObject method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testNewGameObject() throws Exception {
	HiringGame fixture = new HiringGame();

	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	assertNotNull(fixture);
    }

    /**
     * Run the void testNewGameZeroMaxApplicant() method test.
     * 
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testNewGameZeroMaxApplicant() throws HiringException {
	HiringGame fixture = new HiringGame();
	int maxApplicants = 0;
	try {

	    fixture.newGame(maxApplicants, randomValidSeed);
	    fail("Didn't Catch exception");
	} catch (HiringException expected) {
	    println("Exception: Invalid number of applicants. One or more applicants required.");
	}
    }

    /**
     * Run the void testNewGameNegativeMaxApplicant() method test.
     * 
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testNewGameNegativeMaxApplicant() throws HiringException {
	HiringGame fixture = new HiringGame();
	int maxApplicants = -1;
	try {

	    fixture.newGame(maxApplicants, randomValidSeed);
	    fail("Didn't Catch exception");
	} catch (HiringException expected) {
	    println("Exception: Invalid number of applicants. One or more applicants required.");
	}
    }

    /**
     * Run the void testNewGameInvalidRandom() method test.
     * 
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testNewGameInvalidRandom() throws HiringException {
	HiringGame fixture = new HiringGame();
	Random invalidRandom = null;
	try {
	    fixture.newGame(VALID_MAX_APPLICANTS, invalidRandom);
	    fail("Didn't Catch exception");
	} catch (NullPointerException | HiringException expected) {
	    println("Exception: Invalid Random");
	}
    }

    /**
     * Run the Applicant testGetNextApplicantBeforeNewGame() method test.
     * 
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testGetNextApplicantBeforeNewGame() throws HiringException {
	HiringGame fixture = new HiringGame();
	try {
	    fixture.getNextApplicant();
	    fail("Didn't Catch exception");
	} catch (HiringException expected) {
	    println("Exception: Called getNextApplicant before newGame");
	}
    }

    /**
     * Run the testTooManyGetNextApplicantCalls() method test.
     * 
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testTooManyGetNextApplicantCalls() throws HiringException {
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);
	ArrayList<Applicant> applicantArray = new ArrayList<Applicant>();
	try {
	    for (int i = 0; i < (VALID_MAX_APPLICANTS + 1); ++i) {
		applicantArray.add(fixture.getNextApplicant());
	    }
	} catch (HiringException expected) {
	    println("Exception: Too many getNextApplicant calls");
	}
    }

    /**
     * Run the void testAcceptApplicantBeforeNewGame() method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testAcceptApplicantBeforeNewGame() throws HiringException {
	HiringGame fixture = new HiringGame();
	try {
	    fixture.acceptApplicant();
	    fail("Didn't Catch exception");
	} catch (NullPointerException | HiringException expected) {
	    println("Exception: Called acceptApplicant before newGame");
	}
    }

    /**
     * Run the void testGetNextApplicantAfterAcceptApplicant() method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testGetNextApplicantAfterAcceptApplicant()
	    throws HiringException {
	HiringGame fixture = new HiringGame();
	try {
	    fixture.getNextApplicant();
	    fixture.acceptApplicant();
	    fixture.getNextApplicant();
	    fail("Didn't Catch exception");
	} catch (HiringException expected) {
	    println("Exception: Called getNextApplicant before newGame");
	}
    }

    /**
     * Run the void testSuccessfulAcceptofBestApplicant () method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testSuccessfulAcceptofBestApplicant() throws HiringException {
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();

	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	fixture.acceptApplicant();
	fixture.isBestApplicant();

	IApplicant bestApplicant = fixture.getBestApplicant();
	if (applicantList.get(VALID_MAX_APPLICANTS - 1) == bestApplicant) {
	    assertTrue(fixture.isBestApplicant());
	} else {
	    assertFalse(fixture.isBestApplicant());
	}
    }

    /**
     * Run the void testBestApplicantQualityScoreIsUnique () method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testBestApplicantQualityScoreIsUnique() throws HiringException {
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();

	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	fixture.acceptApplicant();
	fixture.isBestApplicant();

	IApplicant bestApplicant = fixture.getBestApplicant();
	// Remove to assure no duplicate bestApplicants occur.
	applicantList.remove(fixture.getBestApplicant());
	for (int i = 0; i < (VALID_MAX_APPLICANTS - 1); ++i) {
	    assertTrue(bestApplicant.getQualityScore() > applicantList.get(i)
		    .getQualityScore());
	}
    }

    /**
     * Run the void testFailureToAcceptBestApplicant () method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testFailureToAcceptBestApplicant() throws HiringException {
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();

	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	fixture.acceptApplicant();
	fixture.isBestApplicant();

	IApplicant bestApplicant = fixture.getBestApplicant();

	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    // Accept all but bestApplicant
	    if (applicantList.get(i) != bestApplicant) {
		assertTrue(fixture.isAccepted());
	    }
	}
    }

    /**
     * Run the void testRejectAllApplicant () method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testRejectAllApplicant() throws HiringException {
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();

	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	IApplicant finalApplicant = applicantList.get(VALID_MAX_APPLICANTS - 1);
	fixture.acceptApplicant();
	fixture.isBestApplicant();
	IApplicant bestApplicant = fixture.getBestApplicant();

	if (fixture.isAccepted() && finalApplicant == bestApplicant) {
	    // finalApplicant is both last, and bestApplicant
	    assertTrue(fixture.isAccepted() && fixture.isBestApplicant());
	} else if (fixture.isAccepted() && finalApplicant != bestApplicant) {
	    // finalApplicant is last, but not bestApplicant
	    assertTrue(fixture.isAccepted() && !fixture.isBestApplicant());
	}
    }

    /**
     * Run the void testAllApplicantDetailsUnique() method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testAllApplicantDetailsUnique() throws HiringException {

	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();

	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	// Test the uniqueness of applicantList
	int size = applicantList.size();
	for (int i = 0; i < size - 1; ++i) {
	    for (int j = i + 1; j < size; ++j) {
		// no need for if ( i == j ) here
		if (!applicantList.get(j).equals(applicantList.get(i))) {
		    assertTrue("Each applicant details are unique.", true);
		    continue;
		} else {
		    assertFalse("Duplicate applicants exist", false);
		}
	    }
	}
    }

    /**
     * Run the void testRejectAllApplicant () method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testRandomnessOfGetNextApplicant() throws HiringException {
	// Test 1
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);
	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();

	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	// Test 2 - Equal Applicants : Different Order && Quality Score
	HiringGame fixtureComparison = new HiringGame();
	fixtureComparison.newGame(VALID_MAX_APPLICANTS, diffRandomValidSeed);

	ArrayList<Applicant> applicantListComparison = new ArrayList<Applicant>();
	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantListComparison.add(fixtureComparison.getNextApplicant());
	}

	assertTrue(applicantList != applicantListComparison);
    }

    /**
     * Run the testGetBestApplicantBeforeNewGame() method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testGetBestApplicantBeforeNewGame() throws HiringException {
	HiringGame fixture = new HiringGame();
	try {
	    fixture.getBestApplicant();
	    fail("Didn't Catch exception");
	} catch (HiringException expected) {
	    println("Exception: Called getBestApplicant before newGame");
	}
    }

    /**
     * Run the testGetBestApplicantBeforeNewGame() method test.
     * 
     * 
     * @throws HiringException
     * @throws Exception
     */
    @Test
    public void testIsBestApplicantBeforeNewGame() throws HiringException {
	HiringGame fixture = new HiringGame();
	try {
	    fixture.isBestApplicant();
	    fail("Didn't Catch exception");
	} catch (HiringException expected) {
	    println("Exception: Called isBestApplicant before newGame");
	}
    }

    /**
     * Run the void testIsAcceptedResetsAfterNewGame() method test.
     * 
     * 
     * @throws HiringException
     */
    @Test
    public void testIsAcceptedResetsAfterNewGame() throws HiringException {
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();
	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	fixture.acceptApplicant();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);
	assertFalse(fixture.isAccepted());
    }

    /**
     * Run the void testGetNextApplicantResetsAfterNewGame() method test.
     * 
     * 
     * @throws HiringException
     */
    @Test
    public void testGetNextApplicantResetsAfterNewGame() throws HiringException {
	HiringGame fixture = new HiringGame();
	fixture.newGame(VALID_MAX_APPLICANTS, randomValidSeed);

	ArrayList<Applicant> applicantList = new ArrayList<Applicant>();
	for (int i = 0; i < VALID_MAX_APPLICANTS; ++i) {
	    applicantList.add(fixture.getNextApplicant());
	}

	int applicantListSize = applicantList.size();
	fixture.acceptApplicant();

	// New Game
	fixture.newGame(VALID_MAX_APPLICANTS, diffRandomValidSeed);
	ArrayList<Applicant> applicantListComparison = new ArrayList<Applicant>();
	applicantListComparison.add(fixture.getNextApplicant());

	int applicantListComparisonSize = applicantListComparison.size();

	if (applicantListSize == VALID_MAX_APPLICANTS) // has completed
	{
	    assertTrue("Successful traversal of applicantList", true);
	    if (applicantListComparisonSize < applicantListSize) // has been
								 // reset
	    {
		assertTrue("getNextApplicant successfully reset", true);
	    }
	} else // has not completed
	{
	    assertFalse("Unsuccessful traversal of applicantList", false);
	}

    }

    /**
     * Perform pre-test initialization.
     * 
     * 
     * 
     * @throws Exception
     *             if the initialization fails for some reason
     */
    @Before
    public void setUp() throws Exception {
	// add additional set up code here
    }

    /**
     * Perform post-test clean-up.
     * 
     * 
     * @throws Exception
     *             if the clean-up fails for some reason
     */
    @After
    public void tearDown() throws Exception {
	// Add additional tear down code here
    }

    /**
     * Launch the test.
     * 
     * @param args
     *            the command line arguments
     * 
     * 
     */
    public static void main(String[] args) {
	new org.junit.runner.JUnitCore().run(HiringGameTest.class);
    }
}