package iaws.covoiturage.domain;

import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nomenclatureOSMServices.CoordLongLati;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import exceptionsOSMServices.ExceptionInternalError;

public class BDPersonnes {

	private static Map<Integer,PersonneLocalise> personnesLocalisees = null;
	private static Element root = null ;
	private static Document document = null;
	private static BDPersonnes instance = null;
	private static String bdName = null;


	private BDPersonnes(String bdname) {
		bdName = bdname;
		SAXBuilder sxb = new SAXBuilder();
		document = null;
		File file = new File(bdName);
		if(!file.exists()){//Si le fichier n'existe pas
			System.out.println("Info : Un nouveau fichier de BD est créé :" + bdName);
			root = new Element("PersonnesLocalisees");
			document = new Document(root);
			save();
			file = new File(bdName);
		}
		try {
			document = sxb.build(file);
		} catch (Exception e){
			e.printStackTrace();
		}
		root = document.getRootElement();
		personnesLocalisees = new LinkedHashMap<Integer, PersonneLocalise>();
		buildPersonneList();
	}

	public static synchronized BDPersonnes getInstance(String bdNameDemand) throws ExceptionInternalError{
		if(instance==null){
			instance = new BDPersonnes(bdNameDemand);
		}
		File currentFile = new File(bdName);
		File demandFile = new File(bdNameDemand);
		if(!currentFile.equals(demandFile)){ //si demande de chargement d'une BD différentes
			throw new ExceptionInternalError("Conflic dans la BD !\nLa BD actuelle ne correspond pas à la BD demandée. Utilisez disconectBD pour changer de BD");
		}
		return instance;
	}
	
	public synchronized void disconectBD(){
		instance =null;
		personnesLocalisees = null;
		bdName = null;
		document = null;
		root = null;
	}

	private Integer generateNewKey(){
		Set<Integer> keySet = personnesLocalisees.keySet();
		Integer newKey = keySet.size();
		while(keySet.contains(newKey)){
			newKey++;
		}
		return newKey;
	}

	/**
	 * en exclusion mutuelle 
	 * */
	public synchronized Integer addPersonneInBD(PersonneLocalise personne){
		Integer newKey = generateNewKey();
		personnesLocalisees.put(newKey, personne);//ajout virtuel (à la bd)
		//Construction des structures XML
		Element child = new Element("personnelocalisee");
		Element personneElem = new Element("Personne");
		personneElem.addContent(new Element("nom").addContent(personne.getNom()));
		personneElem.addContent(new Element("prenom").addContent(personne.getPrenom()));
		personneElem.addContent(new Element("email").addContent(personne.getEmail().getEmail()));
		child.addContent(personneElem);
		Element adresseElem = new Element("Adresse");
		adresseElem.addContent(new Element("numrue").addContent(personne.getNumRue().toString()));
		adresseElem.addContent(new Element("numpostal").addContent(personne.getCodePostal().toString()));
		adresseElem.addContent(new Element("ville").addContent(personne.getVille()));
		adresseElem.addContent(new Element("rue").addContent(personne.getNomRue()));
		child.addContent(adresseElem);
		Element lonlatElem = new Element("LonLat");
		lonlatElem.addContent(new Element("lat").addContent(personne.getCoordLongLati().getLat().toString()));
		lonlatElem.addContent(new Element("lon").addContent(personne.getCoordLongLati().getLon().toString()));
		child.addContent(lonlatElem);
		Element idElem = new Element("id");
		idElem.addContent(newKey.toString());
		child.addContent(idElem);
		
//		Attribute att1 = new Attribute("xmlns", "http://www.univ-tlse3.fr/Services/Covoiturage");
	//	Namespace namespace = Namespace.getNamespace("http://www.univ-tlse3.fr/Services/Covoiturage" );
		//Namespace namespace2 = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance" );
		//Namespace namespace3 = Namespace.getNamespace("schemaLocation", "http://www.univ-tlse3.fr/Services/Covoiturage bd.xsd" );
	//	namespace3.
				
	//	System.out.println("##"+root.getNamespacePrefix());
		//System.out.println("##"+root.getNamespace());

//		
//		root.addNamespaceDeclaration(namespace2 );
//		root.addNamespaceDeclaration(namespace3 );
//		root.addNamespaceDeclaration(namespace);
//		

//		xmlns="http://www.univ-tlse3.fr/Services/Covoiturage"
//				xsi:schemaLocation="http://www.univ-tlse3.fr/Services/Covoiturage bd.xsd">

		

		
		root.addContent(child);
	//	document.setContent(root);
		save();
//		System.out.println("ici save in bd>" + personne);
		return newKey;
	}

	
	public synchronized void deletePersonneInBD(Integer id){
		personnesLocalisees.remove(id);
		boolean drap = false;
		int index = 0;
		List<Element> l =  root.getChildren();
		while(!drap && index < l.size()){
			Element elem = l.get(index);
			String idStr = elem.getChildText("id");
			Integer currentId = Integer.parseInt(idStr);
			if(  currentId.equals(id)){
				root.removeContent(elem);
				drap = true;
			}			
			index++;
		}		
		save();
	}

	

	/**
	 * Sauve le {@link document} dans le fichier.
	 * @param file : le nom du fichier
	 * */
	private void save() {
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(bdName));
		} catch (java.io.IOException e) {
			e.printStackTrace();//TODO
		}
	}

	private void buildPersonneList(){
		List<Element> plXML = root.getChildren("personnelocalisee");
		Element currentElem;
		Adresse adresse;
		Email email;
		EtatCivile etatCivile;
		CoordLongLati coordLongLati;
		Integer key;

		for(Element elem : plXML){
			currentElem = elem.getChild("Personne");
			etatCivile = new EtatCivile(currentElem.getChildText("nom"), currentElem.getChildText("prenom"));
			email = new Email(currentElem.getChildText("email"));

			currentElem = elem.getChild("Adresse");
			adresse = new Adresse(Integer.parseInt( currentElem.getChildText("numrue")), Integer.parseInt( currentElem.getChildText("numpostal")), currentElem.getChildText("rue"), currentElem.getChildText("ville"));

			currentElem = elem.getChild("LonLat");
			coordLongLati = new CoordLongLati(Double.valueOf( currentElem.getChildText("lat")), Double.valueOf( currentElem.getChildText("lon")));

			key = Integer.parseInt( elem.getChildText("id") );
			personnesLocalisees.put(key, new PersonneLocalise(adresse, email, etatCivile, coordLongLati));
		}
	}

	public Collection<PersonneLocalise> getAllPersonne(){
		return personnesLocalisees.values();
	}

	public PersonneLocalise getPersonneLocalise(Personne personne){
		PersonneLocalise rest = null;
		boolean isFind = false;
		int i = 0;
		PersonneLocalise pl ;
		while(i < personnesLocalisees.size() && !isFind){
			pl = personnesLocalisees.get(i);
			if(pl.getPersonne().equals(personne)){
				rest = pl;
				isFind = true;
			}
		} 
		return rest;
	}


	public boolean mailExisteBD(Email email) {
		boolean isFind= false;
		Set<Integer> keySet = personnesLocalisees.keySet();
		Iterator<Integer> ite = keySet.iterator();
		Integer currentkey;
		while(!isFind && ite.hasNext()){
			currentkey = ite.next();
			isFind = personnesLocalisees.get(currentkey).getEmail().equals(email);
		}
		return isFind;
	}


	public boolean mailExisteSI(Email email) {
		System.out.println("Warning : aucun acces vers le SI de l'université tls3");
		return true;
	}


	public PersonneLocalise get(Integer personneID) {
		return personnesLocalisees.get(personneID);
	}

}
