package br.com.healthswar.gameplay.items;

import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.Deck;
import br.com.healthswar.gameplay.Hand;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.gameplay.State;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.player.view.main.MainView;

public class Ferver extends Item {

	private static final long serialVersionUID = 3004858906644713950L;

	public Ferver() {
		super();
		name = "Febre";
		description = "Aumenta o poder de ATK do Sistema Imunológico por 3 turnos, mas todas suas células levam 20 de dano.";
		applied = false;
		duration = 3;
	}

	public void resolve() {
		if(getDuration() >= 0) {
			Player active = State.getState()==null?
					MainView.INSTANCE.getActive():State.getState().getActive();
			Deck deck = active.getField().getDeck();
			Hand hand = active.getField().getHand();
			Fighter fighters[] = active.getField().getFighters();
			
			if(!applied) {
				for(Card card: deck) {
					if(card instanceof Fighter) {
						Fighter fi = (Fighter) card;
						fi.setAtkPower(fi.getAtkPower() + 20);
					}
				}
				
				for(Fighter fighter: fighters) {
					if(fighter != null)
						fighter.setAtkPower(fighter.getAtkPower() + 20);
				}
				
				for(Card card: hand) {
					if(card instanceof Fighter) {
						Fighter fi = (Fighter) card;
						fi.setAtkPower(fi.getAtkPower() + 20);
						card = fi;
					}
				}
				
				applied = true;
			}	
			
			for(Fighter fighter : fighters) {
				if(fighter != null) 
					fighter.setHealthPoints(fighter.getHealthPoints() - 10);
			}
			
			reduceDuration();
		} 
	}
	
}
