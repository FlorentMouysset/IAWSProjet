package iaws.covoiturage.ws.contractfirst;

import iaws.covoiturage.domain.BDPersonnes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

import exceptionsOSMServices.ExceptionInternalError;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("application-context.xml")
public class TestIntegrationCovoiturageOkEndPoint {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @After
    public void cleanBD(){
    	BDPersonnes bd = null;
		try {
			bd = BDPersonnes.getInstance("bd.xml");
		} catch (ExceptionInternalError e) {
			e.printStackTrace();
		}
    	bd.deletePersonneInBD(5);
    }
    
    @Test
    public void covoiturageOkEndpoint() throws Exception {
    	Source requestPayload = new StreamSource(new ClassPathResource("CovoiturageRequest_add_ok.xml").getInputStream() );
        Source responsePayload = new StreamSource(new ClassPathResource("Covoiturage_ok.xml").getInputStream());
        
        mockClient.sendRequest(withPayload(requestPayload)).
                andExpect(payload(responsePayload));
    }
    

    
}
