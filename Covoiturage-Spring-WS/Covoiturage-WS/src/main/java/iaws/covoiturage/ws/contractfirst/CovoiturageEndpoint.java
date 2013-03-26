package iaws.covoiturage.ws.contractfirst;


//import iaws.covoiturage.domain.nomenclature.Adresse;
//
//import iaws.covoiturage.domain.nomenclature.Email;
//import iaws.covoiturage.domain.nomenclature.EtatCivile;
//import iaws.covoiturage.services.CodeErreur;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.services.CodeErreur;
import iaws.covoiturage.services.CovoiturageService;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.w3c.dom.Element;
//import org.w3c.dom.Node;

//import java.text.SimpleDateFormat;
//import org.jdom.xpath.XPath;
import org.springframework.beans.factory.annotation.Autowired;
//import org.jdom.JDOMException;
//import org.jdom.Element;
//import org.jdom.Namespace;
//import org.jdom.Element;
//import java.util.Date;

//import javax.xml.soap.Node;

/**
 */
@Endpoint
public class CovoiturageEndpoint {
    private CovoiturageService releveNotesService;

    private static final String NAMESPACE_URI = "http://www.univ-tlse3.fr/Services/Covoiturage";
//    private static final String NAMESPACE_URI = "http://www.univ-tlse3.fr/co/schemas";

    
    

  //  private XPath startDateExpression;

 //   private XPath endDateExpression;

//   private XPath nameExpression;


 //   @Autowired
 //   public CovoiturageEndpoint( CovoiturageService releveNotesService)
  //      throws JDOMException {
   //   this.releveNotesService = releveNotesService;
//    	  System.out.println("cons endpoint deb");
//      org.jdom.Namespace namespace = org.jdom.Namespace.getNamespace("co", NAMESPACE_URI);
//
//      startDateExpression = XPath.newInstance("//co:StartDate");
//      startDateExpression.addNamespace(namespace);
//      System.out.println("cons endpoint fin");
//      endDateExpression = XPath.newInstance("//hr:EndDate");
//      endDateExpression.addNamespace(namespace);

//      nameExpression = XPath.newInstance("concat(//hr:FirstName,' ',//hr:LastName)");
//      nameExpression.addNamespace(namespace);
    //}
//
    
  //  @PayloadRoot(localPart = "orderRequest", namespace = "http://samples")
  //  @Namespace(prefix = "s", uri="http://samples")
    
   // @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AjoutRequest")  
   // @Namespace(prefix = "s", uri = NAMESPACE_URI)
 //   @ResponsePayload
//    public Order getOrder(@XPathParam("/s:orderRequest/@id") int orderId) {
  //  public void handleAjoutRequest( @XPathParam("/s:AjoutRequest/s:Persone/s:nom") String ajoutRequest)               
//   public void handleAjoutRequest( @XPathParam("/") String ajoutRequest)               
   //     throws Exception {
 //   	System.out.println("deb handler !");
   //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   //   System.out.println("inter handler !>" + ajoutRequest.length() + "<");
    //  System.out.println("inter handler !>" + ajoutRequest + "<");
  //	System.out.println("fin inter handler !");
      
      
    //  Date startDate = null;
      /*try{
    	  startDate = dateFormat.parse(startDateExpression.valueOf(ajoutRequest));
      }catch(Exception e){
    	  e.printStackTrace();
      }*/
  //    Date endDate = dateFormat.parse(endDateExpression.valueOf(holidayRequest));
      //String name = nameExpression.valueOf(holidayRequest);
     // System.out.println(">" + startDate);
    //  return null;
    //  humanResourceService.bookHoliday(startDate, endDate, name);
   // }
    
    @Autowired
    public CovoiturageEndpoint(CovoiturageService covoiturageService) {
        this.releveNotesService = covoiturageService;
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
    	CodeErreur codeErreur;
    	System.out.println( "nom :"+ nom + ".prenom :" + prenom + ".email:"  + email + ".numRue:" + numRue + ".numPostal:" + numPostal+".");
    	System.out.println("deb 2 handle request");

    	Adresse adresse = new Adresse(numRue, numPostal, nomRue, nomVile);
    	
    	System.out.println("deb handle inter -1 request");
    	
    	Email email2 = new Email(email);
    	EtatCivile etatCivile = new EtatCivile(nom, prenom);
    	
    	System.out.println("inter 0 handle ajout request");
    	codeErreur = releveNotesService.addPersonne(etatCivile, email2, adresse);
    	System.out.println("inter 1 handle ajout request");

    	Element retour;
    	retour = GenerateResponseAjout.createResponse(codeErreur);
    	System.out.println("fin handle ajout request");
    	return retour;
    }

}
