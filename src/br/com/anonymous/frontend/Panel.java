package br.com.anonymous.frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Image img;
	
	public Panel() {
		setLayout(null);
	}

	public Panel(Color background) {
		setBackground(background);
		setLayout(null);
	}
	
	public Panel(int largura, int altura, Color background) {
		setSize(largura, altura);
		setBackground(background);
		setLayout(null);
	}
	
	public Panel(Image img) {
		this.img = img;
		setLayout(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}

}
