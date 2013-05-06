package asgn1Solution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>ApplicantTest</code> contains tests for the class
 * <code>{@link Applicant}</code>.
 * 
 * @author Connor Livsey
 * @author n8510873
 * @version $Revision: 1.0 $
 */
public class ApplicantTest {

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
     * Run the void testConstructorWithValidArugments() constructor test.
     * 
     * 
     * 
     * @throws IllegalArgumentException
     * @throws Exception
     */
    @Test
    public void testConstructorWithValidArugments()
	    throws IllegalArgumentException {
	int id = 1;
	double qualityScore = 0.123;

	Applicant result = new Applicant(id, qualityScore);

	// add additional test code here
	assertNotNull(result);
	assertEquals(1, result.getId());
	assertEquals("Applicant [ID:1 QS:0.123]", result.toString());
	assertEquals(0.123, result.getQualityScore(), 1e-12);
    }

    /**
     * Run the void testConstructorWithScoreZero() constructor test.
     * 
     * 
     * 
     * @throws IllegalArgumentException
     * @throws Exception
     */
    @Test
    public void testConstructorWithScoreZero() throws IllegalArgumentException {
	int id = 1;
	double qualityScore = 0.0;

	Applicant result = new Applicant(id, qualityScore);

	// add additional test code here
	assertNotNull(result);
	assertEquals(1, result.getId());
	assertEquals("Applicant [ID:1 QS:0.0]", result.toString());
	assertEquals(0.0, result.getQualityScore(), 1e-12);
    }

    /**
     * Run the void testConstructorWithScoreOne() constructor test.
     * 
     * 
     * 
     * @throws IllegalArgumentException
     * @throws Exception
     */
    @Test
    public void testConstructorWithScoreOne() throws IllegalArgumentException {
	int id = 1;
	double qualityScore = 1.0;

	Applicant result = new Applicant(id, qualityScore);

	// add additional test code here
	assertNotNull(result);
	assertEquals(1, result.getId());
	assertEquals("Applicant [ID:1 QS:1.0]", result.toString());
	assertEquals(1.0, result.getQualityScore(), 1e-12);
    }

    /**
     * Run the void testConstructorWithInvalidQualityScore() constructor test.
     * 
     * 
     * 
     * @throws Exception
     * @throws IllegalArgumentException
     */
    @Test
    public void testConstructorWithInvalidQualityScore()
	    throws IllegalArgumentException {
	int id = 1;
	double qualityScore = 1.1;

	try {
	    Applicant result = new Applicant(id, qualityScore);
	    fail("expected IllegalArgumentException | Result: " + result);
	} catch (IllegalArgumentException e) {
	    println("Caught Expected Exception");
	}
    }

    /**
     * Run the void testGetId_1() method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testGetId_1() throws Exception {
	Applicant fixture = new Applicant(1, 1.0);
	fixture.setId(1);

	int result = fixture.getId();

	// add additional test code here
	assertEquals(1, result);
    }

    /**
     * Run the void testGetId_2() method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testGetId_2() throws Exception {
	Applicant applicant = new Applicant(3, 1.0);
	int result = applicant.getId();
	assertEquals(3, result);
    }

    /**
     * Run the void testGetQualityScore_1() method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testGetQualityScore_1() throws Exception {
	Applicant applicant = new Applicant(100, 1.0);
	double result = applicant.getQualityScore();
	assertEquals(1.0, result, 1e-12);
    }

    /**
     * Run the void testGetQualityScore_2() method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testGetQualityScore_2() throws Exception {
	Applicant applicant = new Applicant(100, 0.5);
	double result = applicant.getQualityScore();
	assertEquals(0.5, result, 1e-12);
    }

    /**
     * Run the void testGetQualityScore_3() method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testGetQualityScore_3() throws Exception {
	Applicant fixture = new Applicant(1, 1.0);
	fixture.setId(1);

	double result = fixture.getQualityScore();

	// add additional test code here
	assertEquals(1.0, result, 1e-12);
    }

    /**
     * Run the void main(String[]) method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testMain_1() throws Exception {
	String[] args = new String[] {};

	Applicant.main(args);

	// add additional test code here
    }

    /**
     * Run the void setId(int) method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testSetId() throws Exception {
	Applicant fixture = new Applicant(1, 1.0);
	fixture.setId(1);
	int appId = 1;

	fixture.setId(appId);
	int result = fixture.getId();
	// add additional test code here
	assertNotNull(result);
	assertEquals(appId, result, 0);
    }

    /**
     * Run the void setQualityScore(double) method test.
     * 
     * 
     * 
     * @throws IllegalArgumentException
     * @throws Exception
     */
    @Test
    public void testSetQualityScoreWithValidScore()
	    throws IllegalArgumentException {
	Applicant fixture = new Applicant(1, 0.5);
	fixture.setId(1);
	double qualityScore = 0.5;

	fixture.setQualityScore(qualityScore);

	double result = fixture.getQualityScore();

	// add additional test code here
	assertNotNull(result);
	assertEquals(qualityScore, result, 1e-12);
    }

    /**
     * Run the void setQualityScore(double) method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testSetQualityScoreWithInvalidScore() throws Exception {
	int id = 1;
	double qualityScore = 1.1;

	Applicant fixture = new Applicant(id, 1.0);

	try {
	    fixture.setQualityScore(qualityScore);

	    fail("Expected exception IllegalArgumentException");
	} catch (IllegalArgumentException e) {
	    println("Caught Expected exception");
	}
    }

    /**
     * Run the void setQualityScore(double) method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testSetQualityScoreWithZero() throws Exception {
	Applicant fixture = new Applicant(1, 0.0);
	fixture.setId(1);
	double qualityScore = 0.0;

	fixture.setQualityScore(qualityScore);
	double result = fixture.getQualityScore();

	// add additional test code here
	assertNotNull(result);
	assertEquals(qualityScore, result, 1e-12);
    }

    /**
     * Run the void setQualityScore(double) method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testSetQualityScoreWithOne() throws Exception {
	Applicant fixture = new Applicant(1, 1.0);
	fixture.setId(1);
	double qualityScore = 1.0;

	fixture.setQualityScore(qualityScore);
	double result = fixture.getQualityScore();

	// add additional test code here
	assertNotNull(result);
	assertEquals(qualityScore, result, 1e-12);
    }

    /**
     * Run the String toString() method test.
     * 
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testToStringHasOverride() throws Exception {
	Applicant fixture = new Applicant(1, 1.0);
	fixture.setId(1);

	String result = fixture.toString();

	// add additional test code here
	assertEquals("Applicant [ID:1 QS:1.0]", result);
    }

    /**
     * Run the String toString() method test.
     * 
     * 
     * @throws Exception
     */
    @Test
    public void testToStringHasOverride_2() throws Exception {
	Applicant fixture = new Applicant(2, 0.0);
	fixture.setId(1);

	String result = fixture.toString();

	// add additional test code here
	assertEquals("Applicant [ID:1 QS:0.0]", result);
    }

    /**
     * Perform pre-test initialization.
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
     */
    public static void main(String[] args) {
	new org.junit.runner.JUnitCore().run(ApplicantTest.class);
    }
}