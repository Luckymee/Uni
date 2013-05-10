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

/**
 * The class <code>FreightCarTest</code> contains tests for the class
 * <code>{@link FreightCar}</code>.
 * 
 * 
 * @author Connor Livsey - n8510873
 * @version $Revision: 1.0 $
 */
public class FreightCarTest {

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
     * Run the FreightCar(Integer,String) constructor test.
     * 
     * @throws Exception
     */
    @Test
    public void testFreightCar_notNull() throws Exception {
	Integer grossWeight = new Integer(1);
	String goodsType = "D";

	FreightCar result = new FreightCar(grossWeight, goodsType);

	assertNotNull(result);
    }

    /**
     * Run the FreightCar(Integer,String) constructor test.
     * 
     * @throws Exception
     */
    @Test
    public void testFreightCar_nullGoodsType() throws Exception {
	Integer grossWeight = new Integer(1);
	String goodsType = null;

	try {
	    @SuppressWarnings("unused")
	    FreightCar result = new FreightCar(grossWeight, goodsType);
	} catch (NullPointerException expected) {
	    assertNull(goodsType);
	    println(expected.toString() + " Goods Type: " + goodsType);
	}
    }

    /**
     * Run the String goodsType() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testGoodsType_ValidGoods() throws Exception {
	FreightCar fixture = new FreightCar(new Integer(1), "D");

	String result = fixture.goodsType();

	assertNotNull(result);
    }

    /**
     * Run the String goodsType() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testGoodsType_validLowerCase() throws Exception {
	String goodsType = "d";
	FreightCar fixture = new FreightCar(new Integer(1), goodsType);

	assertEquals("Freight(D)", fixture.toString());

    }

    /**
     * Run the String goodsType() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testGoodsType_InvalidGoodsChar() throws TrainException {
	String goodsType = "X";
	try {
	    @SuppressWarnings("unused")
	    FreightCar fixture = new FreightCar(new Integer(1), goodsType);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString() + " Found: " + goodsType);
	}
    }

    /**
     * Run the String goodsType() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testGoodsType_InvalidGoodsInt() throws TrainException {
	String goodsType = "1";
	try {
	    @SuppressWarnings("unused")
	    FreightCar fixture = new FreightCar(new Integer(1), goodsType);
	    fail("Didn't catch exception");
	} catch (TrainException expected) {
	    println(expected.toString() + " Found: " + goodsType);
	}
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testToString_notNull() throws Exception {
	FreightCar fixture = new FreightCar(new Integer(1), "D");

	String result = fixture.toString();

	assertNotNull(result);
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testToString_validDangerous() throws Exception {
	FreightCar fixture = new FreightCar(new Integer(1), "D");

	String result = fixture.toString();

	assertNotNull(result);
	assertEquals("Freight(D)", result);
	assertTrue(fixture.goodsType() == "D");
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testToString_validRefrigerated() throws Exception {
	FreightCar fixture = new FreightCar(new Integer(1), "R");

	String result = fixture.toString();

	assertNotNull(result);
	assertEquals("Freight(R)", result);
	assertTrue(fixture.goodsType() == "R");
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testToString_validGeneral() throws Exception {
	FreightCar fixture = new FreightCar(new Integer(1), "G");

	String result = fixture.toString();

	assertNotNull(result);
	assertEquals("Freight(G)", result);
	assertTrue(fixture.goodsType() == "G");
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
	new org.junit.runner.JUnitCore().run(FreightCarTest.class);
    }
}