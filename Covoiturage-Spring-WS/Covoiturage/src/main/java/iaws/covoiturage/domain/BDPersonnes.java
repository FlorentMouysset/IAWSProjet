package iaws.covoiturage.domain;

import java.util.List;

public class BDPersonnes {

	private List<PersonneLocalise> personnesLocalisees;
	
	
	public static void addPersonneInBD(PersonneLocalise personne){
		
	}
	
	
	public static List<PersonneLocalise> getAllPersonne(){
		return null;
	}
	
	public PersonneLocalise getPersonneLocalise(Personne personne){
		PersonneLocalise rest = null;
		boolean isFind = false;
		int i = 0;
		PersonneLocalise pl ;
		
		while(i < personnesLocalisees.size() && !isFind){
			pl = personnesLocalisees.get(i);
			if(pl.getPersonne().equals(personne)){
				rest = pl;
				isFind = true;
			}
		}
		
		return rest;
	}
	
}
