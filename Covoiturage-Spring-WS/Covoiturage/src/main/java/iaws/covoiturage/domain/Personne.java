package iaws.covoiturage.domain;

import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;

public class Personne {
	private Adresse adresse;
	private Email email;
	private EtatCivile etatCivile;
	
	@Override
	public String toString() {
		return "Personne [adresse=" + adresse + ", email=" + email
				+ ", etatCivile=" + etatCivile + "]";
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((etatCivile == null) ? 0 : etatCivile.hashCode());
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
		Personne other = (Personne) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (etatCivile == null) {
			if (other.etatCivile != null)
				return false;
		} else if (!etatCivile.equals(other.etatCivile))
			return false;
		return true;
	}

	public String getNom() {
		return etatCivile.getNom();
	}

	public String getPrenom() {
		return etatCivile.getPrenom();
	}

	public Integer getNumRue() {
		return adresse.getNumRue();
	}

	public String getNomRue() {
		return adresse.getNomRue();
	}

	public Integer getCodePostal() {
		return adresse.getNumPostal();
	}

	public String getVille() {
		return adresse.getNomVille();
	}
	
}
