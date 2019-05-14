package br.reaggeou.ted.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionWithBank {
	
	private Connection connection = null;
	private static ConnectionWithBank connectionWB;
	private static final String CLASS_NAME = "";
	private static final String DRIVER = "";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	private static final String MESSAGE_ERROR = "Erro ao tentar se conectar ao Banco: ";
	
	static {
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private ConnectionWithBank () {
		try {
			connection = DriverManager.getConnection(DRIVER, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(MESSAGE_ERROR + e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static ConnectionWithBank getConnectionWB() {
		if (connectionWB == null) {
			connectionWB = new ConnectionWithBank();
		}
		return connectionWB;
	}
	
	public void closeConnection () {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
