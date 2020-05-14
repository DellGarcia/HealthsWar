package br.com.healthswar.gameplay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Carta extends JLabel implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7106990056333713852L;
	public int id;
	protected String description;
	
	protected boolean virado;
	protected CardLocal local;
	
	protected ImageIcon frontImg;
	protected ImageIcon backImg;
	
	private transient InputStream reader;

	public Carta(int id) {
		this.id = id;
		this.virado = true;
		this.local = CardLocal.DECK;
		
		frontImg = loadImage("../assets/card-sm.jpg");
		backImg = loadImage("../assets/backImg2-sm.jpg");
		
		addMouseListener(this);
		addMouseMotionListener(this); 
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isVirado() {
		return virado;
	}

	public void setVirado(boolean virado) {
		this.virado = virado;
	}

	public CardLocal getLocal() {
		return local;
	}

	public void setLocal(CardLocal local) {
		this.local = local;
	}

	protected synchronized ImageIcon loadImage(String path) {
		Image image = null;
		try {
			reader = getClass().getResourceAsStream(path);
			BufferedInputStream bis = new BufferedInputStream(reader);
			byte[] bytes = new byte[bis.available()];
			int byteRead = bis.read(bytes,0,bis.available());
			image = Toolkit.getDefaultToolkit().createImage(bytes,0,byteRead);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(image);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setIcon(virado?backImg:frontImg);
	}
	
}
