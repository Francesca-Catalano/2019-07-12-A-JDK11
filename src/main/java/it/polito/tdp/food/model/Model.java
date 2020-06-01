package it.polito.tdp.food.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {

	private FoodDao dao ;
	private Map<Integer,Food> mapId;
	private Graph<Food, DefaultWeightedEdge> graph;

	public Model() {
		
		this.dao = new FoodDao();
		this.mapId=new HashMap<>();
		this.dao.listAllFoods(mapId);
		this.graph = new SimpleWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}

	public void creaGrafo(int n) {
List<Food> list = this.dao.listFoodByPortions(mapId, n);
if(list==null)
{
	System.out.print("Errore lettura FoodByPortions");
	return;
}
Graphs.addAllVertices(this.graph, list);
if(this.dao.listAdiacenze(mapId, n)==null)
{
	System.out.print("Errore lettura FoodByPortions");
	return;
}

for (Adiacenza a : this.dao.listAdiacenze(mapId, n))
{
	DefaultWeightedEdge e = this.graph.getEdge(a.getF1(), a.getF2());
	if(e==null)
	{
		Graphs.addEdge(this.graph,a.getF1(), a.getF2(), a.getPeso());
	}
}

	}
	
	public int VertexSize()
	{
		return this.graph.vertexSet().size();
	}

	public int EdgeSize() {
		// TODO Auto-generated method stub
		return this.graph.edgeSet().size();
	}
	public Set<Food> VertexSet()
	{
		return this.graph.vertexSet();
	}
	
}
