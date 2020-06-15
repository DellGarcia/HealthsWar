package br.com.anonymous.frontend;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {

    private int radius;
    private Color borda;
    private int line;

    RoundedBorder(int radius, Color borda, int line) {
       this.radius = radius;
       this.borda = borda;
       this.line = line;
    }

   public Insets getBorderInsets(Component c) {
       return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
   }

   public boolean isBorderOpaque() {
       return true;
   }

   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	   Graphics2D g2d = (Graphics2D) g;
	   
	   g2d.setColor(borda);
	   g2d.setStroke(new BasicStroke(line));
       g2d.drawRoundRect(x+1, y+1, width-line, height-line, radius, radius);
   }
}
