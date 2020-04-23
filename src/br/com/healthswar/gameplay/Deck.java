package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2304845787652432479L;
	
	private ArrayList<Carta> cartas = new ArrayList<Carta>();
	private DeckTheme deckTheme;
	
	public Deck(DeckTheme deckTheme) {
		this.deckTheme = deckTheme;
		int id = 0;
		switch (deckTheme) {
			case IMMUNE_SYSTEM:
				for(int i = 0; i < 20; i++) {
					try {
						cartas.add(new Fighter(id++));
						cartas.add(new Energy(id++));
						cartas.add(new Item(id++));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				break;
	
			case FOREIGN_BODIES:
				for(int i = 0; i < 20; i++) {
					try {
						cartas.add(new Fighter(id++));
						cartas.add(new Energy(id++));
						cartas.add(new Item(id++));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				break;
		}
		Collections.shuffle(cartas);
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}

	@Override
	public String toString() {
		return "Deck [cartas=" + cartas + ", deckTheme=" + deckTheme + "]";
	}
	
}
