package br.com.healthswar.server;

import br.com.healthswar.player.view.ControlView;

public class Main {
	public final static Thread init() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {                      
				new ControlView();
			}
		});
	}
}
