package br.com.healthswar.gameplay;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import br.com.healthswar.player.model.Person;

public class Player extends Person {
	
	private static final long serialVersionUID = -6102667841735506917L;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Field field;
	
	public Player(Socket socket) throws IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in 	= new ObjectInputStream(socket.getInputStream());
	}
	
	public Player(Field field) {
		this.field = field;
	}
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void write(Object object) {
		try {
			out.writeObject(object);
		} catch (IOException e) {
			System.out.println("Erro ao tentar escrever o objeto [ " + object.getClass() + " ]");
			e.printStackTrace();
		}
	}
	
	public Object read() {
		try {
			return in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Erro ao tentar ler o objeto");
			e.printStackTrace();
		}

		return null;
	}

}
