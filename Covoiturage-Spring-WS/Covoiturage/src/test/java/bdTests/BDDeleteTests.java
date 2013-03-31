package bdTests;


import iaws.covoiturage.domain.BDPersonnes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;

import exceptionsOSMServices.ExceptionInternalError;
import fileComparator.FileComparator;


public class BDDeleteTests extends TestCase {
	
	private final String nomfichierRef = "src/test/resources/BD_tests/bd_DEL_REF_origin.xml";
	private final String nomfichierGen = "src/test/resources/BD_tests/bd_DEL_Gen.xml";
	private final String nomfichierFin = "src/test/resources/BD_tests/bd_DEL_REF_fin.xml";
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
		bd.deletePersonneInBD(1);
		assertEquals(1,bd.getAllPersonne().size());
		assertTrue(FileComparator.IsTheSameFiles(nomfichierGen, nomfichierFin));
	}

	
}
