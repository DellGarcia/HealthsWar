package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;

public class DialogView extends JDialog {

	private static final long serialVersionUID = 679931496437068846L;

	private Panel container;
	
	private Label lblMessage;
	private Button btnCalcel;
	private Button btnConfirm;
	
	public DialogView(String message,
						ActionListener calcelAction,
						ActionListener confirmAction) {
		
		setModal(true);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(350, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		container = new Panel(new Color(248,248,255));
		container.setSize(getSize());
		container.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setContentPane(container);
		
		init(message, calcelAction, confirmAction);
		
		setVisible(true);
	}
	
	private void init(String message,
						ActionListener calcelAction,
						ActionListener confirmAction) {
		
		lblMessage = new Label(getWidth(), 40, message, Fonts.TITLE, ColorsUtil.LETTERS_COLOR, null);
		lblMessage.setLocation(getWidth() / 2 - lblMessage.getWidth()/2, 15);
		container.add(lblMessage);
		
		btnCalcel = new Button(100, 40,
				Color.DARK_GRAY, Color.WHITE,
				Fonts.DESTAQUE, "Calcel",
				null, 0,
				ColorsUtil.LETTERS_COLOR, Color.WHITE);
		btnCalcel.setLocation(30, 130);
		btnCalcel.addActionListener(calcelAction);
		container.add(btnCalcel);
		
		btnConfirm = new Button(100, 40,
				Color.DARK_GRAY, Color.WHITE,
				Fonts.DESTAQUE, "Confirm",
				null, 0,
				ColorsUtil.LETTERS_COLOR, Color.WHITE);
		btnConfirm.setLocation(getWidth() - (btnConfirm.getWidth() + 30), 130);
		btnConfirm.addActionListener(confirmAction);
		container.add(btnConfirm);
	}
	
}
