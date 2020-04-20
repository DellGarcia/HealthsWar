package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.util.ArrayList;

public class Field implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6683097949155783980L;

	private int healthsPoint;
	private Fighter[] fighters;
	private ArrayList<Fighter> memoria;
	private ArrayList<Carta> descarte;
	private Deck deck;
	private Hand hand;
	
	public Field(Deck deck) {
		this.fighters = new Fighter[5];
		this.memoria = new ArrayList<Fighter>();
		this.descarte = new ArrayList<Carta>();
		this.healthsPoint = 8000;
		this.deck = deck;
		this.hand = new Hand(deck.getCartas());
		
		for(int i = 0; i < fighters.length; i++) {
			fighters[i] = null;
		}
	}

	public int getHealthsPoint() {
		return healthsPoint;
	}

	public void setHealthsPoint(int healthsPoint) {
		this.healthsPoint = healthsPoint;
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
	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
}
