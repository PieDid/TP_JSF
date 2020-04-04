package com.intiformation.gestioncomptes.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.gestioncomptes.modele.Client;
import com.intiformation.gestioncomptes.tool.DBConnection;

public interface IClientDAO {
	
	// récup de la connexion
	public Connection connection = DBConnection.getInstance();

	// méthodes de la DAO
	
	/**
	 * Récupération de la liste de tous les clients
	 * @return
	 */
	public List<Client> getAllClients();
	
	/**
	 * Ajouter un client
	 * @param client
	 * @return 
	 */
	public boolean addClient(Client client);

	/**
	 * Modifier un client
	 * @param client
	 * @return 
	 */
	public boolean updateClient(Client client);

	/**
	 * Supprime un client a partir de son ID
	 * @param pIdClient
	 * @return 
	 */
	public boolean deleteClientById(int pIdClient);

	/**
	 * Récupérer un client a partir de son ID
	 * @param pIdClient
	 * @return
	 */
	public Client getClientById(int pIdClient);

	
}
