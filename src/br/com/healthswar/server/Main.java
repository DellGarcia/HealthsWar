package br.com.healthswar.server;

import br.com.healthswar.view.TelaControle;

public class Main {
	public final static Thread init() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {                      
				new TelaControle();
			}
		});
	}
}
