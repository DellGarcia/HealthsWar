package br.com.healthswar.gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.comunication.Response;

public class Game {

	private boolean active;
	private State state;

	private Deck array[] = {
			new Deck(DeckTheme.IMMUNE_SYSTEM), new Deck(DeckTheme.FOREIGN_BODIES)
	};
	private List<Deck> decks = new ArrayList<Deck>(Arrays.asList(array));
	
	public Game(Player[] players) {
		sortDeck(players);
		active = true;
		state = new State(players);
		Collections.shuffle(decks);
		init();
	}
	
	public void sortDeck(Player[] players) {
		int i = 0;
		for(Player player: players) {
			player.setField(new Field(decks.get(i)));
			i++;
			if(i > 1) 
				i = 0;
		}
		Collections.shuffle(Arrays.asList(players));
	}
	
	public void init() {
		state.getActive().write(Response.MATCH_READY);
		state.getActive().write(state.getActive().getField());
		state.getActive().write(state.getOpponent().getField());
		
		state.getOpponent().write(Response.MATCH_READY);
		state.getOpponent().write(state.getOpponent().getField());
		state.getOpponent().write(state.getActive().getField());
	}
	
	public void resolve() {
		state.phaseResolve();
	}
	
	/** Player actions */
		public void drawCard() {
			Player player = state.getActive();
			Player oppnent = state.getOpponent();
			
			MatchResponse res = state.drawCard();
			
			player.write(res);
			oppnent.write(res);
		}
		
		public void sendFighter() {
			Player player = state.getActive();
			Player oppnent = state.getOpponent();
			Fighter fighter = (Fighter) player.read();
			
			MatchResponse response = state.summon(fighter);
			
			switch (response) {
				case FIGHTER_READY:
					player.write(response);
					player.write(fighter);
					oppnent.write(response);
					oppnent.write(fighter);
					break;
				case NO_FIGHTER:
					player.write(response);
					oppnent.write(response);
					break;
				default:
					break;
			}
		}
		
		public void useItem() {
			Player player = state.getActive();
			Player oppnent = state.getOpponent();
			Item item = (Item) player.read();
			
			MatchResponse res = state.useItem(item);
			
			switch (res) {
				case ITEM_USED:
					player.write(res);
					player.write(item);
					oppnent.write(res);
					oppnent.write(item);
					break;
	
				case IMPOSSIBLE_TO_USE:
					player.write(res);
					oppnent.write(res);
					break;
					
				default:
					break;
			}
			
		}
		
		public void putEnergy() {
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			Fighter fighter = (Fighter) player.read();
			Energy energy = (Energy) player.read();
			
			MatchResponse response = state.putEnergy(fighter, energy);
			
			switch (response) {
				case ENERGY_READY:
					fighter.getEnergies().add(energy);
					
					player.write(response);
					player.write(fighter);
					player.write(energy);
					
					opponent.write(response);
					opponent.write(fighter);
					opponent.write(energy);
					break;
	
				case IMPOSSIBLE_TO_USE:
					player.write(response);
					opponent.write(response);
					break;
					
				default:
					break;
			}
		}
		
		public void startBattle() {
			state.setPhase(Phases.BATTLE_PHASE);
			state.getActive().write(MatchResponse.BATTLE_STARTED);
			state.getOpponent().write(MatchResponse.BATTLE_STARTED);
		}
		
		public void atack() {
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			Fighter attacker = (Fighter) player.read();
			Fighter target = (Fighter) player.read();
			
			MatchResponse response = state.atack(attacker, target);
			
			switch (response) {
				case SUCCESSFUL_ATACK:
					player.write(response);
					player.write(attacker);
					player.write(target);
					
					opponent.write(response);
					opponent.write(attacker);
					opponent.write(target);
					break;

				case ATACK_FAILED:	
					player.write(response);
					opponent.write(response);
					break;
					
				default:
					break;
			}
		}
		
		public void endTurn() {
			state.getActive().write(MatchResponse.OPPONENT_TURN);
			state.getOpponent().write(MatchResponse.YOUR_TURN);
			state.setPhase(Phases.DRAW_PHASE);
			state.endTurn();
		}
		
	/** Getter e Setters */
		public boolean isAtivo() {
			return active;
		}

		public void setAtivo(boolean ativo) {
			this.active = ativo;
		}

		public State getState() {
			return state;
		}
}
