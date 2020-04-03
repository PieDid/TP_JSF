package com.intiformation.gestioncomptes.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  
	private static final String URL_BDD = "jdbc:mysql://localhost:3306/bdd_gestion_comptes";
	private static final String USER_BDD = "root";
	private static final String PASSWORD_BDD = "root";

	// définition de la connection
	private static Connection connection;

	// ctor privé
	private DBConnection() {
	}

	// méthode pour récupérer l'instance connection
	public static Connection getInstance() {

		if (connection == null) {
			
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(URL_BDD, USER_BDD, PASSWORD_BDD);
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println("DBConnection :: ERROR :: Le pilote est introuvable.");
					e.printStackTrace();
				}
			
		} // end if

		return connection;
	} // end getInstance()

}
