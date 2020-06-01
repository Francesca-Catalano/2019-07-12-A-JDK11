package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import javafx.event.EventType;

public class Simulator {
	


	
	
	//Modello -> Stato del sistema ad ogni passo
  private  List<Stazione> stazioni;
  private List<Food> cibi;
  private Graph<Food, DefaultWeightedEdge> graph;
  private Model model;
  private EventType tipo;
   
  
  public Simulator(Graph<Food, DefaultWeightedEdge> graph,EventType tipo,Model model) {
		super();
		this.graph = graph;
		this.model=model;
		this.tipo=tipo;
	}

//Tipi di evento? -> coda prioritaria
 private  PriorityQueue<Evento> queque;
/*Evento è una classe che devi creare a parte, la classe evento è sempre comparable di event in base al tempo*/

//Parametri della simulazione (sono i dati del testo) sono impostati dal main
	private  int K=0;




 //Valori in output(sono quelli che ti chiede il testo)
	private Double tempoPreparazione;


	public int getK() {
		return K;
	}




	public void setK(int k) {
		K = k;
	}




	public List<Stazione> getStazioni() {
		return stazioni;
	}




	public List<Food> getCibi() {
		return cibi;
	}




	public Graph<Food, DefaultWeightedEdge> getGraph() {
		return graph;
	}




	public PriorityQueue<Evento> getQueque() {
		return queque;
	}




	public Double getTempoPreparazione() {
		return tempoPreparazione;
	}



	
	  public void inizializzazione(Food partenza) {
	  
	  //1. imposto parametri iniziali 
		  
		  this.cibi= new  ArrayList<>(this.graph.vertexSet());
		  this.stazioni= new  ArrayList<>();
		  for(int i=0; i<this.K;i++)
		  {
			  this.stazioni.add(new Stazione(true, null));
		  }
		  this.tempoPreparazione=0.0;
		  
	 // 2.creare la coda la fai nuova xk non vuoiportarti dietro i valori vecchi 
		  this.queque= new PriorityQueue<Evento>();
		  

List<FoodAndCalories> vicini = this.model.ListFAC(partenza);
for(int i=0; i<this.K && i< vicini.size() ;i++)
{
	  this.stazioni.get(i).setLibera(false);
	  this.stazioni.get(i).setFood(vicini.get(i).getF());
	  Evento e = new Evento(vicini.get(i).getPeso(), stazione, food)
}
	
		  
		  // 3.inizio la simulazione : aggiungo il primoevento alla coda priortaria 
	 // 4. imposto a 0 i nuovi contatori
	  
	  }
	  
	/*  public void run (){ Finche la coda non si svuota continuo ad estrarre il
	  primo elemento che rimane e eseguo l eventoo While(e= this.queque.poll()!=
	  null {//eseguo evento} }
*/	 


}
