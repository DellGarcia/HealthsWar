package br.com.anonymous.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class TextField extends JTextField implements FocusListener, KeyListener {
	
	private String placeHolder;
	private Color onFocusBorderColor;
	private Color onFocusTextColor;
	private boolean filled;
	private boolean placeholderActive;
	
	public TextField(int largura, int altura, String placeHolder, Font font, Color background, Color foreground, Color border) {
		setSize(largura, altura);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setBorder(null);
		setToolTipText(placeHolder);
		setBorder(BorderFactory.createLineBorder(border));
		setText(placeHolder);
		
		this.onFocusBorderColor = border;
		this.filled = false;
		this.onFocusTextColor = foreground;
		this.placeHolder = placeHolder;
		this.placeholderActive = true;
		
		addFocusListener(this);
	}

	public TextField(int largura, int altura, String txt, Font font, Color background, Color foreground, Boolean editavel) {
		setSize(largura, altura);
		setText(txt);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setLayout(null);
		setBorder(null);
		setEditable(editavel);
		setFocusable(editavel);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	public TextField(int largura, int altura, String placeHolder, Font font, Color background, Color foreground) {
		setSize(largura, altura);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setToolTipText(placeHolder);
		setText(placeHolder);
		setBorder(null);

		this.onFocusBorderColor = null;
		this.filled = false;
		this.onFocusTextColor = foreground;
		this.placeHolder = placeHolder;
		this.placeholderActive = true;

		addFocusListener(this);
		removeFocusListener(this);
	}

	public TextField(int largura, int altura, Font font, Color foreground) {
		setSize(largura, altura);
		setFont(font);
		setForeground(foreground);
		setBackground(null);
		setLayout(null);
		setHorizontalAlignment(SwingConstants.CENTER);
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
		setText(null);
		setForeground(onFocusTextColor);
		placeholderActive = false;
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().isEmpty()) {
			setText(placeHolder);
			setForeground(onFocusTextColor);
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
			filled = this.getText().length() > 0;
		}
	}
	
}