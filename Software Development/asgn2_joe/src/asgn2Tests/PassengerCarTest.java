package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
     * Run the PassengerCar(Integer,Integer) constructor test.
     * 
     * @throws Exception
     */
    @Test
    public void testPassengerCar_1() throws Exception {
	Integer grossWeight = new Integer(1);
	Integer numberOfSeats = new Integer(1);

	PassengerCar result = new PassengerCar(grossWeight, numberOfSeats);

	// add additional test code here
	assertNotNull(result);
	assertEquals("Passenger(0|1)", result.toString());
	assertEquals(new Integer(1),  result.numberOfSeats());
	assertEquals(new Integer(0), result.numberOnBoard());
	assertEquals(new Integer(1), result.getGrossWeight());
    }

    /**
     * Run the PassengerCar(Integer,Integer) constructor test.
     * 
     * @throws Exception
     */
    @Test
    public void testPassengerCar_2() throws Exception {
	Integer grossWeight = new Integer(1);
	Integer numberOfSeats = new Integer(1);

	PassengerCar result = new PassengerCar(grossWeight, numberOfSeats);

	// add additional test code here
	assertNotNull(result);
	assertEquals("Passenger(0|1)", result.toString());
	assertEquals(new Integer(1), result.numberOfSeats());
	assertEquals(new Integer(0), result.numberOnBoard());
	assertEquals(new Integer(1), result.getGrossWeight());
    }

    /**
     * Run the void alight(Integer) method test.
     * 
     * @throws Exception
     */
    @Test(expected = asgn2Exceptions.TrainException.class)
    public void testAlight_1() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
	Integer departingPassengers = new Integer(1);

	fixture.alight(departingPassengers);

	// add additional test code here
    }

    /**
     * Run the void alight(Integer) method test.
     * 
     * @throws Exception
     */
    @Test(expected = asgn2Exceptions.TrainException.class)
    public void testAlight_2() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
	Integer departingPassengers = new Integer(1);

	fixture.alight(departingPassengers);

	// add additional test code here
    }

    /**
     * Run the void alight(Integer) method test.
     * 
     * @throws Exception
     */
    @Test(expected = asgn2Exceptions.TrainException.class)
    public void testAlight_3() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
	Integer departingPassengers = new Integer(1);

	fixture.alight(departingPassengers);

	// add additional test code here
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     */
    @Test
    public void testBoard_1() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
	Integer newPassengers = new Integer(1);

	Integer result = fixture.board(newPassengers);

	// add additional test code here
	assertNotNull(result);
	assertEquals("1", result.toString());
	assertEquals(1.0, result.doubleValue(), 1.0);
	assertEquals(1, result.intValue());
	assertEquals((short) 1, result.shortValue());
	assertEquals(1.0f, result.floatValue(), 1.0f);
	assertEquals(1L, result.longValue());
	assertEquals((byte) 1, result.byteValue());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     */
    @Test
    public void testBoard_2() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
	Integer newPassengers = new Integer(1);

	Integer result = fixture.board(newPassengers);

	// add additional test code here
	assertNotNull(result);
	assertEquals("1", result.toString());
	assertEquals(1.0, result.doubleValue(), 1.0);
	assertEquals(1, result.intValue());
	assertEquals((short) 1, result.shortValue());
	assertEquals(1.0f, result.floatValue(), 1.0f);
	assertEquals(1L, result.longValue());
	assertEquals((byte) 1, result.byteValue());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     */
    @Test
    public void testBoard_3() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
	Integer newPassengers = new Integer(1);

	Integer result = fixture.board(newPassengers);

	// add additional test code here
	assertNotNull(result);
	assertEquals("1", result.toString());
	assertEquals(1.0, result.doubleValue(), 1.0);
	assertEquals(1, result.intValue());
	assertEquals((short) 1, result.shortValue());
	assertEquals(1.0f, result.floatValue(), 1.0f);
	assertEquals(1L, result.longValue());
	assertEquals((byte) 1, result.byteValue());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     */
    @Test
    public void testBoard_4() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));
	Integer newPassengers = new Integer(1);

	Integer result = fixture.board(newPassengers);

	// add additional test code here
	assertNotNull(result);
	assertEquals("1", result.toString());
	assertEquals(1.0, result.doubleValue(), 1.0);
	assertEquals(1, result.intValue());
	assertEquals((short) 1, result.shortValue());
	assertEquals(1.0f, result.floatValue(), 1.0f);
	assertEquals(1L, result.longValue());
	assertEquals((byte) 1, result.byteValue());
    }

    /**
     * Run the Integer numberOfSeats() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testNumberOfSeats_1() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));

	Integer result = fixture.numberOfSeats();

	// add additional test code here
	assertNotNull(result);
	assertEquals("1", result.toString());
	assertEquals(1.0, result.doubleValue(), 1.0);
	assertEquals(1, result.intValue());
	assertEquals((short) 1, result.shortValue());
	assertEquals(1.0f, result.floatValue(), 1.0f);
	assertEquals(1L, result.longValue());
	assertEquals((byte) 1, result.byteValue());
    }

    /**
     * Run the Integer numberOnBoard() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testNumberOnBoard_1() throws Exception {
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

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testToString_1() throws Exception {
	PassengerCar fixture = new PassengerCar(new Integer(1), new Integer(1));

	String result = fixture.toString();

	// add additional test code here
	assertEquals("Passenger(0|1)", result);
    }

    /**
     * Perform pre-test initialization.
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
     */
    public static void main(String[] args) {
	new org.junit.runner.JUnitCore().run(PassengerCarTest.class);
    }
}
