package iaws.covoiturage.ws.contractfirst;

import iaws.covoiturage.domain.Personne;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

public class GenerateResponseVoisins {

	public static  Element createResponse(List<Personne> personneListe)
			throws ParserConfigurationException, IOException, SAXException{
		
		Element root ;

		SAXBuilder bsx = new SAXBuilder();
		org.jdom2.Document document = null;
    	File file = new File("src/main/resources/ModelCovoiturageVoisins.xml");
		try {
			document = bsx.build(file);
		} catch (Exception e){
			e.printStackTrace();
		}
		root = document.getRootElement();

		Namespace namespace = Namespace.getNamespace("http://www.univ-tlse3.fr/Services/Covoiturage");
		for(Personne currentPersonne : personneListe){	
			Element voisin = new Element("voisin", namespace);
			Element personneElem = new Element("personne", namespace);
			personneElem.addContent(new Element("nom",namespace).addContent(currentPersonne.getNom()));
			personneElem.addContent(new Element("prenom",namespace).addContent(currentPersonne.getPrenom()));
			personneElem.addContent(new Element("email",namespace).addContent(currentPersonne.getEmail().getEmail()));
			voisin.addContent(personneElem);
			Element adresseElem = new Element("adresse",namespace);
			adresseElem.addContent(new Element("numrue",namespace).addContent(currentPersonne.getNumRue().toString()));
			adresseElem.addContent(new Element("numpostal",namespace).addContent(currentPersonne.getCodePostal().toString()));
			adresseElem.addContent(new Element("ville",namespace).addContent(currentPersonne.getVille()));
			adresseElem.addContent(new Element("rue",namespace).addContent(currentPersonne.getNomRue()));
			voisin.addContent(adresseElem);

			root.addContent(voisin);			
		}
				
        return root;		
	}

	
	
}
