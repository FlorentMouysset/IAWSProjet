package iaws.covoiturage.ws.contractfirst;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;


public class GenerateResponseAjout {
	
	public static  Element createResponse(Integer codeErreur, Integer userId)
			throws ParserConfigurationException, IOException, SAXException{
		
		Element root ;
		SAXBuilder bsx = new SAXBuilder();
		org.jdom2.Document document = null;
    	File file = new File("src/main/resources/ModelCovoiturageAjout.txt");
		try {
			document = bsx.build(file);
		} catch (Exception e){
			e.printStackTrace();
		}
		root = document.getRootElement();

		String etatStr;
		if( ((int)userId) == -1){
			etatStr = "KO";
			Namespace namespace = Namespace.getNamespace("http://www.univ-tlse3.fr/Services/Covoiturage");
			Element codeErreurElem = new Element("codeerreur",namespace);
			codeErreurElem.addContent( codeErreur.toString() );
			root.addContent(codeErreurElem);			
		}else{
			etatStr = "OK";
			Attribute etatAtribut = new Attribute("id", userId.toString());
			root.setAttribute(etatAtribut);	
		}
		
		//ajout atrribu etat
		Attribute etatAtribut = new Attribute("etat", etatStr);
		root.setAttribute(etatAtribut);
		
        return root;		
	}

	
}
