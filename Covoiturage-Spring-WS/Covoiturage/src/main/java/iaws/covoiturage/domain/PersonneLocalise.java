package iaws.covoiturage.domain;

import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.CoordLongLati;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

public class PersonneLocalise {

	private CoordLongLati coordLongLati;
	private Personne personne;
	
	public PersonneLocalise(Adresse adresse, Email email, EtatCivile etatCivile, CoordLongLati coordLongLati) {
		personne = new Personne(adresse, email, etatCivile);
		this.coordLongLati = coordLongLati;
		
	}

	public PersonneLocalise(Personne personne, CoordLongLati coordLongLati ){
		this.personne = personne;
		this.coordLongLati = coordLongLati;
	}
	
	public CoordLongLati getCoordLongLati() {
		return coordLongLati;
	}

	public void setCoordLongLati(CoordLongLati coordLongLati) {
		this.coordLongLati = coordLongLati;
	}

	public Personne getPersonne(){
		return this.personne;
	}
	
}
