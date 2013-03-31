package bdTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(BDCreatTests.class);
		suite.addTestSuite(BDGetTests.class);
		suite.addTestSuite(BDAddTests.class);
		suite.addTestSuite(BDDeleteTests.class);
		//$JUnit-END$
		return suite;
	}

}
