package br.com.healthswar.utils;

import br.com.anonymous.frontend.RegistrarFont;
import java.awt.Font;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Fonts {

	public static final Font NORMAL = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/PixelOperator.ttf").getFile(),
					StandardCharsets.UTF_8), "16f");

	public static final Font TITLE = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/PixelOperator.ttf").getFile(),
					StandardCharsets.UTF_8), "20f");

	public static final Font TITLE_2 = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/PixelOperator.ttf").getFile(),
					StandardCharsets.UTF_8), "24f");

	public static final Font DESTAQUE = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/PixelOperator.ttf").getFile(),
					StandardCharsets.UTF_8), "25f");

	public static final Font INPUT = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/PixelOperator.ttf").getFile(),
					StandardCharsets.UTF_8), "30f");

	public static final Font SPLASH = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/PixelOperator.ttf").getFile(),
					StandardCharsets.UTF_8), "45f");

	public static final Font MAIN_BUTTONS = RegistrarFont.minhaFont(
			URLDecoder.decode(Fonts.class.getResource("../assets/fonts/PixelOperator.ttf").getFile(),
					StandardCharsets.UTF_8), "50f");
	
}
