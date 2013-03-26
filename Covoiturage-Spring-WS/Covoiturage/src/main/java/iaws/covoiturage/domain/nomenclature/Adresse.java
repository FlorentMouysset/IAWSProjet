package iaws.covoiturage.domain.nomenclature;

public class Adresse {

	private int numRue;
	private int numPostal;
	private String rue;
	private String ville; 
	
	public Adresse(int numRue, int numPostal, String nomRue, String nomVille) {
		super();
		this.numRue = numRue;
		this.numPostal = numPostal;
		this.rue = nomRue;
		this.ville = nomVille;
	}
	
	public String getNomRue() {
		return rue;
	}
	public String getNomVille() {
		return ville;
	}
	public int getNumRue() {
		return numRue;
	}
	public int getNumPostal() {
		return numPostal;
	}
	
	
	
	
}
