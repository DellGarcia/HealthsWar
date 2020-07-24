package br.com.healthswar.player.view;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.TextField;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;
import br.com.healthswar.utils.StringUtil;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerIPDialog extends JDialog {

    private final Request request;
    private final View view;
    private Panel container;
    private Label lblAviso;
    private TextField title;
    private TextField ip;
    private Button go;
    private Button x;
    ScheduledExecutorService service;

    public ServerIPDialog(Request request, View view) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(750, 350);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setModal(true);

        this.request = request;
        this.view = view;

        init();

        SwingUtilities.invokeLater(() -> ip.requestFocus());

        setContentPane(container);
        setVisible(true);
    }

    private void init() {
        service = Executors.newSingleThreadScheduledExecutor();
        Border lineBorder = BorderFactory.createLineBorder(ColorsUtil.LETTERS_COLOR, 3);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, StringUtil.SERVER_COLON_PORT);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleFont(Fonts.INPUT);
        titledBorder.setTitleColor(ColorsUtil.LETTERS_COLOR);

        container = new Panel(ColorsUtil.BACKGROUND_COLOR);
        container.setBorder(lineBorder);

        title = new TextField(getWidth(), 50,
                StringUtil.FIND_HOST, Fonts.INPUT,
                ColorsUtil.LETTERS_COLOR, ColorsUtil.BACKGROUND_COLOR, false);
        title.setLocation(0, 0);

        lblAviso = new Label(500, 50, "Wrong IP Address",
                Fonts.DESTAQUE, ColorsUtil.RED_WRONG, null);
        lblAviso.setLocation((getWidth() - 500) / 2, (getHeight() - 50) / 2 + 150);
        lblAviso.setVisible(false);

        ip = new TextField(500 - 100, 75, Fonts.DESTAQUE, Color.WHITE);
        ip.setLocation((getWidth() - 500) / 2, (getHeight() - ip.getHeight()) / 2);
        ip.setText("127.0.0.1:2222");
        ip.setBorder(titledBorder);
        ip.setCaretColor(Color.WHITE);

        go = new Button(75, 57, null,
                ColorsUtil.LETTERS_COLOR, Fonts.INPUT, StringUtil.GO, null,
                3, ColorsUtil.LETTERS_COLOR, ColorsUtil.BACKGROUND_COLOR);
        go.setLocation(ip.getWidth() + go.getWidth() * 2 - 20, (getHeight() - go.getHeight()) / 2 + 6);
        go.addActionListener(go());

        x = new Button(50, 50, null,
                ColorsUtil.BACKGROUND_COLOR, Fonts.INPUT, StringUtil.X, null,
                0, null, ColorsUtil.BACKGROUND_COLOR);
        x.setLocation(getWidth() - x.getWidth(), 0);
        x.addActionListener(close());
        x.setOpaque(false);

        container.add(ip);
        container.add(go);
        container.add(x);
        container.add(lblAviso);
        container.add(title);
    }

    private ActionListener close() {
        return e -> dispose();
    }

    private ActionListener go() {
        return e -> {
            try {
                String[] line = ip.getText().split(":");
                String ip = line[0];
                int port = Integer.parseInt(line[1]);

				Player player = new Player(new Socket(ip, port));
				player.write(request);

				Response response = (Response) player.read();

				if(response == Response.MATCH_FOUND) {
                    ((InitView) view).goMatch(player);
					dispose();
				}

			} catch (ArrayIndexOutOfBoundsException | IOException e1) {
				lblAviso.setVisible(true);

                service.schedule(() -> lblAviso.setVisible(false), 5, TimeUnit.SECONDS);
			}
        };
    }
}
