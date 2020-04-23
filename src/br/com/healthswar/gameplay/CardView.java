package br.com.healthswar.gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.dellgarcia.frontend.Label;
import br.com.healthswar.player.view.MainView;
import br.com.healthswar.view.Fonts;

public class CardView extends JPanel implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 534020604704164574L;

	private URI frontImg;
	
	private Carta card;
	
	private Label lblFundo;
	
	public CardView(Carta card) {
		setLayout(null);
		
		setCard(card);
		
		setSize(300, 424);
		setVisible(false);
		addMouseListener(this);
	}

	public void setCard(Carta card) {
		this.card = card;
		if(lblFundo != null) {
			this.remove(lblFundo);
		}
		lblFundo = new Label(getWidth(), getHeight(), "Enviar", Fonts.DESTAQUE, Color.WHITE, new Color(0, 0, 0, 60), SwingConstants.CENTER, SwingConstants.CENTER);
		lblFundo.setVisible(false);
		lblFundo.setOpaque(true);
		this.add(lblFundo);
		try {
			if(card instanceof Fighter) {
				this.frontImg = Carta.class.getResource("../assets/card-md.jpg").toURI();
				lblFundo.setText("Enviar");
			}
			if(card instanceof Energy) {
				this.frontImg = Carta.class.getResource("../assets/energy-md.jpg").toURI();
				lblFundo.setText("Colocar");
			}
			if(card instanceof Item) {
				this.frontImg = Carta.class.getResource("../assets/item-md.png").toURI();
				lblFundo.setText("Usar");
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
			imagem = ImageIO.read(new File(frontImg));
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
		if(card instanceof Fighter) {
			MainView.INSTANCE.sendFighter((Fighter) card);
		}
		
		if(card instanceof Item) {
			MainView.INSTANCE.useItem((Item) card); 
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		lblFundo.setVisible(true);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		lblFundo.setVisible(false);
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
