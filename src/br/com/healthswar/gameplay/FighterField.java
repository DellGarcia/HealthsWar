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
		super();
		setBackground(null);
		setSize(100, 141);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fighter = null;
	}
	
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}
	
	public Fighter getFighter() {
		return this.fighter;
	}
	
	public void removeFighter() {
		this.fighter = null;
	}
	
}
