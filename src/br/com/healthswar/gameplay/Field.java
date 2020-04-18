package br.com.healthswar.gameplay;

import java.util.ArrayList;

public class Field {

	private Fighter[] fighters;
	private ArrayList<Fighter> memoria;
	private ArrayList<Carta> descarte;
	
	public Field() {
		fighters = new Fighter[5];
		memoria = new ArrayList<Fighter>();
		descarte = new ArrayList<Carta>();
		for(int i = 0; i < fighters.length; i++) {
			fighters[i] = null;
		}
	}

	public Fighter[] getCombatentes() {
		return fighters;
	}

	public void setCombatentes(Fighter[] combatentes) {
		this.fighters = combatentes;
	}

	public ArrayList<Fighter> getMemoria() {
		return memoria;
	}

	public void setMemoria(ArrayList<Fighter> memoria) {
		this.memoria = memoria;
	}

	public ArrayList<Carta> getDescarte() {
		return descarte;
	}

	public void setDescarte(ArrayList<Carta> descarte) {
		this.descarte = descarte;
	}
	
}
