package br.com.healthswar.gameplay;

import br.com.healthswar.comunication.Phases;

public class Turn {
	
	private static int turn = 0;
	private static Player[] players;
	private static Player active;
	private static Player opponent;
	private Phases phase;
	private boolean summonAvalible;
	private boolean atackAvalible;
	
	public Turn(Player[] players) {
		init(players);
	}

	public void init(Player[] players) {
		turn++;
		Turn.players = players;
		this.phase = Phases.DRAW_PHASE;
		this.summonAvalible = true;
		this.atackAvalible = true;
		this.setActive();
	}
	
	public void phaseResolve() {
		active.write(phase);
		opponent.write(phase);
		System.out.println("escrevi");
	}
	
	public void endTurn() {
		init(players);
	}
	
	public void write(Object object) {
		active.write(object);
	}

	/** Getter e Setters */
		public static int getTurn() {
			return turn;
		}
	
		public Player getActive() {
			return active;
		}
	
		public void setActive() {
			active = players[turn % 2 == 0 ? 0 : 1];
			opponent = players[turn % 2 != 0 ? 0 : 1];
		}
	
		public Player getOpponent() {
			return opponent;
		}
	
		public Phases getPhase() {
			return phase;
		}
	
		public void setPhase(Phases phase) {
			this.phase = phase;
		}
	
		public boolean isSummonAvalible() {
			return summonAvalible;
		}
	
		public boolean isAtackAvalible() {
			return atackAvalible;
		}
	
}
