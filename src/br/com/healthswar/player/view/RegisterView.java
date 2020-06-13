package br.com.healthswar.player.view;

import java.awt.Color;

import javax.swing.SwingConstants;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.PasswordField;
import br.com.anonymous.frontend.TextField;
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
	
	public RegisterView() {
		setTitle("HealthsWar - Cadastro");
		setSize(1080, 720);
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
		
		container.add(formulario);
	}
	
}
