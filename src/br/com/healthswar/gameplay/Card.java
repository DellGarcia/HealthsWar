package br.com.healthswar.gameplay;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.com.healthswar.utils.CardIdentifier;

public abstract class Card extends JLabel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 7106990056333713852L;
	
	public int id;
	protected String name;
	protected String description;
	
	private boolean turned;
	protected CardLocal local;
	
	protected ImageIcon frontImg;
	protected ImageIcon backImg = loadImage("../../assets/backImg2-sm.jpg");
	
	public Card() {
		this.id = CardIdentifier.getNewId();
		this.turned = true;
		this.local = CardLocal.DECK;
		this.setSize(100, 140);
		
		addMouseListener(this);
		addMouseMotionListener(this); 
	}
	
	public void setImage() {
		ImageIcon icon = 
				new ImageIcon((turned?backImg:frontImg)
						.getImage()
							.getScaledInstance(getWidth(), getHeight(), 1));
		
		setIcon(icon);
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isTurned() {
		return turned;
	}

	public void setTurned(boolean virado) {
		this.turned = virado;
		setImage();
	}

	public CardLocal getLocal() {
		return local;
	}

	public void setLocal(CardLocal local) {
		this.local = local;
	}

	protected ImageIcon loadImage(String path) {
		Image image = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream(path));
			byte[] bytes = new byte[bis.available()];
			int byteRead = bis.read(bytes,0,bis.available());
			image = Toolkit.getDefaultToolkit().createImage(bytes,0,byteRead);
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ImageIcon(image);
	}
	
}
