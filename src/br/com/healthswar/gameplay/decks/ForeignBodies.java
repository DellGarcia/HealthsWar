package br.com.healthswar.gameplay.decks;

import java.util.ArrayList;

import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.energy.Energy;
import br.com.healthswar.gameplay.fighters.Neutrofilo;
import br.com.healthswar.gameplay.items.Item;

public class ForeignBodies extends ArrayList<Card> {

	private static final long serialVersionUID = 4532376847548379587L;

	public ForeignBodies() {
		for (int i = 0; i < 20; i++) {
			add(new Neutrofilo());
			add(new Energy());
			add(new Item());
		}
	}

}
