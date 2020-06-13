package br.com.anonymous.frontend;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ProcessaImagem {
	
	private static BufferedImage bi;
	private static BufferedImage bi2;
	
	public static ImageIcon processarArredondamento(String img) {
		try { bi = ImageIO.read(new File(img)); } catch (IOException e) {}
		
		bi2 = new RoundedImage().imgRedonda(bi);
		
		ImageIcon imgIcon = new ImageIcon(bi2);
		
		return imgIcon;
	}

}
