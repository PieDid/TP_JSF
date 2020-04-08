package com.intiformation.gestioncomptes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestioncomptes.modele.Client;
import com.intiformation.gestioncomptes.modele.Compte;


public class ClientDAOImpl implements IClientDAO {

	/**
	 * affichage de tous les clients
	 */
	@Override
	public List<Client> getAllClients() {

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			// requete pr�par�e
			ps = this.connection.prepareStatement("SELECT * FROM clients");

			// execution + resultat
			rs = ps.executeQuery();

			List<Client> listeClients = new ArrayList<>();
			Client client = null;

			while (rs.next()) {

				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));

				client.toString();
				
				listeClients.add(client);

			} // end while

			return listeClients;

		} catch (SQLException e) {
			System.out.println(
					"DAO :: ClientDAOIpml :: ... Erreur lors de l'�x�cution de la requete getAllClients() de la DAO ...");
			e.printStackTrace();
		}

		return null;
	} // end getAllClients

	/**
	 * ajout d'un client
	 * @return 
	 */
	@Override
	public boolean addClient(Client client) {
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

			int addResult = ps.executeUpdate();
			
			return (addResult == 1) ? true : false;

		} catch (SQLException e) {
			System.out.println(
					"DAO :: ClientDAOIpml :: ... Erreur lors de l'ex�cution de la requete ajouterClient() de la DAO ...");
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
		return false;

	}

	/**
	 * modification d'un client
	 * @return 
	 */
	@Override
	public boolean updateClient(Client client) {
		PreparedStatement ps = null;

		try {

			// 1. d�finition de la requete SQL

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

			int updateResult = ps.executeUpdate();

			return (updateResult == 1) ? true : false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println(
						"DAO :: ClientDAOIpml :: ... Erreur lors de l'ex�cution de la requete modifierClient() de la DAO ...");
				e.printStackTrace();
			}
		}
		return false;

	}

	/**
	 * suppression d'un client
	 * @return 
	 */
	@Override
	public boolean deleteClientById(int pIdClient) {

		PreparedStatement ps = null;

		try {

			// 1. d�finition de la requete SQL

			ps = this.connection.prepareStatement("DELETE FROM clients WHERE id_client=?");

			ps.setInt(1, pIdClient);

			int deleteResult = ps.executeUpdate();

			return (deleteResult == 1) ? true : false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println(
						"DAO :: ClientDAOIpml :: ... Erreur lors de l'ex�cution de la requete supprimerClientById() de la DAO ...");
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * r�cup�ration d'un client par son I
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
						"DAO :: ClientDAOIpml :: ... Erreur lors de l'ex�cution de la requete getClientById() de la DAO ...");
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public List<Client> getClientsByConseiller(int pIdConseiller) {
	
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {

			ps = this.connection.prepareStatement("SELECT * FROM bdd_gestion_comptes.clients\r\n" + "WHERE conseiller_id = ?");
			ps.setInt(1, pIdConseiller);
			
			resultatRequete = ps.executeQuery();


			Client client = null;
			List<Client> listClientsByConseiller = new ArrayList<>();

			while (resultatRequete.next()) {

				int id_client = resultatRequete.getInt(1);
				String nom = resultatRequete.getString(2);
				String prenom = resultatRequete.getString(3);
				String adresse = resultatRequete.getString(4);
				String codePostal = resultatRequete.getString(5);
				String ville = resultatRequete.getString(6);
				String telephone = resultatRequete.getString(7);

				client = new Client(id_client, nom, prenom, adresse, codePostal, ville, telephone);

				// 4.4. Ajout de l'objet dans la liste des hotels
				listClientsByConseiller.add(client);

			} 
			
			return listClientsByConseiller;

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
				e.printStackTrace();
			}
		}

		return null;
	}

}
