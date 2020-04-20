package br.com.healthswar.gameplay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CardView extends Carta {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 534020604704164574L;

	@SuppressWarnings("unused")
	private Carta card;
	
	public CardView(Carta card) {
		this.card = card;
		if(card instanceof Fighter) {
			this.frontImg = "src/br/com/healthswar/assets/card-md.jpg";
		}
		if(card instanceof Energy) {
			this.frontImg = "src/br/com/healthswar/assets/energy-md.jpg";
		}
		if(card instanceof Item) {
			this.frontImg = "src/br/com/healthswar/assets/item-md.png";
		}
		setSize(300, 424);
		virado = false;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		Image imagem = null;
		int width = 300, height = 424;
		
		try {
			imagem = ImageIO.read(new File(virado?backImg:frontImg));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g2d.drawImage(imagem, 0, 0, width, height, this);
		
		g2d.dispose();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setVisible(false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
