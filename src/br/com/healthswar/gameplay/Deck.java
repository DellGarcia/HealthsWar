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
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private DeckTheme deckTheme;
	
	public Deck(DeckTheme deckTheme) {
		this.deckTheme = deckTheme;
		int id = 0;
		switch (deckTheme) {
			case IMMUNE_SYSTEM:
				for(int i = 0; i < 20; i++) {
					try {
						cards.add(new Fighter(id++));
						cards.add(new Energy(id++));
						cards.add(new Item(id++));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				break;
	
			case FOREIGN_BODIES:
				for(int i = 0; i < 20; i++) {
					try {
						cards.add(new Fighter(id++));
						cards.add(new Energy(id++));
						cards.add(new Item(id++));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				break;
		}
		Collections.shuffle(cards);
	}

	public ArrayList<Card> getCartas() {
		return cards;
	}

	public void setCartas(ArrayList<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Deck [cartas=" + cards + ", deckTheme=" + deckTheme + "]";
	}
	
}
