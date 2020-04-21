package br.com.healthswar.gameplay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

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
		
		setCard(card);
		
		setSize(300, 424);
		setVisible(false);
		virado = false;
	}

	public void setCard(Carta card) {
		try {
			if(card instanceof Fighter) {
				this.frontImg = Carta.class.getResource("../assets/card-md.jpg").toURI();
			}
			if(card instanceof Energy) {
				this.frontImg = Carta.class.getResource("../assets/energy-md.jpg").toURI();
			}
			if(card instanceof Item) {
				this.frontImg = Carta.class.getResource("../assets/item-md.png").toURI();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
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
