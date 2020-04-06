package com.intiformation.gestioncomptes.dao;

import java.sql.Connection;

import com.intiformation.gestioncomptes.modele.Conseiller;
import com.intiformation.gestioncomptes.tool.DBConnection;

public interface IConseillerDAO {

	// récup de la connexion
	public Connection connection = DBConnection.getInstance();

	// méthodes de la DAO

	/**
	 * permet de vérifier si un conseiller existe dans la BDD avec mail et Mdp <br/>
	 * 
	 * @param pMail
	 * @param pMdp
	 * @return
	 */
	public boolean isConseillerExists(String pMail, String pMdp);

	/**
	 * Renvoie un conseiller à partir de son Id
	 * @param pIdConseiller
	 * @return
	 */
	public Conseiller getConseillerbyId(int pIdConseiller);
	
	public Conseiller getConseillerbyMail(String pMail);
	
	
}


