package br.com.healthswar.gameplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.comunication.Response;

public class Game {

	private int turno;
	private Phases phase;
	private boolean ativo;
	
	private Player[] players;
	
	private Deck array[] = {
			new Deck(DeckTheme.IMMUNE_SYSTEM), new Deck(DeckTheme.FOREIGN_BODIES)
	};
	private List<Deck> decks = new ArrayList<Deck>(Arrays.asList(array));
	
	public Game(Player[] players) {
		this.players = players;
		this.turno = 1;
		this.phase = Phases.DRAW_PHASE;
		this.ativo = true;
		Collections.shuffle(decks);
	}
	
	public Player[] sortDeck() {
		int i = 0;
		for(Player player: players) {
			player.setField(new Field(decks.get(i)));
			i++;
			if(i > 1) 
				i = 0;
		}
		Collections.shuffle(Arrays.asList(players));
		return players;
	}
	
	public void init(Player player) throws IOException {
		player.out.writeObject(Response.MATCH_READY);
		player.out.writeObject(player.getField());
//		player.out.writeInt(player.getHealthsPoint());
//		player.out.writeObject(player.getField().getDeck());
//		player.out.writeObject(player.getField().getHand());
	}
	 
	// Açoes do player
		public MatchResponse comprarCarta(Field field) throws IOException {
			if(field.getDeck().getCartas().size() > 0 && field.getHand().getCartas().size() < 7) {
				field.getHand().getCartas().add(field.getDeck().getCartas().get(0));
				field.getDeck().getCartas().remove(0);
				this.phase = Phases.MAIN_PHASE;
				return MatchResponse.AVALIBLE_CARD;
			} else {
				return MatchResponse.NO_CARDS;
			}
		}
		
		public void enviarCombatente(Field field) {
			if(phase == Phases.MAIN_PHASE) {

			}
		}
		
		public void usarItem(Field field) {
			if(phase == Phases.MAIN_PHASE) {
				
			}
		}
		
		public void atacar(Fighter escolhido, Fighter alvo) {
			if(phase == Phases.BATTLE_PHASE) {
				
			}
		}
		
		public void encerrarTurno() {
			this.turno++;
			phase = Phases.DRAW_PHASE;
		}
		
	// Getters e Setters
		public int getTurno() {
			return turno;
		}

		public void setTurno(int turno) {
			this.turno = turno;
		}

		public boolean isAtivo() {
			return ativo;
		}

		public void setAtivo(boolean ativo) {
			this.ativo = ativo;
		}

		public Player[] getPlayers() {
			return players;
		}

		public void setPlayers(Player[] players) {
			this.players = players;
		}

		public Phases getPhase() {
			return phase;
		}

		public void setPhase(Phases phase) {
			this.phase = phase;
		}
		
}
