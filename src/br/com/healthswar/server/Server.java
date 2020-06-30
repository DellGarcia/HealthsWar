package br.com.healthswar.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.contoller.PersonDao;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.player.model.Person;
import br.com.healthswar.player.view.ControlView;

public class Server extends ServerSocket {
	
	private static Server server;
	
	public static boolean active;
	
	private ArrayList<Match> solo;
	private ArrayList<Match> duo;
	
	private Server(int port) throws IOException {
		super(port);
		solo = new ArrayList<Match>();
		duo = new ArrayList<Match>();
		solo.add(new Match(Request.PLAY_A_SOLO_MATCH));
		duo.add(new Match(Request.PLAY_A_DUO_MATCH));
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
	
	public void awaitConnetion() throws IOException, ClassNotFoundException {
		ControlView.atualizarLog("Aguardando conexao");
		
		Player player = new Player(accept());
		
		ControlView.atualizarLog("Conexão estabelecida");
		
		Request request = (Request) player.read();
		
		switch(request) {
			case PLAY_A_SOLO_MATCH:
				solo.get(solo.size()-1).addPlayer(player);
				verificarPartida(solo.get(solo.size()-1), Request.PLAY_A_SOLO_MATCH);
				player.write(Response.MATCH_FOUND);
				break;
				
			case PLAY_A_DUO_MATCH:
				duo.get(duo.size()-1).addPlayer(player);
				verificarPartida(duo.get(duo.size()-1), Request.PLAY_A_DUO_MATCH);
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
				
			default:
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
				
				default:
					break;
			}
			ControlView.atualizarLog("Partida completa e inicida");
			match = new Match(request);
		}
	}
	
}
