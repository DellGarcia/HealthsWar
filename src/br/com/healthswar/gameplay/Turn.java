package br.com.healthswar.gameplay;

import java.util.Arrays;

import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;

public class Turn {
	
	private static int turn = 0;
	private static Player[] players;
	private static Player active;
	private static Player opponent;
	private Phases phase;
	private boolean summonAvalible;
	private boolean atackAvalible;
	
	public Turn(Player[] players) {
		init(players);
	}

	public void init(Player[] players) {
		turn++;
		Turn.players = players;
		this.phase = Phases.DRAW_PHASE;
		this.summonAvalible = true;
		this.atackAvalible = true;
		this.setActive();
	}
	
	public void phaseResolve() {
		active.write(MatchResponse.YOUR_TURN);
		active.write(phase);
		opponent.write(MatchResponse.OPPONENT_TURN);
		opponent.write(phase);
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
		if(phase != Phases.MAIN_PHASE)
			return MatchResponse.IMPOSSIBLE_TO_USE;
		
		Field field = active.getField();
		Fighter fighters[] = field.getFighters();
		
		System.out.println(Arrays.toString(fighters));
		
		for(Fighter lutador: fighters) {
			if(lutador.id == fighter.id) {
				field.getHand().remove(energy);
				lutador.getEnergies().add(energy);
				fighter = lutador;
				return MatchResponse.ENERGY_READY;
			}
		}
		return MatchResponse.IMPOSSIBLE_TO_USE;
	}
	
	public void endTurn() {
		init(players);
	}
	
	/** Getter e Setters */
		public static int getTurn() {
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
	
		public boolean isSummonAvalible() {
			return summonAvalible;
		}
	
		public boolean isAtackAvalible() {
			return atackAvalible;
		}
	
}
