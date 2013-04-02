package iaws.covoiturage.ws.contractfirst;


import iaws.covoiturage.domain.Personne;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.services.CovoiturageService;
import iaws.covoiturage.services.ExceptionMailDejaUtil;
import iaws.covoiturage.services.ExceptionMailInvalide;

import java.util.List;

import nomenclatureOSMServices.Km;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
//import org.w3c.dom.Element;
import org.jdom2.Element;

import exceptionsOSMServices.ExceptionAdresseInvalide;
import exceptionsOSMServices.ExceptionInternalError;

/**
 */
@Endpoint
public class CovoiturageEndpoint {
    private CovoiturageService covoiturageService;

    private static final String NAMESPACE_URI = "http://www.univ-tlse3.fr/Services/Covoiturage";
    
    @Autowired
    public CovoiturageEndpoint(CovoiturageService covoiturageService) {
        this.covoiturageService = covoiturageService;
        System.out.println("constru endpoint");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AjoutRequest")
    @Namespace(prefix = "s", uri = NAMESPACE_URI)
    @ResponsePayload
    public Element handleAjoutRequest(@XPathParam("/s:AjoutRequest/s:Personne/s:nom") String nom,
    										@XPathParam("/s:AjoutRequest/s:Personne/s:prenom") String prenom,
    										@XPathParam("/s:AjoutRequest/s:Personne/s:email") String emailStr,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:numrue") Integer numRue,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:numpostal") Integer numPostal,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:rue") String nomRue,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:ville") String nomVile)
                                            throws Exception {

    	Integer userId = -1;
    	Integer codeErreur=-1;

    	Adresse adresse = new Adresse(numRue, numPostal, nomRue, nomVile);
    	Email email = new Email(emailStr);
    	EtatCivile etatCivile = new EtatCivile(nom, prenom);
    	Personne personne = new Personne(adresse, email, etatCivile);
    	
    	try{
    		userId = covoiturageService.addPersonne(personne);
    	}catch(ExceptionMailDejaUtil e){
    		codeErreur= 100;
    	}catch(ExceptionMailInvalide e){
    		codeErreur= 110;
    	}catch(ExceptionAdresseInvalide e){
    		codeErreur=200;
    	}catch(ExceptionInternalError e){
    		codeErreur=120;
    	}

    	Element retour;
    	retour = GenerateResponseAjout.createResponse(codeErreur, userId);
    	return retour;
    }

    
    
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "VoisinsRequest")
    @Namespace(prefix = "s", uri = NAMESPACE_URI)
    @ResponsePayload
    public Element handleVoisinsRequest(@XPathParam("/s:VoisinsRequest/s:idUtil") Integer idUtil,
    										@XPathParam("/s:VoisinsRequest/s:rayon") Double rayon)
    									    throws Exception {

    	List<Personne> personnes = null;
    	personnes = covoiturageService.findAllNeighborhood(idUtil, new Km(rayon)); 
    	Element retour = GenerateResponseVoisins.createResponse(personnes);
    	return retour;
    }

    
    
}
