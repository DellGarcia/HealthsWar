package br.com.healthswar.gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import br.com.anonymous.frontend.Panel;
import br.com.healthswar.view.Fonts;

public class FighterField extends Panel {

	private static final long serialVersionUID = -149395871309683109L;

	private Fighter fighter;
	private ImageIcon image;
	public JTextArea energyCounter;

	public FighterField() {
		super();
		setBackground(null);
		setSize(100, 141);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		init();
	}

	private void init() {
		energyCounter = new JTextArea("");
		energyCounter.setSize(100, 60);
		energyCounter.setFont(Fonts.NORMAL);
		energyCounter.setOpaque(false);
		energyCounter.setBackground(null);
		energyCounter.setForeground(Color.WHITE);
		energyCounter.setEditable(false);
		energyCounter.setVisible(false);
		fighter = null;
	}
	
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
		this.fighter.local = CardLocal.FIELD;
		addMouseListener(fighter);
		this.image = this.fighter.frontImg;
		this.energyCounter.setText(Integer.toString(fighter.getEnergies().size()));
		this.energyCounter.setVisible(true);
		energyCounter.setText("HP: " + fighter.getHealthPoints()
							+"\nATK: " + fighter.getAtkPower()
							+"\nEnergies: " + fighter.getEnergies().size());
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
