package br.com.healthswar.server;

import java.io.IOException;
import java.util.Arrays;

import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.Game;
import br.com.healthswar.gameplay.Player;

public class Partida extends Thread {

	private final Request MATCH_TYPE;

	private Player[] players;
	private int playersConneted;
	private boolean completo;

	private Game game;

	public Partida(Request MATCH_TYPE) {
		this.MATCH_TYPE = MATCH_TYPE;
		this.playersConneted = 0;
		this.completo = false;

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
		if (!completo) {
			this.players[playersConneted] = player;
			this.playersConneted++;
			if (playersConneted == players.length) {
				completo = true;
			}
		}
	}

	public boolean getCompleto() {
		return this.completo;
	}
	
	@Override
	public void run() {
		game = new Game(players);
		players = game.sortDeck();
		
		try {
			game.init(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		while (game.isAtivo()) {
			int vez = game.getTurno() % 2 == 0 ? 0 : 1;
			int outro = game.getTurno() % 2 != 0 ? 0 : 1;
			Player player = players[vez];
			Player p2 = players[outro];
			
			try {
				player.out.writeObject(MatchResponse.YOUR_TURN);
				player.out.writeObject(game.getPhase());
				p2.out.writeObject(MatchResponse.OPPONENT_TURN);
				p2.out.writeObject(game.getPhase());
				
				MatchRequest request = (MatchRequest) player.in.readObject();
				MatchResponse res;
				
				switch (request) {
					case DRAW_A_CARD:
						res = game.comprarCarta(player.getField());
						player.out.writeObject(res);
						p2.out.writeObject(res);
						break;

					case SEND_A_FIGHTER:
						Fighter fighter = (Fighter) player.in.readObject();
						res = game.enviarCombatente(player.getField(), fighter);
						player.out.writeObject(res);
						player.out.writeObject(fighter);
						p2.out.writeObject(res);
						p2.out.writeObject(fighter);
						break;
						
					case USE_AN_ITEM:
						game.usarItem(player.getField());
						break;
						
					case ATACK_THE_OPONENT:
//						game.atacar(new Fighter(), new Fighter());
						break;
					
					case END_THE_TURN:
						game.encerrarTurno();
						player.out.writeObject(MatchResponse.OPPONENT_TURN);
						p2.out.writeObject(MatchResponse.YOUR_TURN);
						break;
						
					case GET_PHASE:
						player.out.writeObject(game.getPhase());
						break;
				}

			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public String toString() {
		return "Partida [MATCH_TYPE=" + MATCH_TYPE + ", players=" + Arrays.toString(players) + ", playersConneted="
				+ playersConneted + ", completo=" + completo + ", game=" + game + "]";
	}

}
