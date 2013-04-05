package iaws.covoiturage.ws.contractfirst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestIntegrationCovoiturageErr100EndPoint.class,
		TestIntegrationCovoiturageErr110EndPoint.class,
		TestIntegrationCovoiturageErr200EndPoint.class,
		TestIntegrationCovoiturageOkEndPoint.class,
		TestVoisinsCovoiturageNonVideEndPoint.class,
		TestVoisinsCovoiturageVideEndPoint.class })
public class AllTests {

}
