package nomenclatureOSMServices;


public class CoordLongLati {

	private Double lon;
	private Double lat;
		
	public CoordLongLati(Double lat, Double lon) {
		super();
		this.lon = lon;
		this.lat = lat;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
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
		CoordLongLati other = (CoordLongLati) obj;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "CoordLongLati [lon=" + lon + ", lat=" + lat + "]";
	}

	public Double getLon() {
		return lon;
	}

	public Double getLat() {
		return lat;
	}



	public Km getDistance(CoordLongLati coord){
		Double latRadThis = Math.PI * this.lat /180;
		Double latRad = Math.PI * coord.lat /180;
		Double lonRadThis = Math.PI * this.lon /180;
		Double lonRad = Math.PI * coord.lon /180;
		Double res =  6371 * Math.acos( Math.cos(latRadThis) * Math.cos(latRad) * Math.cos(lonRadThis-lonRad)+Math.sin(latRadThis) *Math.sin(latRad));
		return new Km(res);
//		System.out.println(coord.lat + " " + this.lat);
//		System.out.println(coord.lat - this.lat);
//		System.out.println(Math.pow((coord.lat - this.lat), 2));
//		System.out.println(Math.pow((coord.lat - this.lat), 2) + Math.pow((coord.lon - this.lon), 1));
//		System.out.println(Math.sqrt( Math.pow((coord.lat - this.lat), 2) + Math.pow((coord.lon - this.lon), 1)));
//		return new Km( Math.sqrt((coord.lat - this.lat) + (coord.lon - this.lon)));
	}
	
}
