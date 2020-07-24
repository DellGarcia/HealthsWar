package br.com.healthswar.player.view;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.ProcessaImagem;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;
import br.com.healthswar.utils.ImageUtil;
import br.com.healthswar.utils.StringUtil;
import java.awt.*;

public class SplashView extends View {

    private Panel container;
    private Thread time;

    public SplashView() {
        setTitle("Health's War - Splash");
        setSize(tk.getScreenSize());
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setUndecorated(true);

        init();
        time.start();

        setContentPane(container);
        setVisible(true);
    }

    private void init() {
        container = new Panel(ColorsUtil.SPLASH_BACKGROUND_COLOR);
        time = new Thread(new Delay(this));

        Label imgAnonymous = new Label(200, 200,
                ProcessaImagem.processarArredondamento(ImageUtil.ANONYMOUS));

        imgAnonymous.setLocation(
            (tk.getScreenSize().width - imgAnonymous.getWidth()) / 2,
            (tk.getScreenSize().height - imgAnonymous.getHeight()) / 2 - 75
        );

        Label lblAnonymous = new Label(200, 100, StringUtil.SPLASH_TEXT_ANONYMOUS,
                Fonts.SPLASH, Color.WHITE, null);

        lblAnonymous.setLocation(
                (tk.getScreenSize().width - lblAnonymous.getWidth()) / 2,
                (tk.getScreenSize().height - lblAnonymous.getHeight()) / 2 + 90
        );

        container.add(imgAnonymous);
        container.add(lblAnonymous);
    }

    public static class Delay implements Runnable {

        View view;

        Delay(View view) {
            this.view = view;
        }

        @Override
        public void run() {
            try { Thread.sleep(2000);
            } catch (InterruptedException e) { e.printStackTrace(); }

            new InitView();
            view.dispose();
        }
    }

}
