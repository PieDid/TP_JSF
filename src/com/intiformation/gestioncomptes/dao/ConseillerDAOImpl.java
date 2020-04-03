package com.intiformation.gestioncomptes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.intiformation.gestioncomptes.model.Client;
import com.intiformation.gestioncomptes.model.Conseiller;

/**
 * implémentation concrète de la couche DAO du conseiller <br/>
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

			// 2. requete préparée
			ps = this.connection.prepareStatement(isExistsReq);

			// 2.1. Passage de param à la requete préparée
			ps.setString(1, pMail);
			ps.setString(2, pMdp);

			// 3. exécution + récup des résultats
			rs = ps.executeQuery();

			// 4. lecture du résultat
			rs.next();
			int verifIsExists = rs.getInt(1);

			// 5. renvoi du résultat
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

			// préparation de la requete
			ps = this.connection.prepareStatement("SELECT * FROM conseillers WHERE id_conseiller=?");

			ps.setInt(1, pIdConseiller);

			resultatRequete = ps.executeQuery();

			Conseiller conseiller = null;

			// résultat de la requete
			resultatRequete.next();

			int id_conseiller = resultatRequete.getInt(1);
			String nom = resultatRequete.getString(2);
			String prenom = resultatRequete.getString(3);
			String mail = resultatRequete.getString(4);
			String mdp = resultatRequete.getString(5);

			// on crée un nouveau conseiller avec les données receuillies
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
						"DAO :: ConseillerDAOIpml :: ... Erreur lors de l'exécution de la requete getConseillerById() de la DAO ...");
				e.printStackTrace();
			}
		}


		return null;
	}

}
