package br.com.anonymous.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextField extends JTextField implements FocusListener {
	
	private String placeHolder;
	private Color onFocusBorderColor;
	
	private Color onFocusTextColor;
	
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

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(BorderFactory.createLineBorder(onFocusBorderColor));
		setText("");
		setForeground(onFocusTextColor);
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().isEmpty()) {
			setText(placeHolder);
			setForeground(Color.LIGHT_GRAY);
			setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		} else {
			setBorder(null);
		}
	}
	
}