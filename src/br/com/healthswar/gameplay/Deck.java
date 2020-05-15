package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> implements Serializable {

	private static final long serialVersionUID = -2304845787652432479L;
	
	@SuppressWarnings("unused")
	private DeckTheme deckTheme;
	
	public Deck(DeckTheme deckTheme) {
		this.deckTheme = deckTheme;
		int id = 0;
		switch (deckTheme) {
			case IMMUNE_SYSTEM:
				for(int i = 0; i < 20; i++) {
					try {
						add(new Fighter(id++));
						add(new Energy(id++));
						add(new Item(id++));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				break;
	
			case FOREIGN_BODIES:
				for(int i = 0; i < 20; i++) {
					try {
						add(new Fighter(id++));
						add(new Energy(id++));
						add(new Item(id++));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				break;
		}
		Collections.shuffle(this);
	}
	
	public Card removeFirst() {
		Card first = get(0);
		remove(0);
		return first;
	}
	
}
