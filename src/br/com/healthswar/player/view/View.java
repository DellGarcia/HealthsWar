package br.com.healthswar.player.view;

import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class View extends JFrame {

	private static final long serialVersionUID = 1L;

	protected final Toolkit TK = Toolkit.getDefaultToolkit();
	
	public View() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
	}
	
}
