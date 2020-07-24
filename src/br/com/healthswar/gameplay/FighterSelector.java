package br.com.healthswar.gameplay;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.TextField;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;
import br.com.healthswar.utils.StringUtil;

import javax.swing.*;
import javax.swing.border.Border;

public class FighterSelector extends Panel {

	private static final long serialVersionUID = -3687574369217347198L;
	
	private Label lblMensagem;
	private TextField title;
	private Fighter[] fighters;
	
	public FighterSelector() {
		Border lineBorder = BorderFactory.createLineBorder(ColorsUtil.LETTERS_COLOR, 3);
		fighters = new Fighter[5];
		
		setBackground(null);
		setSize(700, 300);
		setVisible(false);
		setBorder(lineBorder);
		setOpaque(true);
		
		lblMensagem = new Label(200, 40,
				"Escolha um combatente",
				Fonts.NORMAL, ColorsUtil.LETTERS_COLOR);
		lblMensagem.setLocation(this.getWidth() / 2 - lblMensagem.getWidth()/2, 0);

		title = new TextField(getWidth(), 50,
				StringUtil.CHOOSE_FIGHTER, Fonts.INPUT,
				ColorsUtil.LETTERS_COLOR, ColorsUtil.BACKGROUND_COLOR, false);
		title.setLocation(0, 0);

		add(lblMensagem);
		add(title);
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
