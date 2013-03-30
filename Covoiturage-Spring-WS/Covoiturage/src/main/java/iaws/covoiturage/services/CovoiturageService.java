package iaws.covoiturage.services;

import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

public interface CovoiturageService {

	public int addPersonne(EtatCivile etatCivile, Email email, Adresse adresse) 
	throws ExceptionMailDejaUtil, ExceptionMailInvalide, ExceptionAdresseInvalide;
	
	
	//public List<Personne> findAllNeighborhood(Personne personne, Km rayon);
	
}
