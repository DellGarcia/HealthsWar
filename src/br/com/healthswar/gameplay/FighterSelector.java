package br.com.healthswar.gameplay;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;

public class FighterSelector extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8790766783548959664L;

	private Label lblMensagem;
	private Fighter[] fighters;
	
	public FighterSelector() {
		fighters = new Fighter[5];
		
		setBackground(null);
		setSize(700, 190);
		setVisible(false);
		
		lblMensagem = new Label(200, 40,
				"Escolha um combatente",
				Fonts.NORMAL, ColorsUtil.LETTERS_COLOR);
		lblMensagem.setLocation(this.getWidth() / 2 - lblMensagem.getWidth()/2, 0);
		add(lblMensagem);
	}
	
	public void setSelector(FighterField[] fighters) {
		this.removeAll();

		int x = 20;
		int y = 40;
		for(int i = 0; i < 5; i++) {
			this.fighters[i] = fighters[i].getFighter();
			if(this.fighters[i] != null) {
				this.fighters[i].setLocation(x, y);
				this.fighters[i].local = CardLocal.SELECTOR;
				add(this.fighters[i]);
			}
			x += 120;
		}
		
		add(lblMensagem);
		setVisible(true);
	}
}
