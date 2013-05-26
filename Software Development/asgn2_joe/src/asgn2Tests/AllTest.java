package asgn2Tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all of
 * the tests within its package as well as within any sub packages of its
 * package.
 * 
 * @author Connor Livsey - n8510873
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ FreightCarTest.class, PassengerCarTest.class,
		LocomotiveTest.class, RollingStockTest.class, DepartingTrainTest.class })
public class AllTest {

	/**
	 * Launch the test.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		JUnitCore.runClasses(new Class[] { AllTest.class });
	}
}
