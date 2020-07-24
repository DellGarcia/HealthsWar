package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.server.Main;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;
import br.com.healthswar.utils.ImageUtil;
import br.com.healthswar.utils.StringUtil;

public class InitView extends View {
	private static final long serialVersionUID = 7358900521851754354L;

	Panel container;

	public InitView() {
		super();

		setTitle("Health's War - Main Menu");
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
		Font font = Fonts.MAIN_BUTTONS;

		br.com.anonymous.frontend.Label logo = new br.com.anonymous.frontend.Label(489, 347,
				new ImageIcon(new ImageIcon(ImageUtil.MAIN_LOGO)
						.getImage().getScaledInstance(489, 347, Image.SCALE_DEFAULT)));

		logo.setLocation((getWidth() - logo.getWidth()) / 2, 0);

		br.com.anonymous.frontend.Button soloMatch = new br.com.anonymous.frontend.Button(
				300, 75,
				null, Color.WHITE,
				font, StringUtil.SOLO_MATCH,
				null, 0,
				null, ColorsUtil.LETTERS_COLOR);

		soloMatch.setLocation((getWidth() - soloMatch.getWidth()) / 2, getHeight() / 2 + 45);
		soloMatch.addActionListener(matchAction(Request.PLAY_A_SOLO_MATCH));

		br.com.anonymous.frontend.Button duoMatch = new br.com.anonymous.frontend.Button(
				300, 75,
				null, Color.WHITE,
				font, StringUtil.DUO_MATCH,
				null, 0,
				null, ColorsUtil.LETTERS_COLOR);

		duoMatch.setLocation((getWidth() - duoMatch.getWidth()) / 2, getHeight() / 2 + 120);
		duoMatch.addActionListener(matchAction(Request.PLAY_A_DUO_MATCH));

		br.com.anonymous.frontend.Button localServer = new br.com.anonymous.frontend.Button(
				350, 75,
				null, Color.WHITE,
				font, StringUtil.LOCAL_SERVER,
				null, 0,
				null, ColorsUtil.LETTERS_COLOR);

		localServer.setLocation((getWidth() - localServer.getWidth()) / 2, getHeight() / 2 + 195);
		localServer.addActionListener(openHostPage());

		br.com.anonymous.frontend.Button register = new Button(
				300, 75,
				null, Color.WHITE,
				font, StringUtil.REGISTER,
				null, 0,
				null, ColorsUtil.LETTERS_COLOR);

		register.setLocation((getWidth() - register.getWidth()) / 2, getHeight() / 2 + 270);
		register.addActionListener(openRegisterPage());

		container.add(logo);
		container.add(soloMatch);
		container.add(duoMatch);
		container.add(localServer);
		container.add(register);
	}

	private ActionListener openHostPage() {
		return actionEvent ->  {
			if(ServerView.getInstance() == null) {
				Main.init().start();
			} else {
				ServerView.getInstance().setVisible(true);
			}

			dispose();
		};
	}

	private ActionListener openRegisterPage() {
		return actionEvent -> new RegisterView();
	}

	private ActionListener matchAction(Request request) {
		return actionEvent -> new ServerIPDialog(request, this);
	}

	public void goMatch(Player player) {
		new AwaitView(player);
		dispose();
	}
}
