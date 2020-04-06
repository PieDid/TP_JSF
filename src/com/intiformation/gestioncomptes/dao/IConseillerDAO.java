package com.intiformation.gestioncomptes.dao;

import java.sql.Connection;

import com.intiformation.gestioncomptes.modele.Conseiller;
import com.intiformation.gestioncomptes.tool.DBConnection;

public interface IConseillerDAO {

	// r�cup de la connexion
	public Connection connection = DBConnection.getInstance();

	// m�thodes de la DAO

	/**
	 * permet de v�rifier si un conseiller existe dans la BDD avec mail et Mdp <br/>
	 * 
	 * @param pMail
	 * @param pMdp
	 * @return
	 */
	public boolean isConseillerExists(String pMail, String pMdp);

	/**
	 * Renvoie un conseiller � partir de son Id
	 * @param pIdConseiller
	 * @return
	 */
	public Conseiller getConseillerbyId(int pIdConseiller);
	
	public Conseiller getConseillerbyMail(String pMail);
	
	
}


