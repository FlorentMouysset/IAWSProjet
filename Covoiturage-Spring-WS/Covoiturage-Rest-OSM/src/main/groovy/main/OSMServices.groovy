package main


import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import net.sf.json.groovy.JsonSlurper
import nomenclatureOSMServices.CoordLongLati
import exceptionsOSMServices.ExceptionAdresseInvalide
import exceptionsOSMServices.ExceptionInternalError
import groovy.*
import groovyx.net.http.*



class OSMServices {
	static def latStr
	static def lonStr

/**
 * Vérifie que l'adresse soit correct : une seule node.
 * */
	private static verif(result) throws ExceptionAdresseInvalide{
		def lat= result.getAt("lat")//recuperation les données
		def lon= result.getAt("lon")
		def type = result.getAt("osm_type")
		def nodefind = false
		def valide = false
		def index = -1
		
		type.each{//pour tout les osm_type
			index++
			if(it == "node" && !nodefind){//si on trouve une node pour la prenière fois
				nodefind = true //alors la node est ok mais ...
				valide = true
				latStr = lat.get(index)
				lonStr = lon.get(index)
			}else if(it == "node" && nodefind){//si on trouve une autre node alors 
				valide=false//l'adresse n'est pas bonne
			}
		}

		if(!valide){		
			throw new ExceptionAdresseInvalide("Adresse invalide");
		}
	}
	
	
	
	public static CoordLongLati getCoord(String nomRue, String nomVille, int numRue) throws ExceptionAdresseInvalide, ExceptionInternalError{
		CoordLongLati coord;
		def reqStr = "http://nominatim.openstreetmap.org/search/fr" +"/"+nomVille +  "/" + nomRue + "/" + numRue +"?format=json"
		reqStr = reqStr.replaceAll(" ", "-")
		
		//build the request
		def http = new HTTPBuilder(reqStr)
		
		//send the request
		http.request( GET, JSON ) { req ->

			response.success = { resp, reader ->
				if(resp.statusLine.statusCode != 200){
					throw new ExceptionInternalError("La requete vers OSM retourne le code " + resp.statusLine.statusCode);
				}
				def slurper = new JsonSlurper()//créé le parseur json
				def result = slurper.parseText(reader.toString())
				verif(result)//verification
			}
			response.'404' = { resp ->
					throw new ExceptionInternalError("OSM n'est pas accsesible");
			}
		}

		new CoordLongLati(Double.valueOf(latStr), Double.valueOf(lonStr))//retourne une nouvelle coordonnée
	}

}

