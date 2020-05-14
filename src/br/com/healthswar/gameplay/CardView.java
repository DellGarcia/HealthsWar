package br.com.healthswar.gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.SwingConstants;

import br.com.dellgarcia.frontend.Label;
import br.com.dellgarcia.frontend.Panel;
import br.com.healthswar.player.view.MainView;
import br.com.healthswar.view.Fonts;

public class CardView extends Panel implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 534020604704164574L;

	private transient InputStream frontImg;
	
	private Card card;
	
	private Label lblFundo;
	
	public CardView(Card card) {
		super();
		
		setCard(card);
		
		setSize(300, 424);
		setVisible(false);
		addMouseListener(this);
	}

	public void setCard(Card card) {
		this.card = card;
		
		if(lblFundo != null) {
			this.remove(lblFundo);
		}
		lblFundo = new Label(getWidth(), getHeight(), "Enviar", Fonts.DESTAQUE, Color.WHITE, new Color(0, 0, 0, 60), SwingConstants.CENTER, SwingConstants.CENTER);
		lblFundo.setVisible(false);
		lblFundo.setOpaque(true);
		this.add(lblFundo);
		if(card instanceof Fighter) {
			this.frontImg = Card.class.getResourceAsStream("../assets/card-md.jpg");
			lblFundo.setText("Enviar");
		}
		if(card instanceof Energy) {
			this.frontImg = Card.class.getResourceAsStream("../assets/energy-md.jpg");
			lblFundo.setText("Colocar");
		}
		if(card instanceof Item) {
			this.frontImg = Card.class.getResourceAsStream("../assets/item-md.png");
			lblFundo.setText("Usar");
		}
	}
	
	public Card getCard() {
		return this.card;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		Image imagem = null;
		int width = 300, height = 424;
		
		try {
			imagem = ImageIO.read(frontImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g2d.drawImage(imagem, 0, 0, width, height, this);
		
		g2d.dispose();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(card instanceof Fighter) {
			MainView.INSTANCE.sendFighter((Fighter) card);
			setVisible(false);
		}
		
		if(card instanceof Item) {
			MainView.INSTANCE.useItem((Item) card);
			setVisible(false);
		}
		
		if(card instanceof Energy) {
			MainView.INSTANCE.showSelector();
			setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		lblFundo.setVisible(true);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lblFundo.setVisible(false);
		setVisible(false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}
