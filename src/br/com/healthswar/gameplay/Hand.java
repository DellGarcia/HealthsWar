package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695250996709090460L;
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public Hand(ArrayList<Card> deck) {
		for(int i = 0; i < 5; i++) {
			Card card = deck.get(i);
			card.turned = false;
			card.local = CardLocal.HAND;
			cards.add(card);
		}
		deck.removeAll(cards);
	}
	
	public ArrayList<Card> getCartas() {
		return cards;
	}
	
	public void setCartas(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public Card remove(Card card) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).id == card.id) {
				Card cardCopy = cards.get(i);
				cards.remove(i);
				return cardCopy;
			}	
		}
		return null;
	}

	@Override
	public String toString() {
		return "Hand [cartas " + cards + "]";
	}
	
}
