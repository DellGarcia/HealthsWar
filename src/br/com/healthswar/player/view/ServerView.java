package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.CampoPorta;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.TextField;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.server.Server;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;
import br.com.healthswar.utils.ImageUtil;
import br.com.healthswar.utils.StringUtil;

public class ServerView extends View {

	private static final long serialVersionUID = 1L;
	
	private static ServerView INSTANCE;
	private static JTextArea log;
	private final JPanel container;
	private CampoPorta inputPorta;
	private TextField inputIP;
	private Button	btnStart;
	private Button btnGoGame;
	private Button btnBack;
	private Server server;
	
	private ServerView() {
		super();

		setTitle("Health's War Server");
		setSize(tk.getScreenSize());
		setLocationRelativeTo(null);
		setUndecorated(true);

		container = new Panel(new ImageIcon(ImageUtil.MAIN_BACKGROUND).getImage());
		container.setBackground(ColorsUtil.BACKGROUND_COLOR);
		init();

		setContentPane(container);
		setVisible(true);
	}
	
	private void init() {
		Border lineBorder = BorderFactory.createLineBorder(ColorsUtil.LETTERS_COLOR, 3);
		TitledBorder logTitle = BorderFactory.createTitledBorder(lineBorder, "Server Log");
		logTitle.setTitleJustification(TitledBorder.CENTER);
		logTitle.setTitleFont(Fonts.SPLASH);
		logTitle.setTitleColor(ColorsUtil.LETTERS_COLOR);

		TitledBorder portTitle = BorderFactory.createTitledBorder(lineBorder, StringUtil.SERVER_PORT);
		portTitle.setTitleJustification(TitledBorder.CENTER);
		portTitle.setTitleFont(Fonts.INPUT);
		portTitle.setTitleColor(ColorsUtil.LETTERS_COLOR);

		TitledBorder ipTitle = BorderFactory.createTitledBorder(lineBorder, StringUtil.SERVER_IP);
		ipTitle.setTitleJustification(TitledBorder.CENTER);
		ipTitle.setTitleFont(Fonts.INPUT);
		ipTitle.setTitleColor(ColorsUtil.LETTERS_COLOR);

		Border border = BorderFactory.createCompoundBorder(logTitle,
				BorderFactory.createEmptyBorder(20, 20, 20, 20));

		log = new JTextArea();
		log.setBackground(null);
		log.setSize(new Dimension(500, 500));
		log.setLocation(new Point(((getWidth() - log.getWidth()) / 2) - 250,
				(getHeight() - log.getHeight()) / 2));

		log.setText(StringUtil.LOG_MESSAGE);
		log.setForeground(Color.WHITE);
		log.setFont(Fonts.DESTAQUE);
		log.setBorder(border);
		log.grabFocus();
		log.setEditable(false);

		inputIP = new TextField(300, 75, StringUtil.DEFAULT_IP, Fonts.INPUT, null, Color.WHITE);
		inputIP.setLocation((getWidth() - inputIP.getWidth()) / 2 + (log.getWidth() / 2),
				(getHeight() - inputIP.getHeight()) / 2 - 75 * 2);

		inputIP.setHorizontalAlignment(SwingConstants.CENTER);
		inputIP.setBorder(ipTitle);

		inputPorta = new CampoPorta(300, 75, "2222", Fonts.INPUT, null, Color.WHITE);
		inputPorta.setLocation((getWidth() - inputPorta.getWidth()) / 2 + (log.getWidth() / 2),
							(getHeight() - inputPorta.getHeight()) / 2);

		inputPorta.setHorizontalAlignment(SwingConstants.CENTER);
		inputPorta.setBorder(portTitle);

		btnStart = new Button(
				300, 75,
				null, Color.WHITE,
				Fonts.SPLASH, StringUtil.START_BUTTON,
				null, 0,
				null, ColorsUtil.LETTERS_COLOR);

		btnStart.setLocation((getWidth() - btnStart.getWidth()) / 2 + (log.getWidth() / 2),
							(getHeight() - btnStart.getHeight()) / 2 + 75 * 2);
		btnStart.addActionListener(switchAction());

		btnGoGame = new Button(
				300, 75,
				null, Color.WHITE,
				Fonts.SPLASH, StringUtil.GAME_BUTTON,
				null, 0,
				null, ColorsUtil.LETTERS_COLOR);

		btnGoGame.setLocation((getWidth() - btnStart.getWidth()) / 2 + (log.getWidth() / 2),
				(getHeight() - btnGoGame.getHeight()) / 2 + 75 * 3);
		btnGoGame.setVisible(false);
		btnGoGame.addActionListener(matchAction());

		btnBack = new Button(
				300, 75,
				null, Color.WHITE,
				Fonts.SPLASH, StringUtil.BACK_BUTTON,
				null, 0,
				null, ColorsUtil.LETTERS_COLOR);

		btnBack.setLocation((getWidth() - btnBack.getWidth()) / 2 - (log.getWidth() / 2),
				(getHeight() - btnStart.getHeight()) / 2 + 325);

		btnBack.addActionListener(e-> {
			new InitView();

			service.schedule(() -> setVisible(false), 500, TimeUnit.MILLISECONDS);
		});

		container.add(log);
		container.add(inputPorta);
		container.add(inputIP);
		container.add(btnStart);
		container.add(btnGoGame);
		container.add(btnBack);
	}

	private ActionListener matchAction() {
		return e-> {
			try {
				Player player = new Player(new Socket(inputIP.getText(), inputPorta.getValue()));
				player.write(Request.PLAY_A_DUO_MATCH);

				Response response = (Response) player.read();
				if(response == Response.MATCH_FOUND) {
					new AwaitView(player);
					dispose();
				}
			} catch (IOException unknownHostException) {
				unknownHostException.printStackTrace();
			}
		};
	}
	
	private ActionListener switchAction() {
		return e -> {
			try {
				if(server == null) {
					if(inputPorta.isPreenchido()) {
						int port = inputPorta.getValue();
						String ip = inputIP.getText();

						if(port >= 2000) {
							server = Server.on(port, ip);

							atualizarLog("Servidor IP: "+ ip);
							atualizarLog("Servidor aguardando na porta: " + port);

							aguardarPlayers().start();
							btnStart.setText(StringUtil.STOP_BUTTON);
							btnGoGame.setVisible(true);

						} else {
							JOptionPane.showMessageDialog(null,
									"O valor da porta deve ser maior 2000 e menor que 10000!");
							inputPorta.setText(null);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Preencha o campo da porta corretamente!");
						inputPorta.setText(null);
					}

				} else {
					Server.off();
					server = null;

					btnStart.setText(StringUtil.START_BUTTON);
					btnGoGame.setVisible(false);
					log.setText(StringUtil.LOG_MESSAGE);
				}
			} catch (IOException err) {
				System.out.println("Input Error");
				err.printStackTrace();
			}
		};
	}
	
	private Thread aguardarPlayers() {
		return new Thread(() -> {
			while(Server.active) {
				try {
					server.awaitConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void atualizarLog(String msg) {
		log.setText(log.getText() + "\n" + msg);
	}

	public static ServerView getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ServerView();
		}

		return INSTANCE;
	}

	public static void destroy() {
		INSTANCE.dispose();
		INSTANCE = null;
	}
}
