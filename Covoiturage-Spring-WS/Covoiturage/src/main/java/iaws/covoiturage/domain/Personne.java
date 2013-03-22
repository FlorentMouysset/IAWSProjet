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

	@Override
	public boolean equals(Object obj) {
		boolean rest = false;
		if(obj instanceof Personne){
			Personne personneParam = (Personne) obj;
			if(personneParam.getAdresse().equals(adresse) &&
					personneParam.getEmail().equals(email) &&
					personneParam.getEtatCivile().equals(etatCivile)){
				rest = true;
			}
		}
		return rest;
	}

	
	

	
	
	
	
	
}
