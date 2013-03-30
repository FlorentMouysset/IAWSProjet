package tmp;



import main.OSMServices;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.CoordLongLati;



public class Tmp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Adresse adresse = new Adresse(13, 31500, "drouet", "toulouse");
		// TODO Auto-generated method stub
		CoordLongLati coor = OSMServices.getCoord("drouet", "toulouse",13);
		System.out.println(coor.toString());
	}

}
