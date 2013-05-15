package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import asgn2Train.DepartingTrain;

/**
 * The class <code>DepartingTrainTest</code> contains tests for the class
 * <code>{@link DepartingTrain}</code>.
 * 
 * 
 * @author Connor Livsey - n8510873
 * @version $Revision: 1.0 $
 */
public class DepartingTrainTest {

    private static final Integer VALID_GROSSWEIGHT = new Integer(180);
    private static final Integer VALID_SEATS = new Integer(20);

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
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_vaildlocoPassengerFreightNotNull() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "4E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_validLocomotive() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "4E");

	Train.addCarriage(locomotive);

	assertNotNull(Train);
	assertEquals(locomotive.toString(), Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertTrue(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_validFreightCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + freightCar.toString(), Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertTrue(Train.nextCarriage() == freightCar);
	assertTrue(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_validPassengerCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertTrue(Train.nextCarriage() == passengerCar);
	assertTrue(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_invalidLocomotiveNotEnoughPowerSelf() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "1E");

	Train.addCarriage(locomotive);

	assertNotNull(Train);
	assertEquals(locomotive.toString(), Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertFalse(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_invalidFreightCarNotEnoughPowerLoco() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "2E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + freightCar.toString(), Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertTrue(Train.nextCarriage() == freightCar);
	assertFalse(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_invalidPassengerCarNotEnoughPowerLoco() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "2E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertTrue(Train.nextCarriage() == passengerCar);
	assertFalse(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_validLocoPassengerFreightCanMove() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + freightCar.toString(),
		Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertTrue(Train.nextCarriage() == passengerCar);
	assertTrue(Train.nextCarriage() == freightCar);
	assertTrue(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_validLocoPassengerFreightCantMove() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "2E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + freightCar.toString(),
		Train.toString());
	assertTrue(Train.firstCarriage() == locomotive);
	assertTrue(Train.nextCarriage() == passengerCar);
	assertTrue(Train.nextCarriage() == freightCar);
	assertFalse(Train.trainCanMove());
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_testTwoFreightCarsDiffer() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	FreightCar freightCarTwo = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);
	Train.addCarriage(freightCarTwo);

	assertFalse(freightCar == freightCarTwo);
    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_testTwoPassengerCarsDiffer() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);

	assertFalse(passengerCar == passengerCarTwo);

    }

    /**
     * Run the DepartingTrain() constructor test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testDepartingTrain_testTwoLocomotivesDiffer() throws TrainException {
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "4E");
	Locomotive locomotiveTwo = new Locomotive(VALID_GROSSWEIGHT, "4E");

	assertFalse(locomotive == locomotiveTwo);

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addFreightBeforeLocomotive() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	try {
	    Train.addCarriage(freightCar);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addPassengerBeforeLocomotive() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	try {
	    Train.addCarriage(passengerCar);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addValidPassengerCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	try {
	    Train.addCarriage(locomotive);
	    Train.addCarriage(passengerCar);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addValidFreightCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	try {
	    Train.addCarriage(locomotive);
	    Train.addCarriage(freightCar);

	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + freightCar.toString(), Train.toString());
    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addLocoAfterFreight() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	try {
	    Train.addCarriage(freightCar);
	    Train.addCarriage(locomotive);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addLocoAfterPassengerCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	try {
	    Train.addCarriage(passengerCar);
	    Train.addCarriage(locomotive);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addLocoAfterLoco() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	Locomotive locomotiveTwo = new Locomotive(VALID_GROSSWEIGHT, "9E");
	try {
	    Train.addCarriage(locomotive);
	    Train.addCarriage(locomotiveTwo);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addSameLoco() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");

	try {
	    Train.addCarriage(locomotive);
	    Train.addCarriage(locomotive);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addPassengerAfterFreight() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	try {
	    Train.addCarriage(locomotive);
	    Train.addCarriage(freightCar);
	    Train.addCarriage(passengerCar);

	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the void addCarriage(RollingStock) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testAddCarriage_addFreightAfterBoarded() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	try {
	    Train.addCarriage(locomotive);
	    Train.addCarriage(passengerCar);
	    Train.board(VALID_SEATS);
	    Train.addCarriage(freightCar);

	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Special test to check if object instance already exits. Without this test using the
     * same Rollingstock instance will be valid, causing strange states where boarding one
     * will also inherently board the other.
     * 
     * @throws Exception
     */
    @Test
    public void testAddCarriage_quantumMechanicsTest() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	try {
	    Train.addCarriage(locomotive);
	    Train.addCarriage(passengerCar);
	    Train.addCarriage(passengerCar);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNumberOnBoard_validSeatsMaxPassengers() throws Exception {

	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);

	Train.board(VALID_SEATS);
	Train.numberOnBoard();
	Integer result = Train.numberOnBoard();

	assertEquals("Passenger(20|20)", passengerCar.toString());
	assertEquals(new Integer(VALID_SEATS), result);
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNumberOnBoard_validSeatsNoPassengers() throws Exception {

	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);

	Train.numberOnBoard();
	Integer result = Train.numberOnBoard();

	assertEquals("Passenger(0|20)", passengerCar.toString());
	assertEquals(new Integer(0), result);
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_invalidPassengersNegative() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Integer newPassengers = new Integer(-1);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	try {
	    Train.board(newPassengers);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_zeroPassengersOneCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Integer newPassengers = new Integer(0);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	try {
	    Train.board(newPassengers);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_maxPassengersToSeatsMultiPassengerCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Integer newPassengers = new Integer(VALID_SEATS * 2);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);

	try {
	    Train.board(newPassengers);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + passengerCarTwo.toString(),
		Train.toString());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_morePassengersThanSeats() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, new Integer(10));
	Integer newPassengers = new Integer(VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);

	Integer result = 0;
	try {
	    result = Train.board(newPassengers);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
	assertEquals(new Integer(10), result);
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_maxPassengersOneCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Integer newPassengers = new Integer(0);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	try {
	    Train.board(newPassengers);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_zeroPassengersMultiPassengerCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Integer newPassengers = new Integer(VALID_SEATS * 2);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);

	try {
	    Train.board(newPassengers);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + passengerCarTwo.toString(),
		Train.toString());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_morePassengersThanOneCarLessThanTwo() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Integer newPassengers = new Integer(VALID_SEATS + 10);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);

	try {
	    Train.board(newPassengers);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + passengerCarTwo.toString(),
		Train.toString());
    }

    /**
     * Run the Integer board(Integer) method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testBoard_morePasengersThanSeatsMultiPassengerCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	Integer newPassengers = new Integer((VALID_SEATS * 2) + 10);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);

	Integer result = 0;
	try {
	    result = Train.board(newPassengers);
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + passengerCarTwo.toString(),
		Train.toString());
	assertEquals(new Integer(10), result);
    }

    /**
     * Run the RollingStock firstCarriage() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testFirstCarriage_nullBeforeLocomotive() throws Exception {
	DepartingTrain fixture = new DepartingTrain();

	assertNull(fixture.firstCarriage());
    }

    /**
     * Run the RollingStock firstCarriage() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testFirstCarriage_notNullAfterLocomotive() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");

	Train.addCarriage(locomotive);

	assertNotNull(Train.firstCarriage());
    }

    /**
     * Run the RollingStock nextCarriage() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNextCarriage_nullBeforeAddCarriage() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	Train.addCarriage(locomotive);

	assertNotNull(Train.firstCarriage());
	assertNull(Train.nextCarriage());
    }

    /**
     * Run the RollingStock nextCarriage() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNextCarriage_validNextCarriage() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	RollingStock result = Train.nextCarriage();

	assertNotNull(Train.firstCarriage());
	assertNotNull(result);
	assertEquals(result.toString(), freightCar.toString());
    }

    /**
     * Run the RollingStock nextCarriage() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNextCarriage_nullBeforeFirstCarriage() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	Train.addCarriage(locomotive);

	Train.nextCarriage();
	assertNull(Train.nextCarriage());

    }

    /**
     * Run the RollingStock nextCarriage() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNextCarriage_validReturnsToExpectedValueAfterFirstCarriage() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");
	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	Train.firstCarriage().toString();
	for (int i = 0; i < 5; ++i) {
	    RollingStock result = Train.nextCarriage();

	    if (i >= 1) { // Null after first call
		assertNull(result);
	    }
	}
	Train.firstCarriage().toString();
	RollingStock result = Train.nextCarriage();
	assertNotNull(result);
	assertEquals(result.toString(), freightCar.toString());

    }

    /**
     * Run the Integer numberOfSeats() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNumberOfSeats_beforeAddPassengerCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();

	Integer result = Train.numberOfSeats();

	assertNotNull(result);
	assertTrue(result == 0);
    }

    /**
     * Run the Integer numberOfSeats() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNumberOfSeats_validNumOfSeatsAfterAddPassengerCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Integer result = Train.numberOfSeats();

	assertEquals(result, VALID_SEATS);
    }

    /**
     * Run the Integer numberOfSeats() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNumberOfSeats_multiCarriageNumOfSeatsLoop() throws Exception {
	int numOfPassengerCar = 10;
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");

	Train.addCarriage(locomotive);

	for (int i = 0; i < numOfPassengerCar; ++i) { // Create multi PassengerCars
	    PassengerCar passengerCarLoop = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	    Train.addCarriage(passengerCarLoop);
	}

	Integer result = Train.numberOfSeats();
	Integer numOfSeats = VALID_SEATS * numOfPassengerCar;
	assertEquals(result, numOfSeats);
    }

    /**
     * Run the Integer numberOfSeats() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testNumberOfSeats_validNumOfSeatsAfterMultiPassengercar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);
	Integer result = Train.numberOfSeats();
	Integer numOfSeats = VALID_SEATS * 2;

	assertEquals(result, numOfSeats);
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_invalidRemoveWithNoRollingStock() throws TrainException {
	DepartingTrain Train = new DepartingTrain();

	try {
	    Train.removeCarriage();
	    fail("Didn't catch TrainException.");
	} catch (TrainException expected) {
	    println(expected.toString());
	}
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_vaildRemoveLocomotive() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomtive = new Locomotive(VALID_GROSSWEIGHT, "9E");

	Train.addCarriage(locomtive);

	try {
	    Train.removeCarriage();
	} catch (TrainException expected) {
	    println(expected.toString());
	}
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_validRemovePassengerCarNoPassengers() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);

	try {
	    Train.removeCarriage();
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString(), Train.toString());
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_validRemoveFreightCar() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	try {
	    Train.removeCarriage();
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString(), Train.toString());
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_validRemoveThirdCarNoPassengers() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(freightCar);

	try {
	    Train.removeCarriage();
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_invalidRemovePassengerCarWithPassengers() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.board(VALID_SEATS);

	try {
	    Train.removeCarriage();
	    fail("Didn't catch TrainException.");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	// Train stays intact.
	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_invalidRemoveThirdCarWithPassengers() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(freightCar);
	Train.board(VALID_SEATS);

	try {
	    Train.removeCarriage();
	    fail("Didn't catch TrainException.");
	} catch (TrainException expected) {
	    println(expected.toString());
	}

	// Train stays intact.
	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + freightCar.toString(),
		Train.toString());
    }

    /**
     * Run the void removeCarriage() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testRemoveCarriage_validRemoveAll() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(freightCar);

	try {
	    Train.removeCarriage(); // Freight
	    Train.removeCarriage(); // Passenger
	    Train.removeCarriage(); // Locomotive
	} catch (TrainException expected) {
	    println(expected.toString());
	}
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_vaildLocomotive() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	Train.addCarriage(locomotive);

	assertNotNull(Train);
	assertEquals(locomotive.toString(), Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_validEmptyPassengerCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_validFullPassengerCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.board(VALID_SEATS);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString(), Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_validMultiPassengerCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);
	Train.board(VALID_SEATS + 10);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + passengerCarTwo.toString(),
		Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_validDangerousFreightCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + freightCar.toString(), Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_validGeneralFreightCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "G");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + freightCar.toString(), Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_validRefrigerateFreightCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "R");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + freightCar.toString(), Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_validMultiFreightCarConfig() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "R");
	FreightCar freightCarTwo = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);
	Train.addCarriage(freightCarTwo);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + freightCar.toString() + "-" + freightCarTwo.toString(),
		Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_fullConfigurationEmptyPassengerCar() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "R");
	FreightCar freightCarTwo = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);
	Train.addCarriage(freightCar);
	Train.addCarriage(freightCarTwo);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + passengerCarTwo.toString() + "-"
		+ freightCar.toString() + "-" + freightCarTwo.toString(), Train.toString());
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testToString_fullConfigurationWithPassengers() throws Exception {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "R");
	FreightCar freightCarTwo = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(passengerCar);
	Train.addCarriage(passengerCarTwo);
	Train.addCarriage(freightCar);
	Train.addCarriage(freightCarTwo);

	Train.board(VALID_SEATS);

	assertNotNull(Train);
	assertEquals(locomotive.toString() + "-" + passengerCar.toString() + "-" + passengerCarTwo.toString() + "-"
		+ freightCar.toString() + "-" + freightCarTwo.toString(), Train.toString());
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testTrainCanMove_canMoveWithoutLoco() throws TrainException {
	DepartingTrain Train = new DepartingTrain();

	assertTrue(Train.trainCanMove());
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testTrainCanMove_invalidLocoNotEnoughPowerSelf() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "1E");

	Train.addCarriage(locomotive);

	assertFalse(Train.trainCanMove());
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testTrainCanMove_invalidLocoNotEnoughPowerForCarriages() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "2E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	assertFalse(Train.trainCanMove());
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testTrainCanMove_validLocoPowerSelf() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "2E");

	Train.addCarriage(locomotive);

	assertTrue(Train.trainCanMove());
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws TrainException
     * 
     * 
     */
    @Test
    public void testTrainCanMove_validPowerToMoveMultiCarriages() throws TrainException {
	DepartingTrain Train = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "D");

	Train.addCarriage(locomotive);
	Train.addCarriage(freightCar);

	assertTrue(Train.trainCanMove());
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testTrainCanMove_enoughPowerFullConfiguration() throws Exception {
	DepartingTrain fixture = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "R");

	fixture.addCarriage(locomotive);
	fixture.addCarriage(passengerCar);
	fixture.addCarriage(passengerCarTwo);
	fixture.addCarriage(freightCar);

	boolean result = fixture.trainCanMove();

	assertTrue(result);
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testTrainCanMove_equalPowerFullConfiguration() throws Exception {
	DepartingTrain fixture = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "R");
	FreightCar freightCarTwo = new FreightCar(VALID_GROSSWEIGHT, "D");

	fixture.addCarriage(locomotive);
	fixture.addCarriage(passengerCar);
	fixture.addCarriage(passengerCarTwo);
	fixture.addCarriage(freightCar);
	fixture.addCarriage(freightCarTwo);

	boolean result = fixture.trainCanMove();

	assertTrue(result);
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testTrainCanMove_notEnoughPowerFullConfiguration() throws Exception {
	DepartingTrain fixture = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "8E");
	PassengerCar passengerCar = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	PassengerCar passengerCarTwo = new PassengerCar(VALID_GROSSWEIGHT, VALID_SEATS);
	FreightCar freightCar = new FreightCar(VALID_GROSSWEIGHT, "R");
	FreightCar freightCarTwo = new FreightCar(VALID_GROSSWEIGHT, "D");

	fixture.addCarriage(locomotive);
	fixture.addCarriage(passengerCar);
	fixture.addCarriage(passengerCarTwo);
	fixture.addCarriage(freightCar);
	fixture.addCarriage(freightCarTwo);

	boolean result = fixture.trainCanMove();

	assertFalse(result);
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testTrainCanMove_EqualPower() throws Exception {
	DepartingTrain fixture = new DepartingTrain();
	Locomotive locomotive = new Locomotive(new Integer(900), "9E");
	fixture.addCarriage(locomotive);

	boolean result = fixture.trainCanMove();

	assertTrue(result);
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testTrainCanMove_NotEnoughPower() throws Exception {
	DepartingTrain fixture = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "1E");
	fixture.addCarriage(locomotive);

	boolean result = fixture.trainCanMove();

	assertFalse(result);
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testTrainCanMove_noRollingStock() throws Exception {
	DepartingTrain fixture = new DepartingTrain();

	boolean result = fixture.trainCanMove();

	assertTrue(result);
    }

    /**
     * Run the boolean trainCanMove() method test.
     * 
     * @throws Exception
     * 
     * 
     */
    @Test
    public void testTrainCanMove_EnoughPower() throws Exception {
	DepartingTrain fixture = new DepartingTrain();
	Locomotive locomotive = new Locomotive(VALID_GROSSWEIGHT, "9E");
	fixture.addCarriage(locomotive);

	boolean result = fixture.trainCanMove();

	assertTrue(result);
    }

    /**
     * Perform pre-test initialization.
     * 
     * @throws Exception
     *             if the initialization fails for some reason
     * 
     * 
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
     * 
     * 
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
	new org.junit.runner.JUnitCore().run(DepartingTrainTest.class);
    }
}
