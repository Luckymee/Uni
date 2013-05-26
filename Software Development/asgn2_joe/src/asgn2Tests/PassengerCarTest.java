package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.PassengerCar;

/**
 * The class <code>PassengerCarTest</code> contains tests for the class
 * <code>{@link PassengerCar}</code>.
 * 
 * 
 * @author Connor Livsey - n8510873
 * @version $Revision: 1.0 $
 */
public class PassengerCarTest {

	/**
	 * Simplify Java println();
	 * 
	 * @param line
	 *            A valid String for printing.
	 */
	private static void println(String line) {

		System.out.println(line);
	}

	@Test
	public void testPassengerCar_notNull() throws TrainException {

		Integer grossWeight = new Integer(1);
		Integer numberOfSeats = new Integer(1);

		PassengerCar result = new PassengerCar(grossWeight, numberOfSeats);

		// add additional test code here
		assertNotNull(result);

	}

	@Test
	public void testPassengerCar_validConfiguration() throws TrainException {

		Integer grossWeight = new Integer(1);
		Integer numberOfSeats = new Integer(10);

		PassengerCar result = new PassengerCar(grossWeight, numberOfSeats);
		result.board(5);
		// add additional test code here
		assertNotNull(result);
		assertEquals("Passenger(5|10)", result.toString());
	}

	@Test
	public void testAlight_validAlightingPassengers() throws TrainException {

		Integer newPassengers = 5;
		Integer departingPassengers = new Integer(1);
		PassengerCar fixture = new PassengerCar(new Integer(1), newPassengers);

		fixture.board(newPassengers);
		fixture.alight(departingPassengers);
		Integer result = fixture.numberOnBoard();

		assertEquals("4", result.toString());
		assertEquals("Passenger(4|5)", fixture.toString());
	}

	@Test
	public void testAlight_invalidAlightingPassengerNegative()
			throws TrainException {

		Integer newPassengers = 5;
		Integer departingPassengers = new Integer(-1);
		PassengerCar fixture = new PassengerCar(new Integer(1), newPassengers);

		try {
			fixture.board(newPassengers);
			fixture.alight(departingPassengers);
			fail("Didn't catch exception");
		} catch (TrainException expected) {
			println(expected.toString() + " Found: " + departingPassengers);
		}

	}

	@Test
	public void testAlight_invalidAlightingPassengerNull()
			throws TrainException {

		Integer newPassengers = 5;
		Integer departingPassengers = null;
		PassengerCar fixture = new PassengerCar(new Integer(1), newPassengers);

		try {
			fixture.board(newPassengers);
			fixture.alight(departingPassengers);
			fail("Didn't catch exception");
		} catch (NullPointerException expected) {
			println(expected.toString() + " Found: " + departingPassengers);
		}
	}

	@Test
	public void testAlight_validAlightingPassengerGroups()
			throws TrainException {

		Integer numberOfSeats = 20;
		Integer newPassengers = 20;
		Integer departingPassengersFirst = new Integer(5);
		Integer departingPassengersSecond = new Integer(8);
		PassengerCar fixture = new PassengerCar(new Integer(1), numberOfSeats);

		// Board the passengers
		fixture.board(newPassengers);

		// Alight first group
		fixture.alight(departingPassengersFirst);
		Integer result = fixture.numberOnBoard();

		assertEquals("15", result.toString());
		assertEquals("Passenger(15|20)", fixture.toString());

		// Alight second group
		fixture.alight(departingPassengersSecond);
		result = fixture.numberOnBoard();

		assertEquals("7", result.toString());
		assertEquals("Passenger(7|20)", fixture.toString());
	}

	@Test
	public void testBoard_notNull() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
		Integer newPassengers = new Integer(1);

		fixture.board(newPassengers);
		Integer result = fixture.numberOnBoard();

		// add additional test code here
		assertNotNull(result);
	}

	@Test
	public void testBoard_validPassengersMin() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
		Integer newPassengers = new Integer(1);

		fixture.board(newPassengers);
		Integer result = fixture.numberOnBoard();

		// add additional test code here
		assertNotNull(result);
		assertEquals("1", result.toString());
	}

	@Test
	public void testBoard_zeroPassengersBoarding() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
		Integer newPassengers = new Integer(0);

		fixture.board(newPassengers);
		Integer result = fixture.numberOnBoard();

		// add additional test code here
		assertNotNull(result);
		assertEquals("0", result.toString());
	}

	@Test
	public void testBoard_negativePassengers() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
		Integer newPassengers = new Integer(-1);

		try {
			@SuppressWarnings("unused")
			Integer result = fixture.board(newPassengers);
			fail("Didn't catch exception");
		} catch (TrainException expected) {
			println(expected.toString() + " Found: " + newPassengers);
		}
		// add additional test code here
	}

	@Test
	public void testBoard_halfPassengerToSeats() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(10));
		Integer newPassengers = new Integer(5);

		fixture.board(newPassengers);
		Integer result = fixture.numberOnBoard();

		// add additional test code here
		assertNotNull(result);
		assertEquals("5", result.toString());
		assertEquals("Passenger(5|10)", fixture.toString());
	}

	@Test
	public void testBoard_groupsOfPassengers() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(20));
		Integer firstPassengers = new Integer(5);
		Integer secondPassengers = new Integer(8);

		// Board first passengers
		fixture.board(firstPassengers);
		Integer result = fixture.numberOnBoard();

		// First boarding assertions
		assertNotNull(result);
		assertEquals("5", result.toString());
		assertEquals("Passenger(5|20)", fixture.toString());

		// Board second passengers
		fixture.board(secondPassengers);
		result = fixture.numberOnBoard();

		// Final assertion
		assertNotNull(result);
		assertEquals("13", result.toString());
		assertEquals("Passenger(13|20)", fixture.toString());
	}

	@Test
	public void testNumberOfSeats_notNull() throws TrainException {

		Integer numberOfSeats = 10;
		PassengerCar fixture = new PassengerCar(new Integer(1), numberOfSeats);

		Integer result = fixture.numberOfSeats();

		// add additional test code here
		assertNotNull(result);
		assertEquals("10", result.toString());
	}

	@Test
	public void testNumberOfSeats_validNumberOfSeats() throws TrainException {

		Integer numberOfSeats = 10;
		PassengerCar fixture = new PassengerCar(new Integer(1), numberOfSeats);

		Integer result = fixture.numberOfSeats();

		// add additional test code here
		assertNotNull(result);
		assertEquals("10", result.toString());
		assertEquals("Passenger(0|10)", fixture.toString());
	}

	@Test
	public void testNumberOfSeats_invalidSeats() throws TrainException {

		Integer numberOfSeats = -5;

		try {
			@SuppressWarnings("unused")
			PassengerCar fixture = new PassengerCar(new Integer(1),
					numberOfSeats);
			fail("Didn't catch exception");
		} catch (TrainException expected) {
			println(expected + " Found: " + numberOfSeats);
		}
		// add additional test code here
	}

	@Test
	public void testPassengerCar_tooManyPassenger() throws TrainException {

		Integer grossWeight = new Integer(1);
		Integer numberOfSeats = new Integer(10);
		Integer numberOfPassengers = new Integer(15);

		PassengerCar result = new PassengerCar(grossWeight, numberOfSeats);

		Integer leftOver = result.board(numberOfPassengers);

		assertEquals("Passenger(10|10)", result.toString());
		assertTrue(leftOver == numberOfPassengers - numberOfSeats);
		assertEquals("5", leftOver.toString());
	}

	@Test
	public void testNumberOnBoard_1() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));

		Integer result = fixture.numberOnBoard();

		// add additional test code here
		assertNotNull(result);
		assertEquals("0", result.toString());
		assertEquals(0.0, result.doubleValue(), 1.0);
		assertEquals(0, result.intValue());
		assertEquals((short) 0, result.shortValue());
		assertEquals(0.0f, result.floatValue(), 1.0f);
		assertEquals(0L, result.longValue());
		assertEquals((byte) 0, result.byteValue());
	}

	@Test
	public void testToString_noPassengersNoSeats() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(0));

		String result = fixture.toString();

		// add additional test code here
		assertEquals("Passenger(0|0)", result);
	}

	@Test
	public void testToString_noPassengersValidSeats() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(10));

		String result = fixture.toString();

		// add additional test code here
		assertEquals("Passenger(0|10)", result);
	}

	@Test
	public void testToString_validPassengersValidSeats() throws TrainException {

		PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(10));
		Integer newPassengers = 5;

		fixture.board(newPassengers);
		Integer result = fixture.numberOnBoard();

		// add additional test code here
		assertEquals("5", result.toString());
		assertEquals("Passenger(5|10)", fixture.toString());
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

		new org.junit.runner.JUnitCore().run(PassengerCarTest.class);
	}
}