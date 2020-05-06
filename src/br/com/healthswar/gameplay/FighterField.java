package br.com.healthswar.gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import br.com.dellgarcia.frontend.Panel;

public class FighterField extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -149395871309683109L;

	private Fighter fighter;
	private URL frontImg;
	
	public FighterField() {
		super();
		setBackground(null);
		setSize(100, 141);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fighter = null;
	}
	
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
		this.frontImg = this.fighter.frontImg;
		repaint();
	}
	
	public Fighter getFighter() {
		return this.fighter;
	}
	
	public void removeFighter() {
		this.fighter = null;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		Image imagem = null;
		int width = 100, height = 141;
		
		if(fighter != null) {
			imagem = new ImageIcon(frontImg).getImage();
			g2d.drawImage(imagem, 0, 0, width, height, this);
		}
		
		g2d.dispose();
	}
	
}
