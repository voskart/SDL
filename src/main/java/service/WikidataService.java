package service;

import org.springframework.stereotype.Service;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

@Service
public class WikidataService {
	
	private static String buildQueryString(String stoneID){
		return ""
				+ "PREFIX dbpedia:<http://dbpedia.org/resource/>"
				+ "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
				+ "PREFIX dbpedia-owl:<http://dbpedia.org/ontology/>"
				+ "PREFIX wikidata: <http://wikidata.dbpedia.org/resource/>"
				+ "SELECT ?abstract"
				+ " WHERE {"
				+ "?stein owl:sameAs wikidata:"
				+ stoneID
				+ "."
				+ "?stein dbpedia-owl:abstract ?abstract"
				+ " filter(langMatches(lang(?abstract),\"de\"))"
				+ "}";
	};

	/**
	 * 
	 * @param stoneID the suffix of the Wikipedia-page
	 * @return the content of the abstract property of that page
	 */
	public String getAbstract(String stoneID){
		String queryString = buildQueryString(stoneID);
		Query query = QueryFactory.create(queryString);
		QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet set = exec.execSelect();
		String wikiAbstract = set.next().toString();
		wikiAbstract=wikiAbstract.replace("( ?abstract = \"", "");
		wikiAbstract=wikiAbstract.replace("\"@de )", "");
		return wikiAbstract;
	}
	
	
	
	public static void main(String[] args) {
		//Beispiel
		WikidataService wds = new WikidataService();
		System.out.println(wds.getAbstract("Q744630"));
    }
}
