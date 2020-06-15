package br.com.healthswar.view;

import br.com.anonymous.frontend.RegistrarFont;

import java.awt.Font;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Fonts {

	//public static final Font NORMAL 	= new Font("Verdana", Font.PLAIN, 14);
	//public static final Font TITLE  	= new Font("Verdana", Font.PLAIN, 18);
	//public static final Font DESTAQUE 	= new Font("Verdana", Font.PLAIN, 16);

	public static final Font NORMAL = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "14f");

	public static final Font TITLE = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "18f");

	public static final Font TITLE_2 = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "22f");

	public static final Font DESTAQUE = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "14f");
	
}
