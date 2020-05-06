package br.com.healthswar.gameplay;

import java.awt.Color;

import br.com.dellgarcia.frontend.Panel;

public class FighterSelector extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8790766783548959664L;

	private Fighter[] fighters;
	
	public FighterSelector() {
		super(Color.WHITE);
		
		fighters = new Fighter[5];
		
		setSize(700, 160);
		setVisible(false);
	}
	
	public void setSelector(FighterField[] fighters) {
		this.removeAll();

		int x = 20, y = 10;
		for(int i = 0; i < 5; i++) {
			this.fighters[i] = fighters[i].getFighter();
			if(this.fighters[i] != null) {
				this.fighters[i].setLocation(x, y);
				this.fighters[i].local = CardLocal.SELECTOR;
				add(this.fighters[i]);
			}
			x += 120;
		}
		
		setVisible(true);
	}
}
