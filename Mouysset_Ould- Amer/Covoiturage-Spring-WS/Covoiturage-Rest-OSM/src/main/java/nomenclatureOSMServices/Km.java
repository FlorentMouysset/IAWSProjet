package nomenclatureOSMServices;

public class Km {

	private Double distance;
	
		
	public Km(Double distance) {
		super();
		this.distance = distance;
	}

	public Double getDistance(){
		return distance;
	}

	public boolean IsLessThat(Km km){
		return distance<km.distance;
	}
	
}
