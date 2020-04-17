package br.com.healthswar.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

import br.com.dellgarcia.frontend.TextField;

@SuppressWarnings("serial")
public class CampoPorta extends TextField implements KeyListener {

	private Integer[] array = {
			KeyEvent.VK_NUMPAD0, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD4,
			KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD9,
			KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5,
			KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_0
	};
	
	private List<Integer> valores = Arrays.asList(array);
	
	private boolean filled;
	private int length;
	
	public CampoPorta(int largura, int altura, String txt, Font font, Color background, Color foreground) {
		super(largura, altura, txt, font, background, foreground);
		setFocusable(true);
		
		addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		validarValor(e);
	}
	
	@Override
	public void setText(String t) {
		super.setText(t);
		length = t.length();
	}
	
	private void validarValor(KeyEvent e) {
		boolean valido = false;
		
		for(Integer i: valores) {
			if(e.getKeyCode() == i) {
				valido = true;
			}
		}
		
		if(valido && this.getText().length() < 4)
			this.setText(getText() + e.getKeyChar());
		
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && length > 0) {
			this.setText(getText().substring(0, getText().length()-1));
		}
		
		if(length >= 3 ) {
			filled = true;
		} else {
			filled = false;
		}
	}
	
	public boolean isFilled() {
		if(length >= 3 ) {
			filled = true;
		} else {
			filled = false;
		}
		return this.filled;
	}
	
	public int getValue() {
		return Integer.parseInt(getText());
	}
	
}
