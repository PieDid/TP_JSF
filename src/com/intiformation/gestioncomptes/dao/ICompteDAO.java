package com.intiformation.gestioncomptes.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.gestioncomptes.model.Client;
import com.intiformation.gestioncomptes.model.Compte;
import com.intiformation.gestioncomptes.tool.DBConnection;

public interface ICompteDAO {

	// récup de la connexion
	public Connection connection = DBConnection.getInstance();

	// méthodes de la DAO
	
	/**
	 * Récupère la liste des comptes
	 * @return
	 */
	public List<Compte> getAllComptes();
	
	/**
	 * Ajoute un compte
	 * @param compte
	 */
	public void ajouterCompte(Compte compte);

	/**
	 * Modifie un compte
	 * @param compte
	 */
	public void modifierCompte(Compte compte);

	/**
	 * Supprime un compte via son ID
	 * @param pIdCompte
	 */
	public void supprimerCompteById(int pIdCompte);

	/**
	 * Récupère un compte à partir de son ID
	 * @param pIdCompte
	 * @return
	 */
	public Compte getCompteById(int pIdCompte);

	/**
	 * Récupère la liste des compte d'un client via l'ID de ce client
	 * @param pIdClient
	 * @return
	 */
	public List<Compte> getCompteByClientID(int pIdClient);

	
	/**
	 * Affecte un compe à un client via leurs IDs
	 * @param pIdCcompte
	 * @param pIdClient
	 */
	public void affecterClient(int pIdCcompte, int pIdClient);

	/**
	 * Déposer de l'argent
	 * @param compte
	 * @param montant
	 */
	public void deposer(Compte compte, double montant);

	/**
	 * Retirer de l'argent
	 * @param compte
	 * @param montant
	 */
	public void retirer(Compte compte, double montant);

	
	/**
	 * Transferer de l'argent
	 * @param compte
	 * @param montant
	 * @param compteReceveur
	 */
	public void transferer(Compte compte, double montant, Compte compteReceveur);

}
