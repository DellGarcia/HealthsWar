package br.com.healthswar.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.contoller.PersonDao;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.player.model.Person;
import br.com.healthswar.view.TelaControle;

public class Server extends ServerSocket {
	
	private static Server server;
	
	public static boolean active;
	
	private ArrayList<Partida> solo;
	private ArrayList<Partida> duo;
	
	private Server(int port) throws IOException {
		super(port);
		solo = new ArrayList<Partida>();
		duo = new ArrayList<Partida>();
		solo.add(new Partida(Request.PLAY_A_SOLO_MATCH));
		duo.add(new Partida(Request.PLAY_A_DUO_MATCH));
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
		TelaControle.atualizarLog("Aguardando conexao");
		
		Player player = new Player(accept());
		
		TelaControle.atualizarLog("Conexão estabelecida");
		
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
	
	private void verificarPartida(Partida match, Request request) {
		if(match.getCompleto()) {
			match.start();
			switch(request) {
				case PLAY_A_SOLO_MATCH:
					solo.add(new Partida(request));
					break;
					
				case PLAY_A_DUO_MATCH:
					duo.add(new Partida(request));
					break;
				
				default:
					break;
				
			}
			TelaControle.atualizarLog("Partida completa e inicida");
			match = new Partida(request);
		}
	}
	
}
