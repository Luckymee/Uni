package asgn2Tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all of the tests
 * within its package as well as within any subpackages of its package.
 * 
 * @author Connor Livsey - n8510873
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ FreightCarTest.class, PassengerCarTest.class,
	LocomotiveTest.class, RollingStockTest.class, })
public class TestAll {

    /**
     * Launch the test.
     * 
     * @param args
     *            the command line arguments
     * 
     * @generatedBy CodePro at 8/05/13 6:43 PM
     */
    public static void main(String[] args) {
	JUnitCore.runClasses(new Class[] { TestAll.class });
    }
}
