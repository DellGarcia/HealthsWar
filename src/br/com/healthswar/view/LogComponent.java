package br.com.healthswar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class LogComponent extends JScrollPane {
	
	private JTextArea text;
	
	public LogComponent(Dimension size, Point p) {
		text = new JTextArea();
		text.setBackground(Color.LIGHT_GRAY);
		text.setSize(size);
		text.setLocation(p);
		text.setText("Inicie o servidor e veja o status aqui no Log!");
	}
	
	public void refreshLog(String msg) {
		text.setText(msg+"\n");
	}

}
