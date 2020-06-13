package br.com.anonymous.frontend;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class RegistrarFont {
	
	private static Font fonteCustomizada;
	
	public static Font minhaFont(String font, String tamanhoFont) {
		try {
			// Criando a fonte para uso, de um arquivo de fontes e especificando o tamanho
		    fonteCustomizada = Font.createFont(Font.TRUETYPE_FONT, new File(font)).deriveFont(Float.parseFloat(tamanhoFont));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    
		    // Registrando a fonte
		    ge.registerFont(fonteCustomizada);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		    
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		return fonteCustomizada;
		
	}

}
