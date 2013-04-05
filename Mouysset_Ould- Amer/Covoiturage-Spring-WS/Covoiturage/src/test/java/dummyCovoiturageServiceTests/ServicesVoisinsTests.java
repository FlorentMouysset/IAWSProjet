package dummyCovoiturageServiceTests;

import iaws.covoiturage.domain.Personne;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.services.impl.DummyCovoiturageService;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import nomenclatureOSMServices.Km;

public class ServicesVoisinsTests extends TestCase {
	
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

	public void testUnVoisin(){
		List<Personne> l = null;
		try {
			l = service.findAllNeighborhood(0, new Km(1.9));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(l);
		assertEquals(1,l.size());
		Personne p0 = new Personne(new Adresse(118, 31062, "Route de Narbonne", "Toulouse"), 
				new Email("mail1@univ-tlse3.fr"), new EtatCivile("nom1", "prenom1") );
		
		assertEquals(p0, l.get(0));
	}
	
	
	public void testDeuxVoisins(){
		List<Personne> l = null;
		try {
			l = service.findAllNeighborhood(1, new Km(1.9));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(l);
		assertEquals(2,l.size());

		Personne p0 = new Personne(new Adresse(141, 31062, "Avenue de Rangueil", "Toulouse"), 
				new Email("mail2@univ-tlse3.fr"), new EtatCivile("nom2", "prenom2") );
		Personne p1 = new Personne(new Adresse(65, 31520, "Avenue Tolosane", "Ramonville-Saint-Agne"), new Email("mail0@univ-tlse3.fr"), new EtatCivile("nom0", "prenom0") );	 
		List<Personne> cExpected = new ArrayList<Personne>();
		cExpected.add(p0);
		cExpected.add(p1);
		assertTrue(l.containsAll(cExpected));
	}
	
	
}
