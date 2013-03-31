package allTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		new bdTests.AllTests();
		//$JUnit-BEGIN$
		suite.addTest(bdTests.AllTests.suite());
		new dummyCovoiturageServiceTests.AllTests();
		suite.addTest(dummyCovoiturageServiceTests.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
