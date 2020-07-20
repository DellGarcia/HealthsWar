package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import br.com.healthswar.gameplay.decks.ForeignBodies;
import br.com.healthswar.gameplay.decks.ImmuneSystem;

public class Deck extends ArrayList<Card> implements Serializable {

	private static final long serialVersionUID = -2304845787652432479L;
	
	private DeckTheme deckTheme;
	
	public Deck(DeckTheme deckTheme) {
		this.deckTheme = deckTheme;
		switch (deckTheme) {
			case IMMUNE_SYSTEM:
				addAll(new ImmuneSystem());
				break;
	
			case FOREIGN_BODIES:
				addAll(new ForeignBodies());
				break;
		}
		Collections.shuffle(this);
	}
	
	public Card removeFirst() {
		Card first = get(0);
		remove(0);
		return first;
	}
	
	public DeckTheme getDeckTheme() {
		return deckTheme;
	}
	
}
