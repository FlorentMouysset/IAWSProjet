package iaws.covoiturage.services.impl;

import iaws.covoiturage.domain.BDPersonnes;
import iaws.covoiturage.domain.Personne;
import iaws.covoiturage.domain.PersonneLocalise;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.CoordLongLati;
import iaws.covoiturage.domain.nomenclature.Email;
import iaws.covoiturage.domain.nomenclature.EtatCivile;
import iaws.covoiturage.domain.nomenclature.Km;
import iaws.covoiturage.services.CovoiturageService;
import iaws.covoiturage.services.ExceptionAdresseInvalide;
import iaws.covoiturage.services.ExceptionMailDejaUtil;
import iaws.covoiturage.services.ExceptionMailInvalide;

import java.util.ArrayList;
import java.util.List;

public class DummyCovoiturageService implements CovoiturageService {

	public int addPersonne(EtatCivile etatCivile, Email email, Adresse adresse) 
	throws ExceptionMailDejaUtil, ExceptionMailInvalide, ExceptionAdresseInvalide{
		int userId=-1;
		
		//Vérifie que l'adresse email n'existe pas déjà dans la bd => si true alors dejà attribué
		if(BDPersonnes.mailExisteBD(email)){
			throw new ExceptionMailDejaUtil();
		}else if(BDPersonnes.mailExisteSI(email)==false){//vérifie que l'email existe dans le systeme d'information de la fac
			throw new ExceptionMailInvalide();
		}
		
		Personne personne = new Personne(adresse, email, etatCivile);
		
		CoordLongLati coordLongLati = new CoordLongLati(1,1);
		//TODO ajouter les coordonnées longitude + latidude
		//personne.setCoordLongLati(coordLongLati);
		PersonneLocalise personneLocalise = new PersonneLocalise(personne, coordLongLati);
		BDPersonnes.addPersonneInBD(personneLocalise);
		
		return userId;
	}

	public List<Personne> findAllNeighborhood(Personne personne, Km rayon) {
		List<Personne> listeVoisins = new ArrayList<Personne>();//création liste de voisin initialisé à vide
		List<PersonneLocalise> listeTous = BDPersonnes.getAllPersonne();//récupération de la liste de toutes les personne localisée de la BD
		BDPersonnes bd = new BDPersonnes();
		CoordLongLati coordVoisin = bd.getPersonneLocalise(personne).getCoordLongLati();//récupération des coordonnée GPS de la personne localisée de la personne simple passé en paramètre
		CoordLongLati coordCourante;
		
		for(PersonneLocalise personneCourante : listeTous){//Pour toutes les personnes localisées de la BD
			coordCourante = personneCourante.getCoordLongLati();//récupèration des coordonnées dela personne courante 
			if(coordCourante.getDistance(coordVoisin).IsLessThat(rayon)){//si la distance entre les deux personnes est inférieur au un rayon
				listeVoisins.add(personneCourante.getPersonne());//ajout a la listed es voisins
			}
		}
		
		return listeVoisins;
	}

	
	
}
