package br.com.anonymous.frontend;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class ProgressBar extends JProgressBar {
	
	public ProgressBar(int min, int max, int largura, int altura, Color cor) {
		setMinimum(min);
		setMaximum(max);
		setSize(largura, altura);
		setForeground(cor);
		setBorderPainted(false);
	}

	public ProgressBar(int min, int max, int largura, int altura, Color cor, Color border, int line) {
		setMinimum(min);
		setMaximum(max);
		setSize(largura, altura);
		setForeground(cor);
		setBorder(BorderFactory.createLineBorder(border, line));
	}
	
}
