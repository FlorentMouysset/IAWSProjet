package bdTests;


import iaws.covoiturage.domain.BDPersonnes;
import iaws.covoiturage.domain.PersonneLocalise;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

import java.io.File;
import java.util.Collection;

import exceptionsOSMServices.ExceptionInternalError;

import nomenclatureOSMServices.CoordLongLati;

import junit.framework.TestCase;


public class BDGetTests extends TestCase {
	
	private final String nomfichierRef = "src/test/resources/BD_tests/bd_GET_REF.xml";
	private File file;
	private BDPersonnes bd;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		file = new File(nomfichierRef);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		file = null;
		bd.disconectBD();
		bd = null;
	}
	
	public void testGetBD(){
		
		if(!file.exists()){
			fail();
		}
		
		try {
			bd = BDPersonnes.getInstance(nomfichierRef);
		} catch (ExceptionInternalError e) {
			e.printStackTrace();
			fail();
		}
		Collection<PersonneLocalise> l = bd.getAllPersonne();
		assertEquals(2, l.size());
		
		PersonneLocalise p0 = new PersonneLocalise(new Adresse(0, 1, "rue0", "ville0"), new Email("mail0@univ-tlse3.fr"), new EtatCivile("nom0", "prenom0"), new CoordLongLati(0.1, 0.11) );
		PersonneLocalise p1 = new PersonneLocalise(new Adresse(11, 111, "rue1", "ville1"), new Email("mail1@univ-tlse3.fr"), new EtatCivile("nom1", "prenom1"), new CoordLongLati(1.1, 1.11) );

		assertTrue(bd.get(0).equals(p0));
		assertTrue(bd.get(1).equals(p1));
		
	}
	
}
