package asgn2Tests;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
     * Run the FreightCar(Integer,String) constructor test.
     * 
     * @throws Exception
     */
    @Test
    public void testFreightCar_1() throws Exception {
	Integer grossWeight = new Integer(1);
	String goodsType = "";

	FreightCar result = new FreightCar(grossWeight, goodsType);

	// add additional test code here
	// An unexpected exception was thrown in user code while executing this test:
	// java.lang.StringIndexOutOfBoundsException: String index out of range: 0
	// at java.lang.String.charAt(Unknown Source)
	// at asgn2RollingStock.FreightCar.classificationCheck(FreightCar.java:50)
	// at asgn2RollingStock.FreightCar.<init>(FreightCar.java:34)
	assertNotNull(result);
    }

    /**
     * Run the FreightCar(Integer,String) constructor test.
     * 
     * @throws Exception
     */
    @Test
    public void testFreightCar_2() throws Exception {
	Integer grossWeight = new Integer(1);
	String goodsType = "";

	FreightCar result = new FreightCar(grossWeight, goodsType);

	// add additional test code here
	// An unexpected exception was thrown in user code while executing this test:
	// java.lang.StringIndexOutOfBoundsException: String index out of range: 0
	// at java.lang.String.charAt(Unknown Source)
	// at asgn2RollingStock.FreightCar.classificationCheck(FreightCar.java:50)
	// at asgn2RollingStock.FreightCar.<init>(FreightCar.java:34)
	assertNotNull(result);
    }

    /**
     * Run the String goodsType() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testGoodsType_1() throws Exception {
	FreightCar fixture = new FreightCar(new Integer(1), "");

	String result = fixture.goodsType();

	// add additional test code here
	// An unexpected exception was thrown in user code while executing this test:
	// java.lang.StringIndexOutOfBoundsException: String index out of range: 0
	// at java.lang.String.charAt(Unknown Source)
	// at asgn2RollingStock.FreightCar.classificationCheck(FreightCar.java:50)
	// at asgn2RollingStock.FreightCar.<init>(FreightCar.java:34)
	assertNotNull(result);
    }

    /**
     * Run the String toString() method test.
     * 
     * @throws Exception
     */
    @Test
    public void testToString_1() throws Exception {
	FreightCar fixture = new FreightCar(new Integer(1), "");

	String result = fixture.toString();

	// add additional test code here
	// An unexpected exception was thrown in user code while executing this test:
	// java.lang.StringIndexOutOfBoundsException: String index out of range: 0
	// at java.lang.String.charAt(Unknown Source)
	// at asgn2RollingStock.FreightCar.classificationCheck(FreightCar.java:50)
	// at asgn2RollingStock.FreightCar.<init>(FreightCar.java:34)
	assertNotNull(result);
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