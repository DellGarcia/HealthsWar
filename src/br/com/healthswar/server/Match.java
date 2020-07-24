package br.com.healthswar.server;

import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.gameplay.Game;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.gameplay.effects.EffectMachine;

public class Match extends Thread {

	private Player[] players;
	private int playersConnected;
	private boolean complete;

	public Match(Request MATCH_TYPE) {
		this.playersConnected = 0;
		this.complete = false;

		switch (MATCH_TYPE) {
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
			this.players[playersConnected] = player;
			this.playersConnected++;

			if (playersConnected == players.length) complete = true;
		}
	}

	public boolean getCompleto() {
		return this.complete;
	}
	
	@Override
	public void run() {
		final Game game = new Game(players);
		final EffectMachine effectMachine = EffectMachine.getInstance(); 

		while (game.isAtivo()) {
			game.resolve();
			
			MatchRequest request = (MatchRequest) game.getState().getActive().read();
			
			switch (request) {
				case DRAW_A_CARD:
					game.drawCard();
					effectMachine.resolveEffects();
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
					
				case ATTACK_THE_OPPONENT:
					game.atack();
					break;
				
				case END_THE_TURN:
					game.endTurn();
					break;
			}
		}

		game.endGame();
	}

}
