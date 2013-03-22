package iaws.covoiturage.domain.nomenclature;

public class Km {

	private float distance;
	
	
	
	private float getDistance() {
		return distance;
	}



	public Km(float distance) {
		super();
		this.distance = distance;
	}



	public boolean IsLessThat(Km km){
		return distance<km.getDistance();
	}
	
}
