package iaws.covoiturage.ws.contractfirst;


import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.services.CodeErreur;
import iaws.covoiturage.services.CovoiturageService;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
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

    public CovoiturageEndpoint(CovoiturageService covoiturageService) {
        this.releveNotesService = covoiturageService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CovoiturageRequest")
    @ResponsePayload
    public Element handleCovoiturageRequest(@XPathParam("/AjoutRequest/Persone/nom") String nom,
    										@XPathParam("/AjoutRequest/Persone/prenom") String prenom,
    										@XPathParam("/AjoutRequest/Persone/email") String email,
                                            @XPathParam("/AjoutRequest/Adresse/numrue") Integer numRue,
                                            @XPathParam("/AjoutRequest/Adresse/numpostal") Integer numPostal ) throws Exception {

    	Adresse adresse = new Adresse(numRue, numPostal);
    	Email email2 = new Email(email);
    	EtatCivile etatCivile = new EtatCivile(nom, prenom);
    	CodeErreur codeErreur;
    	
    	codeErreur = releveNotesService.addPersonne(etatCivile, email2, adresse);
    	
    	Element retour;
    	retour = GenerateResponseAjout.createResponse(codeErreur);
    	return retour;
    }

}
