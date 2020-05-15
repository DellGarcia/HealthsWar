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
	private Turn state;

	private Deck array[] = {
			new Deck(DeckTheme.IMMUNE_SYSTEM), new Deck(DeckTheme.FOREIGN_BODIES)
	};
	private List<Deck> decks = new ArrayList<Deck>(Arrays.asList(array));
	
	public Game(Player[] players) {
		sortDeck(players);
		active = true;
		state = new Turn(players);
		Collections.shuffle(decks);
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
		state.write(Response.MATCH_READY);
		state.write(state.getActive().getField());
		state.write(state.getOpponent().getField());
		state.write(MatchResponse.YOUR_TURN);
		
		state.getOpponent().write(Response.MATCH_READY);
		state.getOpponent().write(state.getOpponent().getField());
		state.getOpponent().write(state.getActive().getField());
		state.getOpponent().write(MatchResponse.OPPONENT_TURN);
	}
	 
	/** Player actions */
		public void drawCard() {
			Field field = state.getActive().getField();
			MatchResponse res = null;
			if(!field.getDeck().isEmpty() && field.getHand().size() < 7) {
				field.getHand().add(field.getDeck().removeFirst());
				res = MatchResponse.AVALIBLE_CARD;
			} else {
				res = MatchResponse.NO_CARDS;
			}
			state.setPhase(Phases.MAIN_PHASE);
			state.getActive().write(res);
			state.getOpponent().write(res);
		}
		
		public MatchResponse sendFighter(Field field, Fighter fighter) {
			if(state.getPhase() == Phases.MAIN_PHASE && state.isSummonAvalible()) {
				for(int i = 0; i < field.getFighter().length; i++) {
					if(field.getFighter()[i] == null) {
						field.getFighter()[i] = fighter;
						field.getHand().remove(fighter);
//						this.summonAvalible = false;
						return MatchResponse.FIGHTER_READY;
					}
				}
			}
			return MatchResponse.NO_FIGHTER;
		}
		
		public MatchResponse useItem(Field field, Item item) {
			if(state.getPhase() == Phases.MAIN_PHASE) {
				field.getHand().remove(item);
				field.getDescarte().add(item);
				return MatchResponse.ITEM_USED;
			}
			return MatchResponse.IMPOSSIBLE_TO_USE;
		}
		
		public MatchResponse putEnergy(Field field, Energy energy, Fighter fighter) {
			if(state.getPhase() == Phases.MAIN_PHASE) {
				for(Fighter lutador: field.getFighter()) {
					if(lutador.id == fighter.id) {
						field.getHand().remove(energy);
						lutador.getEnergies().add(energy);
						fighter = lutador;
						return MatchResponse.ENERGY_READY;
					}
				}
			}
			return MatchResponse.IMPOSSIBLE_TO_USE;
		}
		
		public void startBattle() {
			state.setPhase(Phases.BATTLE_PHASE);
		}
		
		public void atack(Fighter escolhido, Fighter alvo) {
			if(state.getPhase() == Phases.BATTLE_PHASE) {
				
			}
		}
		
		public void endTurn() {
			state.write(MatchResponse.YOUR_TURN);
			state.getOpponent().write(MatchResponse.OPPONENT_TURN);
		}
		
	/** Getter e Setters */
		public boolean isAtivo() {
			return active;
		}

		public void setAtivo(boolean ativo) {
			this.active = ativo;
		}

		public Turn getState() {
			return state;
		}
}
