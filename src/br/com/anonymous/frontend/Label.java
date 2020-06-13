package br.com.anonymous.frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public Label(int largura, int altura, ImageIcon img) {
		setSize(largura, altura);
		setBackground(null);
		setOpaque(false);
		setIcon(img);
	}
	
	public Label(int largura, int altura, String txt, Font font, Color foreground) {
		setSize(largura, altura);
		setFont(font);
		setText(txt);
		setForeground(foreground);
		setBackground(null);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
	}
	
	public Label(int largura, int altura, String txt, Font font, Color foreground, Color background) {
		setSize(largura, altura);
		setFont(font);
		setText(txt);
		setForeground(foreground);
		setBackground(background);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
	}
	
	public Label(int largura, int altura, String txt, Font font, Color foreground, 
			Color background, int swingHorizontal, int swingVertical) {
		setSize(largura, altura);
		setFont(font);
		setText(txt);
		setForeground(foreground);
		setBackground(background);
		setHorizontalAlignment(swingHorizontal);
		setVerticalAlignment(swingVertical);
	}

}
