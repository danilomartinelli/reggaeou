package br.reaggeou.ted.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	
	private Connection connection = null;
	private static ConnectionBD connectionDB;
	
	private static final String CLASS_NAME = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/reggaeou";
	private static final String USERNAME = "daniloleonemartinelli";
	private static final String PASSWORD = "";
	
	private ConnectionBD () {
		try {
			Class.forName(CLASS_NAME);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch (ClassNotFoundException e) {
			System.out.println("Erro em ClassNotFoundException: " + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println("Erro ao tentar se conectar ao Banco: " + e.getMessage());
		}
		catch(NoClassDefFoundError e){
			System.out.println("Erro em NoClassDefFoundError: " + e.getMessage());
        }
		
	}

	public Connection getConnection() {
		return connection;
	}

	public static ConnectionBD getConnectionDB() {
		if (connectionDB == null) {
			connectionDB = new ConnectionBD();
		}
		return connectionDB;
	}
	
	public void closeConnection () {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
