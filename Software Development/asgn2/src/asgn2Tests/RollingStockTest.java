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
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

/**
 * The class <code>RollingStockTest</code> contains tests for the class
 * <code>{@link RollingStock}</code>.
 * 
 * @author Connor Livsey - n8510873
 * @version $Revision: 1.0 $
 */
public class RollingStockTest {
	private static final int VALID_TEST_SEATS = 10;

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
	public void testGetGrossWeight_FreightCarNotNull() throws TrainException {

		RollingStock fixture = new FreightCar(new Integer(100), "G");

		Integer result = fixture.getGrossWeight();

		assertNotNull(result);
	}

	@Test
	public void testGetGrossWeight_FreightCarValid() throws TrainException {

		String goodsCode = "G";
		RollingStock fixture = new FreightCar(new Integer(100), goodsCode);

		Integer result = fixture.getGrossWeight();

		assertNotNull(result);
		assertTrue(result == 100);
	}

	@Test
	public void testGetGrossWeight_LocomotiveNotNull() throws TrainException {

		RollingStock fixture = new Locomotive(new Integer(100), "1E");

		Integer result = fixture.getGrossWeight();

		assertNotNull(result);
	}

	@Test
	public void testGetGrossWeight_LocomotiveValid() throws TrainException {

		RollingStock fixture = new FreightCar(new Integer(100), "G");

		Integer result = fixture.getGrossWeight();

		assertNotNull(result);
		assertTrue(result == 100);
	}

	@Test
	public void testGetGrossWeight_PassengerCarNotNull() throws TrainException {

		Integer numOfSeats = 10;
		RollingStock fixture = new PassengerCar(new Integer(100), numOfSeats);

		Integer result = fixture.getGrossWeight();

		assertNotNull(result);
	}

	@Test
	public void testGetGrossWeight_PassengerCarValid() throws TrainException {

		Integer numOfSeats = 10;
		RollingStock fixture = new PassengerCar(new Integer(100), numOfSeats);

		Integer result = fixture.getGrossWeight();

		assertNotNull(result);
		assertTrue(result == 100);
	}

	@Test
	public void testGetGrossWeight_nullGrossWeight() throws TrainException {

		String goodsCode = "G";
		Integer grossWeight = null;

		try {
			@SuppressWarnings("unused")
			RollingStock fixture = new FreightCar(grossWeight, goodsCode);
			fail("Didn't catch exception.");
		} catch (NullPointerException expected) {
			assertNull(grossWeight);
			println(expected.toString() + " Gross Weight: " + grossWeight);
		}
	}

	@Test
	public void testToString_LocomotiveNotNull() throws TrainException {

		RollingStock fixture = new Locomotive(new Integer(100), "1E");

		String result = fixture.toString();

		assertNotNull(result);
	}

	@Test
	public void testToString_FreightCarNotNull() throws TrainException {

		RollingStock fixture = new FreightCar(new Integer(100), "G");

		String result = fixture.toString();

		assertNotNull(result);
	}

	@Test
	public void testToString_PassengerCarNotNull() throws TrainException {

		RollingStock fixture = new PassengerCar(new Integer(100),
				VALID_TEST_SEATS);

		String result = fixture.toString();

		assertNotNull(result);
	}

	@Test
	public void testToString_LocomotiveValid() throws TrainException {

		RollingStock fixture = new Locomotive(new Integer(100), "1E");

		String result = fixture.toString();

		assertNotNull(result);
		assertEquals("Locomotive(1E)", result);
	}

	@Test
	public void testToString_FreightCarValid() throws TrainException {

		String goodsCode = "G";
		RollingStock fixture = new FreightCar(new Integer(100), goodsCode);

		String result = fixture.toString();

		assertNotNull(result);
		assertEquals("Freight(" + goodsCode + ")", result);
	}

	@Test
	public void testToString_PassengerCarValid() throws TrainException {

		RollingStock fixture = new PassengerCar(new Integer(100),
				VALID_TEST_SEATS);

		String result = fixture.toString();

		assertNotNull(result);
		assertEquals("Passenger(0|10)", result);
	}

	@Test
	public void testToString_PassengerLowerBound() throws TrainException {

		RollingStock fixture = new PassengerCar(new Integer(100), 1);

		String result = fixture.toString();

		assertNotNull(result);
		assertEquals("Passenger(0|1)", result);
	}

	@Test
	public void testToString_noPassengers() throws TrainException {

		RollingStock fixture = new PassengerCar(new Integer(100), 0);

		String result = fixture.toString();

		assertNotNull(result);
		assertEquals("Passenger(0|0)", result);
	}

	@Test
	public void testRollingStock_LocomotiveConstructorNotNull()
			throws TrainException {

		RollingStock rollingStock = new Locomotive(new Integer(100), "1E");

		assertNotNull(rollingStock);
	}

	@Test
	public void testRollingStock_PassengerCarConstructorNotNull()
			throws TrainException {

		RollingStock rollingStock = new PassengerCar(new Integer(100),
				VALID_TEST_SEATS);

		assertNotNull(rollingStock);
	}

	@Test
	public void testRollingStock_FreightCarConstructorNotNull()
			throws TrainException {

		RollingStock rollingStock = new FreightCar(new Integer(100), "G");

		assertNotNull(rollingStock);
	}

	/**
	 * Perform pre-test initialization.
	 * 
	 * @throws TrainException
	 *             if the initialization fails for some reason
	 */
	@Before
	public void setUp() throws TrainException {

	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * @throws TrainException
	 *             if the clean-up fails for some reason
	 */
	@After
	public void tearDown() throws TrainException {

	}

	/**
	 * Launch the test.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		new org.junit.runner.JUnitCore().run(RollingStockTest.class);
	}

}