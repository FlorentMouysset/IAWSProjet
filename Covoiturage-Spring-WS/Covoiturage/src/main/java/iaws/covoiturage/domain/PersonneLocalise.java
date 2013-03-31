package iaws.covoiturage.domain;

import nomenclatureOSMServices.CoordLongLati;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

public class PersonneLocalise {


	private CoordLongLati coordLongLati;
	private Personne personne;
	
	public PersonneLocalise(Adresse adresse, Email email, EtatCivile etatCivile, CoordLongLati coordLongLati) {
		personne = new Personne(adresse, email, etatCivile);
		this.coordLongLati = coordLongLati;
		
	}
	
	@Override
	public String toString() {
		return "PersonneLocalise [coordLongLati=" + coordLongLati
				+ ", personne=" + personne + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordLongLati == null) ? 0 : coordLongLati.hashCode());
		result = prime * result
				+ ((personne == null) ? 0 : personne.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonneLocalise other = (PersonneLocalise) obj;
		if (coordLongLati == null) {
			if (other.coordLongLati != null)
				return false;
		} else if (!coordLongLati.equals(other.coordLongLati))
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		return true;
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
	
	public Email getEmail(){
		return personne.getEmail();
	}
	
	public String getNom(){
		return this.personne.getEtatCivile().getNom();
	}
	
	public String getPrenom(){
		return personne.getEtatCivile().getPrenom();
	}
	
	public String getNomRue(){
		return personne.getAdresse().getNomRue();
	}
	
	public Integer getNumRue(){
		return personne.getAdresse().getNumRue();
	}
	
	public Integer getCodePostal(){
		return personne.getAdresse().getNumPostal();
	}
	
	public String getVille(){
		return personne.getAdresse().getNomVille();
	}
	
	public CoordLongLati getCood(){
		return this.coordLongLati;
	}
	
}
