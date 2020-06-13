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

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.server.Main;

public class InitView extends JFrame {

	private static final long serialVersionUID = 7358900521851754354L;

	private Toolkit tk;
	
	private Panel container;
	
	private Button soloMatch;
	private Button duoMatch;
	private Button localServer;
	private Button register;
	
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
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Solo Match",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		soloMatch.setLocation(getWidth()/2 - 100, getHeight()/2 - 25 - 65);
		soloMatch.addActionListener(matchAction(Request.PLAY_A_SOLO_MATCH));
		
		duoMatch = new Button(
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Duo Match",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		duoMatch.setLocation(getWidth()/2 - 100, getHeight()/2 - 25);
		duoMatch.addActionListener(matchAction(Request.PLAY_A_DUO_MATCH));
		
		localServer = new Button(
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Local Server",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		localServer.setLocation(getWidth()/2 - 100, getHeight()/2 - 25 + 65);
		localServer.addActionListener(openHostPage());
		
		register = new Button(
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Register",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		register.setLocation(getWidth()/2 - 100, getHeight()/2 - 25 + 65 + 65);
		register.addActionListener(openRegisterPage());
				
		container.add(soloMatch);
		container.add(duoMatch);
		container.add(localServer);
		container.add(register);
		
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
	
	private ActionListener openRegisterPage() {
		return new ActionListener() {
			
			@Override                     
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
			}
		};
	}
	
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
