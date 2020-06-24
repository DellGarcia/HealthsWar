package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.server.Main;
import br.com.healthswar.utils.Fonts;

public class InitView extends JFrame {

	private static final long serialVersionUID = 7358900521851754354L;

	private Toolkit tk;
	
	private Panel container;
	
	private Button soloMatch;
	private Button duoMatch;
	private Button localServer;
	private Button register;

	private Label logo;
	
	public InitView() {
		tk = Toolkit.getDefaultToolkit();
		
		setTitle("Health's War - Main Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tk.getScreenSize());
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		container = new Panel(Color.WHITE);
		init();

		setContentPane(container);
		setVisible(true);
	}

	private void init() {
		Font font = Fonts.TITLE;

		logo = new Label(668, 157,
				new ImageIcon(getClass().getResource("../../assets/sprites/Logos/Logo2.png")));
		logo.setLocation((getWidth() - logo.getWidth()) / 2, 0);

		soloMatch = new Button(
				210, 50,
				new Color(154, 26, 26), Color.WHITE,
				font, "Solo Match",
				new Color(125, 15, 15), 3,
				new Color(60, 10, 10),
                Color.WHITE, 45);
		soloMatch.setLocation(getWidth()/2 - 100, getHeight()/2 - 25 - 65);
		soloMatch.addActionListener(matchAction(Request.PLAY_A_SOLO_MATCH));

		duoMatch = new Button(
				210, 50,
				new Color(154, 26, 26), Color.WHITE,
				font, "Duo Match",
				new Color(125, 15, 15), 3,
				new Color(60, 10, 10),
				Color.WHITE, 45);
		duoMatch.setLocation(getWidth()/2 - 100, getHeight()/2 - 25);
		duoMatch.addActionListener(matchAction(Request.PLAY_A_DUO_MATCH));

		localServer = new Button(
				210, 50,
				new Color(154, 26, 26), Color.WHITE,
				font, "Local Server",
				new Color(125, 15, 15), 3,
				new Color(60, 10, 10),
				Color.WHITE, 45);
		localServer.setLocation(getWidth()/2 - 100, getHeight()/2 - 25 + 65);
		localServer.addActionListener(openHostPage());

		register = new Button(
				210, 50,
				new Color(154, 26, 26), Color.WHITE,
				font, "Register",
				new Color(125, 15, 15), 3,
				new Color(60, 10, 10),
				Color.WHITE, 45);
		register.setLocation(getWidth()/2 - 100, getHeight()/2 - 25 + 65 + 65);
		register.addActionListener(openRegisterPage());

		container.add(logo);
		container.add(soloMatch);
		container.add(duoMatch);
		container.add(localServer);
		container.add(register);
	}
	
	private ActionListener openHostPage() {
		return actionEvent -> Main.init().start();
	}
	
	private ActionListener openRegisterPage() {
		return actionEvent -> new RegisterView();
	}
	
	private ActionListener matchAction(Request request) {
		return actionEvent -> {

			try {
				Player player = new Player(new Socket(JOptionPane.showInputDialog(
						"Qual o ip do servidor?", "localhost"), 2222));

				player.write(request);

				Response response = (Response) player.read();
				if(response == Response.MATCH_FOUND) {
					new AwaitView(player);
					dispose();
				}

			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null,
						"Problemas ao tentar conectar ao servidor");
				e2.printStackTrace();
			}

		};
	}
	
}
