package iaws.covoiturage.domain.nomenclature;

public class CoordLongLati {
	
	@Override
	public String toString() {
		return "CoordLongLati [lon=" + lon + ", lat=" + lat + "]";
	}



	private double lon;
	private double lat;
	

	
	public CoordLongLati(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}



	public Km getDistance(CoordLongLati coord){
		return new Km( Math.sqrt((coord.lat - this.lat) + (coord.lon - this.lon)));
	}
	
}
