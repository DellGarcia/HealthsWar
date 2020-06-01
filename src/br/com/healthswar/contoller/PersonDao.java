package br.com.healthswar.contoller;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.healthswar.player.model.Person;

public class PersonDao {

	public static void insert(Person person) {
		String sql = "INSERT INTO tbPlayer " +
						"(nome, email, senha, vitorias, derrotas) " +
							"VALUES(?, ?, ?, ?, ?)";
		
		try {
			Connection connection = ConnectionFactory.getConnetion();
			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, person.getName());
			stmt.setString(2, person.getEmail());
			stmt.setString(3, person.getPassword());
			stmt.setInt(4, person.getVitorias());
			stmt.setInt(5, person.getDerrotas());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Não foi possivel inserir");
			e.printStackTrace();
		}
		
	}
	
	public static void update(Person person) {
		String sql = "UPDATE tbPlayer " +
						"SET nome = ?, email = ?, senha = ?, vitorias = ?, derrotas = ? " +
							"WHERE id = ?";
		
		try {
			Connection connection = ConnectionFactory.getConnetion();
			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, person.getName());
			stmt.setString(2, person.getEmail());
			stmt.setString(3, person.getPassword());
			stmt.setInt(4, person.getVitorias());
			stmt.setInt(5, person.getDerrotas());
			stmt.setInt(6, person.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Não foi possivel inserir");
			e.printStackTrace();
		}
		
	}
	
}
