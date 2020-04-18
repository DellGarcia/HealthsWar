package br.com.healthswar.gameplay;

import java.awt.Color;

import javax.swing.BorderFactory;

import br.com.dellgarcia.frontend.Panel;

public class FighterField extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -149395871309683109L;

	private Fighter fighter;
	
	public FighterField() {
		fighter = null;
	}
	
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public Fighter getFighter() {
		return this.fighter;
	}
	
	public void removeFighter() {
		this.fighter = null;
	}
	
}
