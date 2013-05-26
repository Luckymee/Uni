package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;

/**
 * The class <code>LocomotiveTest</code> contains tests for the class
 * <code>{@link Locomotive}</code>.
 * 
 * 
 * @author Connor
 * @version $Revision: 1.0 $
 */
public class LocomotiveTest {

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
	 * Run the Locomotive(Integer,String) constructor test.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotive_notNullCheck() throws TrainException {

		Integer grossWeight = new Integer(1);
		String classification = "1E";

		Locomotive result = new Locomotive(grossWeight, classification);

		assertNotNull(result);
	}

	@Test
	public void testLocomotive_lowerPower() throws TrainException {

		Integer grossWeight = new Integer(1);
		String classification = "1E";

		Locomotive result = new Locomotive(grossWeight, classification);

		// add additional test code here
		assertNotNull(result);
		assertTrue(result.power() == 100);
	}

	@Test
	public void testLocomotive_upperPower() throws TrainException {

		Integer grossWeight = new Integer(9);
		String classification = "9E";

		Locomotive result = new Locomotive(grossWeight, classification);

		// add additional test code here
		assertNotNull(result);
		assertTrue(result.power() == 900);
	}

	@Test
	public void testLocomotive_lowerOutOfBounds() throws TrainException {

		Integer grossWeight = new Integer(0);
		String classification = "0E";
		try {
			@SuppressWarnings("unused")
			Locomotive result = new Locomotive(grossWeight, classification);
			fail("Didn't catch exception.");
		} catch (TrainException expected) {
			println(expected.toString() + " Found: " + classification);
		}
	}

	@Test
	public void testLocomotive_upperOutOfBounds() throws TrainException {

		Integer grossWeight = new Integer(10);
		String classification = "10E";
		try {
			@SuppressWarnings("unused")
			Locomotive result = new Locomotive(grossWeight, classification);
			fail("Didn't catch exception.");
		} catch (TrainException expected) {
			println(expected.toString() + " Found: " + classification);
		}
	}

	@Test
	public void testLocomotive_electricLocomotive() throws TrainException {

		Integer grossWeight = new Integer(9);
		String classification = "9E";

		Locomotive result = new Locomotive(grossWeight, classification);

		// add additional test code here
		assertNotNull(result);
		assertEquals("Locomotive(9E)", result.toString());
	}

	/**
	 * Run the Locomotive(Integer,String) constructor test.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotive_dieselLocomotive() throws TrainException {

		Integer grossWeight = new Integer(9);
		String classification = "9D";

		Locomotive result = new Locomotive(grossWeight, classification);

		// add additional test code here
		assertNotNull(result);
		assertEquals("Locomotive(9D)", result.toString());
	}

	@Test
	public void testLocomotive_steamLocomotive() throws TrainException {

		Integer grossWeight = new Integer(9);
		String classification = "9S";

		Locomotive result = new Locomotive(grossWeight, classification);

		// add additional test code here
		assertNotNull(result);
		assertEquals("Locomotive(9S)", result.toString());
	}

	@Test
	public void testLocomotive_invalidLocomotive() throws TrainException {

		Integer grossWeight = new Integer(1);
		String classification = "1X";
		try {
			@SuppressWarnings("unused")
			Locomotive result = new Locomotive(grossWeight, classification);
			fail("Didn't catch exception.");
		} catch (TrainException expected) {
			println(expected.toString() + " Found: " + classification);
		}
	}

	@Test
	public void testLocomotive_invalidGrossWeight() throws TrainException {

		Integer grossWeight = new Integer(-1);
		String classification = grossWeight + "E";
		try {
			@SuppressWarnings("unused")
			Locomotive result = new Locomotive(grossWeight, classification);
			fail("Didn't catch exception.");
		} catch (TrainException expected) {
			println(expected.toString() + " Found: " + grossWeight);
		}
	}

	@Test
	public void testLocomotive_nullClassification() throws TrainException {

		Integer grossWeight = new Integer(1);
		String classification = null;

		try {
			@SuppressWarnings("unused")
			Locomotive result = new Locomotive(grossWeight, classification);
			fail("Did not catch exception");
		} catch (NullPointerException expected) {
			assertNull(classification);
			println(expected.toString() + " Classification: " + classification);
		}
	}

	@Test
	public void testLocomotive_nullGrossWeight() throws TrainException {

		Integer grossWeight = null;
		String classification = "1E";

		try {
			@SuppressWarnings("unused")
			Locomotive result = new Locomotive(grossWeight, classification);
			fail("Did not catch exception");
		} catch (NullPointerException expected) {
			assertNull(grossWeight);
			println(expected.toString() + " Gross Weight: " + grossWeight);
		}
	}

	@Test
	public void testLocomotive_lowerCase() throws TrainException {

		Integer grossWeight = new Integer(9);
		String classification = "9s";

		Locomotive result = new Locomotive(grossWeight, classification);

		// add additional test code here
		assertNotNull(result);
		assertEquals("Locomotive(9S)", result.toString());
	}

	@Test
	public void testPower_getPower() throws TrainException {

		Locomotive fixture = new Locomotive(new Integer(1), "1E");

		Integer result = fixture.power();

		assertNotNull(result);
		assertTrue(result == 100);
	}

	@Test
	public void testPower() throws TrainException {

		Integer grossWeight = new Integer(1);
		Integer power = new Integer(-1);
		try {
			Locomotive fixture = new Locomotive(grossWeight, power + "E");
			@SuppressWarnings("unused")
			Integer result = fixture.power();
			fail("Didn't catch exception");
		} catch (TrainException expected) {
			println(expected.toString() + " Found: " + power);
		}
	}

	@Test
	public void testToString_validToString() throws TrainException {

		Locomotive fixture = new Locomotive(new Integer(1), "1E");

		String result = fixture.toString();

		assertNotNull(result);
		assertEquals("Locomotive(1E)", result);
	}

	/**
	 * Perform pre-test initialization.
	 * 
	 * @throws TrainException
	 *             if the initialization fails for some reason
	 */
	@Before
	public void setUp() throws TrainException {

		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * @throws TrainException
	 *             if the clean-up fails for some reason
	 */
	@After
	public void tearDown() throws TrainException {

		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		new org.junit.runner.JUnitCore().run(LocomotiveTest.class);
	}
}
