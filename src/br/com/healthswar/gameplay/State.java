package br.com.healthswar.gameplay;

import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;

public class State {
	
	private int turn = 0;
	private Player[] players;
	private Player active;
	private Player opponent;
	private Phases phase;
	private boolean summonAvalible;
	private boolean atackAvalible;
	private boolean energyAvalible;
	
	public State(Player[] players) {
		init(players);
	}

	public void init(Player[] players) {
		turn++;
		this.players = players;
		this.phase = Phases.DRAW_PHASE;
		this.summonAvalible = true;
		this.atackAvalible = true;
		this.energyAvalible = true;
		this.setActive();
	}
	
	public void phaseResolve() {
		active.write(MatchResponse.YOUR_TURN);
		active.write(phase);
		opponent.write(MatchResponse.OPPONENT_TURN);
		opponent.write(phase);
	}
	
	public MatchResponse drawCard() {
		Field field = active.getField();
		phase = Phases.MAIN_PHASE;
		if(!field.getDeck().isEmpty() && field.getHand().size() < 7) {
			field.getHand().add(field.getDeck().removeFirst());
			return MatchResponse.AVALIBLE_CARD;
		} else {
			return MatchResponse.NO_CARDS;
		}
	}
	
	public MatchResponse summon(Fighter fighter) {
		if(phase != Phases.MAIN_PHASE || !summonAvalible)
			return MatchResponse.NO_FIGHTER;
		
		Field field = active.getField();
		Fighter[] fighters = field.getFighters();
		
		for(int i = 0; i < fighters.length; i++) {
			if(fighters[i] == null) {
				field.getHand().remove(fighter);
				fighter.setLocal(CardLocal.FIELD);
				fighters[i] = fighter;
				this.summonAvalible = false;
				return MatchResponse.FIGHTER_READY;
			}
		}
		return MatchResponse.NO_FIGHTER;
	}
	
	public MatchResponse useItem(Item item) {
		if(phase != Phases.MAIN_PHASE)
			return MatchResponse.IMPOSSIBLE_TO_USE;
		
		active.getField().getHand().remove(item);
		active.getField().getDescarte().add(item);
		
		item.local = CardLocal.DESCARTE;
		
		return MatchResponse.ITEM_USED;
	}
	
	public MatchResponse putEnergy(Fighter fighter, Energy energy) {
		if(phase == Phases.MAIN_PHASE && energyAvalible) {
			Field field = active.getField();
			Fighter fighters[] = field.getFighters();
			
			for(Fighter lutador: fighters) {
				if(lutador.id == fighter.id) {
					energy.local = CardLocal.DESCARTE;
					field.getHand().remove(energy);
					field.getDescarte().add(energy);
					lutador.getEnergies().add(energy);
					fighter = lutador;
					this.energyAvalible = false;
					return MatchResponse.ENERGY_READY;
				}
			}
		}
		return MatchResponse.IMPOSSIBLE_TO_USE;
	}
	
	public MatchResponse atack(Fighter attacker, Fighter target) {
		if(atackAvalible && !attacker.getEnergies().isEmpty()) {
			target.healthPoints -= attacker.atkPower;
			attacker.getEnergies().remove(0);
			if(target.healthPoints <= 0) {
				opponent.getField().setDamage(target.healthPoints);
			}
			
			return MatchResponse.SUCCESSFUL_ATACK;
		}
		return MatchResponse.ATACK_FAILED;
	}
	
	public void endTurn() {
		init(players);
	}
	
	/** Getter e Setters */
		public int getTurn() {
			return turn;
		}
	
		public Player getActive() {
			return active;
		}
	
		public void setActive() {
			active = players[turn % 2 == 0 ? 0 : 1];
			opponent = players[turn % 2 != 0 ? 0 : 1];
		}
	
		public Player getOpponent() {
			return opponent;
		}
	
		public Phases getPhase() {
			return phase;
		}
	
		public void setPhase(Phases phase) {
			this.phase = phase;
		}
	
}