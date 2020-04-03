package com.intiformation.gestioncomptes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestioncomptes.modele.Client;


public class ClientDAOImpl implements IClientDAO {

	/**
	 * affichage de tous les clients
	 */
	@Override
	public List<Client> getAllClients() {

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			// requete préparée
			ps = this.connection.prepareStatement("SELECT * FROM clients");

			// execution + resultat
			rs = ps.executeQuery();

			List<Client> listeClients = new ArrayList<>();
			Client client = null;

			while (rs.next()) {

				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));

				listeClients.add(client);

			} // end while

			return listeClients;

		} catch (SQLException e) {
			System.out.println(
					"DAO :: ClientDAOIpml :: ... Erreur lors de l'éxécution de la requete getAllClients() de la DAO ...");
			e.printStackTrace();
		}

		return null;
	} // end getAllClients

	/**
	 * ajout d'un client
	 */
	@Override
	public void ajouterClient(Client client) {
		PreparedStatement ps = null;

		try {

			ps = this.connection.prepareStatement(
					"INSERT INTO clients (nom, prenom, adresse, codePostal, ville, telephone, conseiller_id) VALUES (?,?,?,?,?,?,?)");

			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getAdresse());
			ps.setString(4, client.getCodePostal());
			ps.setString(5, client.getVille());
			ps.setString(6, client.getTelephone());
			ps.setInt(7, client.getIdConseiller());

			int AddResult = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(
					"DAO :: ClientDAOIpml :: ... Erreur lors de l'exécution de la requete ajouterClient() de la DAO ...");
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * modification d'un client
	 */
	@Override
	public void modifierClient(Client client) {
		PreparedStatement ps = null;

		try {

			// 1. définition de la requete SQL

			ps = this.connection.prepareStatement(
					"UPDATE clients SET nom=?, prenom=?, adresse=?, codePostal=?, ville=?, telephone=?, conseiller_id=? WHERE id_client=?");

			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getAdresse());
			ps.setString(4, client.getCodePostal());
			ps.setString(5, client.getVille());
			ps.setString(6, client.getTelephone());
			ps.setInt(7, client.getIdConseiller());
			ps.setInt(8, client.getIdClient());

			int UpdateResult = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println(
						"DAO :: ClientDAOIpml :: ... Erreur lors de l'exécution de la requete modifierClient() de la DAO ...");
				e.printStackTrace();
			}
		}

	}

	/**
	 * suppression d'un client
	 */
	@Override
	public void supprimerClientById(int pIdClient) {

		PreparedStatement ps = null;

		try {

			// 1. définition de la requete SQL

			ps = this.connection.prepareStatement("DELETE FROM clients WHERE id_client=?");

			ps.setInt(1, pIdClient);

			int DeleteResult = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println(
						"DAO :: ClientDAOIpml :: ... Erreur lors de l'exécution de la requete supprimerClientById() de la DAO ...");
				e.printStackTrace();
			}
		}
	}

	/**
	 * récupération d'un client par son I
	 */
	@Override
	public Client getClientById(int pIdClient) {

		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {

			ps = this.connection.prepareStatement("SELECT * FROM clients WHERE id_client=?");

			ps.setInt(1, pIdClient);

			resultatRequete = ps.executeQuery();

			Client client = null;

			resultatRequete.next();

			int id_client = resultatRequete.getInt(1);
			String nom = resultatRequete.getString(2);
			String prenom = resultatRequete.getString(3);
			String adresse = resultatRequete.getString(4);
			String codePostal = resultatRequete.getString(5);
			String ville = resultatRequete.getString(6);
			String telephone = resultatRequete.getString(7);
			int id_conseiller = resultatRequete.getInt(8);

			client = new Client(id_client, nom, prenom, adresse, codePostal, ville, telephone, id_conseiller);

			return client;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
				if (resultatRequete != null)
					resultatRequete.close();
			} catch (SQLException e) {
				System.out.println(
						"DAO :: ClientDAOIpml :: ... Erreur lors de l'exécution de la requete getClientById() de la DAO ...");
				e.printStackTrace();
			}
		}

		return null;
	}

}
