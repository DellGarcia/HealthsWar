package br.com.healthswar.contoller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.healthswar.player.model.Person;

public class PersonDao {
	
	private Connection connection;
	
	public void insert(Person person) {
		String sql = "INSERT INTO tbPlayer " +
						"(nome, email, senha, vitorias, derrotas) " +
							"VALUES(?, ?, ?, ?, ?)";
		
		try {
			connection = ConnectionFactory.getConnetion();
			
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
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void update(Person person) {
		String sql = "UPDATE tbPlayer " +
						"SET nome = ?, email = ?, senha = ?, vitorias = ?, derrotas = ? " +
							"WHERE id = ?";
		
		try {
			connection = ConnectionFactory.getConnetion();
			
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
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void delete(Person person) {
		try {
			connection = ConnectionFactory.getConnetion();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("DELETE " + "FROM tbPlayer WHERE id=?");
			
			stmt.setLong(1, person.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	public List<Person> selectAll() {
		String sql = "SELECT * FROM tbPlayer";
		
		connection = ConnectionFactory.getConnetion();
		
		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			List<Person> persons = new ArrayList<>();
			
			while (rs.next()) {
				Person person = new Person();
				person.setName(rs.getString("nome"));
				person.setEmail(rs.getString("email"));
				person.setPassword(rs.getString("senha"));
				person.setVitorias(rs.getInt("vitorias"));
				person.setDerrotas(rs.getInt("derrotas"));
				persons.add(person);
			}

			stmt.execute();
			stmt.close();
			
			return persons;
		} catch (SQLException e) {
			System.out.println("Não foi possivel consultar");
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public Person selectPerson(int id) {
		try {
			connection = ConnectionFactory.getConnetion();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("DELETE " + "FROM tbPlayer WHERE id=?");
			
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
			
			ResultSet rs = stmt.executeQuery();
			
			Person person = new Person();
			person.setName(rs.getString("nome"));
			person.setEmail(rs.getString("email"));
			person.setPassword(rs.getString("senha"));
			person.setVitorias(rs.getInt("vitorias"));
			person.setDerrotas(rs.getInt("derrotas"));
			
			connection.close();
			return person;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
}
