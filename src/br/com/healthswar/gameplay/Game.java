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
	private boolean summonAvalible;
	
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
		this.summonAvalible = true;
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
	
	public void init(Player[] players) throws IOException {
		players[0].out.writeObject(Response.MATCH_READY);
		players[0].out.writeObject(players[0].getField());
		players[0].out.writeObject(players[1].getField());
		
		players[1].out.writeObject(Response.MATCH_READY);
		players[1].out.writeObject(players[1].getField());
		players[1].out.writeObject(players[0].getField());
	}
	 
	// Açoes do player
		public MatchResponse comprarCarta(Field field) throws IOException {
			if(field.getDeck().getCartas().size() > 0 && field.getHand().getCartas().size() < 7) {
				field.getHand().getCartas().add(field.getDeck().getCartas().get(0));
				field.getDeck().getCartas().remove(0);
				this.phase = Phases.MAIN_PHASE;
				return MatchResponse.AVALIBLE_CARD;
			} else {
				this.phase = Phases.MAIN_PHASE;
				return MatchResponse.NO_CARDS;
			}
		}
		
		public MatchResponse enviarCombatente(Field field, Fighter fighter) {
			if(phase == Phases.MAIN_PHASE && summonAvalible) {
				for(int i = 0; i < field.getCombatentes().length; i++) {
					if(field.getCombatentes()[i] == null) {
						field.getCombatentes()[i] = fighter;
						field.getHand().remove(fighter);
						this.summonAvalible = false;
						return MatchResponse.FIGHTER_READY;
					}
				}
			}
			return MatchResponse.NO_FIGHTER;
		}
		
		public MatchResponse usarItem(Field field, Item item) {
			if(phase == Phases.MAIN_PHASE) {
				field.getHand().remove(item);
				field.getDescarte().add(item);
				return MatchResponse.ITEM_USED;
			}
			return MatchResponse.IMPOSSIBLE_TO_USE;
		}
		
		public MatchResponse colocarEnergia(Field field, Energy energy, Fighter fighter) {
			if(phase == Phases.MAIN_PHASE) {
				for(Fighter lutador: field.getCombatentes()) {
					if(lutador.id == fighter.id) {
						field.getHand().remove(energy);
						lutador.getEnergies().add(energy);
						return MatchResponse.ENERGY_READY;
					}
				}
			}
			return MatchResponse.IMPOSSIBLE_TO_USE;
		}
		
		public void comecarbatalha() {
			this.phase = Phases.BATTLE_PHASE;
		}
		
		public void atacar(Fighter escolhido, Fighter alvo) {
			if(phase == Phases.BATTLE_PHASE) {
				
			}
		}
		
		public void encerrarTurno() {
			this.turno++;
			this.summonAvalible = true;
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
