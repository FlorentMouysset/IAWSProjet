package iaws.covoiturage.domain;

import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

public class Personne {
	private Adresse adresse;
	private Email email;
	private EtatCivile etatCivile;
	
	public Personne(Adresse adresse, Email email, EtatCivile etatCivile) {
		super();
		this.adresse = adresse;
		this.email = email;
		this.etatCivile = etatCivile;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}
	public Email getEmail() {
		return email;
	}
	public EtatCivile getEtatCivile() {
		return etatCivile;
	}

	
	
	
	
	
}
