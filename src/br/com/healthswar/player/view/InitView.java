package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.dellgarcia.frontend.Button;
import br.com.dellgarcia.frontend.Panel;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.server.Main;

@SuppressWarnings("serial")
public class InitView extends JFrame {

	private Toolkit tk;
	
	private Panel container;
	
	private Button soloMatch;
	private Button duoMatch;
//	private Button squadMatch;
	private Button localServer;
	
	public InitView() {
		tk = Toolkit.getDefaultToolkit();
		
		setTitle("Health's War");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tk.getScreenSize());
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		container = new Panel(new Color(65,105,225));
		setContentPane(container);
		
		Font font = new Font("Verdana", Font.PLAIN, 22);
		
		soloMatch = new Button(
				getWidth()/2 - 100, getHeight()/2 - 25 - 65,
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Solo Match",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		soloMatch.addActionListener(matchAction(Request.PLAY_A_SOLO_MATCH));
		
		duoMatch = new Button(
				getWidth()/2 - 100, getHeight()/2 - 25,
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Duo Match",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		duoMatch.addActionListener(matchAction(Request.PLAY_A_DUO_MATCH));
		
//		squadMatch = new Button(
//				getWidth()/2 - 100, getHeight()/2 - 25 + 65,
//				200, 50,
//				Color.DARK_GRAY, Color.WHITE,
//				font, "Squad Match",
//				Color.BLACK, 1,
//				new Color(65,105,225), Color.WHITE);
//		squadMatch.addActionListener(matchAction(Request.PLAY_A_SQUAD_MATCH));
		
		localServer = new Button(
				getWidth()/2 - 100, getHeight()/2 - 25 + 65,
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Local Server",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		localServer.addActionListener(openHostPage());
				
		container.add(soloMatch);
		container.add(duoMatch);
		container.add(localServer);
		
		setVisible(true);
	}
	
	private ActionListener openHostPage() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.init().start();
			}
		};
	}
	
	
	/**
	 * Vê qual partida o player escolheu
	 * */
	private ActionListener matchAction(Request request) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Player player = new Player(new Socket(JOptionPane.showInputDialog("Qual o ip do servidor?", "localhost"), 2222));
					
					player.write(request);
					
					Response response = (Response) player.read();
					if(response == Response.MATCH_FOUND) {
						new AwaitView(player);
						dispose();
					}
					
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null, "Problemas ao tentar conectar ao servidor");
					e2.printStackTrace();
				}
				
			}
		};
	}
	
}
