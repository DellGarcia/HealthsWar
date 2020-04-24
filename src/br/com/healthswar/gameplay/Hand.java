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
	
	public Carta remove(Carta card) {
		for(int i = 0; i < cartas.size(); i++) {
			if(cartas.get(i).id == card.id) {
				Carta c = cartas.get(i);
				cartas.remove(i);
				return c;
			}	
		}
		return null;
	}

	@Override
	public String toString() {
		return "Hand [cartas " + cartas + "]";
	}
	
}
