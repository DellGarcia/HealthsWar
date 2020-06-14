package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.PasswordField;
import br.com.anonymous.frontend.TextField;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.player.model.Person;
import br.com.healthswar.view.Fonts;

public class RegisterView extends View {

	private static final long serialVersionUID = -5727293124800406361L;

	private Panel container;
	private Panel formulario;
	
	private Label lblNome;
	private Label lblEmail;
	private Label lblSenha;
	private Label lblConfirmaSenha;
	
	private TextField txtNome;
	private TextField txtEmail;
	private PasswordField txtSenha;
	private PasswordField txtConfirmaSenha;
	
	private Button btnConfirmar;
	private Button btnVoltar;
	
	public RegisterView() {
		setTitle("HealthsWar - Cadastro");
		setSize(1080, 720);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		container = new Panel(Color.WHITE);
		container.setSize(this.getSize());
		setContentPane(container);
		
		init();
		
		setVisible(true);
	}
	
	private void init() {
		formulario = new Panel(container.getBackground());
		formulario.setSize(300, 500);
		formulario.setLocation(
				container.getWidth() / 2 - formulario.getWidth() / 2, 
				container.getHeight() / 2 - formulario.getHeight() / 2);
		
		lblNome = new Label(100, 40, "Nome:", Fonts.TITLE, Color.BLACK, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblNome.setLocation(0, 10);
		formulario.add(lblNome);
		
		txtNome = new TextField(300, 40, "Digite seu nome", Fonts.DESTAQUE, null, Color.BLACK);
		txtNome.setLocation(0, 50);
		formulario.add(txtNome);
		
		lblEmail = new Label(100, 40, "Email:", Fonts.TITLE, Color.BLACK, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblEmail.setLocation(0, 100);
		formulario.add(lblEmail);
		
		txtEmail = new TextField(300, 40, "Digite seu email", Fonts.DESTAQUE, null, Color.BLACK);
		txtEmail.setLocation(0, 140);
		formulario.add(txtEmail);
		
		lblSenha = new Label(100, 40, "Senha:", Fonts.TITLE, Color.BLACK, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblSenha.setLocation(0, 190);
		formulario.add(lblSenha);
		
		txtSenha = new PasswordField(300, 40, "", Fonts.DESTAQUE, null, Color.BLACK);
		txtSenha.setLocation(0, 230);
		formulario.add(txtSenha);
		
		lblConfirmaSenha = new Label(200, 40, "Confirme a Senha:", Fonts.TITLE, Color.BLACK, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblConfirmaSenha.setLocation(0, 280);
		formulario.add(lblConfirmaSenha);
		
		txtConfirmaSenha = new PasswordField(300, 40, "", Fonts.DESTAQUE, null, Color.BLACK);
		txtConfirmaSenha.setLocation(0, 320);
		formulario.add(txtConfirmaSenha);
		
		btnConfirmar = new Button(100, 40, Color.DARK_GRAY, Color.WHITE, Fonts.DESTAQUE, "Confirmar", null, 0, Color.DARK_GRAY, Color.WHITE);
		btnConfirmar.setLocation(formulario.getWidth() - 100, 380);
		btnConfirmar.addActionListener(registerAction());
		formulario.add(btnConfirmar);
		
		btnVoltar = new Button(100, 40, Color.DARK_GRAY, Color.WHITE, Fonts.DESTAQUE, "Voltar", null, 0, Color.DARK_GRAY, Color.WHITE);
		btnVoltar.setLocation(0, 380);
		btnVoltar.addActionListener(backAction());
		formulario.add(btnVoltar);
		
		container.add(formulario);
	}
	
	private ActionListener backAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
	}
	
	private ActionListener registerAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				String email = txtEmail.getText();
				String senha = new String(txtSenha.getPassword());
				String confirmaSenha = new String(txtConfirmaSenha.getPassword());
				
				if(!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !confirmaSenha.isEmpty() &&
					!nome.equals(txtNome.getPlaceHolder()) && !email.equals(txtEmail.getPlaceHolder())) {
					if(senha.equals(confirmaSenha)) {
						
						Person person = new Person();
						
						person.setName(nome);
						person.setEmail(email);
						person.setPassword(senha);
						person.setDerrotas(0);
						person.setVitorias(0);
						
						try {
							Player player = new Player(new Socket("localhost", 2222));
							
							Request request = Request.REGISTER_PLAYER;
							player.write(request);
							player.write(person);
							
							Response response = (Response) player.read();
							
							if(response == Response.SUCCESSFULLY_REGISTERED) {
								JOptionPane.showMessageDialog(null, "Cadastro efeutado com sucesso");
							} else {
								JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro");
							}
									
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "As senhas não coincidem");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios");
				}
			}
		};
	}
	
}
