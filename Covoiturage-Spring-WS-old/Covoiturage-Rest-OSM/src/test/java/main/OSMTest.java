package main;

import nomenclatureOSMServices.CoordLongLati;
import exceptionsOSMServices.ExceptionAdresseInvalide;
import junit.framework.TestCase;

public class OSMTest extends TestCase {

	
	public void testOSMOKadresseRamonville(){
		CoordLongLati coor=null;
		try {
			coor = OSMServices.getCoord("Avenue Tolosane", "Ramonville-Saint-Agne",65);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
		assertEquals("CoordLongLati [lon=1.473674, lat=43.5466585]", coor.toString());		
	}

	
	public void testOSMOKadresseUPS(){
		CoordLongLati coor=null;
		try {
			coor = OSMServices.getCoord("route de Narbonne", "toulouse",118);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
		assertEquals("CoordLongLati [lon=1.4630301, lat=43.5615654]", coor.toString());		
	}

	public void testOSMOKadresseFacPharmacie(){
		CoordLongLati coor=null;
		try {
			coor = OSMServices.getCoord("Avenue de Rangueil", "Toulouse",141);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
		assertEquals("CoordLongLati [lon=1.468509, lat=43.5675265]", coor.toString());
	}

	public void testOSMOKadresseSMML(){
		CoordLongLati coor=null;
		try {
			coor = OSMServices.getCoord("Grande Rue Saint-Michel", "Toulouse",1);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
		assertEquals("CoordLongLati [lon=1.4476851, lat=43.5845238]", coor.toString());
	}

	public void testOSMOKadresseJJ(){
		CoordLongLati coor=null;
		try {
			coor = OSMServices.getCoord("Allées Jean Jaurès", "toulouse",15);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
		assertEquals("CoordLongLati [lon=1.4486949, lat=43.6060997]", coor.toString());
	}

	public void testOSMOKadresse3Cocus(){
		CoordLongLati coor=null;
		try {
			coor = OSMServices.getCoord("Chemin des Izards", "toulouse",10);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
		assertEquals("CoordLongLati [lon=1.4442692, lat=43.637368]", coor.toString());
	}
	
	
	public void testOSMKOadresse(){
		try {
			OSMServices.getCoord("Narbonne", "toulouse",1180);
		} catch (ExceptionAdresseInvalide e) {
			assert(true);
		}catch(Exception e){
			assert(false);		
		}
		assert(false);		
	}

	public void testOSMadresseInterdite(){
		try {
			OSMServices.getCoord("Narbo\"nne%", "+tou louse",1180);
		} catch (ExceptionAdresseInvalide e) {
			assert(true);
		}catch(Exception e){
			assert(false);		
		}
		assert(false);		
	}

	
}
