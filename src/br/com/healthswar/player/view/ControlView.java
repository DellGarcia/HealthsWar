package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.CampoPorta;
import br.com.anonymous.frontend.Label;
import br.com.healthswar.server.Server;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;

@SuppressWarnings("serial")
public class ControlView extends JFrame {

	private JPanel 	container;
	private Button	btnStart;
	
	private Label lblPorta;
	private CampoPorta txtPorta;
	
	private static JTextArea log;
	
	private Server server;
	
	public ControlView() {
		setTitle("Health's War Server");
		setSize(1080, 720);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		container = new JPanel();
		init();
	}
	
	private void init() {
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		container.setSize(this.getSize());
		setContentPane(container);

		btnStart = new Button(
				100, 40,
				Color.WHITE, Color.BLACK,
				Fonts.DESTAQUE, "Start",
				Color.BLACK, 1,
				new Color(65,105,225), Color.white
				);
		btnStart.setLocation(container.getWidth()/2 - 50, (int)(container.getHeight() / (1.5)));
		container.add(btnStart);
		btnStart.addActionListener(swicthAction());
			
		lblPorta = new Label(500, 40, "Informe uma porta para ligar o servidor (2000 a 9999)", Fonts.TITLE, ColorsUtil.LETTERS_COLOR, null, SwingConstants.CENTER, SwingConstants.CENTER);
		lblPorta.setLocation(container.getWidth()/2 - lblPorta.getWidth()/2, container.getHeight()/2 - lblPorta.getHeight()/2);
		lblPorta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorta.setForeground(ColorsUtil.LETTERS_COLOR);
		container.add(lblPorta);
		
		txtPorta = new CampoPorta(150, 40, "2222", Fonts.DESTAQUE, Color.WHITE, Color.BLACK);
		txtPorta.setLocation(container.getWidth()/2 - txtPorta.getWidth()/2, container.getHeight()/2 - txtPorta.getHeight()/2 + 60);
		container.add(txtPorta);
			
		log = new JTextArea();
		log.setBackground(Color.LIGHT_GRAY);
		log.setSize(new Dimension(700, 300));
		log.setLocation(new Point(container.getWidth()/2 - log.getWidth()/2, 20));
		log.setText("Inicie o servidor e veja o status aqui no Log!");
		log.setFont(Fonts.DESTAQUE);
		log.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		log.setEditable(false);
		log.setFocusable(false);
		container.add(log);
		
		setVisible(true);
	}
	
	private ActionListener swicthAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(server == null) {
						if(txtPorta.isPreenchido()) {
							int val = txtPorta.getValue();
							if(val >= 2000) {
								server = Server.on(val, "26.93.175.222");
								log.setText(log.getText() + "\nServidor aguardando na porta " + val);
								aguardarPlayers().start();
								btnStart.setText("Stop");
							} else {
								JOptionPane.showMessageDialog(null, "O valor da porta deve ser maior 2000 e menor que 10000!");
								txtPorta.setText("");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Prencha o campo da porta corretamente!");
							txtPorta.setText("");
						}
						
					} else {
						Server.off();
						server = null;
						btnStart.setText("Start");
						log.setText("Inicie o servidor e veja o status aqui no Log!");
					}
				} catch (IOException err) {
					System.out.println("Input Error");
					err.printStackTrace();
				}
			}
		};
	}
	
	private Thread aguardarPlayers() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(Server.active) {
					try {
						server.awaitConnection();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public static void atualizarLog(String msg) {
		log.setText(log.getText() + "\n" + msg);
	}
	
}
