package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.Adiacenza;
import it.polito.tdp.food.db.FoodDao;
import it.polito.tdp.food.db.Vicino;

public class Model {
	
	private Graph<Food, DefaultWeightedEdge> graph;
	private Map<Integer,Food> map;
	private FoodDao dao;
	

	public void creaGrafo(int n) {
	this.graph= new SimpleWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
	if(this.dao.listAllFoodByPOrtion(n, map)==null)
	{
		System.out.println("EErrore lettura vertex!\n");
		return;
	}

	System.out.println(this.dao.listAllFoodByPOrtion(n, map));
	Graphs.addAllVertices(this.graph, this.dao.listAllFoodByPOrtion(n, map));
	
	if(this.dao.listAdiacenze( map)==null)
	{
		System.out.println("EErrore lettura edge!\n");
		return;
	}
	
	for(Adiacenza a : this.dao.listAdiacenze(map) )
	{
		if(this.graph.containsVertex(a.getF1()) && this.graph.containsVertex(a.getF2()))
		{
			DefaultWeightedEdge e=  this.graph.getEdge(a.getF1(), a.getF2());
			if(e==null)
			{
				Graphs.addEdge(this.graph, a.getF1(), a.getF2(), a.getPeso());
			}
		}
	}

	}
	
	
	public List<Vicino> listVicini( Food food)
	{
		List<Vicino> list = new ArrayList<>();
		for(Food a : Graphs.neighborListOf(this.graph, food))
		{
			list.add(new Vicino(a, this.graph.getEdgeWeight(this.graph.getEdge(food, a))));
		}

		
		
		Collections.sort(list);
		
		System.out.println(list);
		
		return list;
	}

	public Set<Food> setV() {
		
		return this.graph.vertexSet();
	}

	public Set<DefaultWeightedEdge> setE() {
		
		return this.graph.edgeSet();
	}

	public Model() {
		this.dao= new FoodDao();
		this.map= new HashMap<Integer, Food>();
		this.dao.listAllFoods(map);
		
	}


}
