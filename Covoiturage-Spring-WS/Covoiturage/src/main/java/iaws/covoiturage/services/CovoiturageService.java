package iaws.covoiturage.services;

import iaws.covoiturage.domain.Personne;
import java.util.List;

import nomenclatureOSMServices.Km;
import exceptionsOSMServices.ExceptionAdresseInvalide;
import exceptionsOSMServices.ExceptionInternalError;

public interface CovoiturageService {

	public Integer addPersonne(Personne personne) 
	throws ExceptionMailDejaUtil, ExceptionMailInvalide, ExceptionAdresseInvalide, ExceptionInternalError;
	
	 
	public List<Personne> findAllNeighborhood(Integer personneID, Km rayon) throws ExceptionInternalError;
	
}
