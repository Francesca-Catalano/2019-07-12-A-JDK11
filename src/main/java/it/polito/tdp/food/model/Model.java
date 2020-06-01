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
	public List<Food> VertexSet()
	{ List<Food> list = new ArrayList<>(this.graph.vertexSet());
		Collections.sort(list);
		return list;
	}

	public List<FoodAndCalories> CercaCalorie(Food f, int n) {
		List<FoodAndCalories> list = new ArrayList<>();
		Collections.sort(this.dao.listAdiacenze(mapId, n));
		if(Graphs.neighborListOf(this.graph, f).size()==0)
		{
			return null;
		}
		else if(Graphs.neighborListOf(this.graph, f).size()<=5)
		{
			//System.out.println( "size :"+Graphs.neighborListOf(this.graph, f).size());
			
			for(Food v :Graphs.neighborListOf(this.graph, f))
			{
				DefaultWeightedEdge e = this.graph.getEdge(f, v);
				double peso = this.graph.getEdgeWeight(e);
				list.add(new FoodAndCalories(v, peso));
			}
			return list;
		}
		for(int i=0; i<5;i++)
		{
			Food v = Graphs.neighborListOf(this.graph, f).get(i);
				DefaultWeightedEdge e = this.graph.getEdge(f, v);
				double peso = this.graph.getEdgeWeight(e);
				list.add(new FoodAndCalories(v, peso));
		}
		return list;
		
	}
	
	
	
}
