package bdTests;


import exceptionsOSMServices.ExceptionInternalError;
import fileComparator.FileComparator;
import iaws.covoiturage.domain.BDPersonnes;
import iaws.covoiturage.domain.PersonneLocalise;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import junit.framework.TestCase;
import nomenclatureOSMServices.CoordLongLati;


public class BDAddTests extends TestCase {
	
	private final String nomfichierRef = "src/test/resources/BD_tests/bd_ADD_REF_origin.xml";
	private final String nomfichierGen = "src/test/resources/BD_tests/bd_ADD_Gen.xml";
	private final String nomfichierFin = "src/test/resources/BD_tests/bd_ADD_REF_fin.xml";
	private BDPersonnes bd;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		InputStream input = new FileInputStream(nomfichierRef);
		OutputStream output = new FileOutputStream(nomfichierGen);
		IOUtils.copy(input, output);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		File gen = new File(nomfichierGen);
		gen.delete();
		bd.disconectBD();
		bd = null;
	}
	
	public void testAddBD(){
		try {
			bd = BDPersonnes.getInstance(nomfichierGen);
		} catch (ExceptionInternalError e) {
			e.printStackTrace();
			fail();
		}
		PersonneLocalise p0 = new PersonneLocalise(new Adresse(11, 111, "rue1", "ville1"), new Email("mail1@univ-tlse3.fr"), new EtatCivile("nom1", "prenom1"), new CoordLongLati(1.1, 1.11) );
		try {
			assertEquals(1,(int) bd.addPersonneInBD(p0));
		} catch (ExceptionInternalError e) {
			fail();
		}
		assertTrue(FileComparator.IsTheSameFiles(nomfichierGen, nomfichierFin));
	}

	
}
