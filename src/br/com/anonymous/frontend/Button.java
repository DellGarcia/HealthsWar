package br.com.anonymous.frontend;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button(int width, int height, Color background, Color foreground,
			Font font, String txt, Color border, int line, Color hoverBackground, Color hoverForeground, int radius) {
		setSize(width, height);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setText(txt);
		setSelected(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setBorder(null);
		setBorder(new RoundedBorder(radius, border, line));
		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setUI(new BasicButtonUI() {
			@Override
			public void update(Graphics g, JComponent c) {
				Graphics2D g2d = (Graphics2D) g;
				if (!c.isOpaque()) {

					Color fillColor = c.getBackground();
					AbstractButton button = (AbstractButton) c;
					ButtonModel model = button.getModel();

					if (model.isPressed()) {
						fillColor = fillColor.darker();

					} else if (model.isRollover()) {
						fillColor = fillColor.brighter();
					}

					g2d.setColor(fillColor);
					g2d.fillRoundRect(1, 1, c.getWidth()-line, c.getHeight()-line, radius, radius);
				}

				paint(g2d, c);
			}
		});
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(background);
				setForeground(foreground);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(hoverBackground);
				setForeground(hoverForeground);
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
	public Button(int width, int height, Color background, Color foreground,
				  Font font, String txt, Color border, int line, Color hoverBackground, Color hoverForeground) {
		setSize(width, height);
		setFont(font);
		setBackground(background);
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
				setBackground(background);
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
	
	public Button(int width, int height, Color background,
			Color foreground, Font font, String txt, Color border, int line) {
		setSize(width, height);
		setFont(font);
		setBackground(background);
		setForeground(foreground);
		setText(txt);
		setSelected(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setFocusPainted(false);
		setBorder(BorderFactory.createLineBorder(border, line));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public Button(int width, int height, Color background, Color foreground) {
		setSize(width, height);
		setFont(new Font("Lucida Console", Font.PLAIN, 25));
		setBackground(background);
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
