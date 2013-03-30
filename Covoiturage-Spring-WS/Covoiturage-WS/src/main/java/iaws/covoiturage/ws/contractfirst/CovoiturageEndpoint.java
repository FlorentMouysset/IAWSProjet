package iaws.covoiturage.ws.contractfirst;


import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.services.CovoiturageService;
import iaws.covoiturage.services.ExceptionAdresseInvalide;
import iaws.covoiturage.services.ExceptionMailDejaUtil;
import iaws.covoiturage.services.ExceptionMailInvalide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.w3c.dom.Element;

/**
 */
@Endpoint
public class CovoiturageEndpoint {
    private CovoiturageService releveNotesService;

    private static final String NAMESPACE_URI = "http://www.univ-tlse3.fr/Services/Covoiturage";
    
    @Autowired
    public CovoiturageEndpoint(CovoiturageService covoiturageService) {
        this.releveNotesService = covoiturageService;
        System.out.println("constru endpoint");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AjoutRequest")
    @Namespace(prefix = "s", uri = NAMESPACE_URI)
    @ResponsePayload
    public Element handleAjoutRequest(@XPathParam("/s:AjoutRequest/s:Persone/s:nom") String nom,
    										@XPathParam("/s:AjoutRequest/s:Persone/s:prenom") String prenom,
    										@XPathParam("/s:AjoutRequest/s:Persone/s:email") String email,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:numrue") Integer numRue,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:numpostal") Integer numPostal,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:rue") String nomRue,
                                            @XPathParam("/s:AjoutRequest/s:Adresse/s:ville") String nomVile)
                                            throws Exception {

    	System.out.println("deb handle ajout request");
    	int userId = -1;
    	int codeErreur=-1;
    	System.out.println( "nom :"+ nom + ".prenom :" + prenom + ".email:"  + email + ".numRue:" + numRue + ".numPostal:" + numPostal+".");
    	System.out.println("deb 2 handle request");

    	Adresse adresse = new Adresse(numRue, numPostal, nomRue, nomVile);
    	
    	System.out.println("deb handle inter -1 request");
    	
    	Email email2 = new Email(email);
    	EtatCivile etatCivile = new EtatCivile(nom, prenom);
    	
    	System.out.println("inter 0 handle ajout request");
    	try{
    		userId = releveNotesService.addPersonne(etatCivile, email2, adresse);
    	}catch(ExceptionMailDejaUtil e){
    		codeErreur= 100;
    	}catch(ExceptionMailInvalide e){
    		codeErreur= 110;
    	}catch(ExceptionAdresseInvalide e){
    		codeErreur=200;
    	}
    	System.out.println("inter 1 handle ajout request");

    	Element retour;
    	retour = GenerateResponseAjout.createResponse(codeErreur, userId);
    	System.out.println("fin handle ajout request");
    	return retour;
    }

}
