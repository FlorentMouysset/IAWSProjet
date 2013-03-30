package iaws.covoiturage.ws.contractfirst;

import org.w3c.dom.Element;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.xml.sax.SAXException;


public class GenerateResponseAjout {

	public static  Element createResponse(int codeErreur, int userId)
			throws ParserConfigurationException, IOException, SAXException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.parse(new ClassPathResource("Covoiturage.xml").getInputStream());
        System.out.println("ici generate");
        return doc.getDocumentElement();		
	}

	
}
