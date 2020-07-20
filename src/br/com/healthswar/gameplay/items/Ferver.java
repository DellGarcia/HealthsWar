package br.com.healthswar.gameplay.items;

import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.Deck;
import br.com.healthswar.gameplay.State;
import br.com.healthswar.gameplay.fighters.Fighter;

public class Ferver extends Item {

	private static final long serialVersionUID = -3631402884746179138L;

	private boolean applied;
	
	public Ferver() {
		super();
		name = "Febre";
		description = "Aumenta o poder de ATK do Sistema Imunológico por 3 turnos, mas todas suas células levam 20 de dano.";
		applied = false;
	}

	public void resolve() {
		if(getDuration() >= 0) {
			State state = State.getState();
			Deck immuneSystemDeck = state.getActive().getField().getDeck();
			Fighter fighters[] = state.getActive().getField().getFighters();
			
			if(!applied)
				for(Card card : immuneSystemDeck) {
					if(card instanceof Fighter) {
						Fighter fi = (Fighter) card;
						fi.setAtkPower(fi.getAtkPower() + 20);
						card = fi;
					}
				}
			
			for(Fighter fighter : fighters) {
				if(fighter != null) {
					fighter.setHealthPoints(fighter.getHealthPoints() - 10);
				}
			}
			
			reduceDuration();
		}
	}
	
}
