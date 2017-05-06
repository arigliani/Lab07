package it.polito.tdp.dizionario.model;


import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	 WordDAO dao= new WordDAO();
	 UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

	public List<String> createGraph(int numeroLettere) {
		 List<String> grafoCompleto=  dao.getAllWordsFixedLength(numeroLettere);
		
		for(String s: grafoCompleto){
			grafo.addVertex(s);		
		}
		
		for(String s:grafo.vertexSet()){
			List<String> temp=  dao.getAllSimilarWords(s, s.length());//questo metodo e lendo perche chiede sepre a DB
			for(String p:temp)
				if(!s.equals(p))
			     grafo.addEdge(s,p );
		}
		
		
		return grafoCompleto;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> grafoCompleto=  Graphs.neighborListOf(grafo,parolaInserita);
		
		
		
		return grafoCompleto;
	}

	public String findMaxDegree(int numeroLettere) {
		 String parolaMax="";
		 int numMax=0;
		 
		 List<String> grafoCompleto=  this.createGraph(numeroLettere);
		 for(String s: grafoCompleto){
				int temp=this.displayNeighbours(s).size();
				if(temp>numMax){
					parolaMax=s;
				}
				
		 }
		 
		return parolaMax;
	}
}
