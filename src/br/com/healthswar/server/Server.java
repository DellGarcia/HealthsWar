package br.com.healthswar.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.contoller.PersonDao;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.player.model.Person;
import br.com.healthswar.player.view.ServerView;
import br.com.healthswar.utils.StringUtil;

public class Server extends ServerSocket {
	
	private static Server server;
	public static boolean active;
	
	private final ArrayList<Match> solo;
	private final ArrayList<Match> duo;

	private Server(int port, String ip) throws IOException {
		super(port, 50, InetAddress.getByName(ip));

		solo = new ArrayList<>();
		duo = new ArrayList<>();
		solo.add(new Match(Request.PLAY_A_SOLO_MATCH));
		duo.add(new Match(Request.PLAY_A_DUO_MATCH));
	}
	
	public static Server on(int port, String ip) throws IOException {
		if(server == null) {
			server = new Server(port, ip);
			active = true;
		}

		return server;
	}
	
	public static void off() throws IOException {
		server.close();
		active = false;
		server = null;
	}
	
	public void awaitConnection() throws IOException {
		ServerView.atualizarLog("Aguardando conexão");
		Player player = new Player(accept());

		ServerView.atualizarLog("Conexão estabelecida");
		Request request = (Request) player.read();
		
		switch(request) {
			case PLAY_A_SOLO_MATCH:
				solo.get(solo.size() - 1).addPlayer(player);
				verificarPartida(solo.get(solo.size() - 1), Request.PLAY_A_SOLO_MATCH);
				player.write(Response.MATCH_FOUND);
				break;
				
			case PLAY_A_DUO_MATCH:
				duo.get(duo.size() - 1).addPlayer(player);
				verificarPartida(duo.get(duo.size() - 1), Request.PLAY_A_DUO_MATCH);
				player.write(Response.MATCH_FOUND);
				break;
				
			case DELETE_PLAYER:
				break;
				
			case REGISTER_PLAYER:
				Person person = (Person) player.read();
				
				PersonDao dao = new PersonDao();
				dao.insert(person);
				
				Response response = Response.SUCCESSFULLY_REGISTERED;
				player.write(response);
				break;
				
			case SELECT_ALL_PLAYERS:
				break;
				
			case SELECT_PLAYER:
				break;
				
			case UPDATE_PLAYER:
				break;
		}
	}
	
	private void verificarPartida(Match match, Request request) {
		if(match.getCompleto()) {
			match.start();

			switch(request) {
				case PLAY_A_SOLO_MATCH:
					solo.add(new Match(request));
					break;
					
				case PLAY_A_DUO_MATCH:
					duo.add(new Match(request));
					break;
			}

			ServerView.atualizarLog(StringUtil.UPDATE_LOG);
			match = new Match(request);
		}
	}
	
}
