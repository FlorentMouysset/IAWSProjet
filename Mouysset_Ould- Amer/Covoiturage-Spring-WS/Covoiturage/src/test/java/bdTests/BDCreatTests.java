package bdTests;

import exceptionsOSMServices.ExceptionInternalError;
import fileComparator.FileComparator;
import iaws.covoiturage.domain.BDPersonnes;

import java.io.File;
import junit.framework.TestCase;


public class BDCreatTests extends TestCase {
	
	private final String nomFichierGen = "src/test/resources/BD_tests/bd_CREAT_AUTOGEN.xml";
	private final String nomfichierRef = "src/test/resources/BD_tests/bd_CREAT_REF.xml";
	private BDPersonnes bd;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		File file = new File(nomFichierGen);
		if(file.exists()){
			file.delete();
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		File file = new File(nomFichierGen);
		file.delete();
		bd.disconectBD();
		bd = null;
	}
	
	public void testCreatBD(){
		try {
			bd = BDPersonnes.getInstance(nomFichierGen);
		} catch (ExceptionInternalError e) {
			e.printStackTrace();
			fail();
		}
		File fileGen = new File(nomFichierGen);
		assertTrue(fileGen.exists());				
		assertTrue( FileComparator.IsTheSameFiles(nomFichierGen, nomfichierRef));
	}
	
}
