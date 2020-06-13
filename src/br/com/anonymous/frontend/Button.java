package br.com.anonymous.frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button(int width, int height, Color backgroung, Color foreground, 
			Font font, String txt, Color border, int line, Color hoverBackground, Color hoverForeground) {
		setSize(width, height);
		setFont(font);
		setBackground(backgroung);
		setForeground(foreground);
		setText(txt);
		setSelected(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(border, line));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(backgroung);
				setForeground(foreground);
				setBorder(BorderFactory.createLineBorder(border, line));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(hoverBackground);
				setForeground(hoverForeground);
				setBorder(BorderFactory.createLineBorder(hoverForeground, line));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
				
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
			
		});
		
	}
	
	public Button(int width, int height, Color backgroung, 
			Color foreground, Font font, String txt, Color border, int line) {
		setSize(width, height);
		setFont(font);
		setBackground(backgroung);
		setForeground(foreground);
		setText(txt);
		setSelected(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setFocusPainted(false);
		setBorder(BorderFactory.createLineBorder(border, line));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public Button(int width, int height, Color backgroung, Color foreground) {
		setSize(width, height);
		setFont(new Font("Lucida Console", Font.PLAIN, 25));
		setBackground(backgroung);
		setForeground(foreground);
		setSelected(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setFocusPainted(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public Button(int x, int y, int width, int height) {
		setSize(width, height);
		setLocation(x, y);
		setFont(new Font("Lucida Console", Font.PLAIN, 25));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setSelected(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setFocusPainted(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public Button(int largura, int altura, Image img) {
		setSize(largura, altura);
		setOpaque(false);
		setSelected(false);
		setBorder(null);
		setFocusable(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setIcon(new ImageIcon(img));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent evt) {}
			
			@Override
			public void mousePressed(MouseEvent evt) {}
			
			@Override
			public void mouseExited(MouseEvent evt) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent evt) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent evt) {}
		});
	}
	
	public Button() {
		setFont(new Font("Lucida Console", Font.PLAIN, 25));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setSelected(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setFocusPainted(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
