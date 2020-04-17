package br.com.healthswar.gameplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;

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
			player.setHealthsPoint(8000);
			player.setDeck(decks.get(i));
			player.setHand(new Hand(player.getDeck().getCartas()));
			i++;
		}
		Collections.shuffle(Arrays.asList(players));
		return players;
	}
	
	// Açoes do player
		public void comprarCarta(Player player) throws IOException {
			if(phase == Phases.DRAW_PHASE) {
				if(player.getDeck().getCartas().size() > 0) {
					player.out.writeObject(MatchResponse.AVALIBLE_CARD);
					player.getDeck().getCartas().remove(0);
					this.phase = Phases.MAIN_PHASE;
				} else {
					player.out.writeObject(MatchResponse.NO_CARDS);
				}
			}
		}
		
		public void enviarCombatente(Player player) {
			if(phase == Phases.MAIN_PHASE) {
				
			}
		}
		
		public void usarItem(Player player) {
			if(phase == Phases.MAIN_PHASE) {
				
			}
		}
		
		public void atacar(Combatente escolhido, Combatente alvo) {
			if(phase == Phases.BATTLE_PHASE) {
				
			}
		}
		
		public void encerrarTurno(Player player) {
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
