package br.com.healthswar.player.view;

import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public abstract class View extends JFrame {

	private static final long serialVersionUID = 1L;

	protected final Toolkit tk = Toolkit.getDefaultToolkit();
	protected ScheduledExecutorService service;
	
	public View() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		service = Executors.newSingleThreadScheduledExecutor();
	}

	@Override
	public void dispose() {
		service.schedule(super::dispose, 500, TimeUnit.MILLISECONDS);
	}
}
