package br.com.healthswar.gameplay;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;

public class FighterField extends Panel implements MouseListener {

	private static final long serialVersionUID = 4058110155034721813L;
	
	private Fighter fighter;
	private ImageIcon image;
	private Label lblFundo;
	public JTextPane energyCounter;

	public FighterField() {
		super();

		setBackground(null);
		setSize(100, 141);
		setBorder(BorderFactory.createLineBorder(ColorsUtil.SPLASH_BACKGROUND_COLOR));

		init();
		addMouseListener(this);
		energyCounter.addMouseListener(this);
	}

	private void init() {
		lblFundo = new Label(getWidth(), getHeight(), null,
				Fonts.DESTAQUE, ColorsUtil.INVISIBLE,null,
				SwingConstants.CENTER, SwingConstants.CENTER);



		energyCounter = new JTextPane();
		energyCounter.setSize(100, 141);
		energyCounter.setFont(Fonts.DESTAQUE);
		energyCounter.setForeground(Color.WHITE);
		energyCounter.setOpaque(true);
		energyCounter.setBackground(ColorsUtil.BLACK_TRANSLUCIDE);
		energyCounter.setEditable(false);
		energyCounter.setVisible(false);
		energyCounter.setLocation(0, 0);

		SimpleAttributeSet align = new SimpleAttributeSet();
		StyleConstants.setAlignment(align, StyleConstants.ALIGN_CENTER);
		energyCounter.setParagraphAttributes(align, false);

		fighter = null;
		add(energyCounter);
		add(lblFundo);
	}
	
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;

		if(this.fighter != null) {
			this.fighter.local = CardLocal.FIELD;
			addMouseListener(fighter);
			energyCounter.addMouseListener(fighter.getMouseListeners()[0]);

			image = this.fighter.frontImg;
			energyCounter.setText("HP: " + this.fighter.getHealthPoints()
							+"\nATK: " + this.fighter.getAtkPower()
							+"\nATP: " + this.fighter.getEnergies().size());

			ImageIcon frontImg = new ImageIcon(image.getImage()
					.getScaledInstance(100, 141, Image.SCALE_DEFAULT));
			lblFundo.setIcon(frontImg);

		} else {
			lblFundo.setIcon(null);
		}
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void removeFighter() {
		fighter = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(fighter != null)
			energyCounter.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(fighter != null)
			energyCounter.setVisible(false);
	}
}
