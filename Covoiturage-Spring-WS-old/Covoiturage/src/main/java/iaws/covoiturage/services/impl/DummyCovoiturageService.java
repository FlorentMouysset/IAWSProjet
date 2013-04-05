package iaws.covoiturage.services.impl;

import iaws.covoiturage.domain.BDPersonnes;
import iaws.covoiturage.domain.Personne;
import iaws.covoiturage.domain.PersonneLocalise;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.services.CovoiturageService;
import iaws.covoiturage.services.ExceptionMailDejaUtil;
import iaws.covoiturage.services.ExceptionMailInvalide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nomenclatureOSMServices.CoordLongLati;
import nomenclatureOSMServices.Km;

import exceptionsOSMServices.ExceptionAdresseInvalide;
import exceptionsOSMServices.ExceptionInternalError;

import main.OSMServices;

public class DummyCovoiturageService implements CovoiturageService {

	static private BDPersonnes bd=null;

	public DummyCovoiturageService(String bdName) throws ExceptionInternalError{
		createBD(bdName);
	}
	
	public DummyCovoiturageService() throws ExceptionInternalError{
		createBD("bd.xml");
	}
	
	static private void createBD(String bdName) throws ExceptionInternalError{
		bd = BDPersonnes.getInstance(bdName);
	}
	
	public void detachBD(){
		bd.disconectBD();
		bd=null;
	}
	
	private void verifBD() throws ExceptionInternalError{
		if(bd==null){
			throw new ExceptionInternalError("La BD a été détachée!");
		}
	}
	
	private static boolean verifEmail(String email){
		String regex = "^[a-zA-Z][a-zA-Z0-9]*@univ-tlse3.fr$";
		return email.matches(regex);
	}
	
	public Integer addPersonne(Personne personne ) 
	throws ExceptionMailDejaUtil, ExceptionMailInvalide, ExceptionAdresseInvalide, ExceptionInternalError{
		verifBD();
		Integer userId=-1;
		Email email = personne.getEmail();
		Adresse adresse = personne.getAdresse();

		//Vérifie que l'adresse email n'existe pas déjà dans la bd => si true alors dejà attribué
		if(bd.mailExisteBD(email)){
			System.out.println("email existe dejà");
			throw new ExceptionMailDejaUtil("L'adresse mail exsite déjà");
		}else if( !verifEmail(email.getEmail()) || bd.mailExisteSI(email)==false){//vérifie que l'email existe dans le systeme d'information de la fac
			System.out.println("email invalide");
			throw new ExceptionMailInvalide("L'adresse email est invalide");
		}
		
		//Récupere les coordonnées par OSM
		CoordLongLati coordLongLati = OSMServices.getCoord(adresse.getNomRue(), adresse.getNomVille(), adresse.getNumRue());
		
		//Crée une personne localisé
		PersonneLocalise personneLocalise = new PersonneLocalise(personne, coordLongLati);
		userId = bd.addPersonneInBD(personneLocalise);//l'ajoute à la BD
		return userId;
	}

	public List<Personne> findAllNeighborhood(Integer personneID, Km rayon) throws ExceptionInternalError {
		verifBD();
		List<Personne> listeVoisins = new ArrayList<Personne>();//création liste de voisin initialisé à vide
		Collection<PersonneLocalise> listeTous = bd.getAllPersonne();//récupération de la liste de toutes les personne localisée de la BD
		PersonneLocalise personne = bd.get(personneID);//récupèration de la personne à partir de son ID
		listeTous.remove(personne);//on retire de la liste la personne qui recherche ses voisins
		CoordLongLati coordVoisin = personne.getCoordLongLati();//récupération des coordonnée GPS de la personne localisée de la personne simple passé en paramètre
		CoordLongLati coordCourante;
		for(PersonneLocalise personneCourante : listeTous){//Pour toutes les personnes localisées de la BD
			coordCourante = personneCourante.getCoordLongLati();//récupèration des coordonnées dela personne courante 
			if(coordCourante.getDistance(coordVoisin).IsLessThat(rayon)){//si la distance entre les deux personnes est inférieur au un rayon
				listeVoisins.add(personneCourante.getPersonne());//ajout a la liste des voisins
			}
		}
		
		return listeVoisins;
	}

	
	
}
