package iaws.covoiturage.domain.nomenclature;

public class Adresse {

	private int numRue;
	private int numPostal;
	public int getNumRue() {
		return numRue;
	}
	public int getNumPostal() {
		return numPostal;
	}
	public Adresse(int numRue, int numPostal) {
		super();
		System.out.println("Adresse construct");
		this.numRue = numRue;
		this.numPostal = numPostal;
	}
	
	
	
	
}
