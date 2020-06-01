package it.polito.tdp.food.model;

public class Evento implements Comparable<Evento> {
	
	public enum EventType{
		INIZIO_PREPARAZIONE,
		FINE_PREPARAZIONE,
	}
	
	 private Double time;
	 private Stazione stazione;
	 private Food food;
	 private EventType tipo;
	 
	public Evento(Double time,EventType tipo, Stazione stazione, Food food) {
		super();
		this.time = time;
		this.tipo=tipo;
		this.stazione = stazione;
		this.food = food;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.getTime());
	}

	public Double getTime() {
		return time;
	}

	public Stazione getStazione() {
		return stazione;
	}

	public Food getFood() {
		return food;
	}

}
