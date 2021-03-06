package br.com.healthswar.gameplay;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.healthswar.gameplay.fighters.Fighter;

public class Field implements Serializable {

	private static final long serialVersionUID = -3459851222097829713L;
	
	private int healthsPoint;
	private Fighter[] fighters;
	private ArrayList<Fighter> memory;
	private ArrayList<Card> discard;
	private Deck deck;
	private Hand hand;
	
	public Field(Deck deck) {
		this.fighters = new Fighter[5];
		this.memory = new ArrayList<Fighter>();
		this.discard = new ArrayList<Card>();
		this.healthsPoint = 10;
		this.deck = deck;
		this.hand = new Hand(deck);
		for(int i = 0; i < fighters.length; i++) {
			fighters[i] = null;
		}
	}

	public int getHealthsPoint() {
		return healthsPoint;
	}

	public void setDamage(int damage) {
		this.healthsPoint += damage;
	}

	public Fighter[] getFighters() {
		return fighters;
	}

	public void setFighter(Fighter[] fighter) {
		this.fighters = fighter;
	}

	public void removeFighter(Fighter fighter) {
		for(int i = 0; i < fighters.length; i++) {
			if(fighters[i] != null) {
				if(fighters[i].id == fighter.id) {
					fighters[i] = null;
					break;
				}
			}
		}
	}
	
	public ArrayList<Fighter> getMemory() {
		return memory;
	}

	public void setMemory(ArrayList<Fighter> memory) {
		this.memory = memory;
	}

	public ArrayList<Card> getDescarte() {
		return discard;
	}

	public void setDiscard(ArrayList<Card> discard) {
		this.discard = discard;
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
