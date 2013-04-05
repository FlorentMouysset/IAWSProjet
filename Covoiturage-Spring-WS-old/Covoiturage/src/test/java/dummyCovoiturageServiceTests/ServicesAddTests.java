package dummyCovoiturageServiceTests;

import exceptionsOSMServices.ExceptionAdresseInvalide;
import fileComparator.FileComparator;
import iaws.covoiturage.domain.Personne;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.services.ExceptionMailDejaUtil;
import iaws.covoiturage.services.impl.DummyCovoiturageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;

public class ServicesAddTests extends TestCase {

	private DummyCovoiturageService service;
	private final String nomfichierRef = "src/test/resources/Services_tests/bd_ADD_REF_origin.xml";
	private final String nomfichierGen = "src/test/resources/Services_tests/bd_ADD_Gen.xml";
	private final String nomfichierFin = "src/test/resources/Services_tests/bd_ADD_REF_fin.xml";
	
	
	@Override
	protected void setUp() throws Exception {
		InputStream input = new FileInputStream(nomfichierRef);
		OutputStream output = new FileOutputStream(nomfichierGen);
		IOUtils.copy(input, output);		
		service = new DummyCovoiturageService(nomfichierGen);
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		File gen = new File(nomfichierGen);
		gen.delete();
		service.detachBD();
		service = null;
	}
	
	
	public void testAddOK(){
		Personne p0 = new Personne(new Adresse(118, 31062, "narbonne", "toulouse"), new Email("mail1@univ-tlse3.fr"), new EtatCivile("nom1", "prenom1") );		
		Integer id = null;
		try {
			id = service.addPersonne(p0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(2,(int) id);
		assertTrue(FileComparator.IsTheSameFiles(nomfichierGen, nomfichierFin));
	}

	
	public void testAddKOMailExiste(){
		Personne p0 = new Personne(new Adresse(118, 31062, "narbonne", "toulouse"), new Email("mail0@univ-tlse3.fr"), new EtatCivile("nom1", "prenom1") );		
		Integer id = null;
		try {
			id = service.addPersonne(p0);
			assertEquals(-1, (int) id);
			fail();
		} catch (ExceptionMailDejaUtil e) {
		//=> levé de la bonne exception ok !	
		} catch (Exception e) {
			fail();
		}
	}
	
	
	public void testAddKOAdresse(){
		Personne p0 = new Personne(new Adresse(118, 31062, "existePas", "toulouse"), new Email("mail1@univ-tlse3.fr"), new EtatCivile("nom1", "prenom1") );		
		Integer id = null;
		try {
			id = service.addPersonne(p0);
			assertEquals(-1, (int) id);
			fail();
		} catch (ExceptionAdresseInvalide e) {
		//=> levé de la bonne exception ok !	
		} catch (Exception e) {
			fail();
		}
	}
	
	

	
}
