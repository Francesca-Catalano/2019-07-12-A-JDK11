package it.polito.tdp.food.model;

public class FoodAndCalories {
private Food f;
private double peso;
public FoodAndCalories(Food f, double peso) {
	super();
	this.f = f;
	this.peso = peso;
}
public Food getF() {
	return f;
}
public double getPeso() {
	return peso;
}
@Override
public String toString() {
	return f.getDisplay_name() + " " + peso + "\n";
}
}
