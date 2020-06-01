package br.com.healthswar.contoller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;
	
	private static final String bdName	= "jdbc:mysql://localhost/bdHealthsWar";
	private static final String user		= "root";
	private static final String password	= "123456";

	public static void openConnection() {
		try {
			if(connection == null) {
				connection = DriverManager.getConnection(
	                    bdName, user, password);
			}
		} catch(SQLException e) {
			System.out.println("Não foi possivel criar a conexao com o banco de dados");
		}
	}
	
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnetion() {
		return connection;
	}
}
