package it.polito.tdp.food.model;

public class Evento implements Comparable<Evento> {

	public enum EventType{
		INIZIO_PREPARAZIONE,
		FINE_PREPARAZIONE
		
		
		
	}
	private EventType evento;
	private Double time;
	private Stazione station;
	private Food food;
	
	
	
	public EventType getEvento() {
		return evento;
	}
	
	public Double getTime() {
		return time;
	}

	public Evento(EventType evento, Double time,Stazione station,Food food) {
		super();
		this.evento = evento;
		this.time = time;
		this.station=station;
		this.food=food;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.getTime());
	}

	public Stazione getStation() {
		return station;
	}

	public Food getFood() {
		return food;
	}
	
	

}
