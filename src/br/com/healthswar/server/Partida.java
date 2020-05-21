package br.com.healthswar.server;

import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.gameplay.Game;
import br.com.healthswar.gameplay.Player;

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
				
			default:
				break;
	
		}
	}

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

		while (game.isAtivo()) {
			game.resolve();
			
			MatchRequest request = (MatchRequest) game.getState().getActive().read();
			
			switch (request) {
				case DRAW_A_CARD:
					game.drawCard();
					break;

				case SEND_A_FIGHTER:
					game.sendFighter();
					break;
					
				case USE_AN_ITEM:
					game.useItem();
					break;
					
				case PUT_ENERGY:
					game.putEnergy();
					break;
					
				case START_BATTLE:
					game.startBattle();
					break;
					
				case ATACK_THE_OPONENT:
					game.atack();
					break;
				
				case END_THE_TURN:
					game.endTurn();
					break;
			}
		}
	}

}
