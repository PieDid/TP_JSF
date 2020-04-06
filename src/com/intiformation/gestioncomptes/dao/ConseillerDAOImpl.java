package com.intiformation.gestioncomptes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.intiformation.gestioncomptes.modele.Client;
import com.intiformation.gestioncomptes.modele.Conseiller;

/**
 * impl�mentation concr�te de la couche DAO du conseiller <br/>
 * 
 * @author IN-DF-028
 *
 */
public class ConseillerDAOImpl implements IConseillerDAO{

	@Override
	public boolean isConseillerExists(String pMail, String pMdp) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1. requete SQL
			String isExistsReq = "SELECT COUNT(id_conseiller) FROM conseillers WHERE mail=? AND mot_de_passe=?";

			// 2. requete pr�par�e
			ps = this.connection.prepareStatement(isExistsReq);

			// 2.1. Passage de param � la requete pr�par�e
			ps.setString(1, pMail);
			ps.setString(2, pMdp);

			// 3. ex�cution + r�cup des r�sultats
			rs = ps.executeQuery();

			// 4. lecture du r�sultat
			rs.next();
			int verifIsExists = rs.getInt(1);

			// 5. renvoi du r�sultat
			return (verifIsExists == 1);

		} catch (SQLException e) {
			System.out
					.println("DAO :: ConseillerDAOImpl :: ... Erreur d'execution de la requete isConseillerExists ...");
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Conseiller getConseillerbyId(int pIdConseiller) {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {

			// pr�paration de la requete
			ps = this.connection.prepareStatement("SELECT * FROM conseillers WHERE id_conseiller=?");

			ps.setInt(1, pIdConseiller);

			resultatRequete = ps.executeQuery();

			Conseiller conseiller = null;

			// r�sultat de la requete
			resultatRequete.next();

			int id_conseiller = resultatRequete.getInt(1);
			String nom = resultatRequete.getString(2);
			String prenom = resultatRequete.getString(3);
			String mail = resultatRequete.getString(4);
			String mdp = resultatRequete.getString(5);

			// on cr�e un nouveau conseiller avec les donn�es receuillies
			conseiller = new Conseiller(id_conseiller, nom, prenom, mail, mdp);

			// que l'on retourne
			return conseiller;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			//Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
				if (resultatRequete != null)
					resultatRequete.close();
			} catch (SQLException e) {
				System.out.println(
						"DAO :: ConseillerDAOIpml :: ... Erreur lors de l'ex�cution de la requete getConseillerById() de la DAO ...");
				e.printStackTrace();
			}
		}


		return null;
	}

	
	public Conseiller getConseillerbyMail(String pMail) {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {

			// pr�paration de la requete
			ps = this.connection.prepareStatement("SELECT * FROM conseillers WHERE mail=?");

			ps.setString(1, pMail);

			resultatRequete = ps.executeQuery();

			Conseiller conseiller = null;

			// r�sultat de la requete
			resultatRequete.next();

			int id_conseiller = resultatRequete.getInt(1);
			String nom = resultatRequete.getString(2);
			String prenom = resultatRequete.getString(3);
			String mail = resultatRequete.getString(4);
			String mdp = resultatRequete.getString(5);

			// on cr�e un nouveau conseiller avec les donn�es receuillies
			conseiller = new Conseiller(id_conseiller, nom, prenom, mail, mdp);

			// que l'on retourne
			return conseiller;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			//Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
				if (resultatRequete != null)
					resultatRequete.close();
			} catch (SQLException e) {
				System.out.println(
						"DAO :: ConseillerDAOIpml :: ... Erreur lors de l'ex�cution de la requete getConseillerByMail() de la DAO ...");
				e.printStackTrace();
			}
		}


		return null;
	}

}
