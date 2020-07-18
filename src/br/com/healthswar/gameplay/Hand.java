package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand extends ArrayList<Card> implements Serializable {

	private static final long serialVersionUID = 5695250996709090460L;

	public Hand(Deck deck) {
		for(int i = 0; i < 5; i++) {
			Card card = deck.get(i);
			card.setTurned(false);
			card.local = CardLocal.HAND;
			add(card);
		}
		deck.removeAll(this);
	}
	
	public Card remove(Card card) {
		for(int i = 0; i < size(); i++) {
			if(get(i).id == card.id) {
				Card cardCopy = get(i);
				remove(i);
				return cardCopy;
			}	
		}
		return null;
	}

}
