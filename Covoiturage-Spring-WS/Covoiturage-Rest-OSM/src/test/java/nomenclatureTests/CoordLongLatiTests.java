package nomenclatureTests;
import junit.framework.TestCase;
import nomenclatureOSMServices.CoordLongLati;
import nomenclatureOSMServices.Km;


public class CoordLongLatiTests extends TestCase {

	private CoordLongLati coordRSA;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		coordRSA = new CoordLongLati(43.5466585, 1.473674);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		coordRSA = null;
	}
	
	
	public void testDistance(){
		CoordLongLati coordUPS = new CoordLongLati(43.5615654, 1.4630301);
		Km distance = coordUPS.getDistance(coordRSA);
		assertEquals(1.8663522804993076, distance.getDistance()); 
	}
	
}
