package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695250996709090460L;
	
	private ArrayList<Carta> cartas = new ArrayList<Carta>();
	
	public Hand(ArrayList<Carta> deck) {
		for(int i = 0; i < 5; i++) {
			Carta card = deck.get(i);
			card.virado = false;
			card.local = CardLocal.HAND;
			cartas.add(card);
		}
		deck.removeAll(cartas);
	}
	
	public ArrayList<Carta> getCartas() {
		return cartas;
	}
	
	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}

	@Override
	public String toString() {
		return "Hand [cartas " + cartas + "]";
	}
	
}
