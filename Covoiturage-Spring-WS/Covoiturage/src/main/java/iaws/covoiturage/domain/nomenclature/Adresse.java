package iaws.covoiturage.domain.nomenclature;

public class Adresse {


	private Integer numRue;
	private Integer numPostal;
	private String rue;
	private String ville; 
	
	public Adresse(Integer numRue, Integer numPostal, String nomRue, String nomVille) {
		super();
		this.numRue = numRue;
		this.numPostal = numPostal;
		this.rue = nomRue;
		this.ville = nomVille;
	}
	
	
	@Override
	public String toString() {
		return "Adresse [numRue=" + numRue + ", numPostal=" + numPostal
				+ ", rue=" + rue + ", ville=" + ville + "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numPostal == null) ? 0 : numPostal.hashCode());
		result = prime * result + ((numRue == null) ? 0 : numRue.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
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
		Adresse other = (Adresse) obj;
		if (numPostal == null) {
			if (other.numPostal != null)
				return false;
		} else if (!numPostal.equals(other.numPostal))
			return false;
		if (numRue == null) {
			if (other.numRue != null)
				return false;
		} else if (!numRue.equals(other.numRue))
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}

	public String getNomRue() {
		return rue;
	}
	public String getNomVille() {
		return ville;
	}
	public Integer getNumRue() {
		return numRue;
	}
	public Integer getNumPostal() {
		return numPostal;
	}
	
	
	
	
}
