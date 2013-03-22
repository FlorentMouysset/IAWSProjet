package iaws.covoiturage.domain.nomenclature;

public class EtatCivile {

	private String nom;
	private String prenom;
	
	
	public EtatCivile(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}

	@Override
	public boolean equals(Object obj) {
		boolean rest = false;
		if(obj instanceof EtatCivile){
			EtatCivile etatCivileParam = (EtatCivile) obj;
			if(etatCivileParam.getNom().equals(nom) &&
					etatCivileParam.getPrenom().equals(prenom)){
				rest = true;
			}
		}
		return rest;
	}
	
	
	
}
