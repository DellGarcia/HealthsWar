package br.com.healthswar.statics;

import br.com.anonymous.frontend.RegistrarFont;

import java.awt.Font;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Fonts {

	public static final Font NORMAL = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "16f");

	public static final Font TITLE = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "20f");

	public static final Font TITLE_2 = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "24f");

	public static final Font DESTAQUE = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/Pixellari.ttf").getFile(),
					StandardCharsets.UTF_8), "18f");
	
}
