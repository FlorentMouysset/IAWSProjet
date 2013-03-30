package iaws.covoiturage.domain.nomenclature;

public class Km {

	private double distance;
	
		
	public Km(double distance) {
		super();
		this.distance = distance;
	}



	public boolean IsLessThat(Km km){
		return distance<km.distance;
	}
	
}
