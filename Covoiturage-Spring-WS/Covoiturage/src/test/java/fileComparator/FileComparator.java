package fileComparator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;



public class FileComparator {

		static public boolean IsTheSameFiles(String file1, String file2){
			return calculChecksum(file1) == calculChecksum(file2);
		}
	
	   static private long calculChecksum(String adresse_fichier) {
	        try {
	            FileInputStream fis = new FileInputStream(adresse_fichier);
	            CheckedInputStream cis = new CheckedInputStream(fis, new Adler32());
	            byte[] tempBuf = new byte[128];
	            while (cis.read(tempBuf) >= 0) {}
	            long checksum = cis.getChecksum().getValue();
	      return checksum;        
	        } catch (IOException e) {
	         System.out.println("Error occuring during the Checksum calcul" );
	         return 0;
	        }
	    }
	
	
}
