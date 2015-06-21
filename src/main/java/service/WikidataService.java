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
	
	private static String buildQueryString(String name){
		return ""
				+ "PREFIX dbpedia: <http://dbpedia.org/resource/>"
				+ "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
				+ "select ?abstract"
				+ " where {"
				+ "dbpedia:"
				+ name
				+ " dbpedia-owl:abstract ?abstract."
				+ "filter(langMatches(lang(?abstract),\"de\"))"
				+ "}";
	};

	/**
	 * 
	 * @param name the suffix of the Wikipedia-page
	 * @return the content of the abstract property of that page
	 */
	private String getAbstract(String name){
		String queryString = buildQueryString(name);
		Query query = QueryFactory.create(queryString);
		QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet set = exec.execSelect();
		return ResultSetFormatter.asText(set);		
	}
	
	
	
	public static void main(String[] args) {
		//Beispiel
		WikidataService wds = new WikidataService();
		System.out.println(wds.getAbstract("Migmatite"));
    }
}
