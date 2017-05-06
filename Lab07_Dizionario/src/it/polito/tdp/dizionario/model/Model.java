package it.polito.tdp.dizionario.model;



import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	private WordDAO dao= new WordDAO();
	private UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	private String parolaMax="";
	private int numMax=0;
	 

	public List<String> createGraph(int numeroLettere) {
		 List<String> grafoCompleto=  dao.getAllWordsFixedLength(numeroLettere);
		
		for(String s: grafoCompleto){
			grafo.addVertex(s);		
		}
		
		for(String s:grafo.vertexSet()){	
			for(String p:grafo.vertexSet())
				if(!s.equals(p))
					if(check(s,p))
			             grafo.addEdge(s,p );
		}
		
		
		return grafoCompleto;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> grafoCompleto=  Graphs.neighborListOf(grafo,parolaInserita);	
		
		
		return grafoCompleto;
	}

	public String findMaxDegree(int numeroLettere) {
		
		 
		 List<String> grafoCompleto=  this.createGraph(numeroLettere);
		 for(String s: grafoCompleto){
				int temp=this.displayNeighbours(s).size();
			//	System.out.println("parola: "+s+" dimensione grado: "+temp+"\n");
				if(temp>numMax){
					parolaMax=s;
					numMax=temp;
				}
				
		 }
		 
		return parolaMax;
	}
	
	
	private boolean check(String parola, String temp) {
		int cont=0;
		for(int i=0; i<parola.length(); i++){
			if(parola.charAt(i)!=temp.charAt(i))
				cont++;
			
		}
		
		if(cont>1)
			return false;
		else return true;
			
		
		
	}
}
