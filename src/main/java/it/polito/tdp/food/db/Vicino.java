package it.polito.tdp.food.db;

import it.polito.tdp.food.model.Food;

public class Vicino implements Comparable<Vicino>{
	private Food f2;
	private Double peso;
	public Vicino(Food f2, Double peso) {
		super();
		this.f2 = f2;
		this.peso = peso;
	}
	public Food getF2() {
		return f2;
	}
	public Double getPeso() {
		return peso;
	}
	@Override
	public int compareTo(Vicino o) {
		// TODO Auto-generated method stub
		return -( this.peso.compareTo(o.getPeso()) );
	}
	@Override
	public String toString() {
		return   f2 + " " + peso ;
	}

}
