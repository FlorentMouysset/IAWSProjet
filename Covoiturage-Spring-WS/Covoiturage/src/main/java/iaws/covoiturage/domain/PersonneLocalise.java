package iaws.covoiturage.domain;

import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import nomenclatureOSMServices.CoordLongLati;

public class PersonneLocalise extends Personne {

	private CoordLongLati coordLongLati;
	
	public PersonneLocalise(Adresse adresse, Email email, EtatCivile etatCivile, CoordLongLati coordLongLati) {
		super(adresse, email, etatCivile);
		this.coordLongLati = coordLongLati;
		
	}
	
	@Override
	public String toString() {
		return "PersonneLocalise [coordLongLati=" + coordLongLati
				+ ", personne=" + super.toString() + "]";
	}

	public PersonneLocalise(Personne personne, CoordLongLati coordLongLati ){
		super(personne);
		this.coordLongLati = coordLongLati;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((coordLongLati == null) ? 0 : coordLongLati.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonneLocalise other = (PersonneLocalise) obj;
		if (coordLongLati == null) {
			if (other.coordLongLati != null)
				return false;
		} else if (!coordLongLati.equals(other.coordLongLati))
			return false;
		return true;
	}

	public CoordLongLati getCoordLongLati() {
		return coordLongLati;
	}

	public void setCoordLongLati(CoordLongLati coordLongLati) {
		this.coordLongLati = coordLongLati;
	}

	public boolean equalsPersonne(Personne obj) {
		if (this == obj)
			return true;
		if (super.equals(obj))
			return true;
		return false;
	}

	public Personne getPersonne(){
		return this;
	}
}
