package it.polito.tdp.food.db;

import it.polito.tdp.food.model.Food;

public class Adiacenza {

	private Food f1;
	private Food f2;
	private Double peso;
	
	
	public Adiacenza(Food f1, Food f2, Double peso) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.peso = peso;
	}


	public Food getF1() {
		return f1;
	}


	public Food getF2() {
		return f2;
	}


	public Double getPeso() {
		return peso;
	}
}
