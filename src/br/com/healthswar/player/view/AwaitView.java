package br.com.healthswar.player.view;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.player.view.main.MainView;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;

public class AwaitView extends View {

	private static final long serialVersionUID = 1L;
	
	private final Panel container;
	private final Player player;
	private Label lblMsg;

	public AwaitView(Player player) {
		this.player = player;
		
		setTitle("Health's War - Await View");
		setSize(tk.getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setLayout(null);

		container = new Panel();
		container.setBackground(ColorsUtil.BACKGROUND_COLOR);

		init();

		setContentPane(container);
		setVisible(true);

		animation().start();
		waitMatch().start();
	}

	private void init() {
		lblMsg = new Label(450, 50,
				"Aguardando os outros jogadores...", Fonts.INPUT, ColorsUtil.LETTERS_COLOR);

		lblMsg.setLocation((getWidth() - lblMsg.getWidth()) / 2,
				(getHeight() - lblMsg.getHeight()) / 2);

		container.add(lblMsg);
	}

	private Thread waitMatch() {
		return new Thread(() -> {
			Response response = (Response) player.read();
			if(response == Response.MATCH_READY) {
				MainView.getInstance(player);
				dispose();
			}
		});
	}
	
	private Thread animation() {
		return new Thread(() -> {
			while(true) {
				try {
					lblMsg.setText("Aguardando segundo jogador");

					Thread.sleep(500);

					for(int i = 0; i < 3; i++) {
						lblMsg.setText(lblMsg.getText() + ".");
						Thread.sleep(500);
					}

				} catch(Exception ignored) {}
			}
		});
	}
}
