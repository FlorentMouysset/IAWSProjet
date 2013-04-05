package dummyCovoiturageServiceTests;

import iaws.covoiturage.domain.Personne;
import iaws.covoiturage.services.impl.DummyCovoiturageService;

import java.util.List;

import junit.framework.TestCase;
import nomenclatureOSMServices.Km;

public class ServicesVoisinsVideTests extends TestCase {
	
	private DummyCovoiturageService service;
	private final String nomfichierRef = "src/test/resources/Services_tests/bd_VOISINS_REF.xml";


	@Override
	protected void setUp() throws Exception {
		service = new DummyCovoiturageService(nomfichierRef);
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		service.detachBD();
		service = null;
	}

	public void testVoisinVide(){
		List<Personne> l = null;
		try {
			l = service.findAllNeighborhood(0, new Km(0.1));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(l);
		assertEquals(0,l.size());
		
	}
	
}
