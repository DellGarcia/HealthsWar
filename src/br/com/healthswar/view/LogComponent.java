package br.com.healthswar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class LogComponent extends JScrollPane {
	
	private JTextArea texto;
	
	public LogComponent(Dimension size, Point p) {
		texto = new JTextArea();
		texto.setBackground(Color.LIGHT_GRAY);
		texto.setSize(size);
		texto.setLocation(p);
		texto.setText("Inicie o servidor e veja o status aqui no Log!");
	}
	
	public void atualizarLog(String msg) {
		texto.setText(msg+"\n");
	}

}
