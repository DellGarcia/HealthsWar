package br.com.healthswar.gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.energy.Energy;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.gameplay.items.Item;

public final class Game {

	private boolean active;

	private Deck array[] = {
			new Deck(DeckTheme.IMMUNE_SYSTEM),
			new Deck(DeckTheme.FOREIGN_BODIES)
	};
	private List<Deck> decks = new ArrayList<Deck>(Arrays.asList(array));
	
	public Game(Player[] players) {
		sortDeck(players);
		active = true;
		State.create(players);
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
		State state = State.getState();
		state.getActive().write(Response.MATCH_READY);
		state.getActive().write(state.getActive().getField());
		state.getActive().write(state.getOpponent().getField());
		
		state.getOpponent().write(Response.MATCH_READY);
		state.getOpponent().write(state.getOpponent().getField());
		state.getOpponent().write(state.getActive().getField());
	}
	
	public void resolve() {
		State state = State.getState();
		state.phaseResolve();
	}
	
	/** Player actions */
		public void drawCard() {
			State state = State.getState();
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			Field field = (Field) player.read();
			
			player.setField(field);
			
			MatchResponse res = state.drawCard();
			
			player.write(res);
			player.write(state.getActive().getField());
			opponent.write(res);
			opponent.write(state.getActive().getField());
		}
		
		public void sendFighter() {
			State state = State.getState();
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			Field field = (Field) player.read();
			Fighter fighter = (Fighter) player.read();
			
			player.setField(field);
			
			MatchResponse response = state.summon(fighter);
			
			switch (response) {
				case FIGHTER_READY:
					field = state.getActive().getField();
					
					player.write(response);
					player.write(field);
					player.write(fighter);
					opponent.write(response);
					opponent.write(field);
					opponent.write(fighter);
					break;
				case NO_FIGHTER:
					player.write(response);
					opponent.write(response);
					break;
				default:
					break;
			}
		}
		
		public void useItem() {
			State state = State.getState();
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			Field field = (Field) player.read();
			Item item = (Item) player.read();
			
			player.setField(field);
			
			MatchResponse res = state.useItem(item);
			
			switch (res) {
				case ITEM_USED:
					field = state.getActive().getField();
					
					player.write(res);
					player.write(field);
					opponent.write(res);
					opponent.write(field);
					break;
	
				case IMPOSSIBLE_TO_USE:
					player.write(res);
					opponent.write(res);
					break;
					
				default:
					break;
			}
		}
		
		public void putEnergy() {
			State state = State.getState();
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			Field field = (Field) player.read();
			Fighter fighter = (Fighter) player.read();
			Energy energy = (Energy) player.read();
			
			player.setField(field);
			
			MatchResponse response = state.putEnergy(fighter, energy);
			
			switch (response) {
				case ENERGY_READY:
					field = state.getActive().getField();
					
					player.write(response);
					player.write(field);
					player.write(fighter);
					
					opponent.write(response);
					opponent.write(field);
					opponent.write(fighter);
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
			State state = State.getState();
			state.setPhase(Phases.BATTLE_PHASE);
			state.getActive().write(MatchResponse.BATTLE_STARTED);
			state.getOpponent().write(MatchResponse.BATTLE_STARTED);
		}
		
		public void atack() {
			State state = State.getState();
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			Fighter attacker = (Fighter) player.read();
			Fighter target = (Fighter) player.read();
			
			MatchResponse response = state.atack(attacker, target);
			
			active = opponent.getField().getHealthsPoint() <= 0 ? false:true;
			
			switch (response) {
				case SUCCESSFUL_ATTACK:
					player.write(response);
					player.write(attacker);
					player.write(target);
					
					opponent.write(response);
					opponent.write(attacker);
					opponent.write(target);
					break;

				case ATTACK_FAILED:
					player.write(response);
					opponent.write(response);
					break;
					
				default:
					break;
			}
		}
		
		public void endTurn() {
			State state = State.getState();
			state.getActive().write(MatchResponse.OPPONENT_TURN);
			state.getOpponent().write(MatchResponse.YOUR_TURN);
			state.setPhase(Phases.DRAW_PHASE);
			state.endTurn();
		}
		
		public void endGame() {
			State state = State.getState();
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			MatchResponse response = MatchResponse.END_GAME;
			
			player.write(response);
			player.write(MatchResponse.YOU_WIN);
			opponent.write(response);
			opponent.write(MatchResponse.YOU_LOSE);
		}
		
	/** Getter e Setters */
		public boolean isAtivo() {
			return active;
		}

		public void setAtivo(boolean ativo) {
			this.active = ativo;
		}

		public State getState() {
			return State.getState();
		}
}
