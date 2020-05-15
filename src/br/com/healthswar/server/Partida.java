package br.com.healthswar.server;

import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.gameplay.CardLocal;
import br.com.healthswar.gameplay.Energy;
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.Game;
import br.com.healthswar.gameplay.Item;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.gameplay.Turn;

public class Partida extends Thread {

	private final Request MATCH_TYPE;

	private Player[] players;
	private int playersConneted;
	private boolean complete;

	private Game game;

	public Partida(Request MATCH_TYPE) {
		this.MATCH_TYPE = MATCH_TYPE;
		this.playersConneted = 0;
		this.complete = false;

		switch (this.MATCH_TYPE) {
			case PLAY_A_SOLO_MATCH:
				this.players = new Player[1];
				break;
	
			case PLAY_A_DUO_MATCH:
				this.players = new Player[2];
				break;
				
			case PLAY_A_SQUAD_MATCH:
				break;
				
			default:
				break;
	
		}
	}

	/**
	 * Adiona players enquanto tiver espaco na partida
	 */
	public void addPlayer(Player player) {
		if (!complete) {
			this.players[playersConneted] = player;
			this.playersConneted++;
			if (playersConneted == players.length) {
				complete = true;
			}
		}
	}

	public boolean getCompleto() {
		return this.complete;
	}
	
	@Override
	public void run() {
		game = new Game(players);
		game.init();

		while (game.isAtivo()) {
			Turn state = game.getState();
			Player player = state.getActive();
			Player opponent = state.getOpponent();
			
			System.out.println("Mandei");
			state.phaseResolve();
			
			MatchRequest request = (MatchRequest) player.read();
			MatchResponse response;
			
			Fighter fighter;
			Energy energy;
			Item item;
			
			switch (request) {
				case DRAW_A_CARD:
					game.drawCard();
					break;

				case SEND_A_FIGHTER:
					fighter = (Fighter) player.read();
					response = game.sendFighter(player.getField(), fighter);
					player.write(response);
					opponent.write(response);
					if(response == MatchResponse.FIGHTER_READY) {
						fighter.setLocal(CardLocal.FIELD);
						player.write(fighter);
						opponent.write(fighter);
					}
					break;
					
				case USE_AN_ITEM:
					item = (Item) player.read();
					response = game.useItem(player.getField(), item);
					player.write(response);
					opponent.write(response);
					if(response == MatchResponse.ITEM_USED) {
						item.setLocal(CardLocal.DESCARTE);
						player.write(item);
						opponent.write(item);
					}
					break;
					
				case PUT_ENERGY:
					fighter = (Fighter) player.read();;
					energy = (Energy) player.read();;
					response = game.putEnergy(player.getField(), energy, fighter);
					
					player.write(response);
					opponent.write(response);
					if(response == MatchResponse.ENERGY_READY) {
						fighter.getEnergies().add(energy);
						
						player.write(fighter);
						player.write(energy);
						
						opponent.write(fighter);
						opponent.write(energy);
					}
					break;
					
				case START_BATTLE:
					game.startBattle();
					player.write(MatchResponse.BATTLE_STARTED);
					opponent.write(MatchResponse.BATTLE_STARTED);
					break;
					
				case ATACK_THE_OPONENT:
//						game.atacar(new Fighter(), new Fighter());
					break;
				
				case END_THE_TURN:
					game.endTurn();
					if(state.getPhase() == Phases.MAIN_PHASE) {
						player.write(MatchResponse.OPPONENT_TURN);
					}
					state.setPhase(Phases.DRAW_PHASE);
					opponent.write(MatchResponse.YOUR_TURN);
					break;

			}
		}
	}

}
