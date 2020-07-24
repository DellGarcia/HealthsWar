package br.com.healthswar.contoller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;
	private static final String bdName	= "jdbc:mysql://localhost/bdHealthsWar";
	private static final String user		= "root";
	private static final String password	= "123456";

	private static void openConnection() {
		try {
			if(connection == null) {
				connection = DriverManager.getConnection(
	                    bdName, user, password);
			}

		} catch(SQLException e) {
			System.out.println("Não foi possível criar a conexão com o banco de dados");
		}
	}
	
	public static Connection getConnetion() {
		if(connection == null)
			openConnection();

		return connection;
	}
}
