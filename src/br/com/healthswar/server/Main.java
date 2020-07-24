package br.com.healthswar.server;

import br.com.healthswar.player.view.ServerView;

public final class Main {
	public static Thread init() {
		return new Thread(ServerView::getInstance);
	}
}
