package br.com.healthswar.server;

import java.io.IOException;
import java.net.ServerSocket;

import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.contoller.ConnectionFactory;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.view.TelaControle;

public class Server extends ServerSocket {
	
	private static Server server;
	
	public static boolean active;
	
	private Partida solo;
	private Partida duo;
	private Partida squad;
	
	private Server(int port) throws IOException {
		super(port);
		solo = new Partida(Request.PLAY_A_SOLO_MATCH);
		duo = new Partida(Request.PLAY_A_DUO_MATCH);
		squad = new Partida(Request.PLAY_A_SQUAD_MATCH);
		ConnectionFactory.openConnection();
	}

	public static Server on(int port) throws IOException {
		if(server == null) {
			server = new Server(port);
			active = true;
		}
		return server;
	}
	
	public static void off() throws IOException {
		server.close();
		active = false;
		server = null;
	}
	
	/**
	 * Fica aguardando o player se conectar
	 * e vê o que ele quer fazer
	 * */
	public void awaitConnetion() throws IOException, ClassNotFoundException {
		TelaControle.atualizarLog("Aguardando conexao");
		
		Player player = new Player(accept());
		
		Request request = (Request) player.read();
		player.write(Response.MATCH_FOUND);
		
		switch(request) {
			case PLAY_A_SOLO_MATCH:
				solo.addPlayer(player);
				verificarPartida(solo, Request.PLAY_A_SOLO_MATCH);
				break;
				
			case PLAY_A_DUO_MATCH:
				duo.addPlayer(player);
				verificarPartida(duo, Request.PLAY_A_DUO_MATCH);
				break;
				
			case PLAY_A_SQUAD_MATCH:
				squad.addPlayer(player);
				verificarPartida(squad, Request.PLAY_A_SQUAD_MATCH);
				break;
		}
		
		TelaControle.atualizarLog("Player conectado");
	}
	
	/**
	 * Verifica se a partida está completa
	 * Se estiver inicia a partida em questao
	 * e comeca a preencher outra partida
	 * */
	private void verificarPartida(Partida match, Request request) {
		if(match.getCompleto()) {
			match.start();
			TelaControle.atualizarLog("Partida completa e inicida");
			match = new Partida(request);
		}
	}
	
}
