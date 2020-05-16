package br.com.healthswar.gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import br.com.dellgarcia.frontend.Label;
import br.com.dellgarcia.frontend.Panel;
import br.com.healthswar.view.Fonts;

public class FighterField extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -149395871309683109L;

	private Fighter fighter;
	private ImageIcon image;
	public Label energyCounter;

	public FighterField() {
		super();
		setBackground(null);
		setSize(100, 141);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		energyCounter = new Label(100, 40, "", Fonts.NORMAL, Color.WHITE, null, SwingConstants.CENTER,
				SwingConstants.CENTER);
		energyCounter.setVisible(false);
		fighter = null;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
		addMouseListener(fighter);
		this.image = this.fighter.frontImg;
		this.energyCounter.setText(Integer.toString(fighter.getEnergies().size()));
		this.energyCounter.setVisible(true);
		repaint();
	}

	public Fighter getFighter() {
		return this.fighter;
	}

	public void removeFighter() {
		this.fighter = null;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		int width = 100, height = 141;

		if (fighter != null) {
			g2d.drawImage(image.getImage(), 0, 0, width, height, this);
		}

		g2d.dispose();
	}

}
