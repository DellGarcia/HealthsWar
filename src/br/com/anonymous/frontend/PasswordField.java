package br.com.anonymous.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

public class PasswordField extends JPasswordField implements FocusListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private String placeHolder;
	
	private boolean filled;
	
	private Color onFocusBorderColor;
	private Color onFocusTextColor;
	
	public PasswordField(int largura, int altura, String placeHolder, Font font, Color background, Color foreground) {
		setSize(largura, altura);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setBorder(null);
		setToolTipText(placeHolder);
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setText(placeHolder);
		setForeground(Color.LIGHT_GRAY);
		
		this.onFocusBorderColor = Color.RED;
		this.onFocusTextColor = foreground;
		this.placeHolder = placeHolder;
		
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
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(!(this.getPassword().length > 0)) {
			setText(placeHolder);
			setForeground(Color.LIGHT_GRAY);
			setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			filled = true;
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
		filled = new String(this.getPassword()).length() > 0 ? true : false;
	}
}
