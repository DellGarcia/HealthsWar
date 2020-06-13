package br.com.anonymous.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextField extends JTextField implements FocusListener, KeyListener {
	
	private String placeHolder;
	
	private Color onFocusBorderColor;
	private Color onFocusTextColor;
	
	private boolean filled;
	private boolean placeholderActive;
	
	public TextField(int largura, int altura, String placeHolder, Font font, Color background, Color foreground) {
		setSize(largura, altura);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setBorder(null);
		setToolTipText(placeHolder);
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setText(placeHolder);
		setForeground(Color.LIGHT_GRAY);
		
		this.onFocusBorderColor = Color.BLUE;
		this.filled = false;
		this.onFocusTextColor = foreground;
		this.placeHolder = placeHolder;
		this.placeholderActive = true;
		
		addFocusListener(this);
	}
	
	public void setOnFocusBorderColor(Color onFocusBorderColor) {
		this.onFocusBorderColor = onFocusBorderColor;
	}
	
	public void setOnFocusTextColor(Color onFocusTextColor) {
		this.onFocusTextColor = onFocusTextColor;
	}
	
	public boolean isFilled() {
		return filled;
	}
	
	public String getPlaceHolder() {
		return placeHolder;
	}

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(BorderFactory.createLineBorder(onFocusBorderColor));
		setText("");
		setForeground(onFocusTextColor);
		placeholderActive = false;
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().isEmpty()) {
			setText(placeHolder);
			setForeground(Color.LIGHT_GRAY);
			setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			this.filled = true;
			placeholderActive = true;
		} else {
			setBorder(null);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(placeholderActive) {
			filled = false;
		} else {
			filled = this.getText().length() > 0 ? true : false;
		}
	}
	
}