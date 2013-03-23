package iaws.covoiturage.ws.contractfirst;

import iaws.covoiturage.services.CodeErreur;

import org.w3c.dom.Element;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.xml.sax.SAXException;

public class GenerateResponseAjout {

	protected static  Element createResponse(CodeErreur codeErreur)
			throws ParserConfigurationException, IOException, SAXException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.parse(new ClassPathResource("Covoiturage").getInputStream());
        return doc.getDocumentElement();		
	}
	
}
