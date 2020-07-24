package br.com.healthswar.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public final class ImageUtil {
    /* [Splash View] */
    public static final String ANONYMOUS = URLDecoder.decode(
            ImageUtil.class.getResource("../assets/splash/anonymous.png").getFile(), StandardCharsets.UTF_8);

    /* [Main View] */
    public static final String MAIN_LOGO = URLDecoder.decode(
            ImageUtil.class.getResource("../assets/main/logo.png").getFile(), StandardCharsets.UTF_8);

    public static final String MAIN_BACKGROUND = URLDecoder.decode(
            ImageUtil.class.getResource("../assets/main/background.png").getFile(), StandardCharsets.UTF_8);
}
