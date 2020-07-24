package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.Border;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.PasswordField;
import br.com.anonymous.frontend.TextField;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.player.model.Person;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;

public class RegisterView extends View {

	private static final long serialVersionUID = -5727293124800406361L;

	private final Panel container;
	private Panel form;

	private Label lblName;
	private Label lblEmail;
	private Label lblPassword;
	private Label lblConfirmPass;
	private TextField txtName;
	private TextField txtEmail;
	private PasswordField txtPassword;
	private PasswordField txtConfirmPass;
	private Button btnConfirm;
	private Button btnBack;

	public RegisterView() {
		setTitle("HealthsWar - Cadastro");
		setSize(tk.getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		container = new Panel(ColorsUtil.BACKGROUND_COLOR);
		initForm();
		initFormItens();

		setContentPane(container);
		setVisible(true);
	}

	private void initForm() {
		form = new Panel(container.getBackground());
		form.setSize(450, 500);

		form.setLocation(
				getWidth() / 2 - form.getWidth() / 2,
				getHeight() / 2 - form.getHeight() / 2);

		container.add(form);
	}
	
	private void initFormItens() {
		Color titleColor = ColorsUtil.LETTERS_COLOR;
		Color backgroundColor = ColorsUtil.BACKGROUND_COLOR;
		int width = form.getWidth();

		Border lineBorder = BorderFactory.createLineBorder(titleColor, 2);
		Border border = BorderFactory.createCompoundBorder(lineBorder,
				BorderFactory.createEmptyBorder(5, 10, 5, 5));

		lblName = new Label(100, 40, "Nome:",
				Fonts.DESTAQUE, titleColor, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblName.setLocation(0, 10);
		
		txtName = new TextField(width, 40,
				"Digite seu nome", Fonts.DESTAQUE, null, Color.WHITE);
		txtName.setLocation(0, 50);

		lblEmail = new Label(100, 40,
				"Email:", Fonts.DESTAQUE, titleColor, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblEmail.setLocation(0, 100);
		
		txtEmail = new TextField(width, 40,
				"Digite seu email", Fonts.DESTAQUE, null, Color.WHITE);
		txtEmail.setLocation(0, 140);

		lblPassword = new Label(100, 40, "Senha:",
				Fonts.DESTAQUE, titleColor, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblPassword.setLocation(0, 190);
		
		txtPassword = new PasswordField(width, 40,
				null, Fonts.DESTAQUE, null, Color.WHITE, Color.RED);
		txtPassword.setLocation(0, 230);

		lblConfirmPass = new Label(200, 40, "Confirme a Senha:",
				Fonts.DESTAQUE, titleColor, null, SwingConstants.LEFT, SwingConstants.CENTER);
		lblConfirmPass.setLocation(0, 280);
		
		txtConfirmPass = new PasswordField(width, 40, null,
				Fonts.DESTAQUE, null, Color.WHITE, Color.RED);
		txtConfirmPass.setLocation(0, 320);

		btnBack = new Button(150, 40, backgroundColor, titleColor,
				Fonts.DESTAQUE, "Voltar", titleColor, 3, titleColor, backgroundColor);
		btnBack.setLocation(0, 380);
		btnBack.addActionListener(backAction());

		btnConfirm = new Button(150, 40, backgroundColor, titleColor,
				Fonts.DESTAQUE, "Confirmar", titleColor, 3, titleColor, backgroundColor);
		btnConfirm.setLocation(form.getWidth() - btnConfirm.getWidth(), 380);
		btnConfirm.addActionListener(registerAction());

		form.add(lblName);
		form.add(txtName);
		form.add(lblEmail);
		form.add(txtEmail);
		form.add(lblPassword);
		form.add(txtPassword);
		form.add(lblConfirmPass);
		form.add(txtConfirmPass);
		form.add(btnConfirm);
		form.add(btnBack);
	}
	
	private ActionListener backAction() {
		return e -> dispose();
	}
	
	private ActionListener registerAction() {
		return e -> {
			String nome = txtName.getText();
			String email = txtEmail.getText();
			String senha = new String(txtPassword.getPassword());
			String confirmaSenha = new String(txtConfirmPass.getPassword());

			if(!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !confirmaSenha.isEmpty() &&
				!nome.equals(txtName.getPlaceHolder()) && !email.equals(txtEmail.getPlaceHolder())) {
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
							JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");

						} else {
							JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro");
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
		};
	}

}
