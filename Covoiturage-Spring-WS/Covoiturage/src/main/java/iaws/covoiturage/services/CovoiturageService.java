package iaws.covoiturage.services;

import iaws.covoiturage.domain.Personne;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.domain.nomenclature.Km;

import java.util.List;

public interface CovoiturageService {

	public CodeErreur addPersonne(EtatCivile etatCivile, Email email, Adresse adresse);
	
	
	public List<Personne> findAllNeighborhood(Personne personne, Km rayon);
	
}
