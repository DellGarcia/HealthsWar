package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.player.view.main.MainView;

@SuppressWarnings("serial")
public class AwaitView extends JFrame {

	private Toolkit tk;	
	
	private Player player;
	
	private Panel container;
	
	private Label lblMsg;
	
	public AwaitView(Player player) {
		tk = Toolkit.getDefaultToolkit();
		this.player = player;
		
		setTitle("Health's War - Await View");
		setSize(tk.getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setLayout(null);
		
		container = new Panel();
		container.setBackground(new Color(65,105,225));
		setContentPane(container);
		
		lblMsg = new Label(350, 50, "Aguardando os outros jogadores...", new Font("Verdana", Font.PLAIN, 20), Color.BLACK);
		lblMsg.setLocation(this.getWidth()/2 - lblMsg.getWidth()/2, this.getHeight()/2 - lblMsg.getHeight()/2);
		container.add(lblMsg);
		
		setVisible(true);
		animacao("Aguardando segundo jogador").start();
	
		aguardarPartida().start();
		
	}

	private Thread aguardarPartida() {
		return new Thread(new Runnable() {
			public void run() {
				Response response = (Response) player.read();
				if(response == Response.MATCH_READY) {
					MainView.getInstance(player);
					dispose();
				}
			}
		});
	}
	
	private Thread animacao(String msg) {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						lblMsg.setText(msg);
						Thread.sleep(500);
						for(int i = 0; i < 3; i++) {
							lblMsg.setText(lblMsg.getText() + ".");
							Thread.sleep(500);
						}
					} catch(Exception e) {
						
					}
				}
			}
		});
		
	}
}
