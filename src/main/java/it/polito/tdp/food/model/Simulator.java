package it.polito.tdp.food.model;


import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.food.model.Evento.EventType;
import it.polito.tdp.food.model.Food.StatoPrep;


public class Simulator {
	  //modello
	private Graph<Food, DefaultWeightedEdge> graph;
	private List<Stazione> stazioni;
	private List<Food> cibi;
	private Model model;
	  //iniziali
	private  int k;

	
	  
	  
	  //coda
	private PriorityQueue<Evento> queue;
	  
	  //output
	private int cibiTot=0;
	private Double timeTot=0.0;
	

	public Simulator(Graph<Food, DefaultWeightedEdge> graph,Model model) {
	
		this.graph = graph;
		this.model=model;
	}
	

	


	

	
	  public void inizializzazione(Food partenza,int k) {
		  this.k=k;
		 this.queue= new PriorityQueue<Evento>();
		
		  this.cibi= new ArrayList<>(this.graph.vertexSet());
		  //devo creare la lista che è vuota
		  for(int i=0; i<k;i++)
		  {
			 stazioni.add(new Stazione(true, null)); //xk se è libera per forza non c'è nessun cibo 
		  }
		  
		  //aggiungere il primo evento 
		  List<FoodAndCalories> vicini = this.model.ListFAC(partenza);
		  for(int i=0;i<k && i <vicini.size() ;i++)
		  {
			  this.stazioni.get(i).setLibera(false);
			  this.stazioni.get(i).setFood(vicini.get(i).getF());
			  Evento e = new Evento(EventType.FINE_PREPARAZIONE, vicini.get(i).getPeso(), stazioni.get(i), vicini.get(i).getF());
			  this.queue.add(e);
		  }
		  
		  
	

	  
	  }
	  
	  
	/*  public void run (){ Finche la coda non si svuota continuo ad estrarre il
	  primo elemento che rimane e eseguo l eventoo While(e= this.queque.poll()!=
	  null {//eseguo evento} }
*/	 
public void run()
{
	Evento e = queue.poll();
	while(e!=null)
	{
		switch (e.getEvento())
		{
		case INIZIO_PREPARAZIONE :
			List<FoodAndCalories>  vicini = this.model.ListFAC(e.getFood());
			FoodAndCalories prossimo = null;
			for(FoodAndCalories f : vicini)
			{
				if(f.getF().getStato()== StatoPrep.DA_PREPARARE)
				{
					prossimo = f;
					break;
				}
			}
			if(prossimo !=null)
			{
				prossimo.getF().setStato(StatoPrep.IN_CORSO);
				e.getStation().setLibera(false);
				e.getStation().setFood(prossimo.getF());
				Evento e2 = new Evento(EventType.FINE_PREPARAZIONE, e.getTime()+prossimo.getPeso(), e.getStation(), prossimo.getF());
				this.queue.add(e2);
			}
			break;
			
		case FINE_PREPARAZIONE :
			//quando finissce una ne inziia subito un altra allo stesso tempo tenendo pero in considerazione in cibo che ho appena terminato
			Evento e2= new Evento(EventType.INIZIO_PREPARAZIONE, e.getTime(), e.getStation(), e.getFood());
			this.queue.add(e2);
			this.cibiTot++;
			e.getStation().setLibera(true);
			e.getFood().setStato(StatoPrep.PREPARATO);
			
			break;
		}
		
			
		
		
	}
	
}






}

