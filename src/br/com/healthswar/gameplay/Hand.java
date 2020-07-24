package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.anonymous.frontend.Panel;

public class Hand extends ArrayList<Card> implements Serializable {

	private static final long serialVersionUID = 4097601925250127486L;

	public Hand(Deck deck) {
		for(int i = 0; i < 5; i++) {
			Card card = deck.get(i);
			card.setTurned(false);
			card.local = CardLocal.HAND;
			add(card);
		}
		deck.removeAll(this);
	}
	
	@Override
	public boolean add(Card e) {
		e.setTurned(false);
		e.local = CardLocal.HAND;
		return super.add(e);
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
	
	public void removeHandFromPanel(Panel container) {
		for(int i = 0; i < size(); i++) {
			container.remove(get(i));
		}
	}

}
