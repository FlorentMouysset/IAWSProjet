package osm
import iaws.covoiturage.domain.nomenclature.Adresse
import iaws.covoiturage.domain.nomenclature.CoordLongLati;
import net.sf.json.groovy.JsonSlurper

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

class OSMServices {

	
	
	def internalClosuregetCoord = { reqStr->
		def latNum
		def lonNum
		println "ici"
		def http = new HTTPBuilder(reqStr)

		 http.request( GET, JSON ) { req ->

			 response.success = { resp, reader ->
				println 'my response handler!'
				assert resp.statusLine.statusCode == 200
				println resp.statusLine


				def slurper = new JsonSlurper()
				def result = slurper.parseText(reader.toString())
				def lat= result.getAt("lat")
				def lon= result.getAt("lon")
				if(lat.size()!=0){
					latNum = lat.get(0)
					lonNum = lon.get(0)
				}else{
					//erreur OSM
					println "non tr"
				}

			}

			response.'404' = { resp ->
				println 'not found!'
			}
		}
		new CoordLongLati(latNum, lonNum)
	}


	public static CoordLongLati getCoord( nomRue,  nomVille, numRue){
		CoordLongLati coord;
		def latNum
		def lonNum
		def reqStr = "http://nominatim.openstreetmap.org/search/fr" +"/"+nomVille +  "/" + nomRue + "/" + numRue +"?format=json"
		
println reqStr

def http = new HTTPBuilder(reqStr)

println "http ok"
		
		 http.request( GET, JSON ) { req ->

			 response.success = { resp, reader ->
				println 'my response handler!'
				assert resp.statusLine.statusCode == 200
				println resp.statusLine


				def slurper = new JsonSlurper()
				def result = slurper.parseText(reader.toString())
				def lat= result.getAt("lat")
				def lon= result.getAt("lon")
				if(lat.size()!=0){
					latNum = lat.get(0)
					lonNum = lon.get(0)
				}else{
					//erreur OSM
					println "non tr"
				}

			}

			response.'404' = { resp ->
				println 'not found!'
			}
		}
		 println "fin " + latNum + " " +lonNum
		 
		 
		new CoordLongLati(Double.valueOf(latNum), Double.valueOf(lonNum))
		
	}

}

